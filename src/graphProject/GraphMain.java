package graphProject;

public class GraphMain {

    public static void main(String[] args) {

        // creates a graph that contains String nodes, and sets the graph "size" to 5
        Graph<String> stringGraph = new Graph<>(5);

        // creates 5 nodes
        Node<String> a = new Node<>();
        a.set("a");
        Node<String> b = new Node<>();
        b.set("b");
        Node<String> c = new Node<>();
        c.set("c");
        Node<String> d = new Node<>();
        d.set("d");
        Node<String> e = new Node<>();
        e.set("e");

        // adds the created nodes to the graph
        stringGraph.setListOfNodes(a, b, c, d, e);

        // sets the edges (and the directions) of the nodes
        stringGraph.addDirectionToNodes(a, b, Graph.Direction.TWO_WAY);
        stringGraph.addDirectionToNodes(a, c, Graph.Direction.FROM_FIRST_TO_SECOND);
        stringGraph.addDirectionToNodes(a, d, Graph.Direction.FROM_SECOND_TO_FIRST);
        stringGraph.addDirectionToNodes(b, e, Graph.Direction.TWO_WAY);
        stringGraph.addDirectionToNodes(c, d, Graph.Direction.TWO_WAY);
        stringGraph.addDirectionToNodes(c, e, Graph.Direction.FROM_FIRST_TO_SECOND);
        stringGraph.addDirectionToNodes(e, a, Graph.Direction.FROM_FIRST_TO_SECOND);

        // gets the info of the graph, and walks deep through it...
        stringGraph.printGraphInfoAndDeepWalking(e);
        
    }
    
}
