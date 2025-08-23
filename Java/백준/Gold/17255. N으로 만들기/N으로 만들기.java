import java.io.*;
import java.util.*;

public class Main {
    private static char[] N;
    private static Map<Integer, Boolean> m = new HashMap<>();
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        N = br.readLine().toCharArray();
        solution(0, N.length - 1);

        System.out.println(ans);
    }

    private static int charArrToInt(int s, int e) {
        int num = 0, mul = 1;
        for (int i = e; i >= s; i--) {
            num += (N[i] - '0') * mul;
            mul *= 10;
        }

        return num;
    }

    private static void solution(int s, int e) {
        if (s == e) {
            ans++;
            return;
        }

        // 왼쪽을 제거한 경우
        int left = charArrToInt(s + 1, e);
        int right = charArrToInt(s, e - 1);

        solution(s + 1, e);

        if (left != right) solution(s, e - 1);
    }
}