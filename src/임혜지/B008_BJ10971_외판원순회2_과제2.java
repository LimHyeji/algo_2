package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B008_BJ10971_외판원순회2_과제2 {
	static int n;// 도시의 수
	static int[][] w;// 비용 행렬
	static boolean[] isSel;// 순열 만들 때 사용할 선택 배열
	static int[] order;// 순열로 조합할 여행 순서 배열
	static int res;// 순회 최소비용

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력 받을 준비

		n = Integer.parseInt(in.readLine());// 도시의 수 입력
		w = new int[n][n];// 비용 행렬 생성

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());// 한 줄 입력
			for (int j = 0; j < n; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());// 비용 행렬 입력
			}
		}

		isSel = new boolean[n];// 선택 배열 생성
		order = new int[n];// 순서 배열 생성
		res = Integer.MAX_VALUE;// 순회 최소비용 구하기 위한 초기화
		sol(0);// 순열 메소드 -> 최소비용 업데이트 메소드 호출
		System.out.println(res);// 순회 최소비용 출력
	}

	static void sol(int cnt) {
		if (cnt == n) {// n개의 숫자를 모두 골랐을 때
			cal(order);// 최소비용 계산 메소드 호출
			return;// 재귀 종료
		}
		for (int i = 0; i < n; i++) {
			if (isSel[i])// 선택했다면
				continue;// 건너뛰고
			order[cnt] = i;// 선택하지 않았다면, i 선택 후
			isSel[i] = true;// 선택 체크
			sol(cnt + 1);// 다음 숫자 고르기
			isSel[i] = false;// 재귀 후 선택 해제해서 다음 순열 구하기
		}
	}

	static void cal(int[] order) {
		int sum = 0;// 해당 순서로 여행할 때의 비용 합 변수

		for (int i = 0; i < n - 1; i++) {
			if (w[order[i]][order[i + 1]] == 0)// 해당 길이 없다면 여행 불가하므로
				return;// 종료
			sum += w[order[i]][order[i + 1]];// 해당 길이 있다면 비용 더함
		}
		if (w[order[n - 1]][order[0]] == 0)// 되돌아가는 길이 없다면 여행 불가하므로
			return;// 종료
		sum += w[order[n - 1]][order[0]];// 되돌아가는 길이 있다면 비용 더하고
		res = Math.min(res, sum);// 최소비용 업데이트
	}
}
