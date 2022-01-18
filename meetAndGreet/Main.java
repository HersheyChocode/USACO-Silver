// Copy and paste your work, or start typing.import java.io.*;
import java.io.*; 
// Copy and paste your work, or start typing.import java.io.*;
import java.util.*;


class Main {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new FileReader("greetings.in"));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int B = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());

    int[] numBMoves = new int[B];
    int totalBMoves = 0;
    int[] numEMoves = new int[E];
    int totalEMoves = 0;

    String[] bDirections = new String[B];
    String[] eDirections = new String[E];
    
    int x;
    for (int i = 0; i < B; i++) {
      st = new StringTokenizer(br.readLine());
      x =Integer.parseInt(st.nextToken());
      numBMoves[i] = x;
      totalBMoves+=x;
      bDirections[i]=st.nextToken();
    }
    
    int y;
    for(int i=0; i<E; i++){
      st = new StringTokenizer(br.readLine());
      y =Integer.parseInt(st.nextToken());
      numEMoves[i] = y;
      totalEMoves+=y;
      eDirections[i]=st.nextToken();
    }



    System.out.println(totalEMoves);
    System.out.println(totalBMoves);


    int[] bSteps = new int[totalBMoves];
    int currentPos = 0;
    int counter = 0;
    for (int i=0; i<B;i++){
      for(int j=0; j<numBMoves[i]; j++){
        if(bDirections[i].equals("L")){
          currentPos-=1;
          bSteps[counter] = currentPos;
        } else{
          currentPos+=1;
          bSteps[counter] = currentPos;
        }
        counter+=1;
      }
    }

    int[] eSteps = new int[totalEMoves];
    currentPos = 0;
    counter = 0;
    for (int i=0; i<E;i++){
      for(int j=0; j<numEMoves[i]; j++){
        if(eDirections[i].equals("L")){
          currentPos-=1;
          eSteps[counter] = currentPos;
        } else{
          currentPos+=1;
          eSteps[counter] = currentPos;
        }
        counter+=1;
      }
    }

    int num = 0;
    //int previous = 999999999;
    if(totalEMoves>totalBMoves){
      for(int i =0;i<totalEMoves;i++){
        if(i<totalBMoves){
          if(bSteps[i]==eSteps[i]){
            if(i>0){
              if(eSteps[i-1]!=bSteps[i-1]){
                num+=1;
              }
            }
          }
        } else{
          if (eSteps[i]==bSteps[totalBMoves-1]){
            num+=1;
            //previous = eSteps[i-1];
          }
        }
        
      }



    } else if(totalBMoves>totalEMoves){
      for(int i =0;i<totalBMoves;i++){
        if(i<totalEMoves){
          if(eSteps[i]==bSteps[i]){
            if(i>0){
              if(bSteps[i-1]!=eSteps[i-1]){
                num+=1;
              }
            }
            //previous = eSteps[i];
          }
        } else{
          if (bSteps[i]==eSteps[totalEMoves-1]){
            num+=1;
            //previous = bSteps[i-1];
          }
        }
      }
    } 
    
    
    
    else{
      for(int i =0;i<totalBMoves;i++){
        
      	if(bSteps[i]==eSteps[i]){
          if(i>0){
            if(bSteps[i-1]!=eSteps[i-1]){
              num+=1;
            }
          }
        }
      }
    }

    System.out.println(num);


    br.close();



    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("greetings.out")));

    pw.write(Integer.toString(num));

    pw.close();
  }
}