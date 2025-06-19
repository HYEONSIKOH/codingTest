import java.util.*;
import java.io.*;

public class Main {

    private static void solution(int N, int[] arr) {
        // Stack
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ans = new int[N];

        for (int i = N - 1; i >= 0 ; i--) {
            int num = arr[i];

            while (!stack.isEmpty()) {
                if (num < stack.peek()) {
                    ans[i] = stack.peek();
                    break;
                }
                stack.pop();
            }

            if (stack.isEmpty()) ans[i] = -1;
            stack.push(num);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : ans) sb.append(i).append(' ');

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        solution(N, arr);
    }
}