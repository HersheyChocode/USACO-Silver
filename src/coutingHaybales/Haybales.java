package coutingHaybales;
import java.io.*;
import java.util.*;

public class Haybales {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int q = Integer.valueOf(st.nextToken());
		int[] nums = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}
		Arrays.sort(nums);
		//System.out.println(Arrays.toString(nums));
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
		for(int i = 0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int q1 = Integer.valueOf(st.nextToken());
			int q2 = Integer.valueOf(st.nextToken());
			//System.out.println(q1 + "q1q2 " + q2);
			
			// A = [1, 4, 5, 6, 7, 8]
			//want to find first number >= x inside of A
			// x = 6   A = [0, 0, 0, 1, 1, 1]
			// output hi
			
			// Array of nondecreasing 0s and 1s
			// a = [00000000000000000011111111111111]
			// Goal: output index of last 0 or output index of first 1
			
			//int lo = 0;
			//int hi = n-1;
			//while (lo + 1 < hi) {
				// Loop invariant: a[lo] = 0, a[hi] = 1
				//int mid = (lo + hi)/2;
				//if(nums[mid]==0) lo = mid;
				//else hi = mid;
			//}
			// lo is index of last 0
			// hi is index of first 1
			
			int lo = 0;		
			int hi = n-1;
			while (lo + 1 < hi) {
				// Loop invariant: a[lo] = 0, a[hi] = 1
				int mid = (lo + hi)/2;
				if(nums[mid]>=q1) hi = mid;
				else lo = mid;
			}
			if (nums[0]>=q1) hi = 0; 
			//System.out.print(hi);
			int loVal = hi;
			
			lo = 0;
			hi = n-1;
			while (lo+1<hi) {
				int mid = (lo + hi)/2;
				if(nums[mid]>q2) hi = mid;
				else lo = mid;
			}
			if(nums[n-1]<=q2) {
				lo=n-1;
			}
			//System.out.print(lo);
			
			if ((q1>nums[n-1] && q2>nums[n-1])||(q1<nums[0] && q2<nums[0])) pw.write("0\n");
			else pw.write(String.valueOf(lo-loVal+1)+ "\n");
			//System.out.println("\n" + (lo-loVal+1) + "\n");
		
		}
		pw.close();
	}

}
