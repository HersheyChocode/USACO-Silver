package usaco2016OpenContest;

import java.io.*;
import java.util.*;

public class DiamondCollector {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		int[] sizes = new int[n];
		for (int i = 0; i < n; i++) {
			sizes[i] = Integer.valueOf(br.readLine());
		}
		Arrays.sort(sizes);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		int[] maxes = new int[n];
		for(int i = 0; i<n; i++) {
			int lo = i; int hi = n;
			while(lo+1<hi) {
				int mid = (lo+hi)/2;
				if(sizes[mid]-sizes[i]<=k) lo = mid;
				else hi = mid;
			}
			maxes[i] = lo;
		}
		int max = -1;
		for(int i = 0; i<n; i++) {
			for(int j = maxes[i]; j<n; j++) {
				int val = -10;
				val = maxes[i]-i+maxes[j]-j+2;
				int overlap = maxes[i]-j+1;
				if(overlap>0) val-=overlap;
				if(val>max) max = val;
			}
		}
		pw.println(max);
		System.out.println(max);
		pw.close();
		br.close();
	
	}

}
