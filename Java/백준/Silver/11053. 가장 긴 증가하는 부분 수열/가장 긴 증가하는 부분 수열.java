import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int solution(int N, int[] arr) {
        int[] temp = new int[N];
        temp[0] = arr[0];
        int idx = 1;

        for (int i = 1; i < N; i++) {
            if (arr[i] > temp[idx - 1]) temp[idx++] = arr[i];
            else {
                int pos = Arrays.binarySearch(temp, 0, idx, arr[i]);
                if (pos < 0) pos = -(pos + 1);
                temp[pos] = arr[i];
            }
        }

        return idx;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, arr));
    }
}