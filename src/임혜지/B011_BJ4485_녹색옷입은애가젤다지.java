package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B011_BJ4485_녹색옷입은애가젤다지 {
	static int t = 0;
	static int n;
	static int[][] map;
	static boolean[][] visit;
	static int[][] sum;
	static int dirR[] = { -1, 1, 0, 0 };
	static int dirC[] = { 0, 0, -1, 1 };
	static int res;
	static StringBuilder str = new StringBuilder();

	static class Node {
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while (true) {
			t++;
			n = Integer.parseInt(in.readLine());
			if (n == 0)
				break;
			map = new int[n][n];
			sum = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					sum[i][j] = map[i][j];
				}
			}

			visit = new boolean[n][n];
			res = Integer.MAX_VALUE;
			sol();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(sum[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();

			str.append("Problem ").append(t).append(": ").append(sum[n - 1][n - 1]).append("\n");
		}
		System.out.println(str.toString());
	}

//	static void sol() {
//		Queue<Node> q = new LinkedList<>();
//		boolean visit[][] = new boolean[n][n];
//
//		q.add(new Node(0, 0));
//		visit[0][0] = true;
//		sum[0][0] = map[0][0];
//
//		while (!q.isEmpty()) {
//			Node cur = q.poll();
//
//			for (int dir = 0; dir < 4; dir++) {
//				int newR = cur.row + dirR[dir];
//				int newC = cur.col + dirC[dir];
//
//				if (newR < 0 || newR >= n || newC < 0 || newC >= n) {
//					continue;
//				}
//
//				if (visit[newR][newC]&&(dir==1||dir==3)) {
//					sum[newR][newC] = Math.min(sum[newR][newC], map[cur.row][cur.col] + map[newR][newC]);
//				} else {
//					q.add(new Node(newR, newC));
//					visit[newR][newC] = true;
//					sum[newR][newC] = sum[cur.row][cur.col] + map[newR][newC];
//				}
//			}
//		}
//	}
	
	static void sol() {
		Queue<Node> q = new LinkedList<>();
		boolean visit[][] = new boolean[n][n];

		q.add(new Node(0, 0));
		visit[0][0] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int newR = cur.row + dirR[dir];
				int newC = cur.col + dirC[dir];

				if (newR < 0 || newR >= n || newC < 0 || newC >= n) {
					continue;
				}

				if (visit[newR][newC]) {
					sum[newR][newC] = Math.min(sum[newR][newC], sum[cur.row][cur.col] + map[newR][newC]);
				} else {
					q.add(new Node(newR, newC));
					visit[newR][newC] = true;
					sum[newR][newC] += sum[cur.row][cur.col];
				}
			}
		}
	}

}
