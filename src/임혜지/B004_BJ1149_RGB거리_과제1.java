package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B004_BJ1149_RGB거리_과제1 {
	static int n;// 집의 수
	static int[][] arr;// 최소값 메모이제이션할 이차원 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력받을 준비

		n = Integer.parseInt(in.readLine());// 집의 수 입력
		arr = new int[n + 1][3];// 집 n개에 대해 3개의 색

		for (int i = 1; i <= n; i++) {// i번째 집에 대해
			st = new StringTokenizer(in.readLine());// 한 줄 입력
			int r = Integer.parseInt(st.nextToken());// 색1 입력
			int g = Integer.parseInt(st.nextToken());// 색2 입력
			int b = Integer.parseInt(st.nextToken());// 색3 입력

			arr[i][0] = Math.min(arr[i - 1][1], arr[i - 1][2]) + r;// 이전까지의 색2, 색3 합들 중 최소값에 색1 더하기
			arr[i][1] = Math.min(arr[i - 1][0], arr[i - 1][2]) + g;// 이전까지의 색1, 색3 합들 중 최소값에 색2 더하기
			arr[i][2] = Math.min(arr[i - 1][0], arr[i - 1][1]) + b;// 이전까지의 색1, 색2 합들 중 최소값에 색3 더하기
		}

		System.out.println(Math.min(arr[n][0], Math.min(arr[n][1], arr[n][2])));// 누적된 합들 중 최소값 출력
	}
}
