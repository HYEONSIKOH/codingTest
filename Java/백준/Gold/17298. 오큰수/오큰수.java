import java.util.*;
import java.io.*;

public class Main {

    private static void solution(int N, int[] arr) {
        // Stack
        Deque<Integer> s = new ArrayDeque<>();
        int[] ans = new int[N];

        for (int i = 0; i < N ; i++) {
            while (!s.isEmpty() && arr[s.peek()] < arr[i])
                ans[s.pop()] = arr[i];

            s.push(i);
        }

        while (!s.isEmpty()) ans[s.pop()] = -1;

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