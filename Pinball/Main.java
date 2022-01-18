import java.io.*;
import java.util.*;


class Main {

  static int sum = 0;
  static ArrayList<Integer> ans = new ArrayList<>();
  static int[][] nums;
  static int n;

  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new FileReader("pachinko.in"));

    n = Integer.parseInt(br.readLine());

    nums = new int[n][];

    for(int i = 0; i<n; i++)
    {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int[] arr = new int[i+1];
      for(int j = 0; j<i+1; j++) {
        arr[j] = Integer.parseInt(st.nextToken());
      }

      nums[i] = arr;
    }

    br.close();

    DFS(0,0);

    int max = 0;
    for(int i = 0; i<ans.size(); i++) {
      if(ans.get(i)>max) { max = ans.get(i); }
    }

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pachinko.out")));
    pw.write(String.valueOf(max));
    pw.close();
  }

  public static void DFS(int x, int y) {
    sum+=nums[x][y];

    if(x+1<n) {

      DFS(x+1,y);
      
      DFS(x+1,y+1);

    }
    else {
      ans.add(sum);
    }

    sum-=nums[x][y];
  }
}