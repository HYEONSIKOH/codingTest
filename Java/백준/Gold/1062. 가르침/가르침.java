import java.util.*;
import java.io.*;

import static java.lang.Integer.parseInt;

public class Main {
    private static int ans = -1;

    private static int solution(int N, int K, String[] words){
        if (K < 5) return 0;

        int[] masks = new int[N];
        for (int i = 0; i < N; i++) {
            for (char c : words[i].toCharArray())
                masks[i] |= 1 << (c - 'a');
        }

        int base = 0;
        for (char c : "antic".toCharArray())
            base |= 1 << (c - 'a');

        backtrack(0, 0, K - 5, base, masks);

        return ans;
    }

    private static void backtrack(int idx, int cnt, int K, int bitmask, int[] words) {
        if (cnt > K) return;

        ans = Math.max(ans, cntValid(bitmask, words));

        for (int i = idx; i < 26; i++) {
            if ((bitmask & (1 << i)) == 0)
                backtrack(i + 1, cnt + 1, K, bitmask | (1 << i), words);
        }
    }

    private static int cntValid(int bitmask, int[] words) {
        int cnt = 0;

        for (int word : words) {
            if ((bitmask & word) == word) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String[] words = new String[N];
        for (int i = 0; i < N; i++)
            words[i] = br.readLine();

        bw.write(String.valueOf(solution(N, K, words)));

        // System.gc();
        bw.flush();
        bw.close();
    }
}