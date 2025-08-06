import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int size;
    private static int[] alphabet;

    private static void solution(char[] arr) throws IOException {
        size = arr.length;
        alphabet = new int[26];
        Arrays.sort(arr);

        for (int i = 0; i < size; i++)
            alphabet[arr[i] - 'a']++;

        for (int i = 0; i < 26; i++) {
            if (alphabet[i] != 0) {
                alphabet[i]--;
                backtrack(new StringBuilder().append((char)(i + 'a')));
                alphabet[i]++;
            }
        }
    }

    private static void backtrack(StringBuilder sb) throws IOException {
        if (sb.length() == size) {
            bw.write(sb + "\n");
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (alphabet[i] != 0) {
                alphabet[i]--;
                backtrack(sb.append((char)(i + 'a')));
                sb.deleteCharAt(sb.length() - 1);
                alphabet[i]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = parseInt(br.readLine());

        while (N-- > 0) {
            char[] arr = br.readLine().toCharArray();
            solution(arr);
        }

        // System.gc();
        bw.flush();
        bw.close();
    }
}