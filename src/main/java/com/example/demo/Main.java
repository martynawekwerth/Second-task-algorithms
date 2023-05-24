package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;


public class Main {
    public static void main(String[] args) {
        StepCounter counter;

        for (int i = 3; i <= 10; i++) {

            int numVertices = (int) Math.pow(2, i);
            Graph graph = GraphGenerator.generateGraph(numVertices);
            System.out.println("Wygenerowany graf o " + numVertices + " wierzchołkach");
            System.out.println(graph);

            Vertex startVertex = GraphGenerator.getRandomVertex(graph);
            Vertex endVertex;
            do
                endVertex = GraphGenerator.getRandomVertex(graph);
            while (startVertex.equals(endVertex));
            System.out.println("Losowo wybrany wierzchołek startowy: " + startVertex.label);
            System.out.println("Losowo wybrany wierzchołek docelowy: " + endVertex.label);

            counter = new StepCounter();
            ArrayList<Vertex> bfsResult = graph.breadthFirstSearch(graph, startVertex, endVertex,  counter);
            System.out.print("Wynik BFS: [");
            for (int j=0; j<bfsResult.size(); j++) {
                System.out.print(bfsResult.get(j).label);
                if (j < bfsResult.size() - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("Liczba kroków w BFS: " + counter.get_count());

            counter = new StepCounter();
            ArrayList<Vertex> dfsResult = graph.depthFirstSearch(graph, startVertex, endVertex, counter);
            System.out.print("Wynik DFS: [");
            for (int j=0; j<dfsResult.size(); j++) {
                System.out.print(dfsResult.get(j).label);
                if (j < dfsResult.size() - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
            System.out.println("Liczba kroków w DFS: " + counter.get_count());

            System.out.println();
        }
    }
}


