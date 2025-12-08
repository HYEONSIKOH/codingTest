import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int N = readInt();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= 9; i++)
            sb.append(N).append(" * ").append(i).append(" = ").append(N * i).append('\n');
        
        System.out.print(sb);
    }
    
    private static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}