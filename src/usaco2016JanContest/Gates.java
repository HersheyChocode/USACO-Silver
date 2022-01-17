package usaco2016JanContest;

import java.io.*;
import java.util.*;

public class Gates {
	static int n;
	static int counter = 0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("gates.in"));
		
		n = Integer.valueOf(br.readLine());
		String st = br.readLine();
		int[][] grid = new int[n][2];
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;
		
		int currentX = 0;
		int currentY = 0;
		for(int i = 0; i<n; i++) {
			if(st.charAt(i)=='N') currentY++;
			else if(st.charAt(i)=='S') currentY--;
			else if(st.charAt(i)=='E') currentX++;
			else if(st.charAt(i)=='W') currentX--;
			minX = Math.min(minX, currentX);
			minY = Math.min(minY, currentY);
			grid[i][0] = currentX;
			grid[i][1] = currentY;
			maxX = Math.max(currentX, maxX);
			maxY = Math.max(currentY, maxY);
		}
		minX = Math.abs(minX);
		minY = Math.abs(minY);
		maxX += Math.abs(minX);
		maxY += Math.abs(minY);
		int[][] numOverlaps = new int[maxX+1][maxY+1];
		numOverlaps[0][0] = 1;
		
		ArrayList<int[]>[][] adjacencyList = new ArrayList[maxX+1][maxY+1];
		for(int i = 0; i<maxX+1; i++) {
			for(int j = 0; j<maxY+1; j++) {
				adjacencyList[i][j] = new ArrayList<>();
			}
		}
		
		int[] prev = {minX,minY};
		for(int i = 0;i<n;i++) {
			grid[i][0]+=Math.abs(minX);
			grid[i][1]+=Math.abs(minY);
			numOverlaps[grid[i][0]][grid[i][1]]+=1;
			
			adjacencyList[prev[0]][prev[1]].add(new int[] {grid[i][0],grid[i][1]});
			adjacencyList[grid[i][0]][grid[i][1]].add(new int[] {prev[0],prev[1]});
			prev = new int [] {grid[i][0],grid[i][1]};
		}
		boolean[][] discovered = new boolean[maxX][maxY];
		int numIsolated = 0;
		for(int i = 0; i<maxX; i++) {
			for(int j = 0; j<maxY; j++) {
				discovered = new boolean[maxX][maxY];
				int currCount = counter;
				if(!discovered[i][j]) DFS(i,j,numOverlaps, discovered, new int[] {-1,-1}, new int[] {i,j}, adjacencyList);
				if(counter==currCount) numIsolated++;
			}
		}
		
		System.out.println(counter+ " " + numIsolated);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
		pw.println(counter/4);
		
		br.close();
		pw.close();
	}
	
	public static void DFS(int x, int y, int[][]numOverlaps, boolean[][] discovered, int[] prev, int[] origin, ArrayList<int[]>[][] adjacencyList) {
		if(!discovered[x][y]) {
			discovered[x][y] = true;
			numOverlaps[x][y]--;
			boolean switched = false;
			for(int i = 0; !switched && i< adjacencyList[x][y].size(); i++) {
				if(adjacencyList[x][y].get(i)[0] == x && adjacencyList[x][y].get(i)[1]==y-1&& prev[1]!=y-1) {
					if(y-1>=0) {
						DFS(x,y-1,numOverlaps,discovered,new int[] {x,y}, origin, adjacencyList); 
						//System.out.println(x + " " + y + " " + x + " " + (y-1));
						switched = true;
					}
				}
			}
			for(int i = 0;!switched && i< adjacencyList[x][y].size(); i++) {
				if(adjacencyList[x][y].get(i)[0] == x-1 && adjacencyList[x][y].get(i)[1]==y&& prev[0]!=x-1) {
					if(x-1>=0) {
						DFS(x-1,y,numOverlaps,discovered,new int[] {x,y}, origin, adjacencyList); 
						//System.out.println(x + " " + y + " " + (x-1) + " " + (y));
						switched = true;
					}
				}
			}
			for(int i = 0; !switched && i< adjacencyList[x][y].size(); i++) {
				if(adjacencyList[x][y].get(i)[0] == x && adjacencyList[x][y].get(i)[1]==y+1&&  prev[1]!=y+1) {
					if(y+1<discovered[0].length) {
						DFS(x,y+1,numOverlaps,discovered,new int[] {x,y}, origin, adjacencyList); 
						//System.out.println(x + " " + y + " " + x + " " + (y+1));
						switched = true;
					}
				}
			}
			for(int i = 0;!switched && i< adjacencyList[x][y].size(); i++) {
				if(adjacencyList[x][y].get(i)[0] == x+1 && adjacencyList[x][y].get(i)[1]==y&& prev[0]!=x+1) {
					if(x+1<discovered.length) {
						DFS(x+1,y,numOverlaps,discovered,new int[] {x,y}, origin, adjacencyList); 
						//System.out.println(x + " " + y + " " + (x+1) + " " + (y));
						switched = true;
					}
				}
			}
		} else if(discovered[x][y] && x==origin[0] && y==origin[1]) {
		//	System.out.println(origin[0] + " " + origin[1]);
			counter++;
		}
	}
}
