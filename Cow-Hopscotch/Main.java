import java.util.*;
import java.io.*;

class Main {
  public static int r;
  public static int c;
  public static String board[][];
  public static boolean visited[][];
  public static int found;


  public static void main(String[] args) throws IOException {
    
    BufferedReader br = new BufferedReader(new FileReader("hopscotch.in"));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    r = Integer.valueOf(st.nextToken());
    c = Integer.valueOf(st.nextToken());
    board = new String[r][c];
    visited = new boolean[r][c];

    for(int i = 0; i<r; i++) {
      String line = br.readLine();
      for(int j = 0; j<c; j++) {
        board[i][j] = line.substring(j,j+1);
        //System.out.print(board[i][j]);
      }
      //System.out.println();
    }
    br.close();

    found = 0;
    DFS(0,0);
    //System.out.println(found);
    
    
    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
    pw.write(String.valueOf(found));
    pw.close();

  }


  public static boolean isValid(int x, int y) {
    if(x<0 || y<0 || x>=r || y>=c) {
      return false;
    } return true;
  }
  public static void DFS(int x, int y) {

    if(/*!visited[x][y]&&*/ isValid(x,y)) {

      //visited[x][y] = true;
      if(x==r-1 && y==c-1) {
        found++;
      }
      else {
        for(int i = x+1; i<r; i++) {
          for(int j = y+1; j<c; j++) {
            if(!board[i][j].equals(board[x][y])) {
              //System.out.print("x " + x + " y " + y);
              //System.out.println(" new x " + i + " new y " + j);
              DFS(i,j);
            }
        }
      }
      }
      
    }
  }
}