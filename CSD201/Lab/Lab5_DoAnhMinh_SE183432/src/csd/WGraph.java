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

public class WGraph {
    private int[][] weightMatrix;
    private String[] label;
    private int n;

    // Set the weighted matrix and number of vertices
    public void setWMatrix(int[][] b, int m) {
        n = m;
        weightMatrix = b;
    }

    // Set labels for vertices
    public void setLabel(String[] c) {
        label = c;
    }

    // Dijkstra's shortest path algorithm
    public void dijkstra(int startVertex) {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startVertex] = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = selectMinVertex(dist, visited);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && weightMatrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                    dist[u] + weightMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + weightMatrix[u][v];
                }
            }
        }

        System.out.println("Dijkstra's Shortest Path:");
        for (int i = 0; i < n; i++) {
            System.out.println("Distance from " + label[startVertex] + " to " + label[i] + " is " + dist[i]);
        }
    }

    private int selectMinVertex(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int vertex = -1;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                vertex = i;
            }
        }

        return vertex;
    }
}
