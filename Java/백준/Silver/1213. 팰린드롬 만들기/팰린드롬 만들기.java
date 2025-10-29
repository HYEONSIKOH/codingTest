import java.util.*;
import java.io.*;

public class Main {
    private static final StringBuilder sb = new StringBuilder();
    private static final int[] arr = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        solution(str);
        System.out.println(sb);
    }

    private static void solution(String str) {
        for (char c : str.toCharArray()) arr[c - 'A']++;
        int cnt = 0;
        char temp = ' ';
        for (int i = 0; i < 26; i++) {
            if (arr[i] % 2 != 0) {
                cnt++;
                temp = (char)(i + 'A');
                arr[i]--;
            }

            if (cnt > 1) {
                sb.append("I'm Sorry Hansoo");
                return;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) {
                for (int j = 0; j < arr[i] / 2; j++)
                    sb.append((char) (i + 'A'));
                arr[i] /= 2;
            }
        }

        if (cnt == 1) sb.append(temp);

        for (int i = 25; i >= 0; i--) {
            if (arr[i] > 0) {
                for (int j = 0; j < arr[i]; j++)
                    sb.append((char) (i + 'A'));
            }
        }
    }
}