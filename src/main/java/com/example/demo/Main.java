package com.example.demo;
import java.util.Set;
import com.example.demo.Graf.Graph;


import static com.example.demo.GraphGenerator.getRandomVertex;

public class Main {


    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator();
        StepCounter counter = new StepCounter();


        int numVertices = 23;
        Graph graph = GraphGenerator.generateGraph(numVertices);
        System.out.println("Wygenerowany graf o " + numVertices + " wierzchołkach");


        String startVertex = getRandomVertex(graph);
        String endVertex = getRandomVertex(graph);
        System.out.println("Losowo wybrane wierzchołki: " + startVertex + ", " + endVertex);

        Set<String> bfsResult = graph.breadthFirstSearch(graph, startVertex, counter);
        System.out.println("Wynik BFS: " + bfsResult);
        System.out.println("Liczba kroków w BFS: " + counter.get_count());

        counter = new StepCounter();
        Set<String> dfsResult = graph.depthFirstSearch(graph, startVertex, counter);
        System.out.println("Wynik DFS: " + dfsResult);
        System.out.println("Liczba kroków w DFS: " + counter.get_count());

        for (int i = 0; i < 3; i++) {
            numVertices += 23;
            graph = graphGenerator.generateGraph(numVertices);
            System.out.println("\nWygenerowany graf o " + numVertices + " wierzchołkach");


            startVertex = getRandomVertex(graph);
            endVertex = getRandomVertex(graph);
            System.out.println("Losowo wybrane wierzchołki: " + startVertex + ", " + endVertex);

            counter = new StepCounter();
            bfsResult = graph.breadthFirstSearch(graph, startVertex, counter);
            System.out.println("Wynik BFS: " + bfsResult);
            System.out.println("Liczba kroków w BFS: " + counter.get_count());

            counter = new StepCounter();
            dfsResult = graph.depthFirstSearch(graph, startVertex, counter);
            System.out.println("Wynik DFS: " + dfsResult);
            System.out.println("Liczba kroków w DFS: " + counter.get_count());
        }


    }
}


