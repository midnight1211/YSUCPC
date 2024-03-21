import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;

public class Graph {
    private ArrayList<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public boolean checkIfVertexExistByID(int vid) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getStateID() == vid) {
                return true;
            }
        }
        return false;
    }

    public void addVertex(Vertex newVertex) {
        if (checkIfVertexExistByID(newVertex.getStateID())) {
            System.out.println("Vertex with this ID already exists.");
        } else {
            vertices.add(newVertex);
            System.out.println("New vertex added successfully.\n");
        }
    }

    public Vertex getVertexByID(int vid) {
        for (int i = 0; i < vertices.size(); i++) {
            Vertex temp = vertices.get(i);
            if (temp.getStateID() == vid) {
                return temp;
            }
        }
        return null;
    }

    public boolean checkIfEdgeExistsByID(int fromVertex, int toVertex) {
        Vertex v = getVertexByID(fromVertex);
        if (v != null) {
            LinkedList<Edge> e = v.getEdgeList();
            ListIterator<Edge> it = e.listIterator();
            while (it.hasNext()) {
                if (it.next().getDestinationVertexID() == toVertex) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addEdgeByID(int fromVertex, int toVertex, int weight) {
        boolean check1 = checkIfVertexExistByID(fromVertex);
        boolean check2 = checkIfVertexExistByID(toVertex);

        if (check1 && check2) {
            if (checkIfEdgeExistsByID(fromVertex, toVertex)) {
                System.out.println("Edge between " + fromVertex + " and " + toVertex + " already exists.\n");
            } else {
                Vertex vFrom = getVertexByID(fromVertex);
                Vertex vTo = getVertexByID(toVertex);
                if (vFrom != null && vTo != null) {
                    Edge e = new Edge(toVertex, weight);
                    vFrom.addEdgetoEdgeList(e, weight);
                    System.out.println("Edge between " + fromVertex + " and " + toVertex + " added successfully.\n");
                } else {
                    System.out.println("Invalid Vertex ID entered.");
                }
            }
        } else {
            System.out.println("Invalid Vertex ID entered.");
        }
    }

    public void updateEdgeByID(int fromVertex, int toVertex, int newWeight) {
        if (checkIfEdgeExistsByID(fromVertex, toVertex)) {
            Vertex vFrom = getVertexByID(fromVertex);
            if (vFrom != null) {
                LinkedList<Edge> edgeList = vFrom.getEdgeList();
                ListIterator<Edge> it = edgeList.listIterator();
                while (it.hasNext()) {
                    Edge edge = it.next();
                    if (edge.getDestinationVertexID() == toVertex) {
                        edge.setWeight(newWeight);
                        System.out.println("Edge weight updated successfully.\n");
                        return;
                    }
                }
            }
        }
        System.out.println("Edge not found or invalid Vertex ID.");
    }
}