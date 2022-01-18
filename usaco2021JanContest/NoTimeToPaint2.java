package usaco2021JanContest;

import java.io.*;
import java.util.*;

public class NoTimeToPaint2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.valueOf(st.nextToken());
		int q = Integer.valueOf(st.nextToken());
		char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		HashMap<Character,Integer> alphabet = new HashMap<>();
		for(int i = 0; i<26; i++) {
			alphabet.put(alphabets[i], i);
		}
		
		
		String word = br.readLine().toLowerCase();
		int[] paint = new int[n];
		for(int i = 0; i<n; i++) {
			paint[i] = alphabet.get(word.charAt(i));
		}
		
		PrintWriter pw = new PrintWriter(System.out);
		
		int[] minLast = new int[26];
		int[] prefix = new int[n];
		int numStrokes = 0;
		Arrays.fill(minLast, 27);
		for(int i = 0; i<n; i++) {
			int l = paint[i];
			
			if(minLast[l]==27) {
				numStrokes++;
			}
			
			for(int j = 0; j<26;j++) {
				if(l<minLast[j] && minLast[j]!=27) minLast[j] = l;
			}
			if(minLast[l]<l) numStrokes++;
			minLast[l] = l;
			prefix[i] = numStrokes;
		}
		
		minLast = new int[26];
		int[] suffix = new int[n];
		numStrokes = 0;
		Arrays.fill(minLast, 27);
		for(int i = n-1; i>=0; i--) {
			int l = paint[i];
			
			if(minLast[l]==27 || minLast[l]<l) {
				numStrokes++;
			}
			
			for(int j = 0; j<26;j++) {
				if(l<minLast[j] && minLast[j]!=27) minLast[j] = l;
			}
			minLast[l] = l;
			suffix[i] = numStrokes;
		}
		//System.out.println(Arrays.toString(prefix));
		//System.out.println(Arrays.toString(suffix));
		
		for(int i = 0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int q1 = Integer.valueOf(st.nextToken());
			int q2 = Integer.valueOf(st.nextToken());
			int sum = 0;
			if(q2==n) {
				if(q1>1) {
					sum = prefix[q1-2];
				}
			}
			if(q1==1) {
				sum = suffix[q2];
			}
			else sum = prefix[q1-2]+suffix[q2];
			System.out.println(sum);
		}
	}

}
