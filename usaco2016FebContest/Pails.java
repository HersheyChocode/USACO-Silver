package usaco2016FebContest;

import java.io.*;
import java.util.*;

public class Pails {

	static ArrayList<Integer> hi;
	static boolean[] hi1;
	//static boolean[][][] done;
	static boolean[][] done;
	static int x;
	static int y;
	static int k;
	static int m;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new FileReader("pails.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		x = Integer.valueOf(st.nextToken());
		y = Integer.valueOf(st.nextToken());
		k = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		hi = new ArrayList<>();
		hi1 = new boolean[100000];
		//done = new boolean[101][101][101];
		done = new boolean[101][101];

		move(0,0,0);
		
		LinkedList<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0, 0 });
		int min = Integer.MAX_VALUE;
		done[0][0] = true;
		while (q.size() > 0) {
			int[] next = q.poll();
			int x1 = next[0];
			int y1 = next[1];
			int step = next[2];
			
			int sum = 0;
			if (step < k) {
				int[] nXY = fill(x1, y1, true);
				if (!done[nXY[0]][nXY[1]]) {
					done[nXY[0]][nXY[1]] = true;
					q.add(new int[] { nXY[0], nXY[1], step + 1 });
					sum = nXY[0]+nXY[1];
					min = Math.min(min, Math.abs(m-sum));
				}
				nXY = fill(x1, y1, false);
				if (!done[nXY[0]][nXY[1]]) {
					done[nXY[0]][nXY[1]] = true;
					q.add(new int[] { nXY[0], nXY[1], step + 1 });
					sum = nXY[0]+nXY[1];
					min = Math.min(min, Math.abs(m-sum));
				}
				nXY = pour(x1, y1, true);
				if (!done[nXY[0]][nXY[1]]) {
					done[nXY[0]][nXY[1]] = true;
					q.add(new int[] { nXY[0], nXY[1], step + 1 });
					sum = nXY[0]+nXY[1];
					min = Math.min(min, Math.abs(m-sum));
				}
				nXY = pour(x1, y1, false);
				if (!done[nXY[0]][nXY[1]]) {
					done[nXY[0]][nXY[1]] = true;
					q.add(new int[] { nXY[0], nXY[1], step + 1 });
					sum = nXY[0]+nXY[1];
					min = Math.min(min, Math.abs(m-sum));
				}
				nXY = empty(x1, y1, true);
				if (!done[nXY[0]][nXY[1]]) {
					done[nXY[0]][nXY[1]] = true;
					q.add(new int[] { nXY[0], nXY[1], step + 1 });
					sum = nXY[0]+nXY[1];
					min = Math.min(min, Math.abs(m-sum));
				}
				nXY = empty(x1, y1, false);
				if (!done[nXY[0]][nXY[1]]) {
					done[nXY[0]][nXY[1]] = true;
					q.add(new int[] { nXY[0], nXY[1], step + 1 });
					sum = nXY[0]+nXY[1];
					min = Math.min(min, Math.abs(m-sum));
				}
			}
			else if (step==k) {
				sum = x1+y1;
				min = Math.min(min, Math.abs(m-sum));
			}

		}

	
	
		System.out.println(min);

		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		pw.println(min);

		br.close();
		pw.close();
	}

	public static int[] fill(int x1, int y1, boolean xFirst) {
		if (xFirst) {
			return new int[] { x, y1 };
		} else {
			return new int[] { x1, y };
		}
	}

	public static int[] empty(int x1, int y1, boolean xFirst) {
		if (xFirst) {
			return new int[] { 0, y1 };
		} else {
			return new int[] { x1, 0 };
		}
	}

	public static int[] pour(int x1, int y1, boolean xToY) {
		if (xToY) {
			if (y1 == y)
				return new int[] { x1, y1 };
			else if (x1 + y1 <= y)
				return new int[] { 0, x1 + y1 };
			else {
				int yRemainder = y - y1;
				return new int[] { x1 - yRemainder, y };
			}
		} else {
			if (x1 == x)
				return new int[] { x1, y1 };
			else if (x1 + y1 <= x)
				return new int[] { x1 + y1, 0 };
			else {
				int xRemainder = x - x1;
				return new int[] { x, y1 - xRemainder };
			}
		}
	}

	public static void move(int x1, int y1, int step) {
		/*if (step != k) {
			int[] nXY = fill(x1, y1, true);
			if (!done[nXY[0]][nXY[1]][step]) {
				done[nXY[0]][nXY[1]][step] = true;
				move(nXY[0], nXY[1], step + 1);
			}

			nXY = fill(x1, y1, false);
			if (!done[nXY[0]][nXY[1]][step]) {
				done[nXY[0]][nXY[1]][step] = true;
				move(nXY[0], nXY[1], step + 1);
			}

			nXY = empty(x1, y1, true);
			if (!done[nXY[0]][nXY[1]][step]) {
				done[nXY[0]][nXY[1]][step] = true;
				move(nXY[0], nXY[1], step + 1);
			}

			nXY = empty(x1, y1, false);
			if (!done[nXY[0]][nXY[1]][step]) {
				done[nXY[0]][nXY[1]][step] = true;
				move(nXY[0], nXY[1], step + 1);
			}

			nXY = pour(x1, y1, true);
			if (!done[nXY[0]][nXY[1]][step]) {
				done[nXY[0]][nXY[1]][step] = true;
				move(nXY[0], nXY[1], step + 1);
			}

			nXY = pour(x1, y1, false);
			if (!done[nXY[0]][nXY[1]][step]) {
				done[nXY[0]][nXY[1]][step] = true;
				move(nXY[0], nXY[1], step + 1);
			}
		} else {
			if (!hi1[x1 + y1]) {
				hi.add(x1 + y1);
				hi1[x1 + y1] = true;
			}
		}*/

	}

}
