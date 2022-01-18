import java.util.*;
import java.io.*;

class Main {
  public static ArrayList<Integer>[] adj;
  public static boolean[][] visited;
  public static int[][] comp;
  public static int minPer = 0;
  public static int maxArea = 0;
  public static int per;
  public static int area;
  public static int n;
  public static String[] board;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));

    n = Integer.valueOf(br.readLine());
    comp = new int[n][n];
    adj = new ArrayList[n];
    visited = new boolean[n][n];
    board = new String[n];

    for (int i = 0; i < n; i++) {
      String line = br.readLine();
      board[i] = line;
    }

    br.close();

    /*
     * for (int i=1; i<=n; i++) { adj[i] = new ArrayList<>(); } int m; for (int i=0;
     * i<m; i++) { int a, b; adj[a].add(b); adj[b].add(a); }
     */

    int counter = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        per = 0;
        area = 0;
        if(!visited[i][j])
          floodFill(i, j);
        if(area>maxArea)
        {
          maxArea = area;
          minPer = per;
        }
        else if(area == maxArea)
        {
          if(per<minPer) {
            maxArea = area;
            minPer = per;
          }
        }
      }

    }

    System.out.println(maxArea + " " + minPer);

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
    pw.write(maxArea + " " + minPer);
    pw.close();
  }

  public static boolean isValid(int x, int y) {
    if (x < 0 || y < 0 || x >= n || y >= n) {
      return false;
    }
    return true;
  }

  public static int countPer(int x, int y) {
    int perCount = 0;

    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    for (int i = 0; i < 4; i++) {
      int nx = x+dx[i];
      int ny = y+dy[i];
      if (!isValid(nx, ny)) {
        perCount++;
      } else {
        if (board[nx].charAt(ny) == '.') {
          perCount++;
        }
      }
    }

    return perCount;
  }

  public static void floodFill(int x, int y) {

    if (isValid(x, y) && board[x].charAt(y)=='#' && !visited[x][y]) {
      // comp[x][y] = counter;

      per += countPer(x, y);
      area++;
      visited[x][y] = true;

      int[] dx = { -1, 0, 1, 0 };
      int[] dy = { 0, 1, 0, -1 };

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
	
        floodFill(nx, ny);
      }
    }
  }
}
