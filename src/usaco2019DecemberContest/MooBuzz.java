package usaco2019DecemberContest;

import java.io.*;

public class MooBuzz {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
		long n = Integer.valueOf(br.readLine());
		int[] nums = {1,2,4,7,8,11,13,14};
		if(n<9) {
			pw.write(String.valueOf(nums[(int) (n-1)]));
		} else {
			long x = n%8;
			if(x!=0) {
				x-=1;
				pw.write(String.valueOf(nums[(int) x]+15*(n/8)));
				System.out.println(nums[(int) x]+15*(n/8));
			} else {
				System.out.println(-1+15*(n/8));
				pw.write(String.valueOf(-1+15*(n/8)));
			}
		}
		br.close();
		pw.close();
		
	}

}
//1720478654