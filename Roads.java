import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Roads {

    private static int total;
    private static boolean[] visited;
    private static int[][] adjCities;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt(); 
        for(int a0 = 0; a0 < q; a0++){
            int cities = in.nextInt(); 
            int roads = in.nextInt(); 
            int libCost = in.nextInt(); 
            
            int roadCost = in.nextInt(); 
           
            if (roadCost >= libCost)
            { 

                total = libCost*cities;
            }
            
            else{
                
                visited = new boolean[cities];
                adjCities = new int[cities][cities];

               for (int i = 0; i < roads; i++) {
                    int c1 = in.nextInt();
                    int c2 = in.nextInt();
                    
                    adjCities[c1-1][c1-1] = c1;
                    adjCities[c2-1][c2-1] = c2;
                    dfs(c1);
                }
           }
             System.out.println(total);
        }
    }
  private static void dfs(int city){
        visited[city] = true;
        for (int c : adjCities[city]){
            if(!visited[c]){
                //total += roadCost;
                dfs(c);
            }
        }
    }
    
}