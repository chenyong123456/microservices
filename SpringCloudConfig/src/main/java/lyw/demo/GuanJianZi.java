package lyw.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class GuanJianZi {

    private static int count = 0;

    public static void main(String[] args) {
        HashSet<String> keySet = inputKeyWord();

        HashMap<String, List<String>> map = new HashMap<>();

        for(String s : keySet){
            List<String> list = new ArrayList<String>();
            map.put(s,list);
        }
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String str = scanner.nextLine();
            if(str.equals("123")) break;
            if(StringUtils.isBlank(str)) continue;
            JSONArray jsonArray = JSON.parseArray(str);
            jsonArray.forEach(o -> {
                String s = o.toString();
                for(String i : keySet){
                    if(s.contains(i)){
                        List<String> list = map.get(i);
                        list.add(s);
                        map.put(i,list);
                        addCount();
                    }
                }
            });
        }

        for(Map.Entry<String,List<String>> entry : map.entrySet()){
            System.out.println(entry.getKey() + ": " + Arrays.toString(entry.getValue().toArray()));
        }

        System.out.println(count);



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

    private static void addCount(){
        count = count + 1;
    }
}
