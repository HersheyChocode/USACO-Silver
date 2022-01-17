package usaco2021FebContest;

import java.io.*;
import java.util.*;

public class YearOfTheCow2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int k = Integer.valueOf(st.nextToken());
		
		int[] numbers = new int[n];
		int[] diffs = new int[n];
		
		for(int i = 0; i<n; i++) {
			numbers[i] = Integer.valueOf(br.readLine());
			int x = -(numbers[i]+11)/12;
			numbers[i] = x;
		}
		Arrays.sort(numbers);
		
		for(int i = 0; i<n; i++) {
			numbers[i] = - numbers[i];
		}
		int total = numbers[0];
		
		diffs[n-1] = numbers[n-1]-0;
		for(int i = 0; i<n-1; i++) {
			diffs[i] = numbers[i]-numbers[i+1];
		}
		Arrays.sort(diffs);
		int sum = 0;
		for(int i = 0; i<k-1; i++) {
			sum+=diffs[n-1-i]-1;
		}
		
		System.out.println((total-sum)*12);
	}

}