import java.util.*;
import java.io.*;

public class Main {
	private static Student[] arr;
	private static int N;
	
	private static class Student implements Comparable<Student> {
		String name;
		int d, m, y;
		
		Student(String n, int d, int m, int y) {
			this.name = n;
			this.d = d;
			this.m = m;
			this.y = y;
		}
		
		@Override
		public int compareTo(Student s) {
			if (this.y != s.y) return this.y - s.y;
            if (this.m != s.m) return this.m - s.m;
            return this.d - s.d;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	arr = new Student[N];
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		String name = st.nextToken();
    		int d = Integer.parseInt(st.nextToken());
    		int m = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
    		
    		arr[i] = new Student(name, d, m, y);
    	}

    	System.out.print(solution());
    }
    
    private static String solution() {
    	Arrays.sort(arr);
    	
    	return arr[N-1].name + "\n" + arr[0].name;
    }
}