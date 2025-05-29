import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static void solution(int N, String A, String B) {
        boolean[] bA = new boolean[N];
        boolean[] bB = new boolean[N];

        for (int i = 0; i < N; i++) {
            bA[i] = A.charAt(i) == '1';
            bB[i] = B.charAt(i) == '1';
        }

        // 0번 스위치를 눌렀을 경우
        int zeroCnt = 0;
        boolean[] zeroTemp = bA.clone();
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                zeroTemp[i] = !zeroTemp[i];
                zeroTemp[i+1] = !zeroTemp[i + 1];
                zeroCnt++;
            }
            else {
                if (zeroTemp[i - 1] != bB[i - 1]) {
                    zeroTemp[i - 1] = !zeroTemp[i - 1];
                    zeroTemp[i] = !zeroTemp[i];
                    if (i != N - 1)
                        zeroTemp[i + 1] = !zeroTemp[i + 1];
                    zeroCnt++;
                }
            }
        }

        if (!Arrays.equals(bB, zeroTemp))
            zeroCnt = -1;

        // 0번 스위치를 누르지 않았을 경우
        int cnt = 0;
        boolean[] temp = bA.clone();
        for (int i = 1; i < N; i++) {
            if (temp[i - 1] != bB[i - 1]) {
                temp[i - 1] = !temp[i - 1];
                temp[i] = !temp[i];
                if (i != N - 1)
                    temp[i + 1] = !temp[i + 1];
                cnt++;
            }
        }

        if (!Arrays.equals(bB, temp))
            cnt = -1;

        System.out.println(zeroCnt == -1 ? cnt : (cnt == -1 ? zeroCnt : Math.min(zeroCnt, cnt)));
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String A = br.readLine();
        String B = br.readLine();

        solution(N, A, B);
    }
}
