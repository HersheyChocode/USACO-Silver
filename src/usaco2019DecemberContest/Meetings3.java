package usaco2019DecemberContest;

import java.io.*;
import java.util.*;

public class Meetings3 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int l = Integer.valueOf(st.nextToken());
		
		Cows[] cows = new Cows[n];
		int tw = 0;
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			cows[i] = new Cows(Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),Integer.valueOf(st.nextToken()),i);
			tw += cows[i].w;
		} 
		Arrays.sort(cows);

	}

}
