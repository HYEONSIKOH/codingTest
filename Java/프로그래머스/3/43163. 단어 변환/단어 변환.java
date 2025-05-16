class Solution {
    boolean visited[] = new boolean[50];
    int ans = Integer.MAX_VALUE;

    public boolean cntDifferentChar (String s1, String s2) {
        int cnt = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) cnt++;
            if (cnt > 1) return false;
        }

        return true;
    }

    // DFS + 백트래킹
    public void dfs(String current, String target, String[] words, int depth) {
        if (current.equals(target)) {
            ans = Math.min(ans, depth);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && cntDifferentChar(current, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, depth + 1);
                visited[i] = false;
            }
        }
    }


    public int solution(String begin, String target, String[] words) {
        dfs(begin, target, words, 0);
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}