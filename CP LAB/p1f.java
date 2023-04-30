
/*f) Write a JAVA Program to find a permutation of the vertices (topological
order) which corresponds to the order defined by all edges of the graph
Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that
for every directed edge u v, vertex u comes before v in the ordering. Topological Sorting for a
graph is not possible if the graph is not a DAG.
For example, a topological sorting of the following graph is “5 4 2 3 1 0”. There can be more
than one topological sorting for a graph. For example, another topological sorting of the
following graph is “4 5 2 3 1 0”. The first vertex in topological sorting is always a vertex with indegree as 0 (a vertex with no incoming edges).
PROGRAM*/

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

class Graph {
    private int V;
    private ArrayList<ArrayList<Integer>> adj;

    Graph(int v) {
        V = v;
        adj = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<Integer>());
    }

    void addEdge(int v, int w) { adj.get(v).add(w); }

    void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        adj.get(v).stream().filter(i -> !visited[i])
                .forEach(i -> topologicalSortUtil(i, visited, stack));
        stack.push(v);
    }

    void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        IntStream.range(0, V).filter(i -> !visited[i])
                .forEach(i -> topologicalSortUtil(i, visited, stack));
        while (!stack.empty()) System.out.print(stack.pop() + " ");
    }

    public static void main(String args[]) {
        Graph g = new Graph(6);
        int[][] edges = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        Arrays.stream(edges).forEach(e -> g.addEdge(e[0], e[1]));
        System.out.println("Following is a Topological sort of the given graph");
        g.topologicalSort();
    }
}
