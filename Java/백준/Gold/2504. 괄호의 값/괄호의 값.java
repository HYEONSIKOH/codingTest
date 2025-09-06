import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chs = br.readLine().toCharArray();

        System.out.print(solution(chs));
    }

    private static int solution(char[] chs) {
        int ans = 0;
        Deque<Integer> s = new ArrayDeque<>();
        for (char ch : chs) {
            int num = 1;
            if (ch == '(') s.push(-1);
            else if (ch == '[') s.push(-2);
            else {
                if (ch == ')') {
                    while (!s.isEmpty()) {
                        if (s.peek() == -1) {
                            s.pop();
                            s.push(num * 2);
                            break;
                        }
                        if (s.peek() == -2) return 0;
                        else {
                            if (num == 1) num = s.pop();
                            else num += s.pop();
                        }
                    }
                    
                    if (s.isEmpty()) return 0;
                }
                if (ch == ']') {
                    while (!s.isEmpty()) {
                        if (s.peek() == -2) {
                            s.pop();
                            s.push(num * 3);
                            break;
                        }
                        else if (s.peek() == -1) return 0;
                        else {
                            if (num == 1) num = s.pop();
                            else num += s.pop();
                        }
                    }

                    if (s.isEmpty()) return 0;
                }
            }
        }

        while (!s.isEmpty()) {
            if (s.peek() == -1 || s.peek() == -2) return 0;
            ans += s.pop();
        }

        return ans;
    }
}