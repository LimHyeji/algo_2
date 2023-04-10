package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

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
	static PriorityQueue<Bridge> pq;
	static int res;

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
					cntC++;
					idx--;
				}
			}
		}
		pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] < 0) {
					findBridge(i, j, map[i][j]);
				}
			}
		}
		for (Bridge cur : pq) {
			System.out.println(cur.fromName + " , " + cur.toName + " , " + cur.len);
		}

		parent = new int[cntC + 1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
		
		res=0;
		sol();
		System.out.println(res);
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
				//System.out.println(row + " , " + col + "일 때 방향 " + dir);
				if (check(name, map[newR][newC], cnt)) {
					pq.add(new Bridge(name, map[newR][newC], cnt));
				}
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

	static boolean check(int from, int to, int len) {
		for (Bridge cur : pq) {
			if ((cur.fromName == from && cur.toName == to && cur.len == len)
					|| (cur.fromName == to && cur.toName == from && cur.len == len)) {
				return false;
			}
		}
		return true;
	}

	static void sol() {
		int size=pq.size();
		for(int i=0;i<size;i++) {
			Bridge cur=pq.poll();
			if(find(-1*cur.fromName)!=find(-1*cur.toName)){
				union(-1*cur.fromName,-1*cur.toName);
				res+=cur.len;
			}
		}
		int par=parent[1];
		for(int i=2;i<=parent.length;i++) {
			if(par!=parent[i]) {
				res=-1;
				return;
			}
		}
	}
}
