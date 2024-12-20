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
public class Main {
    public static void main(String[] args) {
        // 1. Test Graph class with Breadth-First and Depth-First Traversal
        System.out.println("Testing Graph with BFS and DFS:");
        int[][] matrix = {
            {0, 1, 0, 1, 0},
            {1, 0, 1, 1, 1},
            {0, 1, 0, 1, 1},
            {1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0}
        };
        String[] labels = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph();
        graph.setAMatrix(matrix, 5);
        graph.setLabel(labels);
        graph.breadthFirstTraversal(0); // BFS from vertex 0
        graph.depthFirstTraversal(0);   // DFS from vertex 0

        // 2. Test WGraph class with Dijkstra’s Algorithm
        System.out.println("\nTesting WGraph with Dijkstra's Algorithm:");
        int[][] weights = {
            {0, 10, 0, 30, 100},
            {10, 0, 50, 0, 0},
            {0, 50, 0, 20, 10},
            {30, 0, 20, 0, 60},
            {100, 0, 10, 60, 0}
        };
        WGraph wGraph = new WGraph();
        wGraph.setWMatrix(weights, 5);
        wGraph.setLabel(labels); // Set labels for vertices
        wGraph.dijkstra(0); // Dijkstra from vertex 0

        // 3. Test WGraphMST class with Prim’s Algorithm
        System.out.println("\nTesting WGraphMST with Prim's Minimum Spanning Tree Algorithm:");
        int[][] mstWeights = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };
        WGraphMST wGraphMST = new WGraphMST();
        wGraphMST.setWMatrix(mstWeights, 5);
        wGraphMST.setLabel(labels); // Set labels for vertices
        wGraphMST.primMST(); // Prim's algorithm for MST

        // 4. Test GraphColoring class with Sequential Coloring Algorithm
        System.out.println("\nTesting GraphColoring with Sequential Coloring Algorithm:");
        GraphColoring graphColoring = new GraphColoring();
        graphColoring.setAMatrix(matrix, 5);
        graphColoring.setLabel(labels); // Ensure labels are set
        graphColoring.sequentialColoring(); // Sequential coloring
    }
}
