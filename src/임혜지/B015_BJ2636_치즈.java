package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B015_BJ2636_치즈 {
	static int h, w;// 배열의 사이즈
	static int[][] arr;// 치즈가 올려진 배열
	static int[] dirR = { -1, 1, 0, 0 };// bfs 탐색할 방향 배열
	static int[] dirC = { 0, 0, -1, 1 };// bfs 탐색할 방향 배열
	static boolean[][] visit;// bfs 탐색 시 사용할 방문 배열
	static int t, cnt;// 모두 녹는 데에 걸리는 시간과 모두 녹기 직전에 남은 치즈 개수

	static class Node {// 치즈의 위치를 담을 클래스
		int row, col;// 행 위치와 열 위치

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 띄어쓰기로 구분해 한 줄 입력

		h = Integer.parseInt(st.nextToken());// 배열의 행 사이즈
		w = Integer.parseInt(st.nextToken());// 배열의 열 사이즈

		arr = new int[h][w];// 배열 생성
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());// 배열 입력
			}
		}

		t = 0;// 시간 초기와
		sol();// bfs 탐색
		System.out.println(t);// 모두 녹는 데에 걸리는 시간 출력
		System.out.println(cnt);// 모두 녹기 직전에 남은 치즈 개수 출력
	}

	static void sol() {
		while (!isEmpty(arr)) {// 배열이 빌 때까지 반복
			Queue<Node> q = new LinkedList<>();// bfs에 사용할 큐 생성
			visit = new boolean[h][w];// 매 회 사용할 방문 배열 생성

			q.add(new Node(0, 0));// 빈 공간인 (0,0) 위치 큐에 추가
			visit[0][0] = true;// 방문으로 처리

			cnt = 0;// 매 회 치즈 개수 셀 변수
			while (!q.isEmpty()) {// 탐색할 노드가 없을 때까지 반복
				Node cur = q.poll();// 큐에서 하나 꺼내서

				for (int dir = 0; dir < 4; dir++) {// 네 방향에 대해
					int newR = cur.row + dirR[dir];// 새로운 방향 인덱스 저장
					int newC = cur.col + dirC[dir];

					if (newR < 0 || newR >= h || newC < 0 || newC >= w) {// 배열의 범위 벗어나면
						continue;// 다음 방향으로 나아감
					}

					if (!visit[newR][newC]) {// 탐색하지 않은 노드에 대해
						visit[newR][newC] = true;// 방문으로 처리하고

						if (arr[newR][newC] == 1) {// 치즈가 있다면
							arr[newR][newC] = -1;// 임시로 -1로 저장해놓고
							cnt++;// 치즈 개수 1 증가
						}

						if (arr[newR][newC] == 0) {// 치즈 바깥쪽에 대해
							q.add(new Node(newR, newC));// 큐에 저장
						}
					}
				}
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (arr[i][j] == -1)
						arr[i][j] = 0;// 배열 한번 순회하면서 -1로 저장해놓은 곳을 0으로 바꿔주기
				}
			}
			t++;
		}
	}

	static boolean isEmpty(int[][] arr) {// 배열이 비어있는지 확인하는 메소드
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (arr[i][j] == 1)
					return false;
			}
		}
		return true;
	}
}
