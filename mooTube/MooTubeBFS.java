package mooTube;

import java.io.*;
import java.util.*;

public class MooTubeBFS {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		

		int n = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		ArrayList<Edges>[] adjacencyList = new ArrayList[n];
		int numVideos;

		int v;
		int k;
		int relevancy;
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
			
			adjacencyList[p-1].add(new Edges(p, q, r));
			adjacencyList[q-1].add(new Edges(q, p, r));

		}
		
		

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			
			relevancy = 1000000001;
			numVideos = 0;
			Arrays.fill(discovered, false);
			
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queue.add(v);
			discovered[v-1]=true;
			while(queue.size()>0) {
				v = queue.poll();
				
				for(int j =0; j<adjacencyList[v-1].size(); j++) {
					int a = adjacencyList[v-1].get(j).a;
					int b = adjacencyList[v-1].get(j).b;
					int c = adjacencyList[v-1].get(j).c;
					
					if(a == v && discovered[b-1]==false && Math.min(relevancy, c)>=k){
						discovered[b-1] = true;
						numVideos+=1;
						relevancy = Math.min(relevancy, k);
						queue.add(b);
					} 
				}
			}
			pw.write(String.valueOf(numVideos)+"\n");
			//System.out.println(numVideos);
		}
		pw.close();
		

	}
	
}

/*class Edges {
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
	
}*/