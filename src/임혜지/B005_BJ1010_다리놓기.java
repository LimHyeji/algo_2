package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B005_BJ1010_다리놓기 {
	static int t;// 테스트 케이스 수
	static int n, m;// 서쪽 개수, 동쪽 개수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력받을 준비

		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력

		for (int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(in.readLine());// 한 줄 입력

			n = Integer.parseInt(st.nextToken());// 서쪽 개수 입력
			m = Integer.parseInt(st.nextToken());// 동쪽 개수 입력

			System.out.println(com(m, n));// 각각의 결과(다리 놓을 수 있는 경우의 수) 출력
		}
	}

	static int com(int n, int k) {// 조합
		int[][] temp = new int[n + 1][n + 1];// n번째에 대해 계산하므로, n+1의 사이즈로 배열 생성

		for (int i = 0; i <= n; i++) {// 모든 숫자에 대해
			for (int j = 0; j <= Math.min(i, k); j++) {
				if (j == 0 || j == i)// 양끝 값의 경우
					temp[i][j] = 1;// 조합에서의 값이 1
				else
					temp[i][j] = temp[i - 1][j - 1] + temp[i - 1][j];// 조합 점화식
			}
		}
		return temp[n][k];// 조합 점화식을 통해 누적된 결과 리턴
	}
}
