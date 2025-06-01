import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // Peek보다 Y가 높이가 낮을 때
            while (!s.isEmpty() && s.peek() > y) {
                ans++;
                s.pop();
            }

            // Peek이랑 Y가 높이가 같을 때, 넣을 필요가 없음
            if (!s.isEmpty() && s.peek() == y)
                continue;

            // Y가 높다면, 최고 높이로 갱신
            s.push(y);
        }

        while(!s.isEmpty())
            if (s.pop() != 0) ans++;

        System.out.print(ans);
    }
}
