import java.util.*;
import java.io.*;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("herding.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));

    int n = Integer.valueOf(br.readLine());
    int[] arr = new int[n];
    for(int i = 0; i<n; i++) 
    {
      arr[i] = Integer.valueOf(br.readLine());
    }
    Arrays.sort(arr);

    //max:
    int sum = 0;
    for(int i = 1; i<n; i++) {
      sum+=arr[i]-arr[i-1]-1;
    }
    int minEndGaps = Math.min(arr[1]-arr[0]-1, arr[n-1]-arr[n-2]-1);
    int max = sum-minEndGaps;

    //System.out.println(sum + " " + minEndGaps);

    //min:

    int j = 0;
    int maxMin = 0;
    for(int i = 0; i<n; i++) {
      int index = arr[i];
      while(j<n-1 && arr[j+1]-index<n) {
        j++;
      } 
      maxMin = Math.max(maxMin, j-i+1);
    }

    int min = n-maxMin;
    if (arr[n-2]-arr[0]==n-2 && arr[n-1]-arr[n-2]>2) {
      min = 2;
    } 
    if (arr[n-1]-arr[1]==n-2 && arr[1]-arr[0]>2) {
      min = 2;
    }

    pw.println(min);
    pw.println(max);

    pw.close();

  }
}