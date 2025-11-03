import java.util.*;
import java.io.*;

public class Main {
    private static int N;

    public static void main(String[] args) throws IOException {
        N = readInt();

        System.out.print(solution());
    }

    private static long solution() {
        if (N < 9) return N;

        long cnt = 0;
        int start = 1, digit = 1;
        while (true) {
            if (start * 10 <= N)
                cnt += (start * 10L - start) * digit++;
            else if (start <= N)
                return (long) (N - start + 1) * digit + cnt;

            start *= 10;
        }
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}