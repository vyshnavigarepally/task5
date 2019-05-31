import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BFss
{
       static class Node{
        int data;
        int level;

        HashMap<Integer,Node> childrenMap = new HashMap<Integer,Node>();

        public Node(int data)
        {
            this.data = data;
        }
    }
    static int[] bfs(int n, int m, int[][] edges, int s) {
        
        HashMap<Integer,Node> map = new HashMap<Integer,Node>(n);
        int[] result = new int[n+1];

        for(int i=0;i<result.length;i++)
            result[i]=-1;

        for(int i=0;i<m;i++)
        {
            Node parentNode;
            Node childNode;

            if(map.containsKey(edges[i][0]))
                parentNode = map.get(edges[i][0]);
            else
            {
                parentNode = new Node(edges[i][0]);
                map.put(parentNode.data,parentNode);
            }
                

            if(map.containsKey(edges[i][1]))
                childNode = map.get(edges[i][1]);
            else
            {
                childNode = new Node(edges[i][1]);
                map.put(childNode.data,childNode);
            }
            
            parentNode.childrenMap.put(childNode.data,childNode);
            childNode.childrenMap.put(parentNode.data,parentNode);
        }

        System.out.println("Map size:"+map.size());

        if(!map.containsKey(s))
            return result;
            
        Node startNode = map.get(s);

        Queue<Node> queue = new LinkedList<Node>();
        HashSet<Node> visited = new HashSet<Node>();

        startNode.level = 0;

        queue.add(startNode);
        visited.add(startNode);
        
        while(!queue.isEmpty())
        {
            
            Node currentNode = queue.poll();
            
            result[currentNode.data] = currentNode.level*6; 
            //Enqueue all the children except those that are already vistied.
            for(Map.Entry<Integer,Node> pair : currentNode.childrenMap.entrySet())
            {
                Node childOfCurrentNode = pair.getValue();

                if(!visited.contains(childOfCurrentNode))
                {
                    visited.add(childOfCurrentNode);
                    childOfCurrentNode.level = currentNode.level+1;
                    queue.add(childOfCurrentNode);
                }        
            }
        }

        return result;
    } 

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 1; i < result.length; i++) {

                if(result[i]!=0)
                {
                    bufferedWriter.write(String.valueOf(result[i]));
                    if (i != result.length) {
                    bufferedWriter.write(" ");
                    }
                }            
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}