import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int score = Integer.parseInt(br.readLine());
        
        System.out.print(solution(score));
    }
    
    private static char solution(int score) {
        switch (score / 10) {
            case 10:
            case 9:  return 'A';
            case 8:  return 'B';
            case 7:  return 'C';
            case 6:  return 'D';
            
            default: return 'F';
        }
    }
}