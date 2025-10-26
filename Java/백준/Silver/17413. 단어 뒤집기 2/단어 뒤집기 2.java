import java.util.*;
import java.io.*;

public class Main {
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solution(br.readLine());
        System.out.println(sb);
    }

    private static void solution(String str) {
        Deque<Character> s = new ArrayDeque<>();

        boolean isOpen = false;
        int cnt = 0;
        for (char c : str.toCharArray()) {
            // 1. 열려고 하거나, 열려 있거나
            if ((c == '<' || isOpen) && c != '>') {
                while (!s.isEmpty()) sb.append(s.pop());
                isOpen = true;
                sb.append(c);
            }

            // 2. 닫으려고 할 때
            else if (c == '>') {
                isOpen = false;
                sb.append(c);
            }

            // 3. 공백을 만나는 경우
            else if (c == ' ') {
                while (!s.isEmpty()) sb.append(s.pop());
                sb.append(c);
            }

            // 4. 나머지
            else s.push(c);
        }

        while (!s.isEmpty()) sb.append(s.pop());
    }
}