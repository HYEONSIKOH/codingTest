import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0) arr[idx++] = num;
            else idx--;
        }

        int sum = 0;
        for (int i = 0; i < idx; i++)
            sum += arr[i];

        System.out.println(sum);
    }
}