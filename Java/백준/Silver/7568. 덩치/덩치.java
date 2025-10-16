import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr;

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        solution();
    }

    private static void solution() {
        int[] temp = new int[N];

        for (int i = 0; i < N; i++)
            temp[i] = function(i);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            sb.append(temp[i] + 1).append(" ");

        System.out.println(sb);
    }

    private static int function(int idx) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (arr[idx][0] < arr[i][0] && arr[idx][1] < arr[i][1])
                cnt++;
        }

        return cnt;
    }
}