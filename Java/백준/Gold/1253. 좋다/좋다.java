import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int solution(int N, int[] arr) {
        Arrays.sort(arr);
        int good = 0;
        for (int i = 0; i < N; i++ ) {
            int L = 0, R = N - 1;
            int target = arr[i];

            while (L < R) {
                if (L == i) {
                    L++;
                    continue;
                }
                if (R == i) {
                    R--;
                    continue;
                }

                int sum = arr[L] + arr[R];
                if (sum < target) L++;
                else if (sum > target) R--;
                else {
                    good++;
                    break;
                }
            }
        }

       return good;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, arr));
    }
}
