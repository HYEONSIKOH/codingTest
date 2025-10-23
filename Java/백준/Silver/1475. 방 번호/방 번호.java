import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        int[] visited = new int[9];
        int MAX = -1;

        for (char c : input) {
            int num = c - '0';
            if (num == 9) num = 6;
            visited[num]++;
        }

        if (visited[6] % 2 == 0) visited[6] /= 2;
        else visited[6] = (visited[6] / 2) + 1;

        for (int i = 0; i < 9; i++)
            MAX = Math.max(MAX, visited[i]);

        System.out.print(MAX);
    }
}