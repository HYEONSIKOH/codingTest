import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void solution(int N, int[] arr) {
        Stack<int[]> s = new Stack<>();

        for (int i = 0; i < N; i++) {
            int h = arr[i];

            while (!s.isEmpty()){
                if (h < s.peek()[1]) {
                    System.out.print(s.peek()[0] + " ");
                    break;
                }
                s.pop();
            }

            if (s.isEmpty()) System.out.print(0 + " ");

            s.push(new int[]{i + 1, h});
        }
    }

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        solution(N, arr);
    }
}
