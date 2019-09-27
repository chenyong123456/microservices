package lyw.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class calculator {

    public static void main(String[] args) {

        HashSet<String> keySet = inputKeyWord();

        HashMap<String,Integer> map = new HashMap<String,Integer>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            if(str.equals("123")) break;
            if(StringUtils.isBlank(str)) continue;
            JSONArray jsonArray = JSON.parseArray(str);
            jsonArray.forEach(o -> {
                String[] array = o.toString().split("、");
                for(String s : array){
                    if(map.containsKey(s)){
                        int count = map.get(s);
                        map.put(s,++count);
                    }else{
                        map.put(s,1);
                    }
                }

            });
        }

        List<String> list = new ArrayList<>();

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue() >= 0) {
                list.add(entry.getKey());
            }
        }


        test(keySet,list);
        System.out.println("cal ...");
        System.out.println(map.size());

        HashMap<String,Integer> count_map = new HashMap<>();

//        for(int i = 0;i < list.size();++i){
//            String str1 = list.get(i);
//            count_map.put(str1,0);
//            for(int j = 0;j < list.size();++j){
//                if(j == i) continue;
//                String str2 = list.get(j);
//                if(Similiar.similiar(str1,str2) / Math.max(str1.length(),str2.length()) < 0.3){
//                    int count = count_map.get(str1);
//                    count_map.put(str1,count+1);
//                }
//            }
//        }

//        //没有关键字的版本
//        HashMap<String,List<String>> result_map = new HashMap<>();
//        for(int i = 0;i < list.size();++i){
//            String str1 = list.get(i);
//            result_map.put(str1,new ArrayList<String>());
//            for(int j = 0;j < list.size();++j){
//                if(j == i) continue;
//                String str2 = list.get(j);
//                if(Similiar.similiar(str1,str2) / Math.max(str1.length(),str2.length()) < 0.4){
//                    List<String> c_list = result_map.get(str1);
//                    c_list.add(str2);
//                }
//            }
//            System.out.println("cal: " + list.get(i));
//        }
//
////        计算相似度后得到的结果
//        for(Map.Entry<String,List<String>> entry : result_map.entrySet()){
//            List<String> c_list = entry.getValue();
//            String[] ss = c_list.toArray(new String[0]);
//            System.out.println(entry.getKey() + " :   " + Arrays.toString(ss));
//        }
//
////        DeepSearch.merge(result_map);
    }


    private static HashSet<String> inputKeyWord(){
        System.out.println("please input keyword");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        HashSet<String> keySet = new HashSet<>();

        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()){
            String str = sc.nextLine();
            if(str.equals("123")) break;
            str = str.substring(0,str.length()-1);
            keySet.add(str);
        }

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("input finished");

        return keySet;
    }

    private static void test(HashSet<String> set, List<String> list){
//        for(String str : set){
//            List<String> rlist = new ArrayList<>();
//            list.forEach(s->{
//                if(Similiar.similiar(s,str) / Math.max(str.length(),s.length()) < 0.3){
//                    rlist.add(s);
//                }
//            });
//            if(rlist.isEmpty()) continue;
//            System.out.println(str + ": " + Arrays.toString(rlist.toArray()));
//            rlist.clear();
//        }

        HashMap<String,List<String>> map = new HashMap<String,List<String>>();

        double minSimi;
        String exp = null; //存放当前相似度最高的key
        for(String str : list){
            minSimi = 1;
            exp = null;
            for (String s : set) {
                if(str.equals("")) break;
                double i = Similiar.similiar(s,str) / Math.max(str.length(),s.length());
                if(i < minSimi) {
                    minSimi = i;
                    exp = s;
                }
            }
            if(minSimi > 0.3) continue;
            if(map.containsKey(exp)){
                List<String> rlist;
                rlist = map.get(exp);
                rlist.add(str);
                map.put(exp,rlist);
            }else{
                List<String> rlist;
                rlist = new ArrayList<>();
                rlist.add(str);
                map.put(exp,rlist);
            }
        }

        for(Map.Entry<String,List<String>> entry : map.entrySet()){
            System.out.println(entry.getKey() + ": " + Arrays.toString(entry.getValue().toArray()));
        }
    }
}
