package secretCowCode;

import java.io.*;
import java.util.*;

public class CowCode {

	static long n;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader bf = new BufferedReader(new FileReader("cowcode.in"));

		String st = bf.readLine();

		n = Long.valueOf(st.substring(st.indexOf(" ") + 1)) -1;
		st = st.substring(0, st.indexOf(" "));

		//System.out.println(n + st + "    " + Integer.MAX_VALUE);
		
		int len = st.length();
		long totalLen = len;
		
		while(totalLen<n) {
			totalLen *= 2;
		}
		
		while(totalLen>len) {
			totalLen /= 2;
			if(n >= totalLen) {
				n-=totalLen;
				n--;
				
				if(n==-1) {
					n = totalLen-1;
				}
			}
		}

		bf.close();
		
		//st = recursive(st);
		
		/*if (n>Integer.MAX_VALUE) {
			st = st.substring(Integer.MAX_VALUE);
			n -= Integer.MAX_VALUE;
		}*/
		
		System.out.println(st.charAt((int) n));

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
		pw.write(st.substring((int) n, (int) n+1));
		pw.close();
	}

	public static String recursive(String st) {
		
		if (st.length() >= n) {
			return st;
		} 
		st += st.substring(st.length() - 1) + st.substring(0, st.length() - 1);
		st = recursive(st);
		
		return st;
	}
	

}