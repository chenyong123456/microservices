package cn.knowimage.jsonoutput;

public class TestJson {
    public static void main(String[] args) {

        //System.out.println(Treatment_choice_return.treatment_choice_return("{'ref': '淋巴','scenario': {'num':2,'id_0': ['哈哈','那你'],'id_1': ['看看']}}"));
        System.out.println(TreatmentDaysReturn.treatment_days("{'scenario':{'num':1,'id_0':{'tag_name':'','time_text':'','time_unit':'','min':0,'max':0}}}"));
        System.out.println(TreatmentEntryStandardReturn.treatment_entry_standard("{'num': 0}"));
        System.out.println(PrepTreatmentCommonReturn.prep_treatmentCommon("{'scenario':{'num':1,'id_0':{'duration':{'time_unit':'','time_text':'','min':0,'max':0},'obligatory_exam':[''],'optional_exam':[''],'notification':['']}}}"));
   /*     System.out.println(Prep_treatment_drug_usage_return.prep_treatment_drug_usage(
                "{'antibio_usage':{'num':2,'id_0': ['哈哈','那你']},'anaesthetic_usage':{'num':1,'id_0': ['哈哈']},'otherdrugs_usage':{'num':1,'id_0': ['哈哈']}}"));
        System.out.println(Prep_treatment_extension_return.prep_treatment_extension("{'num':3,'id_0':{'content':'第一天','content_item':['覅发','解决']},'id_1':{'content':'第二天','content_item':['覅发']},'id_2':{'content':'第三天','content_item':['覅发','解决','哈哈']}}"));*/
        /*System.out.println(TreatmentReturn.treatment(
                "{'duration':{'max':288,'min':24,'text':'','time_unit':'时'},'scenario':{'scenario_num':1,'scenario_id_0':{'notification':['RYhE答复','让他加热天发给'],'optional_exam':['SRGRY都是','F有hEhe'],'obligatory_exam':['ERgh答复','RWEYhER答复'],'treatment_plan':{'num':2,'ref':'eRYetU','id_0':{'content':'sdgRHrWEY','content_item':['SGRdf','dfhER']},'id_1':{'content':'SDR有和士','content_item':['WRTYWSD']}}}}}"));*/
        // {'duration':{'time_unit':'时','text':'','min':1,'max':2},'scenario':{'scenario_num':2,'scenario_id_0':{'treatment_plan':{'ref':'你好','num':2,'id_0':{'content':'结合患','content_item':['那你','看看']},'id_1':{'content':'结合患','content_item':['那你']}}'obligatory_exam':['一个'],'optional_exam':['儿童','零零'],'notification':['哦哦']},'scenario_id_1':{'treatment_plan':{'ref':'你好','num':2,'id_0':{'content':'结合患','content_item':['那你','看看']},'id_1':{'content':'结合患','content_item':['那你']}}'obligatory_exam':['一个'],'optional_exam':['儿童','零零'],'notification':['哦哦']}}}
        //  System.out.println(Drug_usage_return.drug_usage("{'antibio_usage':{'num':2,'id_0':['覅发','让人']},'anaesthetic_usage':{'num':1,'id_0':['覅发']},'otherdrugs_usage':{'num':2,'id_0':['覅发','品牌']}}"));

        System.out.println(AfterMedicalTreatmentReturn.after_medical_treatment("{'duration':{'time_unit':'其他时间描述','time_text':'哈哈哈哈','min':0,'max':0},'scenario':{'num':1,'id_0':{'obligatory_exam':[''],'optional_exam':[''],'recovery_plan':['']}}}"));
       /* System.out.println(After_treatment_drug_usage_return.after_treatment_drug_usage(
                "{'antibio_usage':{'num':2,'id_0':['覅发','让人']},'anaesthetic_usage':{'num':1,'id_0':['一']},'otherdrugs_usage':{'num':2,'id_0':['三','品牌']}}"));
        System.out.println(Discharge_criteria_return.discharge_criteria("['完成','完','流程']"));
        System.out.println(Other_notice_return.other_notice("['haha ','dsgsgd','afafag','trurtufg']"));
    String str = "012010089800";
    String str1 = str.substring(0,2);
    String str2 = str.substring(2,6);
    String str3 = str.substring(6,10);
    String str4 = str.substring(10,12);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
    }*/

    }
}
