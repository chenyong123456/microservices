package cn.knowimage.pathcombine;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * 读配置文件，将配置文件的编码Map保存到Map中 —— 便于根据key查询对应编码value；
 */
public class ReadPropertiesToMapTest {

    public static LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();

    public static void h() {
       // C:\\Users\admin\Desktop\clincial-pathway\src\main\resources
        //C:\Users\admin\Desktop\clincial-pathway\src\main\resources\properties_to_map.properties
        File file = new File("C:\\Users\\admin\\Desktop\\clincial-pathway\\src\\main\\resources\\properties_to_map.properties");
        String str = null;
        try {
            str = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(str);
        String[] strArray = str.split("\\\n");

        for (int i = 0; i < strArray.length; i++) {
            String[] split = strArray[i].split(":");
            linkedHashMap.put(split[0],split[1]);
//            System.out.println(split[0] + ":" + split[1]);
        }
//
        for (String key : linkedHashMap.keySet()) {
            System.out.println("key = " + key + " and value = " + linkedHashMap.get(key));
        }
    }

    public static void main(String[] args) {
        ReadPropertiesToMapTest.h();
    }

}