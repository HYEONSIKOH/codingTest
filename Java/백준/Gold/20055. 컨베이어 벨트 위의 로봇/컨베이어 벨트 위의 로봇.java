import java.util.*;
import java.io.*;

public class Main {

    private static int solution(int N, int K, int[] arr) {
        boolean[] robot = new boolean[2 * N];
        int start_pos = 0;
        int end_pos = N - 1;
        int result = 1;
        int cnt = 0;
        boolean stop = false;

        while (!stop) {
            // 1. 회전
            start_pos = (start_pos == 0) ? 2 * N - 1 : start_pos - 1;
            end_pos = (end_pos == 0) ? 2 * N - 1 : end_pos - 1;

            if (robot[end_pos]) robot[end_pos] = false;

            // 2. 로봇 이동
            if (start_pos != -1) {
                int idx = end_pos;
                for (int i = 0; i < N - 1; i++) {
                    int next = idx;
                    idx = (idx == 0) ? 2 * N - 1 : idx - 1;

                    if (!robot[next] && robot[idx] && arr[next] > 0) {
                        arr[next]--;
                        if (arr[next] == 0) {
                            cnt++;
                            if (cnt >= K) {
                                stop = true;
                                break;
                            }
                        }
                        robot[next] = true;
                        robot[idx] = false;
                    }
                }
                robot[end_pos] = false;
            }

            // 3. 로봇 올리기
            if (arr[start_pos] > 0) {
                arr[start_pos]--;
                if (arr[start_pos] == 0) {
                    cnt++;
                    if (cnt >= K) {
                        stop = true;
                    }
                }
                robot[start_pos] = true;
            }

            if (stop) break;
            result++;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[2 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(solution(N, K, arr)));

        bw.flush();
        bw.close();
    }
}