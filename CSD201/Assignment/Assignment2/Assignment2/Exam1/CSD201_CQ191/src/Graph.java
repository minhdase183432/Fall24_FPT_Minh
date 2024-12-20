
import java.io.*;
import java.util.*;

public class Graph {

    int[][] a;
    int n;
    char v[];
    int deg[];
    int count = 0;
    static int INF = 99; // 99 is considered as infinite value

    Graph() {
        v = "ABCDEFGHIJKLMNOP".toCharArray();
        deg = new int[20];
        a = new int[20][20];
        n = 0;
    }

    //Load data from file
    void loadData(String data, int k) {
        RandomAccessFile f;
        int i, j, x;
        String s;
        StringTokenizer t;
        a = new int[20][20];
        try {
            f = new RandomAccessFile(data, "r");
            for (i = 0; i < k; i++) {
                f.readLine();//ignores the first k-1 lines
            }
            s = f.readLine();//just use the data in the k-th line
            s = s.trim();
            n = Integer.parseInt(s);
            for (i = 0; i < n; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < n; j++) {
                    x = Integer.parseInt(t.nextToken().trim());
                    a[i][j] = x;
                }
            }

            f.close();
        } catch (Exception e) {
        }
    }

    void dispAdj() {
        int i, j;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
        }
    }

    void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes("  " + v[i]);
    }

    void fvisit2(int i, RandomAccessFile f) throws Exception {
        f.writeBytes("\n  " + i);
    }

    void fdispAdj(RandomAccessFile f) throws Exception {
        int i, j;
        f.writeBytes("n = " + n + "\r\n");
        for (i = 0; i < n; i++) {
            f.writeBytes("\r\n");
            for (j = 0; j < n; j++) {
                f.writeBytes("  " + a[i][j]);
            }
        }
        f.writeBytes("\r\n");
    }

    void isConnected2(boolean[] visited, int k, RandomAccessFile f)
            throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0 && a[k][i] < 99) {
                isConnected2(visited, i, f);
            }
        }
    }

    void isConnected(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        isConnected2(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                isConnected2(visited, i, f);
            }
        }
    }

    void bfsTraversal(int k, RandomAccessFile f) throws Exception {
        //------ Start your code here----------------------------------------
        LinkedList<Integer> q = new LinkedList<>();  // Use LinkedList for queue operations
        boolean[] enqueued = new boolean[n];
        int count = 0; // To count the first 3 vertices after the starting node

        q.add(k);
        enqueued[k] = true;

        while (!q.isEmpty() && count < 3) {
            int h = q.poll();

            if (count > 0) { // Skip the first vertex (B itself)
                fvisit(h, f);
                count++;
            } else {
                count++; // Move count up to allow recording from the next vertex
            }

            for (int i = 0; i < n; i++) {
                if (!enqueued[i] && a[h][i] > 0 && a[h][i] < INF) {
                    q.add(i);
                    enqueued[i] = true;
                    if (count >= 3) {
                        break; // Stop after recording 3 vertices
                    }
                }
            }
        }

        //------ End your code here------------------------------------------
    }

    void breadth(int k, RandomAccessFile f) throws Exception {
        LinkedList<Integer> q = new LinkedList<>();  // Use LinkedList instead of custom Queue
        int i, h;
        boolean[] enqueued = new boolean[n];
        for (i = 0; i < n; i++) {
            enqueued[i] = false;
        }

        q.add(k);  // Enqueue the starting vertex
        enqueued[k] = true;

        while (!q.isEmpty()) {
            h = q.poll();  // Dequeue the next vertex
            fvisit(h, f);  // Visit the vertex

            // Enqueue all adjacent vertices that are unvisited
            for (i = 0; i < n; i++) {
                if (!enqueued[i] && a[h][i] > 0 && a[h][i] < INF) {
                    q.add(i);  // Enqueue the vertex
                    enqueued[i] = true;  // Mark as visited
                }
            }
        }
    }

    void findEulerCycle(int k, RandomAccessFile f) throws Exception {
        //------ Start your code here----------------------------------------
        LinkedList<Integer> stack = new LinkedList<>();
        List<Integer> eulerCycle = new ArrayList<>();

        // Create a temporary adjacency matrix to preserve the original graph
        int[][] tempAdj = new int[n][n];

        // Copy the adjacency matrix to the temporary one
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tempAdj[i][j] = a[i][j];
            }
        }

        // Push the starting vertex onto the stack
        stack.push(k);

        // Continue until the stack is empty
        while (!stack.isEmpty()) {
            int u = stack.peek();
            boolean hasEdge = false;

            // Look for an adjacent vertex with an unvisited edge
            for (int v = 0; v < n; v++) {
                if (tempAdj[u][v] > 0 && tempAdj[u][v] < INF) {
                    // There is an edge from u to v
                    stack.push(v);  // Push v to the stack
                    tempAdj[u][v] = tempAdj[v][u] = 0;  // Remove the edge u-v
                    hasEdge = true;
                    break;
                }
            }

            // If no adjacent vertices with unvisited edges were found, backtrack and add u to the cycle
            if (!hasEdge) {
                eulerCycle.add(stack.pop());  // Pop the vertex and add it to the Euler cycle
            }
        }

        // Write the Euler cycle to the file using fvisit
        for (int vertex : eulerCycle) {
            fvisit(vertex, f);  // Print each vertex in the Euler cycle
        }
        //------ End your code here------------------------------------------
    }

    void dijkstra2(boolean[] selected, int[] dist, int[] path, int p,
            int q, boolean[] stopDisplay) {
        int curr;
        for (int i = 0; i < n; i++) {
            selected[i] = false;
            stopDisplay[i] = false;
            dist[i] = a[p][i];
            path[i] = p;
        }
        int[] sele = new int[50];
        int nSele = 0;
        selected[p] = true;
        sele[nSele++] = p;
        curr = p;
        while (curr != q) {
            int t = INF;
            int k = -1;
            for (int i = 0; i < n; i++) {
                if (i == p || selected[i]) {
                    continue;
                }
                if (dist[i] < t) {
                    t = dist[i];
                    k = i;
                }
            }
            if (t == INF) {
                return;
            }
            selected[k] = true;
            curr = k;
            sele[nSele++] = k;
            for (int i = 0; i < n; i++) {
                if (i == p || selected[i]) {
                    continue;
                }
                if (dist[i] > dist[k] + a[k][i]) {
                    dist[i] = dist[k] + a[k][i];
                    path[i] = k;
                }
            }
        }
    }

    void pathDijkstra(int[] dist, int[] path, int p, int q,
            RandomAccessFile f) throws Exception {
        Stack s = new Stack();
        s.push(q);
        do {
            q = path[q];
            s.push(q);
        } while (q != p);
        System.out.print(" ");
        while (!s.isEmpty()) {
            q = s.pop();
        }
    }

    void dijkstra(int p, int q, RandomAccessFile f) throws Exception {
        boolean[] selected = new boolean[n];
        boolean[] stopDisplay = new boolean[n];
        int[] dist = new int[n];
        int[] path = new int[n];
        //Shortest path from vertex p to vertex q
        dijkstra2(selected, dist, path, p, q, stopDisplay);
        pathDijkstra(dist, path, p, q, f);
    }

    // This method is used for Question 1
    void f1() throws Exception {
        loadData("data1.txt", 1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        /**
         * Question 1: Perform depth-first search algorithm on the graph. You
         * are required to use and modify the isConnected(...) and
         * isConnected2(...) methods (provided in this class), and then call
         * isConnected(...) in f1() to write one more line containing the number
         * of connected components of the graph (given in the data1.txt file) to
         * the file f1.txt. Notes: The number of connected components of a graph
         * is also the number of calls to the DFS (depth-first search) algorithm
         * to visit all the vertices of each connected component when it is
         * called on a vertex of that connected component. The output f1() will
         * be written into the file 'f1.txt'. Therefore you should open this
         * file to see/test your code output. Example: With the graph provided
         * in 'data1.txt', the content of 'f1.txt' after running these methods
         * is: A E F I B G C H D 3
         */
        //-------------------------------------------------------------------
        //------ Start your code here----------------------------------------
        boolean[] visited = new boolean[n];
        int connectedComponents = 0;  // To keep track of the number of connected components

        // Iterate through all vertices and perform DFS for each unvisited vertex
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Perform DFS for the unvisited vertex and mark all reachable vertices
                isConnected2(visited, i, f);
                connectedComponents++;  // Each DFS call corresponds to one connected component
            }
        }

        //------ End your code here------------------------------------------
        //-------------------------------------------------------------------       
        f.writeBytes("\r\n");
        f.close();
    }

    // This method is used for Question 2
    void f2() throws Exception {
        loadData("data2.txt", 1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        breadth(1, f);
        f.writeBytes("\r\n");
        /**
         * Question 2: Perform breadth-first search algorithm on the graph.
         * Starting from the vertex B (which index is 1), ONLY display the
         * vertex right after B and the next 2 vertices. Hint: copy breadth(...)
         * to bfsTraversal(...) and modify the method bfsTraversal(...)
         * appropriately. The output f2() will be written into file 'f2.txt'.
         * Therefore you should open this file to see/test your code output.
         * Example: With the graph provided in 'data2.txt', the content of
         * 'f2.txt' after running this method is: B A C H D F I G E A C H
         */
        //-------------------------------------------------------------------
        //------ Start your code here----------------------------------------

        bfsTraversal(1, f);

        //------ End your code here------------------------------------------
        //-------------------------------------------------------------------       
        f.writeBytes("\r\n");
        f.close();
    }

    // This method is used for Question 3
    void f3() throws Exception {
        loadData("data3.txt", 1);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        /**
         * Question 3: Perform the algorithm for finding the Euler cycle on the
         * graph. Suppose the graph G (given in the file data3.txt) is an
         * undirected, connected graph with all vertices of even degree (G is
         * the Euler Graph). You are required to implement the
         * findEulerCycle(...) method (provided in this class), and then call
         * findEulerCycle(...) in f3() to write a line containing an Euler cycle
         * of the graph G (starts from A, index is 0) to the file f1.txt. Hint:
         * use the algorithm for finding an Euler cycle from the vertex X using
         * stack to perform and fvisit(...) to save the result. The output f3()
         * will be written into the file 'f3.txt'. Therefore you should open
         * this file to see/test your code output. Example: With the graph
         * provided in 'data3.txt', the content of 'f3.txt' after running this
         * method is: A C F E B D C B A
         */
        //-------------------------------------------------------------------
        //------ Start your code here----------------------------------------

        findEulerCycle(0, f);

        //------ End your code here------------------------------------------
        //-------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

    // This method is used for Question 4
    void f4() throws Exception {
        loadData("data2.txt", 1);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        /**
         * Question 4: Perform Dijkstra's algorithm on the graph. Apply the
         * Dijstra’s shortest path algorithm to find the shortest path from
         * vertex A (the vertex A has an index of 0) to vertex E (the index of
         * the vertex E is 4), then write 2 lines to the file f4.txt: (1) Line
         * 1: contains vertices in shortest path from A to E, and (2) Line 2:
         * contains the shortest distance. Hint: use and modify dijkstra(...),
         * dijkstra2(...), pathDijkstra(...), fvisit(...), fvisit2(...)
         * (provided in this class) appropriately, then call dijkstra(...) in
         * f4(). The output f4() will be written into the file 'f4.txt'.
         * Therefore you should open this file to see/test your code output.
         * Example: With the graph provided in 'data2.txt', the content of
         * 'f4.txt' after running these methods is: A H G F E 21
         */
        //-------------------------------------------------------------------
        //------ Start your code here----------------------------------------

        dijkstra(0, 4, f);

        //------ End your code here------------------------------------------
        //-------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }
}
