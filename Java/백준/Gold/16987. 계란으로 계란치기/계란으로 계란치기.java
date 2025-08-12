import java.io.*;
import java.util.*;

public class Main {
    private static Egg[] eggs;
    private static int ans = 0;

    private static class Egg {
        int strength; // 내구도
        int weight;   // 무게

        public Egg(String strength, String weight) {
            this.weight = Integer.parseInt(weight);
            this.strength = Integer.parseInt(strength);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(st.nextToken(), st.nextToken());
        }

        System.out.println(solution(N));
    }

    private static int solution(int N) {
        for (int i = 1; i < N; i++) {
            // 계란으로 계란치기!
            eggs[0].strength -= eggs[i].weight;
            eggs[i].strength -= eggs[0].weight;

            backtrack(N, 0);

            // 계란 상태 복구
            eggs[0].strength += eggs[i].weight;
            eggs[i].strength += eggs[0].weight;
        }

        return ans;
    }

    private static void backtrack(int N, int idx) {
        // 다음 계란 선택 (계란이 깨진 경우, 다음 계란을 들어야댐)
        do idx++;
        while (idx < N && eggs[idx].strength <= 0);

        // 맨 오른쪽까지 온 경우
        if (idx >= N) {
            int cnt = 0;
            for (Egg egg : eggs) if (egg.strength <= 0) cnt++;

            ans = Math.max(ans, cnt);
            return;
        }

        boolean isBroken = false;
        for (int i = 0; i < N; i++) {
            if (eggs[i].strength <= 0 || i == idx) continue;

            isBroken = true;

            // 계란으로 계란치기!
            eggs[idx].strength -= eggs[i].weight;
            eggs[i].strength -= eggs[idx].weight;

            backtrack(N, idx);

            // 계란 상태 복구
            eggs[idx].strength += eggs[i].weight;
            eggs[i].strength += eggs[idx].weight;

        }

        if (!isBroken) backtrack(N, idx);
    }
}