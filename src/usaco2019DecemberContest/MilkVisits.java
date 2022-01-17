package usaco2019DecemberContest;

import java.io.*;
import java.util.*;

public class MilkVisits {
	public static int[] changes;
	public static String word;
	static int counter = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		word = br.readLine();
		String[] chars = new String[n];
		
		ArrayList<Integer>[] adjList = new ArrayList[n];
		for(int i = 0; i<n; i++) {
			chars[i] = String.valueOf(word.charAt(i));
			adjList[i] = new ArrayList<>();
		}
		for(int i = 0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken())-1;
			int y = Integer.valueOf(st.nextToken())-1;
			adjList[x].add(y);
			adjList[y].add(x);
		}
		
		changes = new int[n];
		boolean[] discovered = new boolean[n];
		DFS(0,adjList,'p',0,discovered);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken())-1;
			int y = Integer.valueOf(st.nextToken())-1;
			String c = st.nextToken();
			int x1 = changes[x];
			int y1 = changes[y];
			if(x1!=y1) {
				pw.print(1);
			}
			else if(String.valueOf(word.charAt(x)).equals(c)) {
				pw.print(1);
			}
			else {
				pw.print(0);
			}
		}
		
		pw.close();
		
	}
	
	public static void DFS(int x, ArrayList<Integer>[] adjList, char prev, int prevNum, boolean[] discovered) {
		if(!discovered[x]) {
			discovered[x] = true;
			if(prev!=word.charAt(x)) {
				counter++;
				prevNum = counter;
			}
			prev = word.charAt(x);
			changes[x] = prevNum;
			for(int i = 0; i<adjList[x].size(); i++) {
				DFS(adjList[x].get(i),adjList,prev,prevNum,discovered);
			}
		}
	}

}
