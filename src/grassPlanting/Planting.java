package grassPlanting;

import java.io.*;
import java.util.*;

public class Planting {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("planting.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		
		int n = Integer.valueOf(st.nextToken());
		ArrayList<Integer>[] adjList = new ArrayList[n];
		for(int i =0;i<n; i++) {
			adjList[i] = new ArrayList<>();
		}
		for(int i = 0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.valueOf(st.nextToken())-1;
			int y = Integer.valueOf(st.nextToken())-1;
			adjList[x].add(y);
			adjList[y].add(x);
		}
		int max = 0;
		for(int i = 0; i<n; i++) {
			if(adjList[i].size()>max) max = adjList[i].size();
		}
		pw.println(max+1);
		pw.close();
		
	}

}
