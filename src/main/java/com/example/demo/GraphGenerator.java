package com.example.demo;
import com.example.demo.Graf.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {

    public static Graph generateGraph(int n) {
        Graph graph = new Graph();
        for (int i = 1; i <= n; i++) {
            graph.addVertex(String.valueOf(i));
        }
        for (int i = 1; i <= n; i++) {
            int m = (int) (Math.random() * (n - i) + 1);
            int j = i + m;
            graph.addEdge(String.valueOf(i), String.valueOf(j));
        }
        return graph;

    }

    public static String getRandomVertex(Graph graph) {
        List<String> vertices = new ArrayList<>(graph.getVertices());
        Random rand = new Random();
        return vertices.get(rand.nextInt(vertices.size()));
    }
}


