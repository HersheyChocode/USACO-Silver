import java.io.*;
import java.util.*;

class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new FileReader("hps.in"));

    int N = Integer.parseInt(br.readLine());

    String[] moves = new String[N];
 
    for (int i = 0; i < N; i++) {
      moves[i] = (br.readLine());
    }

    System.out.println(Arrays.toString(moves));

    int[] totalScores = new int[9];

    int scores = 0;

    // If starting with P throughout
    for (int x = 1; x < N; x++) {
      if (moves[x].equals("H")) {
        scores += 1;
      }
      totalScores[0] = scores;
    }
    scores = 0;
    // If starting with S throughout
    for (int x = 1; x < N; x++) {
      if (moves[x].equals("P")) {
        scores += 1;
      }
      totalScores[6] = scores;
    }
    scores = 0;
    // If starting with H throughout
    for (int x = 1; x < N; x++) {
      if (moves[x].equals("S")) {
        scores += 1;
      }
      totalScores[3] = scores;
    }

    totalScores[1] = myFunction(N, moves, "P", "H");
    totalScores[2] = myFunction(N, moves, "P", "S");
    totalScores[4] = myFunction(N, moves, "H", "P");
    totalScores[5] = myFunction(N, moves, "H", "S");
    totalScores[7] = myFunction(N, moves, "S", "H");
    totalScores[8] = myFunction(N, moves, "S", "P");
    
    System.out.println(Arrays.toString(totalScores));
    Arrays.sort(totalScores);

    System.out.println(Arrays.toString(totalScores));
    br.close();

    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

    pw.write(String.valueOf(totalScores[8]));

    pw.close();
  }

  static int myFunction(int N, String[] moves, String first, String second) {
    String win1;
    String loose1;
    String win2;
    String loose2;

    if (first.equals("H")) {
      win1 = "S";
      loose1 = "P";
    } else if (first.equals("P")) {
      win1 = "H";
      loose1 = "S";
    } else {
      win1 = "P";
      loose1 = "H";
    }

    if (second.equals("H")) {
      win2 = "S";
      loose2 = "P";
    } else if (second.equals("P")) {
      win2 = "H";
      loose2 = "S";
    } else {
      win2 = "P";
      loose2 = "H";
    }


    int[] totalScores = new int[N];
    int scores = 0;

    for (int x = 0; x < N; x++) {
      if(x==0){
        if(moves[0].equals(win1)){
          scores+=1;
        }
      }
      else{
        if(moves[x].equals(win2)){
          scores+=1;
        }
      }
    }
    totalScores[0] = scores;
    System.out.println("Total Scores[0]");
    System.out.println(totalScores[0]);

    for (int x = 1; x < N; x++) {
      if (moves[x].equals(win1)) {
        scores += 1;
      } else if (!moves[x].equals(win1) && moves[x].equals(win2)) {
        scores -= 1;
      }
      totalScores[x] = scores;
      
    }

    Arrays.sort(totalScores);
    return totalScores[N - 1];
  }

}