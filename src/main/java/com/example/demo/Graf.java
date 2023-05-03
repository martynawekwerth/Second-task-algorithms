package com.example.demo;

import java.util.*;

import com.example.demo.StepCounter;


public class Graf {

    static class Graph {

        Map<Vertex, List<Vertex>> adjacentVertices;

        public Graph() {
            this.adjacentVertices = new HashMap<>();
        }

        StepCounter counter = new StepCounter();

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
            adjacentVertices.putIfAbsent(v1, new ArrayList<>());
            adjacentVertices.putIfAbsent(v2, new ArrayList<>());
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

        public Set<String> getVertices() {
            Set<String> vertices = new HashSet<>();
            for (Vertex v : adjacentVertices.keySet()) {
                vertices.add(v.label);
            }
            return vertices;
        }





        List<Vertex> getAdjVertices(String label) {
            return adjacentVertices.get(new Vertex(label));
        }

        Set<String> breadthFirstSearch(Graph graph, String root, StepCounter counter) {
            Set<String> visited = new LinkedHashSet<String>();
            Queue<String> queue = new LinkedList<String>();
            queue.add(root);
            visited.add(root);
            counter.step();
            while (!queue.isEmpty()) {
                String vertex = queue.poll();

                for (Vertex v : graph.getAdjVertices(vertex)) {
                    if (!visited.contains(v.label)) {
                        visited.add(v.label);
                        queue.add(v.label);
                        counter.step();

                    }
                }
            }
            return visited;
        }


        Set<String> depthFirstSearch(Graph graph, String root, StepCounter counter) {
            Set<String> visited = new LinkedHashSet<String>();
            Stack<String> stack = new Stack<String>();
            stack.push(root);
            while (!stack.isEmpty()) {
                String vertex = stack.pop();
                if (!visited.contains(vertex)) {
                    visited.add(vertex);
                    counter.step();
                    for (Vertex v : graph.getAdjVertices(vertex)) {
                        stack.push(v.label);
                    }
                }
            }
            return visited;
        }
    }
}



