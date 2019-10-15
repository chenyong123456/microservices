package lyw.demo;

import java.util.*;

public class Bracket {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> set = new HashSet<>();
        int count = 0;
        while(sc.hasNextLine()){
            String str = sc.nextLine();
            if(str.equals("123")) break;
            if(stack(str)) {
                count++;
                str.trim();
                set.add(str);
            }
        }
        System.out.println(count);
        set.forEach(s->{
            System.out.println(s);
        });
    }


    public static boolean stack(String str){
        Stack<Character> stack = new Stack<>();
        for(int i = 0;i < str.length();++i){
            char c = str.charAt(i);
            if(c=='('||c=='（') stack.push(c);
            else if(c==')'||c=='）') {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
