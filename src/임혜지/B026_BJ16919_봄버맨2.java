package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B026_BJ16919_봄버맨2 {
	static int r, c, n;// 맵 크기와 시간
	static int[][] map;// 맵
	static int[] dirR = { -1, 1, 0, 0 };// 상하좌우
	static int[] dirC = { 0, 0, -1, 1 };// 상하좌우
	static boolean f;// 메소드 전환할 플래그
	static StringBuilder str = new StringBuilder();// 출력할 문자열

	static class Node {// 맵 위치 담을 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 띄어쓰기로 구분해 입력
		r = Integer.parseInt(st.nextToken());// 맵의 세로 크기 입력
		c = Integer.parseInt(st.nextToken());// 맵의 가로 크기 입력
		n = Integer.parseInt(st.nextToken());// 시간 수 입력

		map = new int[r][c];// 맵 생성
		for (int i = 0; i < r; i++) {
			String input = in.readLine();// 한 줄 입력
			for (int j = 0; j < c; j++) {
				if (input.charAt(j) == 'O') {// 폭탄이 있을 때
					map[i][j] = 3;// 3초 입력
				}
			}
		}

		if (n == 1) {// 1초일 경우
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					str.append((map[i][j] == 0) ? "." : "O");// 입력 그대로 출력
				}
				str.append("\n");
			}
		} else if (n % 2 == 0) {// 짝수일 경우
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					str.append("O");// 모두 폭탄으로 출력
				}
				str.append("\n");
			}
		} else {// 1을 제외한 홀수일 경우
			n = n % 4 + 4;// 시간 초과 해결 위해 추가(4턴을 반복)
			n--;// 1초 경과
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] > 0) {
						map[i][j]--;// 폭탄의 카운트 -1
					}
				}
			}
			f = false;// 메소드 전환할 플래그
			while (n-- > 0) {// 1초씩 지나면서
				if (f == false) {// false일 경우
					add();// 폭탄 추가하고
					f = true;// 다음을 위해 true 전환
				} else {// true일 경우
					bomb();// 폭탄 터지고
					f = false;// 다음을 위해 false 전환
				}
			}
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					str.append((map[i][j] == 0) ? "." : "O");// 폭탄 카운트가 남는 경우에 폭탄 출력
				}
				str.append("\n");
			}
		}
		System.out.println(str.toString());// 결과 출력
	}

	static void add() {// 폭탄 추가할 메소드
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 0) {// 폭탄이 없을 경우
					map[i][j] = 3;// 3초 카운트 시작
				} else {// 폭탄이 있을 경우
					map[i][j]--;// 카운트 -1
				}
			}
		}
	}

	static void bomb() {// 폭탄 터지는 메소드
		Queue<Node> q = new LinkedList<>();// 큐 생성
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j]--;// 카운트 -1
				if (map[i][j] == 0) {// 폭탄 카운트가 끝날 경우
					q.add(new Node(i, j));// 큐에 추가
				}
			}
		}

		while (!q.isEmpty()) {// 큐에 값이 존재할 경우 반복
			Node cur = q.poll();// 하나씩 꺼내서
			// map[cur.row][cur.col] = 0;

			for (int dir = 0; dir < 4; dir++) {// 네 방향에 대해
				int newR = cur.row + dirR[dir];
				int newC = cur.col + dirC[dir];

				if (newR < 0 || newR >= r || newC < 0 || newC >= c) {// 맵 벗어날 경우
					continue;// 패스
				}
				map[newR][newC] = 0;// 네 방향에 대한 위치도 폭탄 터짐
			}
		}
	}
}
