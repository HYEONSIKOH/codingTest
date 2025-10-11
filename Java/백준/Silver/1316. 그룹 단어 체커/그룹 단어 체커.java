import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (isTrue(str)) cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean isTrue(String str) {
        boolean[] visited = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (visited[ch - 'a']) return false;
            while(i + 1 < str.length() && ch == str.charAt(i + 1)) i++;

            visited[ch - 'a'] = true;
        }
        
        return true;
    }
}