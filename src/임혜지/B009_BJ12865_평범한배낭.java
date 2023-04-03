package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B009_BJ12865_평범한배낭 {
	static int n, k;// 물건 개수, 무게 제한
	static int[] w;// 무게
	static int[] v;// 가치
	static int[][] dp;// 가치합 최댓값 계산할 dp 배열

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 한 줄 입력

		n = Integer.parseInt(st.nextToken());// 물건 개수 입력
		k = Integer.parseInt(st.nextToken());// 무게 제한 입력

		w = new int[n + 1]; // 무게 배열 생성
		v = new int[n + 1]; // 가치 배열 생성(dp를 위해 사이즈+1)
		dp = new int[n + 1][k + 1];// dp 배열 생성(사이즈+1)

		for (int i = 1; i <= n; i++) {// n개의
			st = new StringTokenizer(in.readLine());// 줄 입력
			w[i] = Integer.parseInt(st.nextToken());// 무게 입력
			v[i] = Integer.parseInt(st.nextToken());// 가치 입력
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if (w[i] > j) {// 무게를 초과해 담을 수 없는 경우
					dp[i][j] = dp[i - 1][j];// 그대로 저장
				} else {// 담을 수 있는 경우
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);// 새로 담는 것을 추가한 값과 기존 값 비교해 저장
				}
			}
		}
		System.out.println(dp[n][k]);// 최종적인 가치합 최대 출력
	}
}
