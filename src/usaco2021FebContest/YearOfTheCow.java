package usaco2021FebContest;

import java.io.*;
import java.util.*;

public class YearOfTheCow {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		int[] numbers = new int[n];
		TreeSet<Integer> nums = new TreeSet<>();
		ArrayList<Integer> diffs = new ArrayList<>();
		
		for(int i = 0; i<n; i++) {
			numbers[i] = Integer.valueOf(br.readLine());
			int x = (numbers[i]/12 + 1)*12;
			numbers[i] = x;
			nums.add(x);
		}
		Arrays.sort(numbers);
		
		int prev = numbers[0];
		for(int x:nums) {
			if(x!=prev) {
				diffs.add(x-prev+12);
				prev = x;
			}
		}
		diffs.add(12);
		Collections.sort(diffs);
		int sum = 0;
		//first portal to go back, no time skip
		for(int i = 0; i<k-1; i++) {
			sum+=diffs.get(i);
		}
		System.out.print(sum);
	}

}
