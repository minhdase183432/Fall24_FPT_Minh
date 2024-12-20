/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd;

/**
 *
 * @author msi2k
 */

    import java.util.*;

public class Graph {
    private int[][] adjMatrix;
    private String[] label;
    private int n;

    // Set the adjacency matrix and number of vertices
    public void setAMatrix(int[][] b, int m) {
        n = m;
        adjMatrix = b;
    }

    // Set labels for vertices
    public void setLabel(String[] c) {
        label = c;
    }

    // Breadth First Traversal (BFS)
    public void breadthFirstTraversal(int startVertex) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.println("Breadth-First Traversal:");
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(label[vertex] + " ");

            for (int i = 0; i < n; i++) {
                if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    // Depth First Traversal (DFS)
    public void depthFirstTraversal(int startVertex) {
        boolean[] visited = new boolean[n];
        System.out.println("Depth-First Traversal:");
        depthFirstTraversalHelper(startVertex, visited);
        System.out.println();
    }

    // Helper function for DFS
    private void depthFirstTraversalHelper(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(label[vertex] + " ");

        for (int i = 0; i < n; i++) {
            if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                depthFirstTraversalHelper(i, visited);
            }
        }
    }
}

    

