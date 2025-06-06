import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static void solutuion(String str, String explosion) {
        char[] cs = str.toCharArray();
        char[] es = explosion.toCharArray();
        int eLen = explosion.length();

        StringBuilder s = new StringBuilder();

        for (char c : cs) {
            s.append(c);

            if (s.length() >= eLen) {
                boolean isBoom = true;

                for (int i = 0; i < eLen; i++) {
                    if (s.charAt(s.length() - 1 - i) != es[eLen - 1 - i]) {
                        isBoom = false;
                        break;
                    }
                }

                if (isBoom) {
                    s.delete(s.length() - eLen, s.length());
                }
            }
        }

        /**
         * String에 문자를 추가하게 되면, 매번 새로운 String 객체가 생성 -> MemoryOverFlow
         * StringBuilder를 사용 -> StringBuilder는 내부적으로 char[]를 사용하여 메모리 효율적
         */
        if (s.length() == 0) {
            System.out.print("FRULA");
        } else {
            System.out.print(s);
        }
    }

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        String explosion = br.readLine();

        solutuion(str, explosion);
    }
}