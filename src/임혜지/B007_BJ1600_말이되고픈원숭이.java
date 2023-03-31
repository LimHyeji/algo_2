package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B007_BJ1600_말이되고픈원숭이 {
	static int k, w, h;
	static int[] dirR = { -2, -1, 1, 2, -2, -1, 1, 2, -1, 1, 0, 0 };
	static int[] dirC = { -1, -2, -2, -1, 1, 2, 2, 1, 0, 0, -1, 1 };
	static int[][] arr;
	static int res;
	static boolean[][] visit;

	static class Node {
		int row, col, depth, horse;

		Node(int row, int col, int depth, int horse) {
			this.row = row;
			this.col = col;
			this.depth = depth;
			this.horse = horse;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		k = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		arr = new int[h][w];
		visit = new boolean[h][w];

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		res = -1;
		sol();
		System.out.println(res);
	}

	static void sol() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, k));
		visit[0][0] = true;
		Node cur = null;
		while (!q.isEmpty()) {
			cur = q.poll();
			print(visit);
			if (cur.row == h - 1 && cur.col == w - 1) {
				res = cur.depth;
				break;
			}

			if (cur.horse >0) {
				for (int dir = 0; dir < 8; dir++) {
					int newR = cur.row + dirR[dir];
					int newC = cur.col + dirC[dir];

					if (newR < 0 || newR >= h || newC < 0 || newC >= w || arr[newR][newC] == 1
							|| visit[newR][newC] == true)
						continue;

					q.add(new Node(newR, newC, cur.depth + 1, cur.horse - 1));
					visit[newR][newC] = true;
				}
			}

			for (int dir = 8; dir < 12; dir++) {
				int newR = cur.row + dirR[dir];
				int newC = cur.col + dirC[dir];

				if (newR < 0 || newR >= h || newC < 0 || newC >= w || arr[newR][newC] == 1 || visit[newR][newC] == true)
					continue;
				q.add(new Node(newR, newC, cur.depth + 1, cur.horse));
				visit[newR][newC] = true;
			}
		}
	}
	
static void print(boolean[][] arr) {
	for(int i=0;i<h;i++) {
		System.out.println(Arrays.toString(arr[i]));
	}
	System.out.println();
}
}
