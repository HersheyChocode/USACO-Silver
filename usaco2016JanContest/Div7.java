package usaco2016JanContest;

import java.io.*;
import java.util.*;

public class Div7 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader("div7.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
		
		int n = Integer.valueOf(br.readLine());
		int[] nums = new int[n];
		long[] prefixSum = new long[n];
		ArrayList<Integer>[] remainderSum = new ArrayList[7];
		
		for(int i = 0; i<7; i++) {
			remainderSum[i] = new ArrayList<>();
		}
		
		int max = 0;
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(br.readLine());
			if (nums[i]>max) max = nums[i];
			
			if(i!=0) prefixSum[i] =  prefixSum[i-1] + nums[i];
			else prefixSum[0] = nums[i];
			
			int remainder = (int) (prefixSum[i]%7);
			remainderSum[remainder].add(i);
		}
				
		max = -1;
		for(int i = 0; i<7; i++) {
			int max1 = Integer.MIN_VALUE;
			int min1 = Integer.MAX_VALUE;
			
			for(int j = 0; j<remainderSum[i].size(); j++) {
				if(remainderSum[i].get(j)>max1) max1 = remainderSum[i].get(j);
				if(remainderSum[i].get(j)<min1) min1 = remainderSum[i].get(j);
			}
			
			max = Math.max(max1-min1, max);
		}
		
		System.out.println(max);
		pw.println(max);
		br.close();
		pw.close();
	}

}
