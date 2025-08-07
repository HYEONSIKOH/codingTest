import java.util.*;
import java.io.*;

public class Main {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int size;
    private static int range;

    private static char[] arr;
    private static boolean[] visited;
    private static int[] ans;

    private static void solution(){
        size = arr.length;

        // 가장 큰 수 구하기
        if (size > 10) range = (size - 10) / 2 + 10;
        else range = size;
        
        visited = new boolean[range + 1];
        ans = new int[range];
        for (int i = 1; i <= range; i++) {
            if (i < 10) {
                if (arr[0] - '0' == i) {
                    ans[0] = i;
                    visited[i] = true;
                    backtrack(0, 0);
                    visited[i] = false;
                }
            }

            if (i >= 10){
                int num = (arr[0] - '0') * 10 + (arr[1] - '0');
                if (num == i) {
                    ans[0] = i;
                    visited[i] = true;
                    backtrack(1, 0);
                    visited[i] = false;
                }
            }

        }
    }

    private static void backtrack(int idx, int num){
        if (idx == size - 1) {
            StringBuilder sb = new StringBuilder();

            for (int i : ans)
                sb.append(i).append(" ");

            System.out.println(sb.toString());
            System.exit(0);
        }

        for (int i = 1; i <= range; i++) {
            if (visited[i]) continue;
            if (i < 10) {
                if (arr[idx + 1] - '0' == i) {
                    ans[num + 1] = i;
                    visited[i] = true;
                    backtrack(idx + 1, num + 1);
                    visited[i] = false;
                }
            }

            if (idx + 2 < size && i >= 10) {
                if ((arr[idx + 1] - '0') * 10 + (arr[idx + 2] - '0') == i) {
                    ans[num + 1] = i;
                    visited[i] = true;
                    backtrack(idx + 2, num + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = br.readLine().toCharArray();
        solution();

        // System.gc();
        bw.flush();
        bw.close();
    }
}