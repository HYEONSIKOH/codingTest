import java.util.*;
import java.io.*;

public class Main {
    private static int[] arr;
    private static int MAX = Integer.MIN_VALUE, MIN = Integer.MAX_VALUE;
    private static int N;

    private static void solution(int depth, int calc, int[] operators) {
        if (depth == N - 1) {
            MAX = Math.max(MAX, calc);
            MIN = Math.min(MIN, calc);
            return;
        }

        // 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)
        for (int i = 0; i < 4; i++) {
            if (operators[i] == 0) continue;

            operators[i]--;
            if (i == 0) solution(depth + 1, calc + arr[depth + 1], operators);
            else if (i == 1) solution(depth + 1, calc - arr[depth + 1], operators);
            else if (i == 2) solution(depth + 1, calc * arr[depth + 1], operators);
            else if (i == 3) { solution(depth + 1, calc / arr[depth + 1], operators); }
            operators[i]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] operators = new int[4];
        for (int i = 0; i < 4; i++)
            operators[i] = Integer.parseInt(st.nextToken());

        solution(0, arr[0], operators);

        bw.write(String.valueOf(MAX) + "\n");
        bw.write(String.valueOf(MIN) + "\n");

        bw.flush();
        bw.close();
    }
}