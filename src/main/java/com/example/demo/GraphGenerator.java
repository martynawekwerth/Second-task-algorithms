package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphGenerator {

    public static Graph generateGraph(int n) {

        if(n < 1) return null;

        Graph graph = new Graph();
        graph.addVertex(new Vertex(1));
        for (int i = 2; i <= n; i++) {

            graph.addEdge(new Vertex(i), new Vertex((int) (Math.random() * (i - 1) + 1)));
        }



        return graph;

    }

    public static Vertex getRandomVertex(Graph graph) {
        List<Vertex> vertices = new ArrayList<>(graph.getVertices());
        Random rand = new Random();
        return vertices.get(rand.nextInt(vertices.size()));
    }
}


