import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static String str;
    private static int n;
    private static int[] digits;
    private static int[] suffixDigitSum;
    private static long[] suffixNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < N; i++) {
            str = br.readLine();

            boolean hasTwoOrMore = false;
            for (char c : str.toCharArray()) {
                if (c >= '2') { hasTwoOrMore = true; break; }
            }

            if (!hasTwoOrMore) {
                out.append("Hello, BOJ 2023!").append('\n');
                continue;
            }

            out.append(solution()).append('\n');
        }

        System.out.print(out.toString());
    }

    private static int solution() {
        long flag = Long.parseLong(str);

        // Precompute digits
        n = str.length();
        digits = new int[n];
        for (int i = 0; i < n; i++) digits[i] = str.charAt(i) - '0';

        // Minimal additional sum if we split all remaining digits
        suffixDigitSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) suffixDigitSum[i] = suffixDigitSum[i + 1] + digits[i];

        // Maximal additional sum if we take the whole tail as one number
        suffixNum = new long[n + 1];
        long cur = 0, mul = 1;
        for (int i = n - 1; i >= 0; i--) {
            cur = digits[i] * mul + cur; // value of str[i..n-1]
            suffixNum[i] = cur;
            mul *= 10;
        }

        int ans = 0;

        // Incrementally compute digit^m for m = 1, 2, ... without Math.pow
        long[] pow = new long[10];
        for (int d = 0; d <= 9; d++) pow[d] = d; // m = 1

        while (true) {
            long target = 0;
            for (int i = 0; i < n; i++) {
                target += pow[digits[i]];
                if (target > flag) break;
            }
            if (target > flag) break;

            if (canMakeTarget(0, 0L, target)) ans++;

            // advance m -> m+1 with capping to avoid overflow/extra work
            for (int d = 0; d <= 9; d++) {
                if (d == 0) { pow[d] = 0; continue; }
                long next = pow[d] * d;
                pow[d] = (next > flag) ? (flag + 1) : next;
            }
        }

        return ans;
    }

    private static boolean canMakeTarget(int i, long sum, long target) {
        // All digits placed: check exact match
        if (i == n) return sum == target;

        // Lower bound: even if we split all remaining digits
        if (sum + suffixDigitSum[i] > target) return false;

        // Upper bound: even if we take the whole tail as one number
        if (sum + suffixNum[i] < target) return false;

        long val = 0;
        for (int j = i; j < n; j++) {
            val = val * 10 + digits[j];   // build number str[i..j]
            long ns = sum + val;
            if (ns > target) break;       // increasing j only increases ns
            if (canMakeTarget(j + 1, ns, target)) return true;
        }
        return false;
    }
}