/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csd;

import java.util.Arrays;

/**
 *
 * @author msi2k
 */
public class GraphColoring {
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

    // Sequential Coloring Algorithm
    public void sequentialColoring() {
        int[] result = new int[n];
        Arrays.fill(result, -1); // Initialize all vertices as uncolored

        result[0] = 0; // Assign the first color to the first vertex

        boolean[] available = new boolean[n];
        Arrays.fill(available, true);

        for (int u = 1; u < n; u++) {
            for (int i = 0; i < n; i++) {
                if (adjMatrix[u][i] == 1 && result[i] != -1) {
                    available[result[i]] = false; // Mark the color as unavailable
                }
            }

            int color;
            for (color = 0; color < n; color++) {
                if (available[color]) break;
            }

            result[u] = color;

            Arrays.fill(available, true); // Reset for next vertex
        }

        if (label != null) {
            System.out.println("Assigned colors:");
            for (int i = 0; i < n; i++) {
                System.out.println(label[i] + " ---> Color " + result[i]);
            }
        } else {
            System.out.println("Labels are not set for vertices.");
        }
    }
}
