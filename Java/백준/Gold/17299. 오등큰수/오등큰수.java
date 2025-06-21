import java.util.*;
import java.io.*;

public class Main {

    private static int[] solution(int N, int maxNum, int[] arr) throws IOException {
        int[] cntNum = new int[maxNum + 1];
        for (int i = 0; i < N; i++) cntNum[arr[i]]++;

        // Stack
        Deque<Integer> s = new ArrayDeque<>();
        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            while(!s.isEmpty() && cntNum[arr[s.peek()]] < cntNum[arr[i]])
                ans[s.pop()] = arr[i];

            s.push(i);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int maxNum = -1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxNum = Math.max(maxNum, arr[i]);
        }

        int[] ans = solution(N, maxNum, arr);
        for (int i = 0; i < N; i++) {
            bw.write(ans[i] == 0 ? "-1 " : ans[i] + " ");
        }

        bw.flush();
    }
}