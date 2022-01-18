package mountainView;

import java.io.*;
import java.util.*;

public class Mountains {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));
		
		int n = Integer.valueOf(st.nextToken());
		Points[] points = new Points[n];
		
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken()); int b = Integer.valueOf(st.nextToken());
			points[i] = new Points(a,b);
		}
		
		Arrays.sort(points);
		
		int count = 1;
		Points prev = points[n-1];
		for(int i = n-2; i>=0; i--) {
			Points curr = points[i];
			if(curr.b-prev.b>-curr.a+prev.a) {
				count++;
				prev = curr;
			} else if(curr.b-prev.b>curr.a-prev.a) {
				count++;
				prev = curr;
			}
		}
		pw.println(count);
		pw.close();
		br.close();
	}

}

class Points implements Comparable<Points>{
	int a; 
	int b; //y=r
	int right;
	int left;
	public Points(int a, int b) {
		this.a = a; this.b = b;
		right = a+b; left = a-b;
	}
	@Override
	public int compareTo(Points o) {
		if(this.right==o.right) {
			return this.b-o.b;
		}
		else {
			return this.right-o.right;
		}
	}
}