import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void solution(String W, int K) {
        int min = Integer.MAX_VALUE;
        int max = -1;

        // 각 알파벳마다 등장 위치 저장
        List<Integer>[] positions = new ArrayList[26];
        for (int i = 0; i < 26; i++) positions[i] = new ArrayList<>();

        for (int i = 0; i < W.length(); i++) {
            char c = W.charAt(i);
            positions[c - 'a'].add(i);
        }

        // 각 알파벳마다 슬라이딩 윈도우 적용
        for (int i = 0; i < 26; i++) {
            List<Integer> list = positions[i];
            if (list.size() < K) continue;

            for (int j = 0; j <= list.size() - K; j++) {
                int len = list.get(j + K - 1) - list.get(j) + 1;
                min = Math.min(min, len);
                max = Math.max(max, len);
            }
        }

        System.out.println(max == -1 ? -1 : min + " " + max);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                System.out.println("1 1");
                continue;
            }

            solution(W, K);
        }
    }
}