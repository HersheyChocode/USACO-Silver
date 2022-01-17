package bovineGenomics;

import java.io.*;
import java.util.*;

public class Cownomics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());

		String[][] genes = new String[2 * n][m];
		
		int[][] base4genes = new int[2*n][m];

		for (int i = 0; i < 2 * n; i++) {
			st = new StringTokenizer(br.readLine());
			String genome = st.nextToken();
			for (int j = 0; j < m; j++) {
				genes[i][j] = genome.substring(j, j + 1);
				
				int val = 0;
				if (genes[i][j].equals("A")) { base4genes[i][j]=0; }
				else if (genes[i][j].equals("C")) { base4genes[i][j]=1; }
				else if (genes[i][j].equals("T")) { base4genes[i][j]=2; }
				else if (genes[i][j].equals("G")) { base4genes[i][j]=3; }
			}
		}
		
		int baseCounter = 0;
		for(int i = 0; i<m-2; i++) {
			for(int j = i+1; j<m-1; j++) {
				for(int k = j+1; k<m; k++) {
					
					String[] first = new String[n];
					
					
					int[] firstArr = new int[n];
					boolean[] secondArr = new boolean[64];
					
					for(int g = 0; g<n; g++) {
						
						int base4 = base4genes[g+n][i] + base4genes[g+n][j]*4 + base4genes[g+n][k]*16;
						secondArr[base4] = true;
						
						base4 = base4genes[g][i] + base4genes[g][j]*4 + base4genes[g][k]*16;
						firstArr[g] = base4;
						
						
						first[g] = genes[g][i] + genes[g][j] + genes[g][k];
					}
					
					
					boolean dontContinue = false; 
					for(int g = 0; g<n&&!dontContinue; g++) {
						
						if(secondArr[firstArr[g]] == true) { dontContinue = true;}
					}
					
					if(!dontContinue) { baseCounter++; }
					
				}
			}
		}
		
		
		System.out.println(baseCounter);
		br.close();
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		pw.write(String.valueOf(baseCounter));
		pw.close();

	}

}
