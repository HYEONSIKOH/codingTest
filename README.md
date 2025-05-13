# codingTest
### [코딩테스트] 하루 1문제 풀기 도전! <br>
-> BaekJoon Hub를 이용한 자동 업로드
<br><br>

### BaekJoon (백준)
URL: https://www.acmicpc.net/

- Main.java로 제출해야 함.
~~~ java
// Main 입력 시, 대문자 M으로 시작해라. 제발.
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
~~~
<br>

- import문은 C++처럼 내가 수기로 작성
~~~ java
import java.util.*; // 자료구조와 유틸리티 클래스들이 포함된 패키지
import java.io.*;   // 입출력 스트림 관련 클래스들이 포함 (프로그래머스는 불필요)
~~~
<br>

- 입력(input) 시, Scanner 대신 BufferedReader을 사용
~~~ java
// 어짜피 코딩 테스트땐 입력을 받지않음
// 그래서 얜 보고 해도 무방함.
// 맞음 내가 보고 배낄려고 만들어놓은 문서임
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str = "";
	    str = br.readLine(); // readLine을 사용하여 한 줄씩 읽어오기.
	    
	    // 기본적으로 문자열로 읽음
	    // 그래서 Integer.parseInt()로 바꿔줘야댐
	    int a = "";
	    a = Integer.parseInt(br.readLine());
	    
	    // 공백으로 한 글자씩 구분되는 입력의 경우 StringTokenizer로 공백을 제외
	    int [] targetArray = new int[100];
	    st = new StringTokenizer(sc.readLine()," ");
	    for(int i = 0 ; i < M ; i++){
	        targetArray[i] = Integer.parseInt(st.nextToken());
	    }
	}
}
~~~
<br><br>

### Programmers (프로그래머스)
URL: https://programmers.co.kr/

- main 문이 없음 <br>
-> 이런식으로 제출
~~~ java
class Solution {
    public String[] solution(String s, String[] query) {
    
    }
}
~~~
<br>
- 열심히 잘 풀면 댐 ^^