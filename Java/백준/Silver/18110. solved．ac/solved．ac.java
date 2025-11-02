import java.util.*;
import java.io.*;

public class Main {
    private static int[] arr;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = readInt();
        arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = readInt();

        System.out.print(solution());
    }

    private static int solution() {
        Arrays.sort(arr);
        int numToTrim = getTrimmedCount();

        double sum = 0;
        for (int i = numToTrim; i < N - numToTrim; i++)
            sum += arr[i];

        return (int) Math.round(sum / (N - 2 * numToTrim));
    }

    private static int getTrimmedCount() {
        double num = N * 0.15;

        return (int) Math.round(num);
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}