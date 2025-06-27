import java.util.*;
import java.io.*;

public class Main {
    private static char[] reverse(int e, char[] arr) {
        char[] res = new char[e + 1];
        int temp = e;

        for (int i = 0; i <= e; i++)
            res[i] = arr[temp--];

        return res;
    }

    private static int solution(String s, char[] arr) {
        int N = arr.length;
        int SLen = s.length();

        int idx = N - 1;
        for (int i = N - 1; i >= SLen; i--) {
            idx--;
            if (arr[i] == 'B') arr = reverse(idx, arr);
        }

        for (int j = 0; j <= idx; j++)
            if (arr[j] != s.charAt(j)) return 0;
        
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String S = br.readLine();
        char[] T = br.readLine().toCharArray();

        bw.write(String.valueOf(solution(S, T)));

        bw.flush();
        bw.close();
    }
}