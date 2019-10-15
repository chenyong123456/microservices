
package cn.knowimage.controller;

import cn.knowimage.pojo.Audit_Info;
import cn.knowimage.pojo.Audit_InfoAndPathway_Info;
import cn.knowimage.service.Audit_InfoService;
import cn.knowimage.service.redis.RedisService;
import cn.knowimage.util.ClincialResult;
import cn.knowimage.util.JsonUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能:对表audit_info进行查询
 * @author 啊勇
 */
@CrossOrigin
@Controller
public class Audit_InfoController {

	@Autowired
	public Audit_InfoService audit_InfoService;

	//注入redis的服务
	@Autowired
	public RedisService redisService;

	@Value("${BACKGROUND}")
	public String BACKGROUND;//小褚的缓存-->总文件夹

	//对相应的view进行测试
	@RequestMapping("/operationExam")
	public String operationExam() {
		return "index";
	}
	//对相应的view进行测试
	@RequestMapping("/getExamForm")
	public String getExamFormData() {
		return "show";
	}

	//根据pathway_id进行查询表中的一条数据,对梁宇轩提供接口-->根据id进行查询并且分页插件的使用
	@RequestMapping("/pathway_id")
    @ResponseBody
	public JSONObject selectAudit_InfoByPathway_Id(Model model, @RequestParam(value = "page",required = false,defaultValue="0") Integer page,
														 @RequestParam(value = "rows",required = false,defaultValue="10") Integer rows,
												   @RequestParam(value = "pathway_name",required = false,defaultValue="") String pathway_name,
												   @RequestParam(value = "audit_state",required = false,defaultValue="500") Integer audit_state){
		System.out.println(page);
		System.out.println(rows);
		System.out.println(pathway_name);
		System.out.println(audit_state);
		Integer audit_state1 = 0;
		Integer audit_state2 = 0;
		Integer audit_state3 = 0;
		//500代表前台没有传入数据，查询所有的数据
		if(audit_state==500){
			audit_state1 = 0;
			audit_state2 = 1;
			audit_state3 = 2;
		}else{
			audit_state1 = audit_state;
			audit_state2 = audit_state;
			audit_state3 = audit_state;
		}
		if(page==0){
			page = 1;
		}
		JSONObject audit_Info_s = new JSONObject();
		//查询数据库中的所有数据,获得数据的条数
		List<Audit_Info> all = audit_InfoService.findAll(pathway_name,audit_state1,audit_state2,audit_state3);
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Audit_InfoAndPathway_Info> list = null;
		//开始分页-->进行使用分页插件进行分页。
		//if(!redisService.exists(BACKGROUND+":"+"selectAudit_InfoByPathway_Id:"+"audit_Info")) {//redis中不存在数据,去mysql中查询
//		PageHelper.startPage(0,10);
			list = audit_InfoService.selectById(page, rows,pathway_name,audit_state1,audit_state2,audit_state3);
			redisService.set(BACKGROUND+":"+"selectAudit_InfoByPathway_Id:"+"audit_Info", JsonUtils.objectToJson(list));

		//}else {//redis中有就直接从redis中拿
		//	list = JsonUtils.jsonToList((String) redisService.get(BACKGROUND+":"+"selectAudit_InfoByPathway_Id:"+"audit_Info"),Audit_Info.class);
		//}
//		PageInfo<Audit_Info> pageInfo = new PageInfo<Audit_Info>(list);
		//pageInfo.setPageNum(table.getPage());
		for (Audit_InfoAndPathway_Info audit_Info:list){
			//String format = simpleDateFormat.format(audit_Info.getAudit_time());
			//Date date =new Date(format)
		//	audit_Info.setAudit_time(date);
			System.out.print(audit_Info.getPathway_id()+" ");
			System.out.print(audit_Info.getPathway_name()+" ");
            System.out.print(audit_Info.getSubmitter()+" ");
            System.out.print(audit_Info.getData_upload_moment()+" ");
            System.out.print(audit_Info.getChecker()+" ");
			System.out.print(audit_Info.getAudit_state()+" ");
            System.out.print(audit_Info.getAudit_time());
            System.out.print(audit_Info.getAudit_remark()+" ");
            System.out.print(audit_Info.getSubmitter_id()+" ");
            System.out.print(audit_Info.getChecker_id()+" ");
			System.out.println();
		}
		audit_Info_s.put("total",all.size());
		audit_Info_s.put("list",list);
		//model.addAttribute("list",pageInfo.getList());
		return audit_Info_s;
	}

	//对表audit_info进行所有的查询操作
	@RequestMapping("/audit_info")
	@ResponseBody
	public List<Audit_Info> audit_info(Model model){

		List<Audit_Info> audit_infoList = audit_InfoService.findAll("儿",0,0,0);

		model.addAttribute("audit_infoList", audit_infoList);
		return audit_infoList;
	}

	//对audit_Info表施行插入操作
	@RequestMapping("/insertAudit_info")
	public String insertAudit_info(Audit_Info audit_Info) {
		audit_Info.setPathway_id(10);
		audit_Info.setPathway_name("小儿气管（支气管）异物");
		audit_Info.setSubmitter("chen");
		audit_Info.setChecker("Li");
		audit_Info.setAudit_state(0);
		int count = audit_InfoService.insertAudit_info(audit_Info);
		if(count == 1){
			return "ok";
		}else{
			return "false";
		}
	}

	//对audit_info表的checker,audit_state,audit_time(为当前系统时间),audit_remark(什可的意见),checkerid申科人的id需要前台传入参数
	@RequestMapping("/updateAudit")
	@ResponseBody
	public ClincialResult updateById(Integer pathway_id, String checker, Integer audit_state, String audit_remark, Integer checkerid) {
		int flag = audit_InfoService.updateById(pathway_id, checker, audit_state, audit_remark,checkerid);
		if (flag == 1) {//审核成功！
			// this.status = 200;
			// this.msg = "OK";
			// this.data = null;
			return 	ClincialResult.ok();
		}else{//审核失败！
			// this.status = 500;
			// this.msg = "审核失败";
			// this.data = null;
			return	ClincialResult.build(500,"审核失败");
		}

	}

}


