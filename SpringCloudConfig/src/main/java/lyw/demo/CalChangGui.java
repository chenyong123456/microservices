package lyw.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalChangGui {
    public static void main(String[] args) {
        inputData();
    }

    private static void inputData(){
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

        HashMap<String,Integer> exp_map = new HashMap<>();
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            String key = entry.getKey();
            if(key.contains("常规") && !key.contains("护理")){
                exp_map.put(key,0);
            }
        }

        HashMap<String,Integer> one_map = search(exp_map);

//        for(Map.Entry<String,Integer> entry : one_map.entrySet()){
//            if(entry.getValue() < 5) one_map.remove(entry.getKey());
//        }


        //存放数据对应类型的map 并初始化
        HashMap<String, List<String>> type_map = new HashMap<>();
        for(String s : one_map.keySet()){
            ArrayList<String> list = new ArrayList<>();
            type_map.put(s,list);
        }

        //将数据对应类型放入
        for(String s1: one_map.keySet()){
            for(String s2 : exp_map.keySet()){
                if(s2.contains(s1)){
                    List<String> list = type_map.get(s1);
                    list.add(s2);
                }
            }
        }

        for(Map.Entry<String,List<String>> entry : type_map.entrySet()){
            List<String> list = entry.getValue();
            String str = entry.getKey();
            System.out.println(str + ": " + Arrays.toString(list.toArray()));
        }



    }

    /**
     * 通过包含常规关键字的集合找到每一条数据的关键字
     * @param map
     * @return
     */
    private static HashMap<String,Integer> search(HashMap<String,Integer> map){
        HashMap<String,Integer> r_map = new HashMap<>();
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]常规");
        for(String s : map.keySet()){
            Matcher matcher = pattern.matcher(s);
            while(matcher.find()){
                String str = s.substring(matcher.start(),matcher.end());
                if(r_map.containsKey(str)){
                    int count = r_map.get(str);
                    r_map.put(str,++count);
                }else{
                    r_map.put(str,1);
                }
            }
        }
        return r_map;
    }

}
