package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class B011_BJ4485_녹색옷입은애가젤다지 {
	static int t = 0;// 테스트케이스 수
	static int n;// 맵 크기
	static int[][] map;// 맵
	static int[][] sum;// 다익스트라
	static int dirR[] = { -1, 1, 0, 0 };// 상하좌우
	static int dirC[] = { 0, 0, -1, 1 };// 상하좌우
	static StringBuilder str = new StringBuilder();// 출력할 문자열

	static class Node implements Comparable<Node> {// 우선순위큐에 넣어 맵 위치와 가중치합을 저장할 클래스
		int row, col, w;

		Node(int row, int col, int w) {
			this.row = row;
			this.col = col;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {// 가중치합으로 오름차순 정렬
			return Integer.compare(this.w, o.w);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while (true) {// n=0 입력될 때까지 반복
			t++;// 테스트케이스 1번부터 시작
			n = Integer.parseInt(in.readLine());
			if (n == 0)// n이 0이면
				break;// 종료
			map = new int[n][n];// 맵 생성
			sum = new int[n][n];// 다익스트라 배열 생성

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());// 맵 입력
					sum[i][j] = Integer.MAX_VALUE;// 다익스트라 최대값으로 초기화
				}
			}

			sol();// 다익스트라 알고리즘 이용

			str.append("Problem ").append(t).append(": ").append(sum[n - 1][n - 1]).append("\n");// 테케별 결과
		}
		System.out.println(str.toString());// 한꺼번에 출력
	}

	static void sol() {
		PriorityQueue<Node> q = new PriorityQueue<>();// 우선순위 큐로 가중치합이 작은것부터 꺼냄
		q.add(new Node(0, 0, map[0][0]));// 시작점을 큐에 넣고
		sum[0][0] = map[0][0];// 다익스트라 시작지점도 업데이트

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.row == n - 1 && cur.col == n - 1)
				return;// 도착점에 도달하면 종료

			for (int dir = 0; dir < 4; dir++) {// 사방에 대해
				int newR = cur.row + dirR[dir];
				int newC = cur.col + dirC[dir];

				if (newR < 0 || newR >= n || newC < 0 || newC >= n) {// 인덱스 벗어나면
					continue;// 종료
				}

				if (sum[newR][newC] > sum[cur.row][cur.col] + map[newR][newC]) {// 가중치합 최소로 업데이트 가능하면
					sum[newR][newC] = sum[cur.row][cur.col] + map[newR][newC];// 업데이트하고
					q.add(new Node(newR, newC, sum[newR][newC]));// 큐에 추가해서 그 길로 나아감
				}
			}
		}
	}

}
