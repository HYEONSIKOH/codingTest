import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String checkX(char[] s) {
        for (int i = 0; i < 3; i++) {
            // 가로
            if (s[3 * i] == 'X' && s[3 * i + 1] == 'X' && s[3 * i + 2] == 'X') return "win";

            // 세로
            if (s[i] == 'X' && s[i + 3] == 'X' && s[i + 6] == 'X') return "win";
        }

        // 대각선
        if (s[0] == 'X' && s[4] == 'X' && s[8] == 'X') return "win";
        if (s[2] == 'X' && s[4] == 'X' && s[6] == 'X') return "win";

        return "lose";
    }

    private static String checkO(char[] s) {
        for (int i = 0; i < 3; i++) {
            // 가로
            if (s[3 * i] == 'O' && s[3 * i + 1] == 'O' && s[3 * i + 2] == 'O') return "win";

            // 세로
            if (s[i] == 'O' && s[i + 3] == 'O' && s[i + 6] == 'O') return "win";
        }

        // 대각선
        if (s[0] == 'O' && s[4] == 'O' && s[8] == 'O') return "win";
        if (s[2] == 'O' && s[4] == 'O' && s[6] == 'O') return "win";

        return "lose";
    }

    public static void solution(String str) {
        int xCnt = 0, oCnt = 0;
        char[] s = str.toCharArray();
        String xRes = checkX(s);
        String oRes = checkO(s);
        for (char c : s) {
            if (c == 'X') xCnt++;
            else if (c == 'O') oCnt++;
        }

        // X와 O의 개수가 잘못된 경우
        if (xCnt < oCnt || xCnt > oCnt + 1) {
            System.out.println("invalid");
            return;
        }

        // X가 이겼을 때
        if (xRes.equals("win") && oRes.equals("lose")) {
            if (xCnt - 1 == oCnt) {
                System.out.println("valid");
                return;
            }
        }

        // O가 이겼을 때
        if (xRes.equals("lose") && oRes.equals("win")) {
            if (xCnt == oCnt) {
                System.out.println("valid");
                return;
            }
        }

        // 둘 다 졌을 때 (무승부는 오직 모든 칸이 채워졌을 때만 유효)
        if (xRes.equals("lose") && oRes.equals("lose")) {
            if (xCnt + oCnt == 9) {
                System.out.println("valid");
                return;
            }
        }

        System.out.println("invalid");
    }

    public static void main(String[] args) throws IOException {
        String str = br.readLine();

        while (!str.equals("end")) {
            solution(str);
            str = br.readLine();
        }
    }
}
