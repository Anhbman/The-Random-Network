package finalOOP.model.network;

public class Edge {
    private Node start;
    private Node destination;
    private double probality;

    public Edge(Node start, Node destination, double probality) {
        this.start = start;
        this.destination = destination;
        this.probality = probality;
    }

    public Edge(Node start, Node destination) {
        this.start = start;
        this.destination = destination;
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getDestination() {
        return destination;
    }

    public void setDestination(Node destination) {
        this.destination = destination;
    }

    public double getProbality() {
        return probality;
    }

    public void setProbality(double probality) {
        this.probality = probality;
    }

    public boolean compareTo(Edge edge) {
        if (this.start == edge.start && this.destination == edge.destination) {
            return true;
        }
        return false;
    }
}

