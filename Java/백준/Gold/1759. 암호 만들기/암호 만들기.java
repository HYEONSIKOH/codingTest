import java.util.*;
import java.io.*;

public class Main {
    private static boolean[] visited;
    private static char[] arr;
    private static BufferedWriter bw;

    private static void solution(int L, int C, char[] arr) throws IOException {
        Arrays.sort(arr);
        visited = new boolean[C];

        for (int i = 0; i < C; i++) {
            visited[i] = true;
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u')
                backtrack(L, C, 1, 0, i, new StringBuilder(String.valueOf(arr[i])));
            else
                backtrack(L, C, 0, 1, i, new StringBuilder(String.valueOf(arr[i])));
            visited[i] = false;
        }
    }

    // conCnt: 자음 개수, vowCnt: 모음 개수
    private static void backtrack(int L, int C, int conCnt, int vowCnt, int idx, StringBuilder sb) throws IOException {
        if (sb.length() == L) {
            if (conCnt >= 1 && vowCnt >= 2)
                bw.write(sb.toString() + "\n");
            return;
        }

        for (int i = idx + 1; i < C; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u')
                    backtrack(L, C, conCnt + 1, vowCnt, i, new StringBuilder(sb).append(arr[i]));
                else
                    backtrack(L, C, conCnt, vowCnt + 1, i, new StringBuilder(sb).append(arr[i]));
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++)
            arr[i] = st.nextToken().charAt(0);

        solution(L, C, arr);

        // System.gc();
        bw.flush();
        bw.close();
    }
}