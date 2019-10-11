package lyw.demo;

import java.util.Scanner;

public class Similiar {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

//        double simi = 1-similiar(str1,str2)/Math.max(str1.length(),str2.length());
        System.out.println(similiar(str1,str2)/ Math.max(str1.length(),str2.length()));
    }

    public static double similiar(String s1, String s2) {

        if (s1.isEmpty()) {
            return s2.length();
        }

        if (s2.isEmpty()) {
            return s1.length();
        }

        int d[][] = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            d[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            d[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            char s_i = s1.charAt(i - 1);
            for (int j = 1; j <= s2.length(); j++) {
                int cost;
                char t_j = s2.charAt(j - 1);
                if (s_i == t_j) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1),
                        d[i - 1][j - 1] + cost);
            }
        }
        return d[s1.length()][s2.length()];

    }

}


//该算法的解决是基于动态规划的思想，具体如下：
//        设 s 的长度为 n，t 的长度为 m。如果 n = 0，则返回 m 并退出；如果 m=0，则返回 n 并退出。否则构建一个数组 d[0..m, 0..n]。
//        将第0行初始化为 0..n，第0列初始化为0..m。
//        依次检查 s 的每个字母(i=1..n)。
//        依次检查 t 的每个字母(j=1..m)。
//        如果 s[i]=t[j]，则 cost=0；如果 s[i]!=t[j]，则 cost=1。将 d[i,j] 设置为以下三个值中的最小值：
//        紧邻当前格上方的格的值加一，即 d[i-1,j]+1
//        紧邻当前格左方的格的值加一，即 d[i,j-1]+1
//        当前格左上方的格的值加cost，即 d[i-1,j-1]+cost
//        重复3-6步直到循环结束。d[n,m]即为莱茵斯坦距离。
