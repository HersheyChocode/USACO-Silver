package wheresBessie;

import java.io.*;
import java.util.*;

public class Where {

	public static int n;
	public static String[][] xyChars;

	public static int counter = 0;
	public static String numColors = "";

	public static int x1root, y1root;
	public static int x2root, y2root;
	public static int[] xList = { -1, 1, 0, 0 };
	public static int[] yList = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		// File Input Example 1:
		// Reading in one piece of data per line

		// Note: In USACO, the first line will always tell you the number of subsequent
		// lines you need to read in
		// 1. open the file
		BufferedReader br = new BufferedReader(new FileReader("where.in"));

		// 2. create a StringTokenizer object to read in the first line of data
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 3. get the first numbers from the first line
		n = Integer.parseInt(st.nextToken());

		// 3. create array of size NxM to store data
		xyChars = new String[n][n];

		// 4. read in the remaining lines. note that you only want to update the
		// StringTokenizer once per line of data
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			String x = (st.nextToken());
			for (int j = 0; j < n; j++) {
				xyChars[i][j] = x.substring(j, j + 1);
			}
		}

		// 5. close file
		br.close();

		boolean[][] discovered = new boolean[n][n];
		
		ArrayList<int[]> PCLS = new ArrayList<int[]>();

		for (int x1 = 0; x1 < n; x1++) {
			for (int y1 = 0; y1 < n; y1++) {
				for (int x2 = 0; x2 < n; x2++) {
					for (int y2 = 0; y2 < n; y2++) {
						
						if (x1 <= x2 && y1 <= y2) {
							
							for (int i = 0; i < n; i++) {
								Arrays.fill(discovered[i], false);
							}
							x1root = x1;
							y1root = y1;
							x2root = x2;
							y2root = y2;
							
							counter = 0;
							numColors = "";
							
							//System.out.println(x1root + " " + y1root + " bottom left" + x2root + " " + y2root);
							
							for(int x = x1; x<=x2; x++) {
								for(int y = y1; y<= y2; y++) {
									if(!discovered[x][y]) {
										if(numColors.indexOf(xyChars[x][y])==-1) { counter++; }
										numColors+=xyChars[x][y];
										DFS(x,y, discovered);
									}
								}
							}
							
							if(counter==2)
							{
								String string1 = numColors.substring(0,1);
								int[] colors = {0,0};
								for(int i = 0; i<numColors.length(); i++) {
									if(numColors.substring(i,i+1).equals(string1)) {
										colors[0] ++;
									} else { colors[1]++;}
								}
								
								if((colors[0]==1 && colors[1] >1 )|| colors[1]==1 && colors[0]>1) {
									PCLS.add(new int[] {x1, y1, x2, y2});
								}
							}
							
						}
					}
				}
			}
		}
		
		//for (int i = 0; i < n; i++) {
		//	Arrays.fill(discovered[i], false);
		//}
		/*x1root = 0;
		y1root = 0;
		x2root = 3;
		y2root = 2;
		DFS(0,0, discovered);*/
		
		int numPCLS = 0;
		for(int i = 0; i<PCLS.size(); i++) {
			//System.out.println(Arrays.toString(PCLS.get(i)));
			boolean metOne = true;
			for(int j = 0; j<PCLS.size(); j++)
			{
				// i = top
				if(PCLS.get(i)[0]>=PCLS.get(j)[0] && PCLS.get(i)[2]<=PCLS.get(j)[2]  && i!=j) {
					if(PCLS.get(i)[1]>=PCLS.get(j)[1]&& PCLS.get(i)[3]<=PCLS.get(j)[3])
					{
						metOne = false;
					}
				} /*else if (PCLS.get(i)[2]<PCLS.get(j)[2] && PCLS.get(i)[0]>=PCLS.get(j)[0]) {
					metOne = false;
				} else if(PCLS.get(i)[3]<PCLS.get(j)[3] && PCLS.get(i)[1]>=PCLS.get(j)[1]) {
					metOne = false;
				} else if(PCLS.get(i)[1]>PCLS.get(j)[1] && PCLS.get(i)[3]<=PCLS.get(j)[3]) {
					metOne = false;
				}*/
			}
			if(metOne) { numPCLS++;}
		}
		
		System.out.println(numPCLS);
		
		

		// File Output Example:

		// 1. open the file
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));

		// 2. write to file
		pw.write(String.valueOf(numPCLS));

		// 3. close file
		pw.close();
	}

	public static boolean ifPossible(int x1, int y1) {
		if (x1 < x1root || x1 < 0 || x1 > x2root || x1 > n || y1 < y1root || y1 < 0 || y1 > y2root || y1 > n) {
			return false;
		}
		return true;
	}

	public static void DFS(int x1, int y1, boolean[][] discovered) {
		// TODO Auto-generated method stub
		
		//System.out.println(x1 +" " + y1);

		if (ifPossible(x1, y1) && discovered[x1][y1] == false) {
			
			//System.out.println("made it" + x1+ ' ' +y1);

			String character = xyChars[x1][y1];
			discovered[x1][y1] = true;

			int numNotEqual = 0;
			int numPossible = 0;

			boolean foundOne = false; //checks if it is connected to one same letter that wasn't found yet
			boolean hasOne = false; //checks if it is connected to one same letter that was already found

			for (int i = 0; i < 4; i++) {
				
				int nx = x1 + xList[i];
				int ny = y1 + yList[i];

				if (ifPossible(nx, ny)) {
					
					//System.out.println(x1+ " "+ y1 + "\t" + x1+xList[i] + "\t" + y1+yList[i]);

					numPossible += 1;

					
					/*if (!xyChars[nx][ny].equals(character)) {
						numNotEqual += 1;
					} else {
						if (discovered[nx][ny] == true) {
							hasOne = true;
						} else {
							foundOne = true;
							discovered[nx][ny]= true;
						}
					}*/
					if(xyChars[nx][ny].equals(character)) {
						DFS(nx, ny, discovered);
					}
				}
			}

			/*if (numNotEqual == numPossible) {
				//System.out.println(x1+ " "+ y1);
				if(numColors.indexOf(character)==-1) { counter+=1;}
				numColors += character;
				System.out.println("Here");
			} else if (foundOne && !hasOne) {
				if(numColors.indexOf(character)==-1) { counter+=1;}
				numColors += character;
				System.out.println("Here 2");
			}*/
		}
	}
}
