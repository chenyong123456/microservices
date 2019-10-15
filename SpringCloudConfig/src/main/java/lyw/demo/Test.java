package lyw.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            if(str.equals("123")) break;
            list.add(str);
        }

        for(int i = 0;i < list.size();++i){
            for(int j = 0;j < list.size();++j){
                if(i == j) continue;
                if(Similiar.similiar(list.get(i),list.get(j)) <= 1){
                    System.out.println(list.get(i) + "->" + list.get(j));
                }
            }
        }
    }
}
