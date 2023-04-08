package com.example.demo;

import org.junit.Test;

import java.util.*;


public class Graf {

    class Graph {

        private Map<Vertex, List<Vertex>> adjacentVertices;

        public Graph() {
            this.adjacentVertices = new HashMap<>();
        }

        class Vertex {
            String label;

            Vertex(String label) {
                this.label = label;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Vertex vertex = (Vertex) o;
                return Objects.equals(label, vertex.label);
            }

            @Override
            public int hashCode() {
                return Objects.hash(label);
            }
        }

        void addVertex(String label) {
            adjacentVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
        }

        void removeVertex(String label) {
            Vertex v = new Vertex(label);
            adjacentVertices.values().stream().forEach(e -> e.remove(v));
            adjacentVertices.remove(new Vertex(label));
        }

        void addEdge(String label1, String label2) {
            Vertex v1 = new Vertex(label1);
            Vertex v2 = new Vertex(label2);
            adjacentVertices.get(v1).add(v2);
            adjacentVertices.get(v2).add(v1);
        }

        void removeEdge(String label1, String label2) {
            Vertex v1 = new Vertex(label1);
            Vertex v2 = new Vertex(label2);
            List<Vertex> eV1 = adjacentVertices.get(v1);
            List<Vertex> eV2 = adjacentVertices.get(v2);
            if (eV1 != null)
                eV1.remove(v2);
            if (eV2 != null)
                eV2.remove(v1);
        }

        Graph createGraph() {
            Graph graph = new Graph();
            graph.addVertex("Bob");
            graph.addVertex("Alice");
            graph.addVertex("Mark");
            graph.addVertex("Rob");
            graph.addVertex("Maria");
            graph.addEdge("Bob", "Alice");
            graph.addEdge("Bob", "Rob");
            graph.addEdge("Alice", "Mark");
            graph.addEdge("Rob", "Mark");
            graph.addEdge("Alice", "Maria");
            graph.addEdge("Rob", "Maria");
            return graph;
        }

        List<Vertex> getAdjVertices(String label) {
            return adjacentVertices.get(new Vertex(label));
        }

        Set<String> breadthFirstTraversal(Graph graph, String root) {
            Set<String> visited = new LinkedHashSet<String>();
            Queue<String> queue = new LinkedList<String>();
            queue.add(root);
            visited.add(root);
            while (!queue.isEmpty()) {
                String vertex = queue.poll();
                for (Vertex v : graph.getAdjVertices(vertex)) {
                    if (!visited.contains(v.label)) {
                        visited.add(v.label);
                        queue.add(v.label);
                    }
                }
            }
            return visited;
        }
    }


    @Test
    public void testBreadthFirstTraversal() {
        Graf.Graph graph = new Graf().new Graph();
        graph = graph.createGraph();
        Set<String> expected = new LinkedHashSet<>(Arrays.asList("Bob", "Alice", "Rob", "Mark", "Maria"));
        Set<String> result = graph.breadthFirstTraversal(graph, "Bob");
        if (expected.equals(result)) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }
}