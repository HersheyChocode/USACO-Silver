package mooTube;

import java.io.*;
import java.util.*;

public class MooTubeDFS {
	
	public static int numVideos;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));

		int n = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		ArrayList<Edges>[] adjacencyList = new ArrayList[n];
		// int[][] nums = new int[n][3];
		int[][] qs = new int[Q][2];

		int v;
		int k;
		int relevancy;
		//public int numVideos;
		boolean[] discovered = new boolean[n];
		
		for(int i =0; i<n; i++) {
			adjacencyList[i] = new ArrayList<Edges>();
		}

		// nums
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			/*
			 * st = new StringTokenizer(br.readLine()); for (int j = 0; j < 3; j++) {
			 * nums[i][j] = Integer.parseInt(st.nextToken()); }
			 */
			Edges x = new Edges(p, q, r);
			Edges y = new Edges(q, p, r);
			
			adjacencyList[p-1].add(x);
			adjacencyList[q-1].add(y);

		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			qs[i][0] = Integer.parseInt(st.nextToken());
			qs[i][1] = Integer.parseInt(st.nextToken());

			v = qs[i][1];
			k = qs[i][0];
			relevancy = 1000000001;
			numVideos = 0;
			Arrays.fill(discovered, false);
			discovered[v - 1] = true;
			
			DFS(v,discovered,adjacencyList,relevancy,k);
			pw.write(String.valueOf(numVideos)+"\n");
			System.out.println(numVideos);
		}
		pw.close();
	}
	
	public static void DFS(int v, boolean[] discovered, ArrayList<Edges>[] adjacencyList, int relevancy, int k) {
		for(int j =0; j<adjacencyList[v-1].size(); j++) {
			int a = adjacencyList[v-1].get(j).a;
			int b = adjacencyList[v-1].get(j).b;
			int c = adjacencyList[v-1].get(j).c;
			/*
			System.out.print(a);
			System.out.print(b);
			System.out.println(c);
			System.out.print(a==v);
			System.out.print(discovered[b-1]);
			System.out.println(Math.min(relevancy, c)>=k);*/
			
			if(a == v && discovered[b-1]==false && Math.min(relevancy, c)>=k){
				//System.out.println("Made it");
				//System.out.print(a);
				//System.out.print(b);
				//System.out.println(c);
				discovered[b-1] = true;
				numVideos+=1;
				relevancy = Math.min(relevancy, k);
				DFS(b, discovered, adjacencyList, relevancy, k);
				
			} 
		}
	}

}


class Edges {
	public int a;
	public int b;
	public int c;

	public Edges(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public String toString() {
		return "a "+String.valueOf(a)+" b "+String.valueOf(b);
	}
	
}
