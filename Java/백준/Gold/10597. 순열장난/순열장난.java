import java.util.*;
import java.io.*;

import static java.lang.Integer.*;

public class Main {
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringBuilder sb;
    private static int size;
    private static int range;

    private static char[] arr;
    private static boolean[] visited;

    private static void solution(){
        size = arr.length;

        // 가장 큰 수 구하기
        if (size > 10) range = (size - 10) / 2 + 10;
        else range = size;

        sb = new StringBuilder();
        visited = new boolean[range + 1];
        for (int i = 1; i <= range; i++) {
            if (i < 10) {
                if (arr[0] - '0' == i) {
                    sb.append(i).append(" ");
                    visited[i] = true;
                    backtrack(0);
                    visited[i] = false;
                    sb.delete(sb.length() - 2, sb.length());
                }
            }

            if (i >= 10){
                int num = (arr[0] - '0') * 10 + (arr[1] - '0');
                if (num == i) {
                    sb.append(num).append(" ");
                    visited[i] = true;
                    backtrack(1);
                    visited[i] = false;
                    sb.delete(sb.length() - 3, sb.length());
                }
            }

        }
    }

    private static void backtrack(int idx){
        if (idx == size - 1) {
            System.out.println(sb.toString());
            System.exit(0);
        }

        for (int i = 1; i <= range; i++) {
            if (visited[i]) continue;
            if (i < 10) {
                if (arr[idx + 1] - '0' == i) {
                    sb.append(i).append(" ");
                    visited[i] = true;
                    backtrack(idx + 1);
                    visited[i] = false;
                    sb.delete(sb.length() - 2, sb.length());
                }
            }

            if (idx + 2 < size && i >= 10) {
                int num = (arr[idx + 1] - '0') * 10 + (arr[idx + 2] - '0');
                if (num == i) {
                    sb.append(num).append(" ");
                    visited[i] = true;
                    backtrack(idx + 2);
                    visited[i] = false;
                    sb.delete(sb.length() - 3, sb.length());
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