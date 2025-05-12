package baekjoon.Implementation.b3758;

import java.util.*;
import java.io.*;

// [BaekJoon] 3758: KCPC
// https://www.acmicpc.net/problem/3758
// 2025.05.12
public class Main{
    static class Team {
        int[] scoreList;
        int id;
        int submitNum = 0;
        int lastSubmit;
        int totalScore = 0;
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int[] answer;
    private static int T, n, k, t, m;
    private static Team[] teams;

    public static void solution() throws IOException {
        // 팀 ID i, 문제 번호 j, 획득한 점수 s
        int i = 0, j, s;
        for (int idx = 0; idx < m; idx++) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            j = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());

            if (teams[i-1] == null) {
                teams[i-1] = new Team();
                teams[i-1].scoreList = new int[k + 1];
                teams[i-1].id = i;
            }
            teams[i-1].submitNum++;
            teams[i-1].lastSubmit = idx;
            teams[i-1].scoreList[j] = Math.max(teams[i-1].scoreList[j], s);
        }

        for (int idx = 0; idx < n; idx++) {
            for (int J = 1; J <= k; J++) {
                teams[idx].totalScore += teams[idx].scoreList[J];
            }
        }

        Arrays.sort(teams, (o1, o2) -> {
            if (o1.totalScore == o2.totalScore) {
                if (o1.submitNum == o2.submitNum) { return o1.lastSubmit - o2.lastSubmit; }
                return o1.submitNum - o2.submitNum;
            }
            return o2.totalScore - o1.totalScore;
        });

        for (int grade = 1; grade <= n; grade++) {
            if (teams[grade - 1].id == t) {
                System.out.println(grade);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 팀의 개수 n, 문제의 개수 k, 당신 팀의 ID t, 로그 엔트리의 개수 m
        T = Integer.parseInt(br.readLine());
        answer = new int[T];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            teams = new Team[n];

            solution();
        }
    }
}
