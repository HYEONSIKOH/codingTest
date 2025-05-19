import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void solution(String W, int K) {
        int size = W.length();
        int[] alphabetCnt = new int[26];

        for (char c : W.toCharArray()) alphabetCnt[c - 'a']++;

        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < size; i++) {
            char c = W.charAt(i);
            if (alphabetCnt[c - 'a'] < K) continue;

            int cnt = 1;
            for (int j = i + 1; j < size; j++) {
                if (c == W.charAt(j)) cnt++;
                if (cnt == K) {
                    min = Math.min(min, j - i + 1);
                    max = Math.max(max, j - i + 1);
                    break;
                }
            }
        }

        System.out.println(max == -1 ? -1 : min + " " + max);
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if(K == 1) {
                System.out.println("1 1");
                continue;
            }

            solution(W, K);
        }
    }
}