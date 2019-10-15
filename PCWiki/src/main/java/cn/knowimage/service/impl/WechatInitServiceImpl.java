package cn.knowimage.service.impl;

import cn.knowimage.mapper.PathwayInfoMapper;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.service.WechatInitService;
import cn.knowimage.service.redis.RedisService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能:初始化WechatApplet的所有数据到redis中.
 * @author 啊勇
 */

@Service
public class WechatInitServiceImpl implements WechatInitService {

    //对表Pathway_info实现增加
    @Autowired
    public PathwayInfoMapper pathway_InfoMapper;
    //注入redis的服务
    @Autowired
    public static RedisService redisService;

    @Value("${WechatApplet}")
    public static String WechatApplet;//小褚WechatApplet的缓存-->总文件夹

    //将数据库中的所有数据都加入到redis中
    @Override
    public void wechatAppInit() {
        JSONObject redisJson = new JSONObject();
            //从数据库中查询出所有数据
            List<PathwayInfo> list = pathway_InfoMapper.findAll();
            //通过for循环将所有数据都加入redis中
        for (int i = 0; i <list.size(); i++) {

            redisJson.put("pathway_id", list.get(i).getPathway_id());
            redisJson.put("pathway_index", list.get(i).getPathway_index());
            redisJson.put("pathway_name", list.get(i).getPathway_name());
            redisJson.put("first_diagnose", list.get(i).getFirst_diagnosis());
            redisJson.put("suitable_subject_disc", list.get(i).getSuitable_subject_disc());
            redisJson.put("diagnosis", list.get(i).getDiagnosis());
            //这里不是一个字段进行数据的处理,去除带num的数据
            //处理treatment_choice字段中num数据
            JSONObject dealJson = JSONObject.fromObject(list.get(i).getTreatment_choice());
            dealJson.getJSONObject("scenario").remove("num");
            redisJson.put("treatment_choice", dealJson.toString());

            //处理treatment_days字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getTreatment_days());
            dealJson.getJSONObject("scenario").remove("num");
            redisJson.put("treatment_days", dealJson.toString());

            //处理treatment_entry_standard字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getTreatment_entry_standard());
            dealJson.remove("num");
            redisJson.put("treatment_entry_standard", dealJson.toString());

            redisJson.put("type", list.get(i).getType());

            redisJson.put("drug_use_period", list.get(i).getDrug_use_period());

            //处理prep_treatment_common字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getPrep_treatment_common());
            dealJson.getJSONObject("scenario").remove("num");
            redisJson.put("prep_treatment_common", dealJson.toString());

            //处理prep_treatment_drug_usage字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getPrep_treatment_drug_usage());
            dealJson.getJSONObject("antibio_usage").remove("num");
            dealJson.getJSONObject("anaesthetic_usage").remove("num");
            dealJson.getJSONObject("otherdrugs_usage").remove("num");
            redisJson.put("prep_treatment_drug_usage", dealJson.toString());

            //处理prep_treatment_extension字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getPrep_treatment_extension());
            dealJson.remove("num");
            redisJson.put("prep_treatment_extension", dealJson.toString());
            //处理treatment字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getTreatment());
            int scenario_num = dealJson.getJSONObject("scenario").getInt("scenario_num");
            dealJson.getJSONObject("scenario").remove("scenario_num");
            //动态循环删除scenario_id_0中的带num的值
            for (int j = 0; j < scenario_num; j++) {
                dealJson.getJSONObject("scenario").getJSONObject(String.format("scenario_id_%d", j)).getJSONObject("treatment_plan").remove("num");
            }
            redisJson.put("treatment", dealJson.toString());

            //处理drug_usage字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getDrug_usage());
            dealJson.getJSONObject("antibio_usage").remove("num");
            dealJson.getJSONObject("anaesthetic_usage").remove("num");
            dealJson.getJSONObject("otherdrugs_usage").remove("num");
            redisJson.put("drug_usage", dealJson.toString());

            //处理after_medical_treatment字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getAfter_medical_treatment());
            dealJson.getJSONObject("scenario").remove("num");
            redisJson.put("after_medical_treatment", dealJson.toString());

            //处理after_treatment_drug_usage字段的数据
            dealJson = JSONObject.fromObject(list.get(i).getAfter_treatment_drug_usage());
            dealJson.getJSONObject("antibio_usage").remove("num");
            dealJson.getJSONObject("anaesthetic_usage").remove("num");
            dealJson.getJSONObject("otherdrugs_usage").remove("num");
            redisJson.put("after_treatment_drug_usage", dealJson.toString());

            redisJson.put("discharge_criteria", list.get(i).getDischarge_criteria());
            redisJson.put("other_notice", list.get(i).getOther_notice());
            System.out.println(redisJson.toString());
            System.out.println(list.get(i).getPathway_name());
            redisService.set(WechatApplet + ":" + "wechatApp:" + list.get(i).getPathway_name(), redisJson.toString());
            //查询数据库的数据就写入redis
            //redisService.set(WechatApplet + ":" + "wechatApp:" + list.get(i).getPathway_name(), redisJson.toString());
        }
    }

}
