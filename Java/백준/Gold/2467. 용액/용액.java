import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void solution(int[] arr) {
        int N = arr.length;
        int rightAns = N - 1, leftAns = 0;
        int L = 0, R = N - 1;
        int temp = arr[R] + arr[L];

        while (L < R) {
            int sum = arr[R] + arr[L];
            if (Math.abs(sum) <= Math.abs(temp)) {
                leftAns = L;
                rightAns = R;
                temp = sum;
            }

            if (sum < 0) L++;
            else if (sum > 0) R--;
            else {
                leftAns = L;
                rightAns = R;
                break;
            }
        }

        System.out.print(arr[leftAns] + " " + arr[rightAns]);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        solution(arr);
    }
}
