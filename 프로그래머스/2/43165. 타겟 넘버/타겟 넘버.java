import java.util.*;

class Solution {
    int answer = 0;
    int[] n;
    List<List<Integer>> graph;

    public void init (int size) {
        answer = 0;
        graph = new ArrayList<>();

        for (int i = 0; i < size; i++) { graph.add(new ArrayList<>()); }
    }

    public void dfs (int idx, int target, int sum) {
        sum += (idx % 2 == 0) ? n[idx / 2] : -n[idx / 2];

        if (graph.get(idx).isEmpty()) {
            if (sum == target) answer++;
            return;
        }

        for (int i = 0; i < 2; i++) {
            int nextIdx = graph.get(idx).get(i);
            dfs(nextIdx, target, sum);
        }
    }

    public int solution(int[] numbers, int target) {
        int len = numbers.length;
        n = numbers;
        this.init(len * 2);

        for (int i = 0; i < len - 1; i++) {
            int idx = i * 2;
            int nextIdx = (i + 1) * 2;

            graph.get(idx).add(nextIdx);
            graph.get(idx).add(nextIdx + 1);
            graph.get(idx + 1).add(nextIdx);
            graph.get(idx + 1).add(nextIdx + 1);
        }

        dfs(0, target, 0);
        dfs(1, target, 0);

        return answer;
    }
}