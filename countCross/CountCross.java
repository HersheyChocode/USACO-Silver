package countCross;

import java.util.*;

import java.io.*;

public class CountCross {

	static boolean[][] grid;
	static int[][] roads;
	static int[][] cows;
	static ArrayList<XYCoord>[][] adjacencyList;

	static XYCoord root = new XYCoord(0, 0);
	static int counter = 0;

	public static void main(String[] args) throws IOException {
		// File Input Example 1:
		// Reading in one piece of data per line

		// Note: In this case, the first line will tell you the number of lines and the
		// number of numbers in each line

		// 1. open the file
		BufferedReader br = new BufferedReader(new FileReader("countcross.in"));

		// 2. create a StringTokenizer object to read in the first line of data
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 3. get the two numbers from the first line
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		grid = new boolean[n][n];
		roads = new int[r][4];
		cows = new int[k][2];

		adjacencyList = new ArrayList[n][n];

		// stores the roads
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				roads[i][j] = Integer.parseInt(st.nextToken()) - 1;
			}

		}

		// creates cow matrix
		for (int i = 0; i < n; i++) {
			Arrays.fill(grid[i], false);
		}

		// stores cows in an array and matrix
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());

			cows[i][0] = Integer.parseInt(st.nextToken()) - 1;
			cows[i][1] = Integer.parseInt(st.nextToken()) - 1;
			grid[cows[i][0]][cows[i][1]] = true;
		}

		// creates adjacency list
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjacencyList[i][j] = new ArrayList<XYCoord>();
				makeAdjacencyList(i, j);
				//System.out.println(Arrays.toString(adjacencyList[i][j].toArray()));
			}
		}

		// PRINT
		/*for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}*/

		boolean[][] discovered = new boolean[n][n];

		// fills discovered array false
		for (int i = 0; i < n; i++) {
			Arrays.fill(discovered[i], false);
		}

		// DFS
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n; j++) {
				Arrays.fill(discovered[j], false);
			}
			root = new XYCoord(cows[i][0], cows[i][1]);
			//System.out.println("\n root" + root);

			DFS(discovered, root);
			//System.out.println(counter + " counter");
		}

		System.out.println((k*k - k - counter)/2);

		PrintWriter pw = new PrintWriter("countcross.out");
		pw.write(String.valueOf((k*k - k - counter)/2));
		pw.close();

	}

	public static void makeAdjacencyList(int i, int j) {

		XYCoord current = new XYCoord(i, j);

		XYCoord x = new XYCoord(i + 1, j);
		XYCoord y = new XYCoord(i - 1, j);
		XYCoord z = new XYCoord(i, j + 1);
		XYCoord a = new XYCoord(i, j - 1);
		if (ifPossible(current, x)) {
			adjacencyList[i][j].add(x);
		}
		if (ifPossible(current, y)) {
			adjacencyList[i][j].add(y);
		}
		if (ifPossible(current, z)) {
			adjacencyList[i][j].add(z);
		}
		if (ifPossible(current, a)) {
			adjacencyList[i][j].add(a);
		}

		/*
		 * for(int k = 0; k<adjacencyList[i][j].size(); k++) {
		 * System.out.print(adjacencyList[i][j] + " HHHHH  "); } System.out.println();
		 */
	}

	public static boolean ifPossible(XYCoord coordinate1, XYCoord coordinate2) {
		int x = coordinate1.x;
		int y = coordinate1.y;
		int x2 = coordinate2.x;
		int y2 = coordinate2.y;
		int n = grid.length;

		if (x < 0 || x > n - 1 || y < 0 || y > n - 1 || x2 < 0 || x2 > n - 1 || y2 < 0 || y2 > n - 1) {
			return false;
		}

		for (int i = 0; i < roads.length; i++) {
			if (roads[i][0] == x && roads[i][1] == y && roads[i][2] == x2 && roads[i][3] == y2) {
				// System.out.println(coordinate1 + "" + coordinate2 + "DIDNT MAKE IT");
				return false;
			}
			if (roads[i][0] == x2 && roads[i][1] == y2 && roads[i][2] == x && roads[i][3] == y) {
				// System.out.println(coordinate1 + " " + coordinate2 + "DIDNT MAKE IT");
				return false;
			}
		}
		return true;
	}

	public static void DFS(boolean discovered[][], XYCoord coordinate) {
		//System.out.println(coordinate);
		int x = coordinate.x;
		int y = coordinate.y;

		if (discovered[x][y] == false) {
			discovered[x][y] = true;
			if (grid[x][y] == true && !coordinate.equals(root)) {
				counter+=1;
			}
			for (int i = 0; i < adjacencyList[x][y].size(); i++) {
				XYCoord l = adjacencyList[x][y].get(i);
				//System.out.println("this is the next dfs thing: From " + coordinate + "To: " + l);
				DFS(discovered, l);
			}
		}

	}
}

class XYCoord {
	public int x;
	public int y;

	public XYCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "x " + String.valueOf(x + 1) + " y " + String.valueOf(y + 1);
	}
}
