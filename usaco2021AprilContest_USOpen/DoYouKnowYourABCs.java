package usaco2021AprilContest_USOpen;

import java.io.*;
import java.util.*;

public class DoYouKnowYourABCs {
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.valueOf(st.nextToken());
		
		for(int i = 0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.valueOf(st.nextToken());
			int[] nums  = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				nums[j] = Integer.valueOf(st.nextToken());
			}
			Arrays.sort(nums);
			TreeSet<Integer> possibilities = new TreeSet<>();
			for(int j = n-1; j>=0; j--) {
				possibilities.add(nums[j]);
				for(int k = j-1; k>=0; k--) {
					possibilities.add(nums[j]-nums[k]);
					for(int l = k-1; l>=0; l--) {
						if(nums[j]-nums[k]-nums[l]>0) possibilities.add(nums[j]-nums[k]-nums[l]);
					}
				}
			}
			ArrayList<Integer> ps = new ArrayList<>();
			for(int a: possibilities) {
				ps.add(a);
			}
			int total = 0; 
			for(int a = 0; a<ps.size(); a++) {
				int A = ps.get(a);
				for(int b = a; b<ps.size(); b++) {
					int B = ps.get(b);
					for(int c = b; c<ps.size(); c++) {
						int C = ps.get(c);
						boolean valid = true;
						int count = 0;
						for(int j = 0; j<n && valid; j++) {
							if(!(nums[j]==A || nums[j]==B || nums[j]==C || nums[j]==A+B || nums[j]==A+C || nums[j]==B+C || nums[j]==A+B+C)) {
								valid = false;
							} else count++;
						}
						if(count==n) {
							total++;
						}
					}
				}
			}
			System.out.println(total);
		}
		
		
	}
}
