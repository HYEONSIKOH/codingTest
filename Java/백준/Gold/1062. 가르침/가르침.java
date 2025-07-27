import java.util.*;
import java.io.*;

import static java.lang.Integer.parseInt;

public class Main {
    private static boolean[] visited = new boolean[26];
    private static int bitmask = 0;
    private static int ans = -1;

    private static int solution(int N, int K, String[] words){
        if (K < 5) return 0;

        int[] newWards = new int[N];
        for (int i = 0; i < N; i++) {
            String word = words[i];
            int bitmask = 0;

            for (char c : word.toCharArray()) {
                bitmask |= (1 << (c - 'a'));
            }

            newWards[i] = bitmask;
        }

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        for (int i = 0; i < 26; i++) {
            if (visited[i]) bitmask |= (1 << i);
        }

        backtrack(-1, 0, K - 5, newWards);

        return ans;
    }

    private static void backtrack(int idx, int cnt, int K, int[] words) {
        if (cnt > K) return;

        bitmask = 0;
        for (int i = 0; i < 26; i++) {
            if (visited[i]) bitmask |= (1 << i);
        }

        ans = Math.max(ans, isValid(words));

        for (int i = idx + 1; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i, cnt + 1, K, words);
                visited[i] = false;
            }
        }
    }

    private static int isValid(int[] words) {
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