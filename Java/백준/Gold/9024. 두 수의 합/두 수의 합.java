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
        InputReader in = new InputReader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cnt = in.nextInt();
        while(cnt-- > 0) {
            System.gc();

            int n = in.nextInt();
            int k = in.nextInt();

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = in.nextInt();

            bw.write(solution(n, k, arr) + "\n");
        }

        bw.flush();
        bw.close();
    }

    static class InputReader {
        public byte[] buf;
        public int buf_p, size;

        public InputReader() {
            buf = new byte[1 << 24];
            buf_read();
        }

        public int nextInt() {
            if (buf_p == size) buf_read();
            if (size == -1) return -1;

            int i = 0;
            boolean negative = false;
            if (buf[buf_p] == '-') {
                negative = true;
                if (++buf_p == size) buf_read();
            }

            while (true) {
                if (buf[buf_p] < 48 || buf[buf_p] > 57) {
                    buf_p++;
                    return negative ? -i : i;
                }
                i = 10 * i + buf[buf_p++] - 48;

                if (buf_p == size) buf_read();
            }
        }

        private void buf_read() {
            try {
                size = System.in.read(buf);
                buf_p = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}