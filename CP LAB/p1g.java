
/*
g) Write a JAVA Program to find all the articulation points of a graph
We are given an undirected graph. An articulation point (or cut vertex) is defined as a vertex
which, when removed along with associated edges, makes the graph disconnected (or more
precisely, increases the number of connected components in the graph). The task is to find all
articulation points in the given graph.
PROGRAM
*/

import java.util.ArrayList;

public class Graph {

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void APUtil(ArrayList<ArrayList<Integer>> adj, int u, boolean[] visited, int[] disc,
                        int[] low, int parent, boolean[] isAP) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                APUtil(adj, v, visited, disc, low, u, isAP);
                low[u] = Math.min(low[u], low[v]);
                if (parent != -1 && low[v] >= disc[u])
                    isAP[u] = true;
            }
            else if (v != parent)
                low[u] = Math.min(low[u], disc[v]);
        }
        if (parent == -1 && children > 1)
            isAP[u] = true;
    }

    static void AP(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] isAP = new boolean[V];
        int time = 0, par = -1;
        for (int u = 0; u < V; u++)
            if (!visited[u])
                APUtil(adj, u, visited, disc, low, par, isAP);
        for (int u = 0; u < V; u++)
            if (isAP[u])
                System.out.print(u + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        addEdge(adj, 1, 0);
        addEdge(adj, 0, 2);
        addEdge(adj, 2, 1);
        addEdge(adj, 0, 3);
        addEdge(adj, 3, 4);
        System.out.println("Articulation points in the graph");
        AP(adj, V);
    }
}
