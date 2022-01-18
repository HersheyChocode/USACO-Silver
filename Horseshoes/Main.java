import java.util.*;
import java.io.*;

class Main {
  static ArrayList<Integer>[] adj;
  public static boolean[][] visited;
  static int[] comp;
  static int[] size;
  public static String[][] board;

  public static int n;
  public static int ans = 0;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new FileReader("hshoe.in"));

    n = Integer.parseInt(br.readLine());

    System.out.println(n);

    size = new int[n];
    comp = new int[n];
    visited = new boolean[n][n];
    board = new String[n][n];

    StringTokenizer st;

    for (int i = 0; i <n; i++) {
      st = new StringTokenizer(br.readLine());
      String line = st.nextToken();
      for(int j = 0; j<n; j++) {
        board[i][j] = line.substring(j,j+1);
      }
    }

    if(board[0][0].equals("(")) {
      dfs(0,0,1,0);
    }
    System.out.println(ans);
    
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hshoe.out")));
    
    // 2. write to file
    pw.write(String.valueOf(ans));

    // 3. close file
    pw.close();
    /*
    int m;
    for (int i = 0; i < m; i++) {
      int a, b;
      adj[a].add(b);
      adj[b].add(a);
    }
    int counter = 0;
    for (int i = 1; i <= n; i++) {
      if (!visited[i]) {
        counter++;
        floodFill(i, counter);
      }
    }*/
  }

  public static void dfs(int x, int y, int open, int closed) {
      int [] dx = {1,0,-1,0};
      int [] dy = {0, 1, 0, -1};

      if(!visited[x][y] && open!=closed){
        
      		visited[x][y] = true;
        	for(int i = 0; i<4; i++) {
            	int nx = x + dx[i];
              int ny = y + dy[i];
              
              if(nx>=0 && ny>=0 && nx<n && ny<n) {
                if(board[nx][ny].equals(")") ) {
                  System.out.println(") closed" + nx + " " + ny);
                  dfs(nx, ny, open, closed+1);
                  //visited[x][y] = false;
                }
                else {
                  
                  if(closed==0){
                    System.out.println("( open" + nx + " " + ny);
                    dfs(nx, ny, open+1, closed);
                    //visited[x][y] = false;
                  }
                }
              }
            }

          visited[x][y] = false;
      }
      if(open==closed)
        ans = Math.max(closed+closed, ans);
    }
/*
  public static void floodFill(int current, int counter) {

    if (!visited[current]) {
      comp[current] = counter;
      visited[current] = true;
      size[counter]++;
      for (int i = 0; i < adj[current].size(); i++) {
        floodFill(adj[current].get(i), counter);
      }
    }
  }*/
}