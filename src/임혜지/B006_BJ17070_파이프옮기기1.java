package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B006_BJ17070_파이프옮기기1 {
	static int n;// 배열 사이즈
	static int arr[][];// 입력받을 배열
	static int width[][];// 가로 방향 경우의 수
	static int height[][];// 세로 방향 경우의 수
	static int diagonal[][];// 대각선 방향 경우의 수
	static int res = 0;// 결과로 출력할 총 경우의 수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력받을 준비

		n = Integer.parseInt(in.readLine());// 배열 사이즈 입력

		arr = new int[n][n];// 입력받을 배열 생성
		width = new int[n][n];// 가로방향 파이프의 경우의 수를 담을 배열 생성
		height = new int[n][n];// 세로방향 파이프의 경우의 수를 담을 배열 생성
		diagonal = new int[n][n];// 대각선방향 파이프의 경우의 수를 담을 배열 생성
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());// 한 줄 입력
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());// 빈칸(0)과 벽(1) 입력
			}
		}

		sol(0, 1, 0);// (0,1)에서 가로방향(0)으로 시작
		System.out.println(res);// 총 경우의 수 출력
	}

	static void sol(int row, int col, int dir) {// 현재의 위치와 파이프 방향
		if (row == n - 1 && col == n - 1) {// 끝까지 도달했다면
			res++;// 경우의 수 1 증가시키고
			return;// 종료
		}

		if (dir == 0) {// 가로
			if (col - 1 >= 0 && arr[row][col] != 1) {// 배열의 인덱스를 벗어나지 않으면서 벽이 아닌 경우
				width[row][col] = width[row][col - 1] + diagonal[row][col - 1];// 가로 방향은 왼쪽 가로와 왼쪽 대각선 경우의 수 더함
				if (col + 1 < n && arr[row][col + 1] != 1) {// 가로 방향으로 나아갈 수 있다면
					sol(row, col + 1, 0);// 재귀 호출
				}
				if (row + 1 < n && col + 1 < n && arr[row + 1][col + 1] != 1 && arr[row][col + 1] != 1
						&& arr[row + 1][col] != 1) {// 대각선 방향으로 나아갈 수 있다면
					sol(row + 1, col + 1, 2);// 재귀 호출
				}
			}
		}
		if (dir == 1) {// 세로
			if (row - 1 >= 0 && arr[row][col] != 1) {// 배열의 인덱스를 벗어나지 않으면서 벽이 아닌 경우
				height[row][col] = height[row - 1][col] + diagonal[row - 1][col];// 세로 방향은 위쪽 세로와 위쪽 대각선 경우의 수 더함
				if (row + 1 < n && arr[row + 1][col] != 1) {// 세로 방향으로 나아갈 수 있다면
					sol(row + 1, col, 1);// 재귀 호출
				}
				if (row + 1 < n && col + 1 < n && arr[row + 1][col + 1] != 1 && arr[row][col + 1] != 1
						&& arr[row + 1][col] != 1) {// 대각선 방향으로 나아갈 수 있다면
					sol(row + 1, col + 1, 2);// 재귀 호출
				}
			}
		}
		if (dir == 2) {// 대각선
			if (col - 1 >= 0 && row - 1 >= 0 && arr[row][col] != 1) {// 배열의 인덱스를 벗어나지 않으면서 벽이 아닌 경우
				diagonal[row][col] = width[row - 1][col - 1] + height[row - 1][col - 1] + diagonal[row - 1][col - 1];
				// 대각선 방향은 왼위쪽 가로와 왼위쪽 세로와 왼위쪽 대각선 경우의 수 더함
				if (col + 1 < n && arr[row][col + 1] != 1) {// 가로 방향으로 나아갈 수 있다면
					sol(row, col + 1, 0);// 재귀 호출
				}
				if (row + 1 < n && arr[row + 1][col] != 1) {// 새로 방향으로 나아갈 수 있다면
					sol(row + 1, col, 1);// 재귀 호출
				}
				if (row + 1 < n && col + 1 < n && arr[row + 1][col + 1] != 1 && arr[row][col + 1] != 1
						&& arr[row + 1][col] != 1) {// 대각선 방향으로 나아갈 수 있다면
					sol(row + 1, col + 1, 2);// 재귀 호출
				}
			}
		}
	}
}
