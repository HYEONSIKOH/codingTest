import java.util.*;
import java.io.*;

public class Main {
    private static int solution(int N, int[] arr) {
        Arrays.sort(arr);

        int size = 0;
        for (int i = N; i > 0; i--)
            size += i;

        int[] sum = new int[size];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N ; j++)
                sum[idx++] = arr[i] + arr[j];
        }

        Arrays.sort(sum);

        for (int i = N-1; i > 0; i--) {
            for (int j = 0; j < i; j++)
                if (Arrays.binarySearch(sum, arr[i] - arr[j]) >= 0)
                    return arr[i];
        }

        return arr[2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(in.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(in.readLine());

        bw.write(String.valueOf(solution(N, arr)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}