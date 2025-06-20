import java.util.*;
import java.io.*;

public class Main {

    private static void solution(int N, int[] arr) {
        // Stack
        Deque<Integer> s = new ArrayDeque<>();
        long ans = 0;

        for (int i = 0; i < N; i++) {
            while(!s.isEmpty() && s.peek() <= arr[i]) {
                s.pop();
            }
            ans += s.size();
            s.push(arr[i]);
        }

        System.out.print(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        solution(N, arr);
    }
}