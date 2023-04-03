package 임혜지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B016_SWEA1263_사람네트워크2 {
	static int t;// 테스트 케이스 수
	static int n;// 노드 개수
	static int[][] adj;// 인접 행렬
	static int res;// 결과로 출력할 cc 최소값
	static StringBuilder str = new StringBuilder();// 출력할 문자열

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));// 버퍼로 입력
		StringTokenizer st = null;// 띄어쓰기로 구분해 입력받을 준비

		t = Integer.parseInt(in.readLine());// 테스트 케이스 수 입력
		for (int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(in.readLine());// 한 줄 입력
			n = Integer.parseInt(st.nextToken());// 노드 개수 입력

			adj = new int[n][n];// 인접 행렬 생성

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());// 인접 행렬 입력
					if (i != j && adj[i][j] == 0) {// 자기 자신으로의 방향도 아니면서 인접하지 않으면
						adj[i][j] = 9999999;// 큰 수로 초기화
					}
				}
			}

			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					if (i == k)// 시작 정점과 경유하는 정점 같으면
						continue; // 패스
					for (int j = 0; j < n; j++) {
						if (i == j || k == j)// 경유하는 정점과 목적지가 같거나 시작 정점과 도착 정점이 같다면
							continue; // 패스
						adj[i][j] = Math.min(adj[i][k] + adj[k][j], adj[i][j]);// 인접 행렬 최단거리로 업데이트
					}
				}
			}

			res = Integer.MAX_VALUE;// cc 최소값 구하기 위한 초기화
			for (int i = 0; i < n; i++) {
				int sum = 0;// 각 노드의 최단거리 합 구하기 위한 초기화
				for (int j = 0; j < n; j++) {
					sum += adj[i][j];// 노드의 최단거리 더해서
				}
				res = Math.min(res, sum);// cc 최소값 업데이트
			}

			str.append("#").append(test_case).append(" ").append(res).append("\n");// 결과 붙여서
		}
		System.out.println(str.toString());// 한꺼번에 출력
	}
}
