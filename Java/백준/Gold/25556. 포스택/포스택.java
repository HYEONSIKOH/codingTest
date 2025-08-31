import java.io.*;
import java.util.*;

public class Main {
    private static int[] stack = new int[4];
    private static int[] arr;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        backTrack(0);
        System.out.println("NO");
    }

    private static void backTrack(int idx) {
        if (idx == N) {
            System.out.println("YES");
            System.exit(0);
        }

        for (int i = 0; i < 4; i++) {
            int temp = stack[i];
            if (temp < arr[idx]) {
                stack[i] = arr[idx];
                backTrack(idx + 1);
                stack[i] = temp;
            }

            if (temp == 0) {
                stack[i] = arr[idx];
                backTrack(idx + 1);
                stack[i] = 0;
                break;
            }
        }
    }
}