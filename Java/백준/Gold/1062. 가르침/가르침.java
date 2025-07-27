import java.util.*;
import java.io.*;

import static java.lang.Integer.parseInt;

public class Main {
    private static boolean[] visited = new boolean[26];
    private static int ans = -1;

    private static int solution(int N, int K, String[] words){
        if (K < 5) return 0;

        String[] newWards = new String[N];
        for (int i = 0; i < N; i++)
            newWards[i] = words[i].substring(4, words[i].length() - 4);

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        backtrack(-1, 0, K - 5, newWards);

        return ans;
    }

    private static void backtrack(int idx, int cnt, int K, String[] words) {
        if (cnt > K) return;

        ans = Math.max(ans, isValid(words));

        for (int i = idx + 1; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtrack(i, cnt + 1, K, words);
                visited[i] = false;
            }
        }
    }

    private static int isValid(String[] words) {
        int cnt = 0;

        for (String word : words) {
            boolean isPossible = true;

            for (char c : word.toCharArray()) {
                if (!visited[c - 'a']) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) cnt++;
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