package usaco2015Contest;

import java.util.*;
import java.io.*;

public class Lightson {

	static boolean[][] necessary;
	static boolean[][] grid;
	static int count = 1;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());

		grid = new boolean[n][n];
		necessary = new boolean[n][n];
		ArrayList<int[]>[][] adjacencyList = new ArrayList[n][n];

		grid[0][0] = true;
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.valueOf(st.nextToken()) - 1;
			int second = Integer.valueOf(st.nextToken()) - 1;
			int a = Integer.valueOf(st.nextToken()) - 1;
			int b = Integer.valueOf(st.nextToken()) - 1;

			if (!necessary[first][second]) {
				necessary[first][second] = true;
				adjacencyList[first][second] = new ArrayList<>();
			}
			adjacencyList[first][second].add(new int[] { a, b });
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));

		boolean[][] discovered = new boolean[n][n];
		DFS(0, 0, adjacencyList, n, discovered);
		System.out.println(count);
		
		pw.write(String.valueOf(count));
		pw.close();
		br.close();

	}

	public static boolean isValid(int a, int b, int n) {
		if (a < 0 || a > n - 1 || b < 0 || b > n - 1) {
			//System.out.println(a+" "+ b);
			return false;
		}
		return true;
	}

	public static void DFS(int x, int y, ArrayList<int[]>[][] adjacencyList, int n, boolean[][] discovered) {
		if (!discovered[x][y]) {
			discovered[x][y] = true;
			for (int i = 0; necessary[x][y] && i < adjacencyList[x][y].size(); i++) {
				//System.out.println(adjacencyList[x][y].size());
				int a = adjacencyList[x][y].get(i)[0];
				int b = adjacencyList[x][y].get(i)[1];
				if (!grid[a][b]) {
					grid[a][b] = true;
					count++;
				}
				for (int j = 0; j < 4; j++) {
					int nx = a + dx[j];
					int ny = b + dy[j];
					if (isValid(nx, ny, n) && discovered[nx][ny]) {
						DFS(a, b, adjacencyList, n, discovered);
						//System.out.println((nx+1)+" "+(ny+1));
					}
				}
				//System.out.println("a b " + a + " " + b);
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (isValid(nx, ny, n) && grid[nx][ny]) {
					DFS(nx, ny, adjacencyList, n, discovered);
					//System.out.println((nx+1)+" "+(ny+1));
				}
					
			}
		}
	}

}
