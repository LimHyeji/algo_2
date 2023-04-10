package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B022_BJ3055_탈출 {
	static int r, c;
	static char[][] map;
	static int[] dirR = { -1, 1, 0, 0 };
	static int[] dirC = { 0, 0, -1, 1 };
	static int res;

	static class Node {
		int row, col;
		int cnt;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}

		Node(int row, int col, int cnt) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}

	static Node start;
	static Queue<Node> water;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		water = new LinkedList<>();
		visit = new boolean[r][c];

		for (int i = 0; i < r; i++) {
			String input = in.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'S') {
					start = new Node(i, j, 0);
				} else if (map[i][j] == '*') {
					water.add(new Node(i, j));
					visit[i][j] = true;
				}
			}
		}

		// 입력 종료
		res = Integer.MAX_VALUE;
		move();
		System.out.println(res == Integer.MAX_VALUE ? "KAKTUS" : res);
	}

	static void spread() {
		while (!water.isEmpty()) {
			Node cur = water.poll();

			for (int dir = 0; dir < 4; dir++) {
				int newR = cur.row + dirR[dir];
				int newC = cur.col + dirC[dir];

				if (newR < 0 || newR >= r || newC < 0 || newC >= c)
					continue;
				if (map[newR][newC] == '.')
					map[newR][newC] = '*';
			}
		}
	}

	static void move() {
		Queue<Node> q = new LinkedList<>();

		q.add(start);
		visit[start.row][start.col] = true;
		map[start.row][start.col] = '.';

		while (!q.isEmpty()) {
			spread();

			Node cur = q.poll();

			if (map[cur.row][cur.col] == 'D') {
				res = Math.min(res, cur.cnt);
			}
			for (int dir = 0; dir < 4; dir++) {

				int newR = cur.row + dirR[dir];
				int newC = cur.col + dirC[dir];

				if (newR < 0 || newR >= r || newC < 0 || newC >= c || visit[newR][newC]) {
					continue;
				}

				if (map[newR][newC] == '*' || map[newR][newC] == 'X') {
					continue;
				}

				q.add(new Node(newR, newC, cur.cnt + 1));
				visit[newR][newC] = true;

			}
		}
	}

	static void print() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
