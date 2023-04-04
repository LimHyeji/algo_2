package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B020_SWEA_키순서_과제5 {
	static int t;// 테스트 케이스 수
	static int n, m;// 노드 번호 개수, 비교 횟수
	static ArrayList<Integer> adj[];// 인접리스트
	static int a[];// 진출 노드수 셀 배열
	static int b[];// 진입 노드수 셀 배열
	static StringBuilder str = new StringBuilder();// 결과로 출력할 문자열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력받을 준비

		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력
		for (int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(in.readLine());// 학생 수 입력
			m = Integer.parseInt(in.readLine());// 비교 횟수 입력
			adj = new ArrayList[n + 1];// 인접리스트 생성
			for (int i = 0; i <= n; i++) {
				adj[i] = new ArrayList<>();// 인접리스트 각각 초기화
			}
			a = new int[n + 1];// 진출 노드수 배열 초기화
			b = new int[n + 1];// 진입 노드수 배열 초기화

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(in.readLine());// 한 줄 입력
				int from = Integer.parseInt(st.nextToken());// 시작정점
				int to = Integer.parseInt(st.nextToken());// 도착정점
				adj[from].add(to);// 인접리스트에 추가(방향 존재)
			}
			sol();//  노드수 세는 메소드

			int cnt = 0;// 등수를 알 수 있는 학생 수 카운트 위한 벼누
			for (int i = 1; i <= n; i++) {
				if ((a[i] - 1) + b[i] == n - 1) {// 진출 노드수와 진입 노드수를 더한 것이 n-1이면
					cnt++;// 등수를 알 수 있음
				}
			}

			str.append("#").append(test_case).append(" ").append(cnt).append("\n");// 문자열 붙이고
		}
		System.out.println(str.toString());// 결과 한꺼번에 출력
	}

	static void sol() {
		for (int i = 1; i <= n; i++) {// 각각의 노드에 대해
			Queue<Integer> q = new LinkedList<>();// bfs 탐색을 위한 큐 생성
			boolean visit[] = new boolean[n + 1];// bfs 탐색을 위한 방문 배열 생성
			q.add(i);// 시작정점 큐에 추가하고
			visit[i] = true;// 방문 체크

			while (!q.isEmpty()) {
				int cur = q.poll();
				a[i]++;// 진출 노드수 1 증가(단, 자기 자신이 포함되는 것도 고려)
				for (int v : adj[cur]) {// 인접한 정점에 대해
					if (!visit[v]) {// 탐색하지 않은 정점은
						b[v]++;// 진입 노드수 1 증가
						q.add(v);// 인접 정점을 큐에 추가하고
						visit[v] = true;// 방문 체크
					}
				}
			}
		}
	}

}
