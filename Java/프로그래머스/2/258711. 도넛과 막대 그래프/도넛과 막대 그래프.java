import java.util.*;

class Solution {
    List<List<Integer>> in;
    List<List<Integer>> out;
    boolean[] visited;
    int[] ans = new int[4];

    public void init(int size) {
        visited = new boolean[size];
        in = new ArrayList<>();
        out = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            in.add(new ArrayList<>());
            out.add(new ArrayList<>());
        }

    }

    public int[] solution(int[][] edges) {
        int size = edges.length;
        int max = -1;

        // 가장 큰 노드번호
        for (int i = 0; i < size; i++)
            max = Math.max(max, Math.max(edges[i][0], edges[i][1]));

        init(max + 1);

        for (int i = 0; i < size; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            in.get(b).add(a);
            out.get(a).add(b);
            visited[a] = true;
            visited[b] = true;
        }

        // 정점 구하기
        for (int i = 1; i <= max; i++) {
            int outCnt = out.get(i).size();
            int inCnt = in.get(i).size();

            if (outCnt >= 2 && inCnt == 0 && visited[i]) {
                ans[0] = i;
                break;
            }
        }

        // in 배열에서 정점 제거
        for (int i = 1; i <= max; i++) {
            // 정점이 존재하는 경우
            if (in.get(i).contains(ans[0])) {
                in.get(i).remove((Integer) ans[0]);
            }
        }

        // 총 그래프 개수
        int graphCnt = out.get(ans[0]).size();

        for (int i = 1; i <= max; i++) {
            int outCnt = out.get(i).size();
            int inCnt = in.get(i).size();

            // 막대 구하기
            if (outCnt == 0 && visited[i]) ans[2]++;

            // 8자 모양 그래프 수 구하기
            if (outCnt == 2 && inCnt == outCnt && visited[i]) ans[3]++;
        }

        ans[1] = graphCnt - ans[2] - ans[3];

        return ans;
    }
}