import java.util.*;
import java.io.*;

public class Main {
    private static int[] arr;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

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
}