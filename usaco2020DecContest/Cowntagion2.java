package usaco2020DecContest;

import java.io.*;
import java.util.*;

public class Cowntagion2 {
	static ArrayList<Integer>[] adjList;
	static int numDays;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		adjList = new ArrayList[n];
		for(int i = 0; i<n; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i = 0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken())-1;
			int y = Integer.valueOf(st.nextToken())-1;
			adjList[x].add(y);
			adjList[y].add(x);
		}
		
		numDays = 0; 
		boolean[] visited = new boolean[n];
		DFS(0,visited);
		
		System.out.println(numDays);
		

	}
	public static void DFS(int x, boolean[] visited) {
		if(!visited[x]) {
			visited[x] = true;
			//all cows start off w/one diseased cow
			int numChildren = adjList[x].size();
			if(x!=0) numChildren-=1;
			int pow = 0;
			while(Math.pow(2, pow)<=numChildren) {
				pow++;
			}
			numDays+=pow;
			for(int i: adjList[x]) {
				if(!visited[i]) {
					numDays++;
					DFS(i,visited);
				}
			}
		}
	}

}
