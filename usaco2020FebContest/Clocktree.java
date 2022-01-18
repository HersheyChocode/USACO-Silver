package usaco2020FebContest;

import java.io.*;
import java.util.*;

public class Clocktree {
	//static int[] times;
	static ArrayList<Integer>[] adj;
	static int reached12 = 0; 
	static int n;
	static boolean n12;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("clocktree.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		int[] times = new int[n];
		st = new StringTokenizer(br.readLine());
	    adj = new ArrayList[n];
		for(int i = 0; i<n; i++) {
			times[i] = Integer.valueOf(st.nextToken());
			if(times[i]==12) reached12++;
			adj[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken())-1;
			int y = Integer.valueOf(st.nextToken())-1;
			adj[x].add(y);
			adj[y].add(x);
		}
		
		int first12 = reached12;
		int counter = 0;
		for(int i = 0; i<n; i++) {
			//System.out.println(i);
			n12 = false;
			reached12 = first12;
			DFS(i,times.clone(),true,reached12);
			if(n12) counter++;
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
		pw.println(counter);
		System.out.println(counter);
		
		pw.close();
		br.close();
		
	}
	
	public static void DFS(int x, int[] times, boolean start, int reached12) {
		//System.out.println((x+1) + " " + times[x] + " "  + start + " " + reached12);
		if(times[x]!=12&&!n12) {
			//System.out.println("made it"  + " " + (x+1));
			if(!start)times[x]++;
			if(times[x]==12) reached12++;
			if(reached12==n) n12=true;
			for(int i: adj[x]) {
				DFS(i, times, false, reached12);
			}
			//times[x]--;
			//reached12--;
		}
		
	}

}
