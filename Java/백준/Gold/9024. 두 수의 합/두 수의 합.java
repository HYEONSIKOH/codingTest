import java.util.*;
import java.io.*;

public class Main {
    private static int solution(int n, int k, int[] arr) {
        Arrays.sort(arr);

        int L = 0, R = n - 1;
        int ans = 1, min = Integer.MAX_VALUE;
        while (L < R) {
            int sum = arr[L] + arr[R];
            int absKMin = Math.abs(sum - k);

            if (absKMin < min) {
                min = absKMin;
                ans = 1;
            } else if (absKMin == min) ans++;

            if (sum > k) R--;
            else if (sum < k) L++;
            else {
                R--;
                L++;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cnt = Integer.parseInt(br.readLine());
        while(cnt-- > 0) {
            System.gc();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            bw.write(solution(n, k, arr) + "\n");
        }

        bw.flush();
        bw.close();
    }
}