package usaco2020DecContest;

import java.util.*;
import java.io.*;

public class Cowntagion {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		ArrayList<Integer>[] adjList = new ArrayList[n];
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
		System.out.println(adjList[0]);
		
		LinkedList<Integer> q = new LinkedList<>();
		q.add(0);
		boolean[] visited = new boolean[n];
		int numDays = 0; 
		
		int pow0 = 0;
		while(Math.pow(2, pow0)<n-1) { 
			pow0++;
		}
		numDays+=pow0;
		visited[0] = true;
		
		while(q.size()>0) {
			int x = q.poll();
			System.out.println(x + " " + numDays);
			if(!visited[x] && x!=0) {
				numDays++;
				visited[x] = true;
				int size = 0;
				for(int i: adjList[x]) {
					if(!visited[i]) size++;
				}
				int pow = 0;
				while(Math.pow(2,pow)<size) {
					pow++;
				}
				numDays+=pow;
				//System.out.println(numDays);
				for(int i : adjList[x]) {
					//if(!visited[i]) System.out.println(numDays);
					if(!visited[i]) q.add(i);
				}
			}
			else if (x==0) {
				for(int i: adjList[0]) {
					q.add(i);
				}
			}
		}
		
		System.out.println(numDays);
	}

}
