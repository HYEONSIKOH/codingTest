import java.util.*;
import java.io.*;

public class Main {
    private static int[][] arr;
    private static BitSet switches;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        switches = new BitSet(N);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (st.nextToken().equals("1")) switches.set(i);
            else switches.clear(i);
        }

        M = Integer.parseInt(br.readLine());
        arr = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        solution();
    }

    private static void solution() {
        for (int i = 0; i < M; i++) {
            if (arr[i][0] == 1) maleSwitch(arr[i][1]);
            else femaleSwitch(arr[i][1]);
        }

        printSwitches();
    }

    private static void maleSwitch(int idx) {
        for (int i = idx; i <= N; i += idx)
            switches.flip(i - 1);
    }

    private static void femaleSwitch(int idx) {
        int left = idx - 2;
        int right = idx;

        switches.flip(idx - 1);

        while (left >= 0 && right < N) {
            if (switches.get(left) == switches.get(right)) {
                switches.flip(left);
                switches.flip(right);
                left--;
                right++;
            } else break;
        }
    }

    private static void printSwitches() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(switches.get(i) ? "1 " : "0 ");
            if ((i + 1) % 20 == 0) sb.append("\n");
        }

        System.out.print(sb);
    }
}