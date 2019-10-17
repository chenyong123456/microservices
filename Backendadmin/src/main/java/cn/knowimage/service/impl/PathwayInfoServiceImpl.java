package cn.knowimage.service.impl;

import cn.knowimage.JsonPojo.MakeJson.MakeJsonPathway;
import cn.knowimage.JsonPojo.ReturnJson.ReturnJsonPathway_Info;
import cn.knowimage.mapper.PathwayInfoMapper;
import cn.knowimage.mapper.RecentWorkMapper;
import cn.knowimage.mapper.UserMapper;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.ReceivePathway;
import cn.knowimage.pojo.RecentWork;
import cn.knowimage.pojo.User;
import cn.knowimage.service.PathwayInfoService;
import cn.knowimage.util.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PathwayInfoServiceImpl implements PathwayInfoService  {
    @Autowired
    PathwayInfoMapper pathwayInfoMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RecentWorkMapper recentWorkMapper;

    /**
     *
     * @param receivePathway 前端传入的Json字符串
     * @return 成功或者失败
     * 实现过程:
     *          1.首先将前端传入的Json字符串转换为数据库的Json格式
     *          2.判断前端是否传入SelectId(既PathwayIndex)
     *              2.1   Y，则为先查找了字段再进行了提交操作，可以判断出此时此刻在修改操作
     *              2.2   N，则为直接进行提交操作，可以判断出，此时此刻在新增操作
     */
    @Override
    public int insertPathwayInfo(ReceivePathway receivePathway,PathwayInfo pathwayInfo) {
        //判断首先判断该字段是否已存在
        System.out.println("新增或修改是否传入了selectId:" + receivePathway.getSelectId());
        if (receivePathway.getSelectId() == null || "".equals(receivePathway.getSelectId())) {
            //判断index是否重复
            String exist = pathwayInfoMapper.findNameByIndex(pathwayInfo.getPathway_index());
            if (exist!=null){
                System.out.println("该版本号已存在");
                return 0;
            }else {
                System.out.println("开始新增++++++++++++++++++++++");
                int statePathwayInfo = pathwayInfoMapper.insertPathwayInfo(pathwayInfo);
                if ((statePathwayInfo == 1)) {
                    System.out.println("新增成功");
                    return 1;
                } else {
                    System.out.println("新增失败");
                    return 0;
                }
            }
        }else {
            System.out.println("传入了index开始修改++++++++++++++++++++++");
            //首先将PathwayIndex  Set入pathwayInfo中
            pathwayInfo.setPathway_index(receivePathway.getSelectId());
            int statePathwayInfo = pathwayInfoMapper.updatePathwayInfo(pathwayInfo);
            if ((statePathwayInfo == 1)) {
                System.out.println("修改成功");
                return 1;
            } else {
                System.out.println("修改失败");
                return 0;
            }

        }
    }

    /**
     *
     * @param pathway_index  查询的pathway_index
     * @return 返回pathway_info和exam_form的共同前端数据
     * 实现过程:
     *          1.首先调用exam_form的接口，获得exam的数据
     *          2.查找pathway_info的数据，拼凑前端返回json
     *          3.一起put入returnPathwayInfoAndExamForm对象中
     */
    @Override
    public JSONObject findPathwayInfoByIndex(String pathway_index) {
        JSONObject returnPathwayInfoAndExamForm  = new JSONObject();
        //通过httpclient请求exam_form的数据，将String转换为jsonObject，一起传给前端
        Map<String,String> map = new HashMap<>();
        map.put("cp_id",pathway_index);
        String s;
        s= HttpClientUtil.doGet("http://192.168.50.78:8003/getExamFormDataById", map);
        System.out.println("exam_form的数据s==========="+s);
        JSONObject exam_form = new JSONObject();
        try {
            exam_form = JSONObject.fromObject(s);
        }catch (Exception e){
            System.out.println("可能exam_form服务器未启动或报错，直接返回了");
            return exam_form;
        }
        PathwayInfo pathwayInfo = pathwayInfoMapper.findPathwayInfoByIndex(pathway_index);
        String username = userMapper.findUserNameById(pathwayInfo.getSubmitter_id());
        JSONObject last = ReturnJsonPathway_Info.make(pathwayInfo,username);
        returnPathwayInfoAndExamForm.put("pathway_info",last);
        returnPathwayInfoAndExamForm.put("exam_form",exam_form);
        return returnPathwayInfoAndExamForm;
    }

    /**
     *
     * @param query 路径发布页面，搜索框模糊查询
     * @return  返回含有输入字段的全部有关信息
     */
    @Override
    public JSONArray findLikePathwayName(String query) {
        List<PathwayInfo> pathwayInfos = pathwayInfoMapper.findLikePathwayName(query);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < pathwayInfos.size(); i++) {
            JSONObject value = new JSONObject();
            //获取pathway_name
            String pathway_name = pathwayInfos.get(i).getPathway_name();
            //获取Pathway_id
            String ID = pathwayInfos.get(i).getPathway_index();
            //获取Audit_state
            Integer state = pathwayInfos.get(i).getAudit_state();
            value.put("label", pathway_name);
            value.put("value", ID);
            value.put("static", state);
            jsonArray.add(value);
        }
        return jsonArray;
    }

    /**
     * 勇哥牌分页查询
     * @param pathway_name  查询name
     * @param audit_state1  查询状态1
     * @param audit_state2  查询状态2
     * @param audit_state3  查询状态3
     * @param page          分页page
     * @param rows          分页rows
     * @return              返回分页后的结果
     */
    @Override
    public JSONObject pathwayByPage(String pathway_name, Integer audit_state1, Integer audit_state2, Integer audit_state3, Integer page, Integer rows,Integer commit_state1,Integer commit_state2) {
        int allNum =pathwayInfoMapper.findAllStateAndLikeName(pathway_name,audit_state1,audit_state2,audit_state3,commit_state1,commit_state2);
        List<PathwayInfo> pathwayInfos =pathwayInfoMapper.selectPathwayByPage(page, rows,pathway_name,audit_state1,audit_state2,audit_state3,commit_state1,commit_state2);
        List<User> users = userMapper.findAllUserNameAndId();
        for (int i =0;i<pathwayInfos.size();i++){
            for (int j=0;j<users.size();j++){
                if (users.get(j).getId().equals(pathwayInfos.get(i).getSubmitter_id())){
                    pathwayInfos.get(i).setSubmitter_id(users.get(j).getUsername());
                }
                if (users.get(j).getId().equals(pathwayInfos.get(i).getChecker_id())){
                    pathwayInfos.get(i).setChecker_id(users.get(j).getUsername());
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",allNum);
        jsonObject.put("list",pathwayInfos);
        return jsonObject;
    }

    @Override
    public int updateAudit(PathwayInfo pathwayInfo) {
        pathwayInfo.setEditor_id(userMapper.findIdByUserName(pathwayInfo.getEditor_id()));
        //创建审核时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PathwayInfo pathwayInfo1 = pathwayInfoMapper.findPathwayInfoByIndex(pathwayInfo.getPathway_index());
        RecentWork recentWork = new RecentWork();
        recentWork.setMethod("审核了字段"+pathwayInfo1.getPathway_name());
        recentWork.setUser_id(pathwayInfo.getChecker_id());
        recentWork.setCp_index(pathwayInfo.getPathway_index());
        recentWork.setCreate_time(formatter.format(new Date()));
        recentWorkMapper.insertRecentWork(recentWork);
        pathwayInfo.setAudit_time(formatter.format(new Date()));
        int i = pathwayInfoMapper.updateAudit(pathwayInfo);
        return i;
    }
    //删除
    @Override
    public int deletePathwayInfo(String pathwayIndex) {
        return pathwayInfoMapper.deletePathwayInfoByIndex(pathwayIndex);
    }

    @Override
    public List<String> findMyWork(String editor_id) {
        return pathwayInfoMapper.selectPathwayNameByEditorId(editor_id);
    }

}
