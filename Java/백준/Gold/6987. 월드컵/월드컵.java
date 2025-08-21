import java.io.*;
import java.util.*;

public class Main {
    private static int[][] arr = new int[6][3];
    private static int[][] matches = new int[15][2];
    
    private static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 6; j++) {
                matches[idx][0] = i;
                matches[idx][1] = j;
                idx++;
            }
        }

        for (int test = 0; test < 4; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++)
                    arr[i][j] = Integer.parseInt(st.nextToken());
            }

            isPossible = false;
            if (isValidInput()) backtrack(0);
            
            System.out.print((isPossible ? 1 : 0) + " ");
        }
    }

    private static boolean isValidInput() {
        for (int i = 0; i < 6; i++) {
            int total = arr[i][0] + arr[i][1] + arr[i][2];
            if (total != 5) return false;
        }
        return true;
    }

    private static void backtrack(int gameIdx) {
        if (isPossible) return;
        if (gameIdx == 15) {
            isPossible = true;
            return;
        }

        int team1 = matches[gameIdx][0];
        int team2 = matches[gameIdx][1];

        // team1 승, team2 패
        if (arr[team1][0] > 0 && arr[team2][2] > 0) {
            arr[team1][0]--;
            arr[team2][2]--;
            backtrack(gameIdx + 1);
            arr[team1][0]++;
            arr[team2][2]++;
        }

        // 무승부
        if (arr[team1][1] > 0 && arr[team2][1] > 0) {
            arr[team1][1]--;
            arr[team2][1]--;
            backtrack(gameIdx + 1);
            arr[team1][1]++;
            arr[team2][1]++;
        }

        // team1 패, team2 승
        if (arr[team1][2] > 0 && arr[team2][0] > 0) {
            arr[team1][2]--;
            arr[team2][0]--;
            backtrack(gameIdx + 1);
            arr[team1][2]++;
            arr[team2][0]++;
        }
    }
}