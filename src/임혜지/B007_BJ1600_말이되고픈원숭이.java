package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B007_BJ1600_말이되고픈원숭이 {
	static int k, w, h;// 말 이동횟수, 맵의 크기
	static int[][] arr;// 맵
	static int[] dirR = { -1, -2, -2, -1, 1, 2, 2, 1, -1, 1, 0, 0 };// 0~7번은 말의 이동, 8~11번은 상하좌우이동
	static int[] dirC = { -2, -1, 1, 2, 2, 1, -1, -2, 0, 0, -1, 1 };// 0~7번은 말의 이동, 8~11번은 상하좌우이동
	static boolean[][][] visit;// bfs 시 사용할 방문 배열
	static int res;// 원숭이의 최소 이동횟수

	static class Monkey {// 원숭이의 현 위치와 총 이동횟수와 말 이동횟수 카운트할 클래스
		int row, col;
		int cnt, horse;

		Monkey(int row, int col, int cnt, int horse) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
			this.horse = horse;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력받을 준지

		k = Integer.parseInt(in.readLine());// 말 이동횟수 제한 입력

		st = new StringTokenizer(in.readLine());// 한줄 입력
		w = Integer.parseInt(st.nextToken());// 맵의 가로 입력
		h = Integer.parseInt(st.nextToken());// 맵의 세로 입력
		arr = new int[h][w];// 맵 생성

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());// 맵 입력
			}
		}

		res = Integer.MAX_VALUE;// 최소값 구하기 위한 초기화
		visit = new boolean[h][w][k + 1];// 방문 배열 생성... 다른 경로에 대해 visit 체크를 달리해야하므로 3차원 배열 생성
		sol();// bfs 탐색
		System.out.println(res == Integer.MAX_VALUE ? -1 : res);// 최소값 업데이트가 일어나지 않았으면(끝에 도달하지 못했으면) -1 출력

	}

	static void sol() {
		Queue<Monkey> q = new LinkedList<>();// bfs 탐색할 큐 생성

		q.add(new Monkey(0, 0, 0, 0));// 시작위치 큐에 추가
		visit[0][0][0] = true;// 방문 체크

		while (!q.isEmpty()) {
			Monkey cur = q.poll();

			if (cur.row == h - 1 && cur.col == w - 1) {// 도착지점에 도달했다면
				res = Math.min(res, cur.cnt);// 최소값 업데이트
			}

			if (cur.horse < k) {// 말 이동 가능 횟수가 남아있을 때
				for (int dir = 0; dir < 8; dir++) {// 말 이동 가능
					int newR = cur.row + dirR[dir];
					int newC = cur.col + dirC[dir];

					if (newR < 0 || newR >= h || newC < 0 || newC >= w)// 맵의 크기를 벗어나면
						continue;// 다음 방향으로 이동

					if (!visit[newR][newC][cur.horse + 1] && arr[newR][newC] == 0) {// k번째(말 이동횟수에 대해) 방문하지 않았고, 장애물이 아닐
																					// 때
						q.add(new Monkey(newR, newC, cur.cnt + 1, cur.horse + 1));// 이동 가능하므로 큐에 추가하고
						visit[newR][newC][cur.horse + 1] = true;// 방문 체크
					}
				}
			}

			for (int dir = 8; dir < 12; dir++) {// 상하좌우 이동
				int newR = cur.row + dirR[dir];
				int newC = cur.col + dirC[dir];

				if (newR < 0 || newR >= h || newC < 0 || newC >= w)// 맵의 크기를 벗어나면
					continue;// 다음 방향으로 이동

				if (!visit[newR][newC][cur.horse] && arr[newR][newC] == 0) {// k번째(말 이동횟수에 대해) 방문하지 않았고, 장애물이 아닐 때
					q.add(new Monkey(newR, newC, cur.cnt + 1, cur.horse));// 이동 가능하므로 큐에 추가하고
					visit[newR][newC][cur.horse] = true;// 방문 체크
				}
			}
		}
	}
}
