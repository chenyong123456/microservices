package cn.knowimage.service.impl;

import cn.knowimage.JsonPojo.ReturnJson.ReturnJsonPathway_Info;
import cn.knowimage.mapper.PathwayInfoMapper;
import cn.knowimage.mapper.RecentWorkMapper;
import cn.knowimage.mapper.UserMapper;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.ReceivePathway;
import cn.knowimage.pojo.RecentWork;
import cn.knowimage.pojo.User;
import cn.knowimage.service.PathwayInfoService;
import cn.knowimage.service.redis.RedisService;
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
    private RedisService redisService;
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
     *          2.判断前端是否传入fileStatic(传入了值为9999    否则值为0)
     *              2.1   Y，新增操作，先查询是否又重复版本
     *              2.2   N，修改操作
     */
    @Override
    public int insertPathwayInfo(ReceivePathway receivePathway,PathwayInfo pathwayInfo) {
        //判断首先判断该字段是否已存在
        System.out.println("新增或修改是否传入了fileStatic:" + receivePathway.getFileStatic());
        //更新=0
        if (receivePathway.getFileStatic() == 9999) {
            System.out.println("输出pathwayIndex===================================="+pathwayInfo.getPathway_index());
            //判断index是否重复
            String exist = pathwayInfoMapper.findNameByIndex(pathwayInfo.getPathway_index());
            //exis有名字就说明pathway_index存在
            if (exist!=null){
                System.out.println("BackedAdmin该版本号已存在");
                return 0;
                //0, 为数据的更新
            }else {
                System.out.println("开始新增++++++++++++++++++++++");
                //9999开始新增
                int statePathwayInfo = pathwayInfoMapper.insertPathwayInfo(pathwayInfo);

                if ((statePathwayInfo == 1)) {
                    System.out.println("新增成功");
                    return 1;
                } else {
                    System.out.println("新增失败");
                    return 0;
                }
            }
            //传入的数据进行修改操作
        }else {  // 0代表更新
            //在这里加入redis,删掉redis中对应要修改的数据格式
            //先获取redis中key = pathway_name + pathway_index
            String redisKey = pathwayInfo.getPathway_name()+pathwayInfo.getPathway_index();
            if(redisService.exists(redisKey)){
                redisService.remove(redisKey);
            }
            System.out.println("传入了index开始修改++++++++++++++++++++++");
            int statePathwayInfo = pathwayInfoMapper.updatePathwayInfo(pathwayInfo);
            int flag = 0;
            if("1".equals(receivePathway.getCommit())) {
                flag = pathwayInfoMapper.updateAuditState(0, pathwayInfo.getPathway_index());
            }
            System.out.println("修改状态的数量:------>" + flag);
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
        System.out.println("传进来的pathway_index的值为******************:" + pathway_index);
        JSONObject returnPathwayInfoAndExamForm  = new JSONObject();
        //通过httpclient请求exam_form的数据，将String转换为jsonObject，一起传给前端
        Map<String,String> map = new HashMap<>();
        map.put("cp_id",pathway_index);
        String s;
        //梁梁的接口数据
        s= HttpClientUtil.doGet("https://cy.knowimage.cn:7022/examform/getExamFormDataById", map);
        System.out.println("exam_form的数据s==========="+s);
        JSONObject exam_form = new JSONObject();
        try {
            exam_form = JSONObject.fromObject(s);
        }catch (Exception e){
            System.out.println("可能exam_form服务器未启动或报错，直接返回了");
            return exam_form;
        }
        //获取pathinfo表中的所有数据
        PathwayInfo pathwayInfo = pathwayInfoMapper.findPathwayInfoByIndex(pathway_index);



        //根据pathinfo表中的submitter_id提交人id获取提交人姓名
        String username = userMapper.findUserNameById(pathwayInfo.getSubmitter_id());
        JSONObject last = ReturnJsonPathway_Info.make(pathwayInfo,username);

        returnPathwayInfoAndExamForm.put("pathway_info",last);
        returnPathwayInfoAndExamForm.put("exam_form",exam_form);

        System.out.println("最终两张表合并之后的数据格式--->:" + returnPathwayInfoAndExamForm.toString());
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
        System.out.println("模糊从数据库中查询出来的数据--->记住是模糊查询*******:" + pathwayInfos);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < pathwayInfos.size(); i++) {
            String publishYear = pathwayInfos.get(i).getPathway_index().substring(2, 6).replaceAll("\t", "");
            JSONObject value = new JSONObject();
            //获取pathway_name
            String pathway_name = pathwayInfos.get(i).getPathway_name();
            //获取Pathway_id
            String ID = pathwayInfos.get(i).getPathway_index();
            //获取Audit_state
            Integer state = pathwayInfos.get(i).getAudit_state(); //1 已审核, 0 未审核
            Integer commit = Integer.parseInt(pathwayInfos.get(i).getCommit_state()); //0为未提交，1为已提交
            if (state==0 && commit==0) {
                state = 0;
            } else if (state==0 && commit==1) {
                state=1;
            } else if (state==1 && commit==1) {
                state=2;
            } else if(state==2 && commit==0) {
                state=3;
            }
            if(pathwayInfos.get(i).getDepartment_code() == 1023){
                value.put("label", pathway_name +"[县级医院]"+ "(" + publishYear + "年)");
            }else {
                value.put("label", pathway_name + "(" + publishYear + "年)");
            }
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
        //存入年份数组 012016016000
        String year = "";
        for (int i =0;i<pathwayInfos.size();i++){
            for (int j=0;j<users.size();j++){
                if (users.get(j).getId().equals(pathwayInfos.get(i).getSubmitter_id())){
                    pathwayInfos.get(i).setSubmitter_id(users.get(j).getUsername());
                }
                if (users.get(j).getId().equals(pathwayInfos.get(i).getChecker_id())){
                    pathwayInfos.get(i).setChecker_id(users.get(j).getUsername());
                }
            }

            year = pathwayInfos.get(i).getPathway_index().substring(2,6);

            pathwayInfos.get(i).setPathway_name( pathwayInfos.get(i).getPathway_name() + "(" + year + "年)");
            /*System.out.println("*************************************************************");
            System.out.println("拼接的pathway_name------>:" + pathwayInfos.get(i).getPathway_name());
            System.out.println("**************************************************************");*/
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",allNum);
        jsonObject.put("list",pathwayInfos);
        return jsonObject;
    }

    /**
     * 审核操作
     * @param pathwayInfo 被审核的字段--->后台的审核页面哦.
     * @return 成功返回1 失败返回null
     */
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
    //删除ElRecentWork表中index的数据
    @Override
    public int deleteElRecentWork(String pathwayIndex) {
        return pathwayInfoMapper.deleteElRecentWork(pathwayIndex);
    }


    @Override
    public List<String> findMyWork(String editor_id) {
        return pathwayInfoMapper.selectPathwayNameByEditorId(editor_id);
    }

    @Override
    public JSONObject selectPathwayNameByUserName(String query, String username) {
        List<PathwayInfo> pathwayInfos = pathwayInfoMapper.selectPathwayNameByUserName(query, username);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("num",pathwayInfos.size()>0?1:0);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < pathwayInfos.size(); i++) {
            String publishYear = pathwayInfos.get(i).getPathway_index().substring(2, 6).replaceAll("\t", "");
            JSONObject value = new JSONObject();
            //获取pathway_name
            String pathway_name = pathwayInfos.get(i).getPathway_name();
            //获取Pathway_id
            String ID = pathwayInfos.get(i).getPathway_index();
            //获取Audit_state
            Integer state = pathwayInfos.get(i).getAudit_state();
            Integer commit = Integer.parseInt(pathwayInfos.get(i).getCommit_state());
            if (state==0 && commit==0) {
                state = 0;
            } else if (state==0 && commit==1) {
                state=1;
            } else if (state==1 && commit==1) {
                state=2;
            } else if(state==2 && commit==0) {
                state=3;
            }
            if(pathwayInfos.get(i).getDepartment_code() == 1023){
                value.put("label", pathway_name +"[县级医院]"+ "(" + publishYear + "年)");
            }else {
                value.put("label", pathway_name + "(" + publishYear + "年)");
            }
            value.put("value", ID);
            value.put("static", state);
            jsonArray.add(value);
        }
        jsonObject.put("pathwayInfo",jsonArray);
        return jsonObject;
    }

    public List finderror(){
        List<PathwayInfo> pathwayInfos = pathwayInfoMapper.finderror();
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i<pathwayInfos.size();i++) {
            try {
                ReturnJsonPathway_Info.make(pathwayInfos.get(i),"123");
            }catch (Exception e){
                System.out.println("捕获异常，异常为"+pathwayInfos.get(i).getPathway_index());
                list.add(pathwayInfos.get(i).getPathway_index());
            }
        }
        return list;
    }

    @Override
    public int insertTest() {
        PathwayInfo pathwayInfo = pathwayInfoMapper.findPathwayInfoByIndex("012016998801");
        for (int i=28;i<40;i++){
            pathwayInfo.setPathway_name("临时数据"+i);
            int result = pathwayInfoMapper.insertPathwayInfo(pathwayInfo);
            System.out.println("插入临时数据"+i);
        }
        System.out.println(pathwayInfo);
        return 0;
    }

    @Override
    public PathwayInfo selectOneByIndex(String pathway_index) {
        return pathwayInfoMapper.findPathwayInfoByIndex(pathway_index);
    }
}
