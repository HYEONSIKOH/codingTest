import java.io.*;
import java.util.*;

public class Main {
    private static char[][] arrs;
    private static int[] ans;

    private static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arrs = new char[N][L];
        for (int i = 0; i < N; i++)
            arrs[i] = br.readLine().toCharArray();

        solution();
        System.out.println("NONE");

    }

    private static void solution() {
        ans = new int[L];
        Arrays.sort(arrs, (a, b) -> {
            for (int i = 0; i < L; i++) {
                if (a[i] != b[i]) return a[i] - b[i];
            }
            return 0;
        });

        for (int i = 0; i < N; i++) {
            ans[0] = i;
            backTrack(1, 1 << i);
        }
    }

    private static void backTrack(int idx, int visited) {
        // 유효성 검사
        if (idx <= L) {
            for (int i = 0; i < idx - 1; i++)
                if (arrs[ans[i]][idx - 1] != arrs[ans[idx - 1]][i]) return;
        } 
        
        if (idx >= L) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < L; i++)
                sb.append(arrs[ans[i]]).append('\n');

            System.out.print(sb);
            System.exit(0);
        }

        // 다음 순번
        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0) continue;
            ans[idx] = i;
            backTrack(idx + 1, visited | (1 << i));
        }
    }
}