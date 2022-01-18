package usaco2015Contest;

import java.io.*;
import java.util.*;

public class Bcount {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		int q = Integer.valueOf(st.nextToken());
		
		int[] nums = new int[n];
		
		int[] b1 = new int[n];
		int[] b2 = new int[n];
		int[] b3 = new int[n];
		
		int[][] queries = new int[q][2];
		
		for(int i = 0; i<n; i++) {
			nums[i] = Integer.valueOf(br.readLine());
			
			if(nums[i]==1) {
				if(i==0) {
					b1[0] = 1;
					b2[0] = 0;
					b3[0] = 0;
				}
				else {
					b1[i] = b1[i-1]+1;
					b2[i] = b2[i-1];
					b3[i] = b3[i-1];
				}
				
			} else if(nums[i]==2) {
				if(i==0) {
					b1[0] = 0;
					b2[0] = 1;
					b3[0] = 0;
				}
				else {
					b1[i] = b1[i-1];
					b2[i] = b2[i-1]+1;
					b3[i] = b3[i-1];
				}
			} else {
				if(i==0) {
					b1[0] = 0;
					b2[0] = 0;
					b3[0] = 1;
				}
				else {
					b1[i] = b1[i-1];
					b2[i] = b2[i-1];
					b3[i] = b3[i-1]+1;
				}
			}
		}
		//System.out.println(Arrays.toString(nums));
		//System.out.println(Arrays.toString(b1));
		//System.out.println(Arrays.toString(b2));
		//System.out.println(Arrays.toString(b3));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
		for(int i = 0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			queries[i][0] = Integer.valueOf(st.nextToken())-1;
			queries[i][1] = Integer.valueOf(st.nextToken())-1;
			//System.out.println(queries[i][0]+" "+queries[i][1]);
			String x;
			if(queries[i][1]==0) {
				//System.out.println(b1[0]+ " " + b2[0] + " " + b3[0]);
				x = (b1[0]+ " " + b2[0] + " " + b3[0]+"\n");
			} else if(queries[i][0]==0){
				//System.out.print(b1[queries[i][1]]+ " ");
				//System.out.print(b2[queries[i][1]]+" ");
				//System.out.println(b3[queries[i][1]]);
				x = (b1[queries[i][1]]+ " "+b2[queries[i][1]]+" "+b3[queries[i][1]]+"\n");
			} else {
				//System.out.print(b1[queries[i][1]]-b1[queries[i][0]-1]+ " ");
				//System.out.print(b2[queries[i][1]]-b2[queries[i][0]-1]+" ");
				//System.out.println(b3[queries[i][1]]-b3[queries[i][0]-1]);
				x = ((b1[queries[i][1]]-b1[queries[i][0]-1])+ " "+(b2[queries[i][1]]-b2[queries[i][0]-1])+" "+(b3[queries[i][1]]-b3[queries[i][0]-1])+"\n");
			}
			//System.out.println(x);
			pw.write(x);
		}
		pw.close();
		br.close();
		
	}

}
