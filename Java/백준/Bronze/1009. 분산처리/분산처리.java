import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			int result = 1;
			for(int j=0;j<b;j++) result=(result*a)%10;
			
			if(result==0) System.out.println("10");
			else System.out.println(result);
		}
	}
}