package usaco2016OpenContest;

import java.io.*;
import java.util.*;

public class ClosingTheFarm {
	static int counter  = 0;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("closing.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		ArrayList<Integer>[] adjList = new ArrayList[n];
		int[] order = new int[n];
		for(int i = 0; i<n; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken())-1;
			int y = Integer.valueOf(st.nextToken())-1;
			adjList[x].add(y);
			adjList[y].add(x);
		}
		for(int i = 0; i<n; i++) {
			order[i] = Integer.valueOf(br.readLine())-1;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
		boolean[] visited = new boolean[n];
		boolean[] closed = new boolean[n];
		int numClosed = -1;
		for(int i = -1; i<n-1; i++) {
			//order[i+1] will always be open
			numClosed++;
			visited = new boolean[n];
			if(i>=0) closed[order[i]] = true;
			counter = 0;
			//System.out.println("i" + i);
			DFS(order[i+1], visited, adjList, closed);
			if(counter==(n-numClosed)) {
				pw.println("YES");
				System.out.println("YES");
			}
			else {
				pw.println("NO");
				System.out.println("NO");
			}
		}
		pw.close();
		br.close();
		
	}
	public static void DFS(int x, boolean[] visited, ArrayList<Integer>[] adjList, boolean[] closed) {
		if(!visited[x] && !closed[x]) {
			visited[x] = true;
			//System.out.println(x + " " + counter);
			counter++;
			for(int i : adjList[x]) {
				DFS(i, visited, adjList, closed);
			}
		}
	}
}
