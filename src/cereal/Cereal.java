package cereal;

import java.io.*;
import java.util.*;

public class Cereal {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		int[][] nums = new int[n][2];
		boolean[] available = new boolean[m];
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			nums[i][0] = Integer.valueOf(st.nextToken())-1;
			nums[i][1] = Integer.valueOf(st.nextToken())-1;
		}
		
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
		for(int i = 0; i<n; i++) {
			available = new boolean[m];
			int counter = 0;
			for(int j = i; j<n; j++) {
				if(available[nums[j][0]]) {
					if(!available[nums[j][1]]) {
						available[nums[j][1]] = true;
						counter++;
					}
				} else {
					available[nums[j][0]] = true;
					counter++;
				}
			}
			//System.out.println(counter);
			pw.write(String.valueOf(counter)+"\n");
		}
		
		pw.close();
		br.close();
	}

}
