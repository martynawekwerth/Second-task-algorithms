package com.example.demo;

import java.util.*;

public class Graph {

    Map<Vertex, List<Vertex>> adjacentVertices;

    public Graph() {
        this.adjacentVertices = new HashMap<>();
    }

    StepCounter counter = new StepCounter();


    void addVertex(Vertex v) {
        adjacentVertices.putIfAbsent(v, new ArrayList<>());
    }

    void removeVertex(Vertex v) {
        adjacentVertices.values().stream().forEach(e -> e.remove(v));
        adjacentVertices.remove(v);
    }

    void addEdge(Vertex v1, Vertex v2) {
        adjacentVertices.putIfAbsent(v1, new ArrayList<>());
        adjacentVertices.putIfAbsent(v2, new ArrayList<>());
        if(!adjacentVertices.get(v1).contains(v2))
            adjacentVertices.get(v1).add(v2);
        if(!adjacentVertices.get(v2).contains(v1))
            adjacentVertices.get(v2).add(v1);
    }

    void removeEdge(Vertex v1, Vertex v2) {
        List<Vertex> eV1 = adjacentVertices.get(v1);
        List<Vertex> eV2 = adjacentVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    public Set<Vertex> getVertices() {
        return new HashSet<>(adjacentVertices.keySet());
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Vertex, List<Vertex>> entry: adjacentVertices.entrySet()){
            stringBuilder.append(entry.getKey().label + ": [");
            for(Vertex v : entry.getValue()) {
                stringBuilder.append(v.label + ",");
            }
            stringBuilder.append("], ");
        }
        return stringBuilder.toString();
    }


    List<Vertex> getAdjVertices(Vertex v) {
        return adjacentVertices.get(v);
    }

    ArrayList<Vertex> breadthFirstSearch(Graph graph, Vertex root, Vertex target, StepCounter counter) {
        ArrayList<Vertex> visited = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        counter.step();
        outerLoop:
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();

            for (Vertex v : graph.getAdjVertices(vertex)) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                    counter.step();
                    if(v.equals(target))
                        break outerLoop;
                }
            }
        }
        return visited;
    }


    ArrayList<Vertex> depthFirstSearch(Graph graph, Vertex root, Vertex target, StepCounter counter) {
        ArrayList<Vertex> visited = new ArrayList<>();
        Stack<Vertex> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                counter.step();
                if(vertex.equals(target))
                    break;
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    stack.push(v);
                }
            }
        }
        return visited;
    }
}



