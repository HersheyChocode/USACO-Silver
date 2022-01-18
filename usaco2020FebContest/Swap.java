package usaco2020FebContest;

import java.io.*;
import java.util.*;

public class Swap {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("swap.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		int[][] ms = new int[m][2];
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			ms[i][0] = Integer.valueOf(st.nextToken())-1;
			ms[i][1] = Integer.valueOf(st.nextToken())-1;	
		}
		
		int[] arr = new int[n];
		ArrayList<Integer>[] positions = new ArrayList[n];
		for(int i = 0; i<n; i++) {
			arr[i] = i;
			positions[i] = new ArrayList<>();
		}
		for(int i = 0; i<m; i++) {
			int l = ms[i][0];
			int r = ms[i][1];
			for(int j = 0; j<(r+1-l)/2; j++) {
				int x = arr[j+l];
				int y = arr[r-j];
				arr[r-j] = x;
				arr[j+l] = y;
			}
		}
		boolean[] visited1 = new boolean[n];
		int[] compPos = new int[n];
		int[] arrPos = new int[n];
		for(int i = 0; i<n; i++) {
			int x = i;
			while(!visited1[x]) {
				visited1[x] = true;
				positions[i].add(x);
				compPos[x] = i;
				arrPos[x] = positions[i].size()-1;
				x = arr[x];
			}
		}
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
		for(int i = 0; i<n; i++) {
			int cPos = compPos[i];
			int aPos = arrPos[i];
			pw.println(positions[cPos].get((k+aPos)%positions[cPos].size())+1);
			
		}
		
		br.close();
		pw.close();
	}

}
