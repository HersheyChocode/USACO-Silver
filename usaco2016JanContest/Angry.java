package usaco2016JanContest;

import java.io.*;
import java.util.*;


public class Angry {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader("angry.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		int[] nums = new int[n];
		
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(br.readLine());
		}
		
		Arrays.sort(nums);
				
		int lo = 0;
		int hi = 500000000;
		while(lo+1<hi) {
			int mid = (lo+hi)/2;
			
			int sum = 0;
			int min = nums[0];
			int counter = 0; //position in nums
			int numTurns = 1; //# of "segments"
			boolean valid = true;
			
			while(numTurns<=k && sum<=2*mid && counter<n) {
				if (nums[counter] - min<=2*mid) {
					sum = nums[counter] - min;
					counter++;
				}
				else {
					numTurns++;
					min = nums[counter];
					sum = 0;
					counter++;
				}
				if(numTurns>k) valid = false;
			}
			
			if(valid) hi = mid;
			else lo = mid;
		}
		
		int current = nums[0];
		int counter = 1;
		for(int i = 1; i<n; i++) {
			if(nums[i] == current) counter++;
		}
		if(counter==n) pw.println(0);
		else pw.println(hi);
		System.out.println(hi);
		br.close();
		pw.close();
	}

}
