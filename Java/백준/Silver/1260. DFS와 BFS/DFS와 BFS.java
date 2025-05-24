import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Dfs {
    List<List<Integer>> v = new ArrayList<>();
    List<Boolean> visited = new ArrayList<>();

    public Dfs(int n) {
        for (int i = 0; i <= n; i++) {
            v.add(new ArrayList<>());
            visited.add(false);
        }
    }

    public void addEdge(int x, int y) {
        v.get(x).add(y);
        v.get(y).add(x);
    }
    
     public void sortArray() {
        for (List<Integer> integers : v) {
            integers.sort(null);
        }
    }

    public void search(int x) {
        visited.set(x, true);
        System.out.print(x + " ");

        for (int i = 0; i < v.get(x).size(); i++) {
            int next_node = v.get(x).get(i);
            if (!visited.get(next_node)) {
                search(next_node);
            }
        }
    }
}

class Bfs {
    List<List<Integer>> v = new ArrayList<>();
    List<Boolean> visited = new ArrayList<>();
    Queue<Integer> q = new LinkedList<>();

    public Bfs(int n) {
        for (int i = 0; i <= n; i++) {
            v.add(new ArrayList<>());
            visited.add(false);
        }
    }

    public void addEdge(int x, int y) {
        v.get(x).add(y);
        v.get(y).add(x);
    }


    public void search(int x) {
        for (List<Integer> integers : v) {
            integers.sort(null);
        }
        q.add (x);

        // Queue가 비어있을 때까지 반복
        while(!q.isEmpty()) {
            int node = q.poll();

            if (!visited.get(node)) {
                visited.set(node, true);
                System.out.print(node + " ");
            }

            for (int i = 0; i < v.get(node).size(); i++) {
                int nextNode = v.get(node).get(i);

                if (!visited.get(nextNode)) { q.add(nextNode); }
            }
        }
    }

}

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer cmd = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(cmd.nextToken());
        int N = Integer.parseInt(cmd.nextToken());
        int V = Integer.parseInt(cmd.nextToken());

        Dfs dfs = new Dfs(M);
        Bfs bfs = new Bfs(M);

        for (int i = 0; i < N; i++) {
            StringTokenizer nodes = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(nodes.nextToken());
            int y = Integer.parseInt(nodes.nextToken());

            dfs.addEdge(x, y);
            bfs.addEdge(x, y);
        }
        
        dfs.sortArray();
        dfs.search(V);
        System.out.println();
        bfs.search(V);
    }
}