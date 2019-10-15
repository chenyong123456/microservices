package cn.knowimage.util;

/**
 * 功能:主要对字符串进行相应的切割和修改
 * @author 啊勇
 */
public class StringIncise {

    //对一个字符串进行相应的切割处理
    public static String InciseToReload(String treatIncise){
      String aftertreatment = treatIncise.replaceAll("\"num\":1,","");
        return aftertreatment;
    }
    public static void main(String[] args) {
//        System.out.println("{\"scenario\":{\"num\":1,\"id_0\":{\"max\":96,\"min\":0,\"tag_name\":\"\",\"time_unit\":\"小时\"}}}");
        System.out.println(InciseToReload("{\"scenario\":{\"num\":1,\"id_0\":{\"max\":96,\"min\":0,\"tag_name\":\"\",\"time_unit\":\"小时\"}}}"));
    }
}
