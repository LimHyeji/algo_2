package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B010_BJ14502_연구소 {
	static int n, m;// 맵의 크기
	static int[][] map;// 맵

	static class Node {// 바이러스 위치 담을 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static ArrayList<Node> virus;// 처음에 존재하던 바이러스 위치 담을 리스트
	static int[] dirR = { -1, 1, 0, 0 };// 상하좌우
	static int[] dirC = { 0, 0, -1, 1 };// 상하좌우
	static int res;// 최대 안전구역 수

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = new StringTokenizer(in.readLine());// 띄어쓰기로 구분해 입력
		n = Integer.parseInt(st.nextToken());// 맵의 세로 크기
		m = Integer.parseInt(st.nextToken());// 맵의 가로 크기
		map = new int[n][m];// 맵 생성
		virus = new ArrayList<>();// 바이러스 리스트 생성

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());// 한 줄 입력
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());// 맵 입력
				if (map[i][j] == 2) {// 바이러스라면
					virus.add(new Node(i, j));// 리스트에 추가
				}
			}
		}

		res = 0;// 최대값 구하기 위한 초기화
		getWall(0, 0);// 벽 3개의 위치부터 구함
		System.out.println(res);// 최대 안전구역 수 출력

	}

	static int[] wall = new int[3];// 벽 3개의 조합

	static void getWall(int cnt, int start) {// 조합으로 벽 3개의 값을 구하는 메소드
		if (cnt == 3) {// 3개의 값을 모두 구하면
			sol(wall);// 벽 리스트를 솔루션 메소드에 보냄
			return;// 재귀 종료
		}

		for (int i = start; i < m * n; i++) {// 0부터 n*m-1까지의 수 중 3개 구함
			wall[cnt] = i;
			getWall(cnt + 1, i + 1);
		}
	}

	static void sol(int[] wall) {
		int[][] copy = copy();// 현재의 맵을 복사해놓고 활용

		for (int i = 0; i < 3; i++) {// 3개의 벽에 대해
			int row = wall[i] / m;// 나눈 몫이 행 인덱스이고
			int col = wall[i] % m;// 나머지가 열 인덱스
			if (copy[row][col] == 1 || copy[row][col] == 2)// 이미 바이러스가 있거나 벽이 있다면
				return;// 백트래킹
			copy[row][col] = 1;// 빈곳이라면 복사해놓은 맵에 벽을 세움
		}

		Queue<Node> q = new LinkedList<>();// bfs 탐색할 큐 생성
		boolean visit[][] = new boolean[n][m];// bfs 탐색 시 사용할 방문 배열 생성
		for (int v = 0; v < virus.size(); v++) {// 초기에 있던 바이러스에 대해
			q.add(virus.get(v));// 큐에 넣어놓고
			visit[virus.get(v).row][virus.get(v).col] = true;// 방문 체크

			while (!q.isEmpty()) {
				Node cur = q.poll();

				for (int dir = 0; dir < 4; dir++) {
					int newR = cur.row + dirR[dir];
					int newC = cur.col + dirC[dir];

					if (newR < 0 || newR >= n || newC < 0 || newC >= m || visit[newR][newC])// 맵을 벗어나거나 방문한 곳이라면
						continue;// 패스

					if (copy[newR][newC] == 1 || copy[newR][newC] == 2)// 이미 바이러스가 있거나 벽이 있다면
						continue;// 패스

					q.add(new Node(newR, newC));// 빈 곳이면서 방문하지 않은 곳이라면 큐에 추가
					visit[newR][newC] = true;// 방문 체크
					copy[newR][newC] = 2;// 복사해놓은 맵에 바이러스 업데이트
				}
			}
		}
		res = Math.max(res, getSafe(copy));// 모두 탐색한(바이러스 모두 퍼진) 후 안전구역 수 구해서 최대값 업데이트
	}

	static int getSafe(int[][] arr) {// 안전구역(0인 곳) 수 구하는 메소드
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0)
					cnt++;
			}
		}
		return cnt;
	}

	static int[][] copy() {// 각 턴에 대해 맵 복사하는 메소드
		int[][] copy = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}
}
