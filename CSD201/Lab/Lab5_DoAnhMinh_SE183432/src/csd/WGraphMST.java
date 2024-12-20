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

public class WGraphMST {
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

    // Prim's algorithm to find the minimum spanning tree
    public void primMST() {
        boolean[] inMST = new boolean[n];
        int[] key = new int[n];
        int[] parent = new int[n];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0; // Start from the first vertex
        parent[0] = -1;

        for (int count = 0; count < n - 1; count++) {
            int u = selectMinKeyVertex(key, inMST);
            inMST[u] = true;

            for (int v = 0; v < n; v++) {
                if (weightMatrix[u][v] != 0 && !inMST[v] && weightMatrix[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = weightMatrix[u][v];
                }
            }
        }

        printMST(parent);
    }

    private int selectMinKeyVertex(int[] key, boolean[] inMST) {
        int min = Integer.MAX_VALUE;
        int vertex = -1;

        for (int i = 0; i < n; i++) {
            if (!inMST[i] && key[i] < min) {
                min = key[i];
                vertex = i;
            }
        }

        return vertex;
    }

    private void printMST(int[] parent) {
        System.out.println("Minimum Spanning Tree:");
        for (int i = 1; i < n; i++) {
            System.out.println(label[parent[i]] + " - " + label[i] + " : " + weightMatrix[i][parent[i]]);
        }
    }
}
