package com.visualisingdatastructures.graphsadjlist2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graphs g = new Graphs(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        System.out.println(g);
        System.out.print("Basic BFS: ");
        g.bfs(0);
        System.out.println("\n");
        System.out.print("Basic DFS: ");
        g.dfs(0);
        System.out.println("\n");
        System.out.println("DFS with goal");
        g.DFS(0, 4);
        System.out.println("\n");
        System.out.println("BFS with goal");
        g.BFS(0, 4);
    }

    public static class Graphs {

        private final LinkedList<Integer>[] adj;
        private int E;
        private int V;

        public Graphs(int nodes){
            this.V = nodes;
            this.E = 0;
            this.adj = new LinkedList[nodes];
            for (int i = 0; i < V; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(V + " vertices" + ", " + E + " edges" + "\n");
            for (int i = 0; i < V; i++) {
                sb.append(i + ": ");
                for(int w : adj[i]){
                    sb.append(w + " ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        // O(V+E)
        public void bfs(int s){
            boolean[] visited = new boolean[V];
            Queue<Integer> q = new LinkedList<>();
            visited[s] = true;
            q.offer(s);
            while(!q.isEmpty()){
                int u = q.poll();
                System.out.print(u + " ");
                for(int v : adj[u]){
                    if(!visited[v]){
                        visited[v] = true;
                        q.offer(v);
                    }
                }
            }
        }

        // O(V+E)
        public void dfs(int s){
            boolean[] visited = new boolean[V];
            Stack<Integer> stack = new Stack<>();
            stack.push(s);
            while(!stack.isEmpty()) {
                int u = stack.pop();
                if (!visited[u]) {
                    visited[u] = true;
                    System.out.print(u + " ");
                    for(int v : adj[u]) {
                        if(!visited[v]) {
                            stack.push(v);
                        }
                    }
                }
            }
        }

        // DFS with goal
        public boolean DFS(int start, int goal){
            int depth = 0;
            if(start == goal){
                System.out.println("Goal " + start + " found at depth 0");
                return true;
            }
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[V];
            LinkedList<Integer> path = new LinkedList<>();

            stack.push(start);

            while(!stack.isEmpty()){
                int u = stack.pop();
                if(u == goal){
                    System.out.println("Goal node " + u + " found at level " + depth);
                    System.out.print("path: " );
                    for(int i : path){
                        System.out.print(i + " ");
                    }
                    System.out.print(u);
                    return true;
                } else {
                    if(!visited[u]){
                        visited[u] = true;
                        path.addLast(u);
                        depth++;
                        for(int v : adj[u]){
                            if(!visited[v]){
                                stack.push(v);
                            }
                        }
                    }
                }
            }
            System.out.println("Goal node not found");
            return false;
        }

        public void addEdge(int u, int v){
            this.adj[u].add(v);
            this.adj[v].add(u);
            E++;
        }
    }
}
