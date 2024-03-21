import java.util.LinkedList;
import java.util.Iterator;
public class Vertex {
    public static int state_id;
    public String state_name;
    public static LinkedList<Edge> edgeList;

    public Vertex() {
        state_id = 0;
        state_name = "";
    }

    public Vertex(int id, String name) {
        state_id = id;
        state_name = name;
    }

    public int getStateID() {
        return state_id;
    }

    public String getStateName() {
        return state_name;
    }

    public void setID(int id) {
        state_id = id;
    }

    public void setStateName(String name) {
        state_name = name;
    }

    public LinkedList<Edge> getEdgeList() {
        return edgeList;
    }

    public static void addEdgetoEdgeList(Edge toVertexID, int weight) {
        Edge e = new Edge();
        edgeList.push(e);
        System.out.println("Edge between " + state_id + " and " + toVertexID + " added successfully.\n");
    }

    public void printEdgeList() {
        System.out.println("[");
        Iterator<Edge> it = edgeList.iterator();
        while (it.hasNext()) {
            System.out.println(Edge.getDestinationVertexID() + "(" + Edge.getWeight() + ") --> ");
        }
        System.out.println("]");
        System.out.println("\n");
    }

    public void updateVertexName(String name) {
        state_name = name;
        System.out.println("Vertex Name Updated Successfully");
    }
}
