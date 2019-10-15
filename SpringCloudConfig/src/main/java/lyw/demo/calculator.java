package lyw.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class calculator {

    public static void main(String[] args) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            if(str.equals("123")) break;
            if(StringUtils.isBlank(str)) continue;
//            JSONArray jsonArray = JSON.parseArray(str);
//            jsonArray.forEach(o -> {
//                if(map.containsKey(o.toString())){
//                    int count = map.get(o.toString());
//                    map.put(o.toString(),++count);
//                }else{
//                    map.put(o.toString(),1);
//                }
//            });
            map.put(str,1);
        }

        List<String> list = new ArrayList<>();

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue() >= 0) {
                list.add(entry.getKey());
            }
        }


//        HashMap<String,Integer> count_map = new HashMap<>();
//
////        for(int i = 0;i < list.size();++i){
////            String str1 = list.get(i);
////            count_map.put(str1,0);
////            for(int j = 0;j < list.size();++j){
////                if(j == i) continue;
////                String str2 = list.get(j);
////                if(Similiar.similiar(str1,str2) / Math.max(str1.length(),str2.length()) < 0.3){
////                    int count = count_map.get(str1);
////                    count_map.put(str1,count+1);
////                }
////            }
////        }

        HashMap<String,List<String>> result_map = new HashMap<>();
        boolean f[] = new boolean[list.size()];
        for(int i = 0;i < list.size();++i){
            String str1 = list.get(i);
            result_map.put(str1,new ArrayList<String>());
            for(int j = 0;j < list.size();++j){
                if(j == i) continue;
                String str2 = list.get(j);
                if(Similiar.similiar(str1,str2) / Math.max(str1.length(),str2.length()) < 0.3){
                    List<String> c_list = result_map.get(str1);
                    c_list.add(str2);
                }
            }
            System.out.println("cal: " + list.get(i));
        }


        int count = 0;
//        计算相似度后得到的结果
        for(Map.Entry<String,List<String>> entry : result_map.entrySet()){
            List<String> c_list = entry.getValue();
            for(String s : c_list){
                result_map.get(s).remove(entry.getKey());
            }
            String[] ss = c_list.toArray(new String[0]);

            if(ss.length>0){
                count++;
                System.out.println(entry.getKey());
            }
        }

        System.out.println(count);



//        DeepSearch.merge(result_map);
    }
}
