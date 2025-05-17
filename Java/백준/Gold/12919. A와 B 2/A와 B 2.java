import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static int dfs(String s, String T) {
        int res = 0;
        String temp = T;

        if (T.length() <= s.length()) {
            if (T.equals(s)) return 1;
            return 0;
        }

        if (T.charAt(T.length() - 1) == 'A') {
            T = T.substring(0, T.length() - 1);
            res += dfs(s, T);
        }

        if (temp.charAt(0) == 'B') {
            temp = new StringBuilder(temp).reverse().toString();
            temp = temp.substring(0, temp.length() - 1);
            res += dfs(s, temp);
        }

        if (res >= 1) return 1;
        else return 0;
    }


    public static void solution(String S, String T) {
        System.out.println(dfs(S, T));
    }

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        String S = st.nextToken();

        st = new StringTokenizer(br.readLine());
        String T = st.nextToken();

        solution(S, T);
    }
}