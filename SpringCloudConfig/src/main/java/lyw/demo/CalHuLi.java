package lyw.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CalHuLi {
    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            if(str.equals("123")) break;
            if(StringUtils.isBlank(str)) continue;
            JSONArray jsonArray = JSON.parseArray(str);
            jsonArray.forEach(o -> {

                if(map.containsKey(o.toString())){
                    int count = map.get(o.toString());
                    map.put(o.toString(),++count);
                }else{
                    map.put(o.toString(),1);
                }
            });
        }

        int count = 0;
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            String key = entry.getKey();
            if(key.contains("常规护理") && key.contains("护理常规")){
                count++;
                System.out.println(key);
            }
        }
        System.out.println(count);
    }
}
