package usaco2021AprilContest_USOpen;

import java.io.*;
import java.util.*;

public class Acowdemia {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		int l = Integer.valueOf(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[n];
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		for(int i = 0; i<k; i++) {
			if(nums[0]<n) {
				int lo = 0;
				int hi = n;
				while(lo+1<hi) {
					int mid = (lo+hi)/2;
					if(n-mid>=nums[mid]) lo = mid;
					else hi = mid;
				}
				
				int x = nums[lo];
			//	System.out.println(lo);
				lo = 0;
				hi = n;
				while(lo+1<hi) {
					int mid = (lo+hi)/2;
					if(nums[mid]>x) hi = mid;
					else lo = mid;
					//System.out.println(lo);
				}
				/*int leftover = 0;
				if(lo<l-1) {
					leftover = l-1-lo;
					lo1 = lo+1;
					hi1 = n-leftover-1;
					while(lo1+1<hi1) {
						if()
					}
				}*/
				
				
				
				for(int j = lo; j>=lo-l+1 && j>=0; j--) {
					nums[j]++;
				}
				//System.out.println(Arrays.toString(nums));
			}
			
		}
		
		//System.out.println(Arrays.toString(nums));
		
		PrintWriter pw = new PrintWriter(System.out);
		
		boolean foundOne = true;
		for(int i = 0; i<=n && foundOne; i++) {
			int lo = 0;
			int hi = n;
			while(lo+1<hi) {
				int mid = (lo+hi)/2;
				if(nums[mid]<i) lo = mid;
				else hi = mid;
			}
			if(nums[0]==i) hi = 0;
			if(n-hi<i) {
				pw.println(i-1);
				foundOne = false;
			}
		}
		 if(foundOne) {
			 pw.println(n);
		 }
		 pw.close();
	}
}
