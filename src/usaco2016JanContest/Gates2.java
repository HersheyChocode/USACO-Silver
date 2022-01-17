package usaco2016JanContest;

import java.io.*;
import java.util.*;

public class Gates2 {
	static int n;
	static int maxX;
	static int maxY;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("gates.in"));
		
		n = Integer.valueOf(br.readLine());
		String st = br.readLine();
		int[][] grid = new int[n][2];
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		maxY = Integer.MIN_VALUE;
		
		int currentX = 0;
		int currentY = 0;
		for(int i = 0; i<n; i++) {
			if(st.charAt(i)=='N') currentY+=2;
			else if(st.charAt(i)=='S') currentY-=2;
			else if(st.charAt(i)=='E') currentX+=2;
			else if(st.charAt(i)=='W') currentX-=2;
			minX = Math.min(minX, currentX);
			minY = Math.min(minY, currentY);
			grid[i][0] = currentX;
			grid[i][1] = currentY;
			maxX = Math.max(currentX, maxX);
			maxY = Math.max(currentY, maxY);
		}
		minX = Math.abs(minX)+2;
		minY = Math.abs(minY)+2;
		maxX += Math.abs(minX);
		maxY += Math.abs(minY);

		//System.out.println(minX + " " + minY);
		//System.out.println(maxX + " " + maxY);
		
		ArrayList<int[]>[][] adjacencyList = new ArrayList[maxX+3][maxY+3];
		for(int i = 0; i<maxX+3; i++) {
			for(int j = 0; j<maxY+3; j++) {
				adjacencyList[i][j] = new ArrayList<>();
			}
		}
		
		int[] prev = {minX,minY};
		for(int i = 0;i<n;i++) {
			grid[i][0]+=Math.abs(minX);
			grid[i][1]+=Math.abs(minY);
			
			adjacencyList[prev[0]][prev[1]].add(new int[] {grid[i][0],grid[i][1]});
			adjacencyList[grid[i][0]][grid[i][1]].add(new int[] {prev[0],prev[1]});
			prev = new int [] {grid[i][0],grid[i][1]};
			
		}
		
		
		boolean discovered[][] = new boolean[maxX+3][maxY+3];
		int counter = 0;
		for(int i = 1; i<maxX+3; i+=2) {
			for(int j = 1; j<maxY+3; j+=2) {
				if(!discovered[i][j] && isValid(i,j)) {
					counter++;
					//System.out.println(i+" "+j + "main loop");
					DFS(i,j, adjacencyList, discovered);
				}
				
			}
		}
		
		System.out.println(counter);
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
		pw.println(counter-1);
		
		br.close();
		pw.close();
	}
	public static boolean isValid(int x, int y) {
		if(x<=0 || y<=0 || x>=maxX+3 || y>=maxY+3 || x%2==0 ||y%2==0) {
			return false;
		} 
		return true;
	}
	public static void DFS(int x, int y, ArrayList<int[]>[][] adjacencyList, boolean discovered[][]) {
		if(!discovered[x][y]) {
			//System.out.println(x+ " " + y);
			discovered[x][y] = true;
			boolean top=true,bottom=true,left=true,right=true;
			int size = adjacencyList[x-1][y-1].size();
			for(int i = 0; i<size; i++) {
				if(adjacencyList[x-1][y-1].get(i)[0]==x+1 && adjacencyList[x-1][y-1].get(i)[1]==y-1) bottom = false;
				if(adjacencyList[x-1][y-1].get(i)[0]==x-1 && adjacencyList[x-1][y-1].get(i)[1]==y+1) left = false;
			}
			size = adjacencyList[x+1][y+1].size();
			for(int i = 0; i<size; i++) {
				if(adjacencyList[x+1][y+1].get(i)[0]==x+1 && adjacencyList[x+1][y+1].get(i)[1]==y-1) right = false;
				if(adjacencyList[x+1][y+1].get(i)[0]==x-1 && adjacencyList[x+1][y+1].get(i)[1]==y+1) top = false;
			}
			/*if(top && isValid(x,y+2)) {
				System.out.println(x + " " +(y+2) + "top");
			}
			if(left && isValid(x-2,y)) {
				System.out.println((x-2) + " " +y + "left");
			}
			if(bottom && isValid(x,y-2)) {
				System.out.println(x + " " +(y-2) + "bottom");
			}
			if(right && isValid(x+2,y)) {
				System.out.println((x+2) + " " +(y) + "right");
			}*/
			if(top && isValid(x,y+2)) {
				DFS(x,y+2, adjacencyList,  discovered); 
			}
			if(left && isValid(x-2,y)) {
				DFS(x-2,y,adjacencyList, discovered); 
			}
			if(bottom && isValid(x,y-2)) {
				DFS(x,y-2,adjacencyList, discovered); 
			}
			if(right && isValid(x+2,y)) {
				DFS(x+2,y,adjacencyList, discovered); 
			}
		}
	}

}
