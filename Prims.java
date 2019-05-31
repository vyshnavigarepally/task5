import java.util.*;
import java.io.*;
class Prims {
      public static int PrimsAlgorithm(Vertex[] graph, int source) 
      {
		int cost = 0;
		boolean[] visited = new boolean[graph.length];
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
		visited[source] = true;

		for (Edge edge : graph[source].neighbors) {
			priorityQueue.add(edge);
		}
       while (!priorityQueue.isEmpty()) {
		Edge e = priorityQueue.remove();
         if (visited[e.end]) {
				continue;
			}
       	visited[e.end] = true;
			cost += e.cost;

	   for (Edge neighbor : graph[e.end].neighbors) {
				priorityQueue.add(neighbor);
			}
		}

		for (int i = 1; i < graph.length; i++) {
			if (!visited[i]) {
				return -1;
			}
		}
     return cost;
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
        for (int i = 1; i <= t; i++) 
        {
            int vertices = in.nextInt();
			int edges = in.nextInt();

			Vertex[] graph = new Vertex[vertices + 1];
            for (int j = 0; j <= vertices; j++) {
				graph[j] = new Vertex();
			}
            for (int k = 0; k < edges; k++) 
            {
				int start = in.nextInt();
				int finish = in.nextInt();
				int cost = in.nextInt();
				graph[start].neighbors.add(new Edge(finish, cost));
				graph[finish].neighbors.add(new Edge(start, cost));
			}

			int cost = PrimsAlgorithm(graph, 1);

			if (cost == -1) {
				System.out.printf("Campus #%d: I'm a programmer, not a miracle worker!\n", i);
			} else {
			 	System.out.printf("Campus #%d: %d\n", i, cost);	
			 }
		}
	}

	static class Vertex {
		List<Edge> neighbors;

		public Vertex() {
			neighbors = new ArrayList<Edge>();
		}
	}

	static class Edge implements Comparable<Edge> {
		int end, cost;

		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		public int compareTo(Edge e) {
			return this.cost - e.cost;
		}
	}
}