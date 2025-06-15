import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int R, C;

    private static Queue<int[]> qj = new ArrayDeque<>();
    private static Queue<int[]> qf = new ArrayDeque<>();

    private static int[][] jihoon;
    private static int[][] fire;

    private static boolean range(int x, int y) {
        return x > -1 && y > -1 && x < R  && y < C;
    }

    private static void solution(char[][] maze) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while(!qf.isEmpty()) {
            int[] pf = qf.poll();

            for (int i = 0; i < 4; i++) {
                int nX = pf[0] + dx[i];
                int nY = pf[1] + dy[i];

                if(range(nX, nY) && maze[nX][nY] != '#' && fire[nX][nY] == 0) {
                    qf.add(new int[]{nX, nY});
                    fire[nX][nY] = fire[pf[0]][pf[1]] + 1;
                }
            }
        }

        while(!qj.isEmpty()) {
            int x = qj.peek()[0];
            int y = qj.peek()[1];
            qj.poll();

            for (int i = 0; i < 4; i++) {
                int nX = x + dx[i];
                int nY = y + dy[i];

                if (!range(nX, nY)) {
                    System.out.println(jihoon[x][y]);
                    return;
                }

                if(maze[nX][nY] != '#' && jihoon[nX][nY] == 0 && (fire[nX][nY] > jihoon[x][y] + 1 || fire[nX][nY] == 0)) {
                    qj.add(new int[]{nX, nY});
                    jihoon[nX][nY] = jihoon[x][y] + 1;
                }
            }
        }

        System.out.print("IMPOSSIBLE");
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        jihoon = new int[R][C];
        fire = new int[R][C];

        char[][] maze = new char[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                maze[r][c] = str.charAt(c);
                if(maze[r][c] == 'J') {
                    jihoon[r][c] = 1;
                    qj.add(new int[]{r, c});
                } else if (maze[r][c] == 'F') {
                    fire[r][c] = 1;
                    qf.add(new int[]{r, c});
                }
            }
        }

        solution(maze);
    }
}