package usaco2021AprilContest_USOpen;

import java.io.*;
import java.util.*;

public class Acowdemia2 {
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
		
		int lo = 1;
		int hi = n;
		while(lo+1<hi) {
			int mid = (lo+hi)/2;
			
			int start = n-mid;
			long total = 0;
			boolean works = true;
			
			for(int i = start; i<n && nums[i]<mid && works; i++) {
				if(nums[i]+k<mid) {
					works = false;
				}
				total+=mid-nums[i];
			}
			
			long product = (long) l* (long) k;
			
			if(total>product) works = false;
			if(works) lo = mid;
			else hi = mid;
		}
		
		boolean works = true;
		long total = 0;
		for(int i = 0; i<n && nums[i]<n && works; i++) {
			if(nums[i]+k<n) {
				works = false;
			}
			total+=n-nums[i];
		}
		long product = (long) l* (long) k;
		if(total>product) works = false;
		if(works) lo = n;
		
		System.out.println(lo);
	}

}
