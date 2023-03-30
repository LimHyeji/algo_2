package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class B012_BJ2239_스도쿠_과제3 {
	static class Node {// 채워야 하는 스도쿠 위치 저장할 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static int[][] arr = new int[9][9];// 스도쿠
	static StringBuilder str = new StringBuilder();// 출력할 문자열
	static ArrayList<Node> list = new ArrayList<>();// 0인 위치들 저장하는 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력

		for (int i = 0; i < 9; i++) {
			String input = in.readLine();// 한 줄 입력
			for (int j = 0; j < 9; j++) {
				arr[i][j] = input.charAt(j) - '0';// char에서 '0' 빼서 int형 배열에 저장
				if (arr[i][j] == 0) {// 0이면(채워넣어야하는 위치이면)
					list.add(new Node(i, j));// 리스트에 추가
				}
			}
		}

		sol(0);// 리스트 0번 인덱스부터 호출

	}

	static void sol(int cnt) {
		if (cnt == list.size()) {// 리스트에 대한 순회가 끝났으면
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					str.append(arr[i][j]);
				}
				str.append("\n");
			}
			System.out.println(str.toString());// 결과로 출력 후
			System.exit(0);// 사전식 순서로 첫번째 결과를 출력해야 하기 때문에 시스템 종료
		}

		int row = list.get(cnt).row;// 리스트에서 꺼낸 행 위치
		int col = list.get(cnt).col;// 리스트에서 꺼낸 열 위치

		for (int i = 1; i <= 9; i++) {// 1부터 9까지의 숫자에 대해
			if (check(row, col, i)) {// 채워넣을 수 있는 숫자가 있으면
				arr[row][col] = i;// 그 숫자로 채워넣고
				sol(cnt + 1);// 재귀 호출 후
				arr[row][col] = 0;// 재귀 종료 시 다시 풀어주기
			}
		}

	}

	static boolean check(int row, int col, int num) {
		for (int i = 0; i < 9; i++) {
			if (row == i && col == i)// 현재위치와 동일 시에는
				continue;// 무시
			if (arr[i][col] == num || arr[row][i] == num)// 가로 또는 세로에 이미 그 숫자가 존재하면
				return false;// false 리턴
		}

		int tempR = row / 3 * 3;// 스도쿠 3*3 박스의 행 인덱스
		int tempC = col / 3 * 3;// 스도쿠 3*3 박스의 열 인덱스

		for (int i = tempR; i < tempR + 3; i++) {
			for (int j = tempC; j < tempC + 3; j++) {
				if (arr[i][j] == num)// 만약 그 박스에 이미 그 숫자가 존재하면
					return false;// false 리턴
			}
		}
		return true;// 모든 for문 종료 시 true 리턴
	}
}
