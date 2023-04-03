package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B017_BJ9205_맥주마시면서걸어가기 {
	static int t, n;// 테스트 케이스 수, 편의점 개수

	static class Node {// 위치를 저장할 클래스
		int row, col;

		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static Node start;// 상근이네 집 위치
	static ArrayList<Node> list;// 편의점과 락페스티벌 위치 리스트
	static Node end;// 락페스티벌 위치
	static boolean visit[];// bfs에 사용할 방문 배열

	static int getD(Node from, Node to) {// 거리를 구하는 메소드
		return Math.abs(from.row - to.row) + Math.abs(from.col - to.col);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력 받을 준비

		t = Integer.parseInt(in.readLine());// 테스트케이스 수 입력
		for (int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(in.readLine());// 편의점 개수 입력
			list = new ArrayList<>();// 탐색할 위치 리스트 생성

			st = new StringTokenizer(in.readLine());// 한 줄 입력
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			start = new Node(y, x);// 상근이네 집 위치 입력

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(in.readLine());// 한 줄 입력
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				list.add(new Node(y, x));// 총 n개의 편의점 위치 입력
			}

			st = new StringTokenizer(in.readLine());// 한 줄 입력
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			end = new Node(y, x);// 락페스티벌 위치 입력
			list.add(end);// 리스트에 마지막으로 추가

			visit = new boolean[n + 1];// 방문 배열 생성
			System.out.println(sol() ? "happy" : "sad");// 도착 가능하면 happy 출력
		}

	}

	static boolean sol() {
		Queue<Node> q = new LinkedList<>();// bfs 탐색에 사용할 큐 생성
		q.add(start);// 큐에 시작점 저장

		while (!q.isEmpty()) {
			Node cur = q.poll();// 현재 순회중인 노드
			if (cur.row == end.row && cur.col == end.col) {// 도착점에 도달했으면
				return true;// true 리턴
			}

			for (int i = 0; i < list.size(); i++) {// 모든 리스트에 대해
				if (!visit[i] && getD(cur, list.get(i)) <= 1000) {// 탐색하지 않았으면서 거리가 1000m 이하라면
					q.add(new Node(list.get(i).row, list.get(i).col));// 큐에 추가하고
					visit[i] = true;// 방문 체크
				}
			}
		}
		return false;// 더이상 순회할 것이 없으면서 도착점에 도달하지 않았다면 false 리턴
	}
}
