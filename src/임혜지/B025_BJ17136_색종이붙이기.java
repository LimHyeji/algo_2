package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B025_BJ17136_색종이붙이기 {
	static int[][] paper;// 색종이 배열
	static int res;// 최소 색종이 수

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력받을 준비

		paper = new int[10][10];// 색종이 배열 생성
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(in.readLine());// 한 줄 입력
			for (int j = 0; j < 10; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());// 색종이 입력
			}
		}

		res = Integer.MAX_VALUE;// 최소값 출력 위한 초기화
		sol(new int[6], 0);// 각 색종이 개수와 depth 넘기는 dfs 호출
		System.out.println(res == Integer.MAX_VALUE ? -1 : res);// 최소값 초기화가 안될 경우 -1 출력
	}

	static void sol(int[] cnt, int depth) {// dfs 메소드
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (paper[i][j] == 1) {// 1이라면
					for (int k = 1; k <= 5; k++) {// 사이즈 1부터 5까지
						if (check(i, j, k) && cnt[k] < 5) {// 그 사이즈의 색종이를 깔 수 있으면
							update(i, j, k);// 색종이 깔고
							cnt[k]++;// 해당 색종이 1개 사용
							sol(cnt, depth + 1);// 다음 1 찾기
							cnt[k]--;// 해당 색종이 1개 사용한 것 해제
							delete(i, j, k);// 색종이 깔기 해제
						}
					}
					return;// 1이 있음에도 5종류 모두 깔기 못할 경우, 색종이 깔기가 불가능하므로 종료
				}
			}
		}
		res = Math.min(res, depth);// 최소값 업데이트
		return;
	}

	static boolean check(int row, int col, int size) {// 해당 위치에서 해당 사이즈로 색종이 깔기 가능한지 체크하는 메소드
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (i >= 10 || j >= 10 || paper[i][j] == 0) {// 인덱스를 벗어나는 경우도 체크
					return false;
				}
			}
		}
		return true;
	}

	static void update(int row, int col, int size) {// 해당 위치에 해당 사이즈로 색종이 깔기(0으로 만듦)
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				paper[i][j] = 0;
			}
		}
	}

	static void delete(int row, int col, int size) {// 해당 위치에 해당 사이즈로 깔았던 색종이 다시 걷기(1로 만듦)
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				paper[i][j] = 1;
			}
		}
	}

}
