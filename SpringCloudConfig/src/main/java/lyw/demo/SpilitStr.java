package lyw.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class SpilitStr {

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        HashMap<String,Integer> map = new HashMap<>();
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            if(str.equals("123")) break;
            String[] ss = str.split("，|、");
            for(String s : ss){
                if(map.containsKey(s)){
                    int count = map.get(s);
                    map.put(s,++count);
                }else{
                    map.put(s,1);
                }
            }
        }

        HashSet<String> set = new HashSet<>();
        HashSet<String> count_set = new HashSet<>();
//        while(sc.hasNextLine()){
//            String str = sc.nextLine();
//            if(str.equals("123")) break;
//            if(map.get(str) > 1){
//                set.add(str);
//            }
//
//        }

        for(Map.Entry<String,Integer> entry : map.entrySet()){
            if(entry.getValue() > 5) count_set.add(entry.getKey());
        }

        count_set.forEach(s->{
            System.out.println(s);
        });

        System.out.println(count_set.size());
    }
}
