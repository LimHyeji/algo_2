package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1 땅, 0 바다
 * 
 * 다리방향이 일정(같은 열 또는 같은 행)
 * 다리길이가 2 이상
 */
/*
 * 1을 발견하면 상/하/좌/우 중 한방향으로 쭉 나아가기(길이 세기)
 * -> 0이었다가 1을 발견하면 완료(단, 길이 1이면 리턴) -> 완료하면 연결된 섬들을 모두 -1로 바꿔주기
 * -> 인덱스 아웃이면 리턴
 * 
 * --> 1이 더이상 없을 때까지 위 과정 반복
 * --> 
 */
public class B019_BJ17472_다리만들기2 {
	static int n, m;
	static int[][] map;

	static int[] dirR = { -1, 1, 0, 0 };// 상하좌우
	static int[] dirC = { 0, 0, -1, 1 };// 상하좌우

	static class Node {
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static class Bridge implements Comparable<Bridge> {
		int fromName, toName;
		int len;

		Bridge(int fromName, int toName, int len) {
			this.fromName = fromName;
			this.toName = toName;
			this.len = len;
		}

		@Override
		public int compareTo(Bridge o) {
			return Integer.compare(this.len, o.len);
		}
	}

	static int cntC;
	// static ArrayList<Bridge> list;
	static PriorityQueue<Bridge> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cntC = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					naming(i, j);
					// print();
					cntC++;
					idx--;
				}
			}
		}

		// list=new ArrayList<>();
		pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] < 0) {
					findBridge(i, j, map[i][j]);
				}
			}
		}
		for (Bridge b : pq) {
			System.out.println(b.fromName + " , " + b.toName + " , " + b.len);
		}

		parent = new int[cntC + 1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		// sol();
	}

	static int idx = -1;

	static void naming(int row, int col) {
		Queue<Node> q = new LinkedList<>();

		q.add(new Node(row, col));
		map[row][col] = idx;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int dir = 0; dir < 4; dir++) {
				int newR = cur.row + dirR[dir];
				int newC = cur.col + dirC[dir];

				if (newR >= n || newR < 0 || newC >= m || newC < 0) {
					continue;
				}

				if (map[newR][newC] == 1) {
					q.add(new Node(newR, newC));
					map[newR][newC] = idx;
				}
			}
		}
	}

	static void findBridge(int row, int col, int name) {
		for (int dir = 0; dir < 4; dir++) {
			int newR = row;
			int newC = col;

			int cnt = 0;
			while (true) {
				newR += dirR[dir];
				newC += dirC[dir];

				if (newR < 0 || newR >= n || newC < 0 || newC >= m || map[newR][newC] == name) {
					cnt = 0;
					break;
				}

				if (map[newR][newC] == 0) {
					cnt++;

				} else if (map[newR][newC] != 0) {
					if (cnt == 1) {
						cnt = 0;
					}
					break;
				}
			}

			if (cnt != 0) {
				// list.add(new Bridge(name,map[newR][newC],cnt));
				System.out.println(row + " , " + col + "일 때 방향 " + dir);
				pq.add(new Bridge(name, map[newR][newC], cnt));
			}
		}
	}

	static int[] parent;

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return false;

		if (x <= y)
			parent[y] = x;
		else
			parent[x] = y;
		return true;
	}

	static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void sol() {
		// union find
		
	}
}
