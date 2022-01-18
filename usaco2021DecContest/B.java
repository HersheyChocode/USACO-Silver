//package usaco2021DecContest;

import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.valueOf(st.nextToken());
		for(int h = 0; h<t; h++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int paths = Integer.valueOf(st.nextToken());
			ArrayList<Integer>[] edges = new ArrayList [n];
			for(int i = 0; i<n; i++) edges[i] = new ArrayList<>();
			for(int i = 0; i<paths; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.valueOf(st.nextToken())-1;
				int b = Integer.valueOf(st.nextToken())-1;
				edges[a].add(b);
				edges[b].add(a);
			}
			//System.out.println(Arrays.toString(edges));
			boolean[] visited = new boolean[n];
			int[]regions = new int[n];
			int counter = -1;
			for(int i = 0; i<n; i++) {
				if(!visited[i]) {
					counter++;
					DFS(edges, visited, i, counter, regions);
				}
			}
			counter++;
			//System.out.println(Arrays.toString(regions));
			//counter = number regions
			int[][] minMaxRegions = new int[counter][2];
			for(int i = 0; i<counter; i++) {
				minMaxRegions[i][0] = Integer.MAX_VALUE;
				minMaxRegions[i][1] = Integer.MIN_VALUE;
			}
			for(int i = 0; i<n; i++) {
				int reg = regions[i];
				if(i<minMaxRegions[reg][0]) minMaxRegions[reg][0] = i;
				if(i>minMaxRegions[reg][1]) minMaxRegions[reg][1] = i;
			}
			//backtrack from n
			boolean found = false;
			int sum = 0;
			int count = n-1;
			while(!found) { //assumes count is the max of the component
				int reg = regions[count];
				count = minMaxRegions[reg][0]; //count = min of component
				if(count == 0) found = true;
				else {
					count = count-1; //count = number 1 less than min of component
					sum++;
				}
				
			}
			System.out.println(sum);
		}

	}
	
	public static void DFS(ArrayList<Integer>[] edges, boolean[] visited, int x, int counter, int[] regions) {
		if(!visited[x]) {
			visited[x] = true;
			regions[x] = counter;
			for(int y: edges[x]) {
				DFS(edges, visited, y, counter, regions);
			}
		}
	}

}
