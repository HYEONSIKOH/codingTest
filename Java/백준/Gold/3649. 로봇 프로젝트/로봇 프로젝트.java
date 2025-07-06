import java.util.*;
import java.io.*;

public class Main {
    private static String isPossible = "danger";

    private static int[] solution(int x, int n, int[] robots) {
        int[] result = new int[] {0, 0, Integer.MIN_VALUE};
        Arrays.sort(robots);

        int L = 0, R = n - 1;
        while (L < R) {
            int sum = robots[L] + robots[R];
            if (sum == x) {
                if (result[2] < Math.abs(robots[L] - robots[R])) {
                    result[0] = robots[L];
                    result[1] = robots[R];
                    result[2] = Math.abs(robots[L] - robots[R]);
                }
                isPossible = "yes";
                R--;
            } else {
                if (sum < x) L++;
                else R--;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            isPossible = "danger";
            String line = br.readLine();
            if (line == null || line.isEmpty()) break;

            int x = Integer.parseInt(line) * 10000000;
            int n = Integer.parseInt(br.readLine());

            int[] robots = new int[n];
            for (int i = 0; i < n; i++)
                robots[i] = Integer.parseInt(br.readLine());

            int[] res = solution(x, n, robots);

            bw.write(isPossible.equals("yes") ? "yes " + res[0] + " " + res[1] : "danger");
            bw.newLine();

            bw.flush();
        }

        bw.close();
    }
}