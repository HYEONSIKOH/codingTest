import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solution();
    }

    private static void solution() {
        //int[] arr = {1, 3, 5, 7, 9, 20, 31, 42, 53, 64, 75, 86, 97};
        boolean[] visited = new boolean[10000];
        for (int i = 0; i < 10000; i++) {
            int n = i;
            int sum = n;

            while (n > 0) {
                sum += n % 10;
                n /= 10;
            }
            if (sum < 10000)
                visited[sum] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            if (!visited[i])
                sb.append(i).append('\n');
        }

        System.out.print(sb);
    }
}