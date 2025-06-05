import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static void solutuion(String str, String explosion) {
        char[] cs = str.toCharArray();
        char[] es = explosion.toCharArray();
        int eLen = explosion.length();

        Stack<Character> s = new Stack<>();

        for (char c : cs) {
            s.push(c);
            
            if (s.size() >= eLen) {
                boolean boom = true;
                
                for (int i = 0; i < eLen; i++) {
                    if (s.get(s.size() - 1 - i) != es[eLen - 1 - i]) {
                        boom = false;
                        break;
                    }
                }

                if (boom) {
                    for (int i = 0; i < eLen; i++) {
                        s.pop();
                    }
                }
            }
        }

        /**
         * String에 문자를 추가하게 되면, 매번 새로운 String 객체가 생성 -> MemoryOverFlow
         * StringBuilder를 사용 -> StringBuilder는 내부적으로 char[]를 사용하여 메모리 효율적
         */
        if (s.isEmpty()) {
            System.out.print("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (char c : s) sb.append(c);
            System.out.print(sb);
        }
    }

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        String explosion = br.readLine();

        solutuion(str, explosion);
    }
}