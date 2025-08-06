import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {
    private static char[] arr;
    private static List<String> strings;
    private static int size;
    private static String temp;
    private static int[] alphabet;

    private static void solution(char[] arr) {
        size = arr.length;
        strings = new ArrayList<>();
        temp = "";
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

    private static void backtrack(StringBuilder sb) {
        //System.out.println(sb.toString());
        if (sb.length() == size) {
            strings.add(sb.toString());
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = parseInt(br.readLine());

        while (N-- > 0) {
            arr = br.readLine().toCharArray();
            solution(arr);

            for (String string : strings)
                bw.write(string + "\n");
        }

        // System.gc();
        bw.flush();
        bw.close();
    }
}