import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<String> ans;

    public static void solution(int k, int before, int n, int sum, int depth, String str) {
        int s = String.valueOf(n).length();
        int next = Math.abs(n) + 1;

        if (str.length() > 1 && str.charAt(str.length() - 1 - s) == ' ') {
            int merged = Integer.parseInt(String.valueOf(Math.abs(before)) + Math.abs(n));
            if (before < 0) merged *= -1;
            sum = sum - before + merged;
            n = merged;
        } else { sum += n; }

        if (depth >= k) {
            if (sum == 0) ans.add(str);
            return;
        }

        // 다음이 +인 경우
        solution(k, n, next, sum, depth + 1, str + "+" + next);

        // 다음이 -인 경우
        solution(k, n, -next, sum, depth + 1, str + "-" + next);

        // 다음이 공백인 경우
        solution(k, n, next, sum, depth + 1, str + " " + next);
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            ans = new ArrayList<>();

            solution(K, 0, 1, 0, 1, "1");
            ans.sort(Comparator.naturalOrder());

            for (String s : ans) System.out.println(s);
            System.out.println();
        }
    }
}
