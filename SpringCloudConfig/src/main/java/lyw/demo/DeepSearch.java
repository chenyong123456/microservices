package lyw.demo;

import java.util.*;

public class DeepSearch {
    private static HashSet<List<String>> hashSet = new HashSet<>();

    //    用来存储一轮深搜后的数据
    private static HashSet<String> fset = new HashSet<>();

    public static void main(String[] args) {


    }

    public static void deepSearch(Map<String,List<String>> map,String str,HashSet<String> set){
        List<String> list = new ArrayList<>();

        if(set.contains(str)) return;

        set.add(str);

        fset.add(str);

        list = map.get(str);

        list.forEach(s->{
            deepSearch(map,s,set);
        });

    }

    public static void merge(Map<String,List<String>> map){

        int count = 0;
        HashSet<String> set = new HashSet<>();

        for(String key : map.keySet()){
            deepSearch(map,key,set);

            if(fset.isEmpty()) continue;
            System.out.println(Arrays.toString(fset.toArray(new String[0])));

            fset.clear();
            count++;
        }
        System.out.println("总条数: " + count);
    }
}
