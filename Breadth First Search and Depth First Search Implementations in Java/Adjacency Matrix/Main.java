package com.graphs.dfsadjmatrix;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Directed Graph - Node60 is a goal node
        DFS.Node node40 = new DFS.Node(40, false);
        DFS.Node node10 = new DFS.Node(10, false);
        DFS.Node node20 = new DFS.Node(20, false);
        DFS.Node node30 = new DFS.Node(30, false);
        DFS.Node node60 = new DFS.Node(60, true);
        DFS.Node node50 = new DFS.Node(50, false);
        DFS.Node node70 = new DFS.Node(70, false);

        DFS.nodes.add(node40); // 0
        DFS.nodes.add(node10); // 1
        DFS.nodes.add(node20); // 2
        DFS.nodes.add(node30); // 3
        DFS.nodes.add(node60); // 4
        DFS.nodes.add(node50); // 5
        DFS.nodes.add(node70); // 6
        System.out.print("Nodes ArrayList: ");
        DFS.printNodes(DFS.nodes);

        // Building of matrix
        DFS.AdjMatrix adj = new DFS.AdjMatrix(7);
        adj.addEdge(0, 1);
        adj.addEdge(2, 1);
        adj.addEdge(0, 2);
        adj.addEdge(1, 3);
        adj.addEdge(2, 3);
        adj.addEdge(2, 4);
        adj.addEdge(3, 4);
        adj.addEdge(2, 5);
        adj.addEdge(4, 6);
        adj.addEdge(5, 6);
        System.out.print("\n" + "\nThe Adjacency Matrix");
        System.out.println(adj);

        // DFS
        System.out.println("==== DFS start ====");
        DFS.dfsIterative(adj.adjMatrix, node40);
        System.out.println();
        DFS.clearVisitedFields();
        System.out.println("All visit fields of nodes reset\n");

        // BFS
        DFS.bfsIterative(adj.adjMatrix, node40);

        DFS.clearVisitedFields();
        System.out.println("\nAll visit fields of nodes reset");

        // === Visual representation of the graph ===
        // Key:
        // E = end
        // S = start
        //           E   <  <
        //   S  40--10--30----
        //   v  |    |   |   | ^  <  <
        //   v  ----20------60    70
        //    >   v  |             | ^
        //           50------------- ^
        //             >    >    >

    }

    public static class DFS {

        public static int depth = -1;

        public static void clearVisitedFields(){
            // reset node object visited fields
            for(int i = 0; i < nodes.size(); i++){
                nodes.get(i).visited = false;
            }
        }

    // nodes array for indexing reference
    public static ArrayList<Node> nodes = new ArrayList<>();

        // Node class
        public static class Node {
            private int data;
            private boolean visited;
            private boolean goal;

            public Node(int data, boolean goal){
                this.data = data;
                this.goal = goal;
            }

            public int getData() {
                return data;
            }

            @Override
            public String toString(){
                return "" + this.getData();
            }
        }

        public static class AdjMatrix{
            private int E;
            private int V;
            private int[][] adjMatrix;

            public AdjMatrix(int nodes){
                this.V = nodes;
                this.E = 0;
                this.adjMatrix = new int[nodes][nodes];
            }

            public void addEdge(int u, int v){
                adjMatrix[u][v] = 1;
                adjMatrix[u][v] = 1;
                E++;
            }
            public void loopMatrix(int[][]adjMatrix){
                for(int i = 0; i < V; i++){
                    System.out.println(i);
                }
            }


            @Override
            public String toString(){
                StringBuilder sb = new StringBuilder();
                sb.append("\n" + V + " vertices, " + E + " edges \n");
                for(int v = 0; v < V; v++){
                    sb.append(v + ": ");
                    for(int w : adjMatrix[v]){
                        sb.append(w + " ");
                    }
                    sb.append("\n");
                }
                return sb.toString();
            }

        }

        // === findNeighbours method with comments ===
        // this finds whether there is an edge present in the matrix, DFS uses it
        public static ArrayList<Node> findNeighbours(int[][] adjMatrix, Node x){
            System.out.println("nodeIndex reset to -1");
            int nodeIndex = -1;
            System.out.println("new neighbours array added to memory");
            ArrayList<Node> neighbours = new ArrayList<>();
            System.out.println("loop stored nodes array, if element equals element in nodes array");
            System.out.println("Make nodeIndex that index of nodes");
            for(int i = 0; i < nodes.size(); i++){
                if(nodes.get(i).equals(x)){
                    nodeIndex = i;
                    System.out.println(x + " = nodes[" + i + "]");
                    System.out.println("nodeIndex = " + i);
                    break;
                }
            }
            boolean change = nodeIndex != -1;
            System.out.println("nodeIndex has changed = " + change);
            if(nodeIndex != -1){
                System.out.println("Loop matrix");
                System.out.println("nodeIndex: " + nodeIndex);
                for(int j = 0; j < adjMatrix[nodeIndex].length; j++){
                    System.out.println("coordinates: " + "[" + nodeIndex + "]"
                    + "[" + j + "]" + " = " + adjMatrix[nodeIndex][j]);
                    if(adjMatrix[nodeIndex][j] == 1){
                        System.out.println("Edge Found");
                        neighbours.add(nodes.get(j));
                        System.out.println("Neighbours array adds neighbour " + nodes.get(j));
                        System.out.println("Neighbours Array: " + neighbours);
                    }
                }
                System.out.println("loop ends");
            }
            return neighbours;
        }

        // ==== DFS iterative with comments of traversal ====
        public static void dfsIterative(int[][] adjMatrix, Node start){
            Stack<Node> stack = new Stack<>();
            LinkedList<Node> path = new LinkedList<>();
            stack.push(start);
            System.out.println("stack initial push: " + start);
            System.out.println("current stack: " + stack);
            while(!stack.isEmpty()){
                System.out.println("");
                System.out.println("Back to the while loop");
                System.out.println("Is stack empty? " + stack.isEmpty());
                Node element = stack.pop();
                System.out.println(element + " popped from stack");
                System.out.println("Element = " + element);
                System.out.println("current stack: " + stack);
                if(!element.visited){
                    System.out.println("Is element visited? " + element.visited);
                    System.out.println("print data: " + element.data + " ");
                    path.addLast(element);
                    System.out.println(element + " has been set to visited");
                    element.visited = true;
                }

                System.out.println("Neighbours array = findNeighbours" +
                        "(adjMatrix, " + element + ")");
                System.out.println("");
                ArrayList<Node> neighbours = findNeighbours(adjMatrix, element);
                System.out.println("resulting neighbours array = " + neighbours);
                System.out.println("");
                System.out.println("loop neighbours array if i < neighbours.size()");
                for (int i = 0; i < neighbours.size(); i++) {
                    System.out.println(i);
                    Node n = neighbours.get(i);
                    System.out.println("n = " + n);
                    System.out.println("is n not null and not visited yet?");
                    if(n != null && !n.visited){
                        System.out.println("yes");
                        System.out.println("push " + n + " to stack");
                        stack.push(n);
                        System.out.println("current stack: " + stack);
                    } else {
                        System.out.println("n has been visited");
                        System.out.println("");
                    }
                }
            }
            System.out.println("stack is empty");
            System.out.println("DFS ends");
            System.out.println("Path = " + path);
        }

        // ==== FindNeighbours No Comments ====
        public static ArrayList<Node> findNeighbours2(int[][] adjMatrix, Node x){
            int nodeIndex = -1;
            ArrayList<Node> neighbours = new ArrayList<>();
            for(int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i).equals(x)) {
                    nodeIndex = i;
                    System.out.println("Node index: " + nodeIndex);
                    break;
                }
            }
            if(nodeIndex != -1){
                for(int j = 0; j < adjMatrix[nodeIndex].length; j++){
                    System.out.println("Coordinates: (" + nodeIndex + ", " + j + ") ");
                    if(adjMatrix[nodeIndex][j] == 1){
                        System.out.println("Connection found");
                        neighbours.add(nodes.get(j));
                        System.out.println("current neighbours array: " + neighbours);
                    }
                }
            }
            return neighbours;
        }

        // ==== DFS iterative No Comments ====
        public static void dfsIterative2(int[][] adjMatrix, Node start){
            Stack<Node> stack = new Stack<>();
            stack.push(start);
            while(!stack.isEmpty()){
                Node element = stack.pop();
                if(!element.visited){
                    System.out.print(element.data + " ");
                    element.visited = true;
                }
                ArrayList<Node> neighbours = findNeighbours(adjMatrix, element);
                for (int i = 0; i < neighbours.size(); i++) {
                    Node n = neighbours.get(i);
                    if(n != null && !n.visited){
                        stack.push(n);
                    }
                }
            }
        }

        // ==== DFS with goal ====
        public static void dfsIterativeGoal(int[][] adjMatrix, Node start, Node goal){
            Stack<Node> stack = new Stack<>();
            stack.push(start);
            LinkedList<Node> path = new LinkedList<>();
            while(!stack.isEmpty()){
                Node element = stack.pop();
                if(!element.visited){
                    element.visited = true;
                    path.addLast(element);
                    depth++;
                }
                if(element.goal){
                    System.out.println("Goal " + element + " found at depth " + depth);
                    System.out.println("Path: " + path);
                    return;
                }
                ArrayList<Node> neighbours = findNeighbours2(adjMatrix, element);
                for (int i = 0; i < neighbours.size(); i++) {
                    Node n = neighbours.get(i);
                    if(n != null && !n.visited){
                        stack.push(n);
                    }
                }
            }
            System.out.println("Goal not found");
            System.out.println("Path: " + path);
        }

        // BFS iterative with comments of traversal
        public static void bfsIterative(int[][] adjMatrix, Node start){
            Queue<Node> queue = new LinkedList<>();
            LinkedList<Node> path = new LinkedList<>();
            System.out.println("==== BFS start ====");
            queue.offer(start);
            System.out.println("current queue: " + queue);
            start.visited = true;
            System.out.println(start + " marked visited");
            while(!queue.isEmpty()){
                Node element = queue.poll();
                System.out.println("Element polled: " + element);
                path.addLast(element);
                System.out.println(element + " added to current path: " + path);
                ArrayList<Node> neighbours = findNeighbours2(adjMatrix, element);
                for (int i = 0; i < neighbours.size(); i++) {
                    Node n = neighbours.get(i);
                    if(n != null && !n.visited){
                        queue.offer(n);
                        System.out.println(n + " added to queue not visited: " + queue);
                        n.visited = true;
                        System.out.println(n + " is now visited");
                    }
                }
            }
            System.out.println("BFS ends");
            System.out.println("BFS traversal path: " + path);
        }

        public static void printNodes(ArrayList<Node> nodes){
            for (int i = 0; i < nodes.size(); i++) {
                System.out.print(nodes.get(i) + ", ");
            }
        }

    }


}
