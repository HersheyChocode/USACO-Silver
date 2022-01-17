package usaco2021JanContest;

import java.io.*;
import java.util.*;

public class NoTimeToPaint {

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
		char[] paint = new char[n];
		for(int i = 0; i<n; i++) {
			paint[i] = word.charAt(i);
		}
		//System.out.println('d'-'a');
		for(int i = 0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int q1 = Integer.valueOf(st.nextToken())-1;
			int q2 = Integer.valueOf(st.nextToken());
			char min = '|';
			char max = '.';
			boolean[] alph = new boolean[26];
			int total = 0;
			for(int j = 0; j<q1; j++) {
				if(!alph[alphabet.get(paint[j])]) {
					total++;
					alph[alphabet.get(paint[j])] = true;
				}
			}
			alph = new boolean[26];
			for(int j = q2; j<n; j++) {
				if(!alph[alphabet.get(paint[j])]) {
					total++;
					alph[alphabet.get(paint[j])] = true;
				}
			}
			System.out.println(total);
		}
	}

}
