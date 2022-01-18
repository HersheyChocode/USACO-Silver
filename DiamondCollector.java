import java.util.*;
import java.io.*;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("diamond.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));

    int n = Integer.valueOf(st.nextToken());
    int k = Integer.valueOf(st.nextToken());
    int[] arr = new int[n];

    for(int i = 0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      arr[i] = Integer.valueOf(st.nextToken());
    } Arrays.sort(arr);

    int[] left = new int[n];
    int x = 0;
    for(int i = 0; i<n; i++) {
      while(x<n && arr[i]-arr[x]>k) {
        x++;
      }
      left[i] = i-x+1;
      if(i>0 &&left[i-1]>left[i]) left[i]=left[i-1];
    }

    x = n-1;
    int[] right = new int[n];
    for(int i = n-1; i>=0; i--) {
      while(x>=0 && arr[x]-arr[i]>k) {
        x--;
      }
      right[i] = x-i+1;
      if(i<n-1 && right[i+1]>right[i]) right[i] = right[i+1];
    }

    int max = -1;
    for(int i = 0; i<n-1; i++) {
      if(right[i+1]+left[i]>max) max = right[i+1]+left[i];
    }

    pw.print(max);
    pw.close();
    br.close();
  }
}