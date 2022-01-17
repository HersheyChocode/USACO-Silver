package berryPicking;

import java.io.*;
import java.util.*;

public class Berries {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("berries.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		
		
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		int max = Integer.MIN_VALUE;
		for(int i = 1; i<nums[n-1]; i++) {
			int[] nums2 = nums.clone();
			
			int count = 0;
			boolean works = true;
			
			int counter = n-1;
			for(int j = 0; counter>=0 && j<k/2; j++) {
				
				if(nums2[counter]/i >=1) {
					nums2[counter]-=i;
				} else {
					counter--;
					if(counter>= 0 && nums2[counter]/i >=1) {
						nums2[counter]-=i;
					} else {
						works = false;
					}
				}
			}
			
			if(works) {
				Arrays.sort(nums2);
				counter = n-1;
				int numBaskets = 0;
				for(int j = 0; counter>=0 && j<k/2; j++) {
					if(nums2[counter]/i >=1) {
						nums2[counter]-=i;
						count+=i;
						numBaskets++;
						//System.out.println(i + " " + counter);
					} else {
						counter--;
						if(counter>=0 && nums2[counter]/i >=1) {
							nums2[counter]-=i;
							count+=i;
							numBaskets++;
						}
					}
				}
				if(numBaskets<k/2) {
					Arrays.sort(nums2);
					for(int j = n-1; j>=0 && numBaskets<k/2; j--) {
						count+=nums2[j];
						numBaskets++;
					}
				}
			}
			
			max = Math.max(count, max);
			//System.out.println(max+" " +i);
		}
		System.out.println(max);
		pw.write(String.valueOf(max));
		
		pw.close();
		br.close();
	}

}
