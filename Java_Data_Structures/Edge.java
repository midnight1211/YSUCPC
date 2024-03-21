public class Edge {
    public static int DestinationVertexID;
    public static int weight;

    Edge() { }
    Edge(int destVID, int w) {
        DestinationVertexID = destVID;
        weight = w;
    }

    public void setEdgeValues(int destVID, int w) {
        DestinationVertexID = destVID;
        weight = w;
    }

    public void setWeight(int w) {
        weight = w;
    }

    public static int getDestinationVertexID() {
        return DestinationVertexID;
    }

    public static int getWeight() {
        return weight;
    }
}