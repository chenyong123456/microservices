package lyw.demo;


import java.util.HashSet;
import java.util.List;

/**
 * 判断数据是否满足分到类中
 */
public class CalContain {

    private static HashSet<String> set = new HashSet<>();

    static {
        set.add("、");
        set.add(",");
        set.add("，");
        set.add("/");
        set.add("或");
        set.add("与");
        set.add("和");
    }

    public static void beginCal(Type type){

    }

    private static boolean Cal(Type type,Data data){
        String typeName = type.getName();
        String dataName = data.getName();

        if(dataName.contains(typeName)){
            List<Data> dataList = type.getList();
            List<Type> typeList = data.getList();

            dataList.add(data);
            typeList.add(type);
        }

        return true;
    }

    private static boolean contains(String dataName,String typeName){
        if(dataName.contains(typeName)) return true;
        String new_dataName = split(dataName);
        if(new_dataName.contains(typeName)) return true;
        return false;
    }

    private static String split(String dataName){


        return null;
    }
}
