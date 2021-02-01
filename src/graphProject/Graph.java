package graphProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph<T> {

    /**
     * Directions that the nodes can have.<br>
     *     (Maybe FROM_SECOND_TO_FIRST can be deleted...)
     */
    public enum Direction {
        FROM_FIRST_TO_SECOND,
        FROM_SECOND_TO_FIRST,
        TWO_WAY
    }

    private List<Node<T>> nodes;
    private boolean[][] edgesOfNodes;

    /**
     * Constructor of Graph.
     * @param numberOfNodes is the number of nodes the graph will have
     */
    public Graph(int numberOfNodes) {
        setNumberOfNodes(numberOfNodes);
        this.nodes = new ArrayList<>();
    }

    /**
     *
     * @param numberOfNodes is the number of nodes the graph will have
     */
    private void setNumberOfNodes(int numberOfNodes) {
        edgesOfNodes = new boolean[numberOfNodes][numberOfNodes];
    }

    /**
     * Adds any number of nodes to the nodes' list of the graph.
     * @param nodes is the nodes
     */
    @SafeVarargs
    public final void setListOfNodes(Node<T>... nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
    }

    /**
     * Not necessary, but maybe can be used instead of {@link Graph#setListOfNodes(Node[])}.
     * @param nodes is the list of nodes that the graph contains
     */
    public void setListOfNodes(List<Node<T>> nodes) {
        this.nodes = nodes;
    }

    /**
     * Sets the edge and its direction of two nodes.
     * @param firstNode is a node
     * @param secondNode is an other node
     * @param direction is the direction comes from {@link Direction}.
     */
    public void addDirectionToNodes(Node<T> firstNode, Node<T> secondNode, Direction direction) {
        switch (direction) {
            case FROM_FIRST_TO_SECOND -> edgesOfNodes[firstNode.getId()][secondNode.getId()] = true;
            case FROM_SECOND_TO_FIRST -> edgesOfNodes[secondNode.getId()][firstNode.getId()] = true;
            case TWO_WAY -> {
                edgesOfNodes[firstNode.getId()][secondNode.getId()] = true;
                edgesOfNodes[secondNode.getId()][firstNode.getId()] = true;
            }
        }
    }

    /**
     * Outputs the edges between the nodes of the graph as "node -> children node, other children node, etc"
     */
    public void printEdges() {
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < edgesOfNodes.length; i++) {
            b.append(nodes.get(i).getT()).append(" -> ");
            for (int j = 0; j < edgesOfNodes[i].length; j++) {
                if (edgesOfNodes[i][j]) {
                    b.append(nodes.get(j).getT()).append(", ");
                }
            }
            b.setLength(b.length() - 2);
            b.append("\n");
        }

        b.setLength(b.length() - 1);

        System.out.println(b.toString());
    }

    /**
     * Calls {@link Graph#printEdges()} and {@link Graph#doDeepWalking(List, List, Node, int)}.
     * @param startNode is one node of the graph, which will be the beginner of the deep walking
     */
    public void printGraphInfoAndDeepWalking(Node<T> startNode) {
        System.out.println("Graph info:");
        printEdges();
        System.out.println();
        System.out.println("Deep walking:");

        List<Node<T>> walking = new ArrayList<>();
        List<Node<T>> startNodesChildren = findChildrenOfNode(startNode);

        doDeepWalking(walking, startNodesChildren, startNode, 0);
    }

    /**
     * Finds the children of a node.
     * @param node is the parent node
     * @return the list of the children
     */
    private List<Node<T>> findChildrenOfNode(Node<T> node) {
        List<Node<T>> children = new ArrayList<>();
        for (int i = 0; i < edgesOfNodes[node.getId()].length; i++) {
            if (edgesOfNodes[node.getId()][i]) {
                children.add(nodes.get(i));
            }
        }
        return children;
    }

    /**
     * Do deep walking!<br>
     *     Recursively finds a node's children, creates a walk without stepping on a node that has been already reached,
     *     and prints the walk to the console.
     * @param walking is an empty list (at the beginning) that will store the reached nodes
     * @param children is the children of the starter node
     * @param startNode is the node to start the walking with
     * @param tab is the number of the tabulator
     *            (necessary because of the printout, and may be zero when the method is called)
     */
    private void doDeepWalking(List<Node<T>> walking, List<Node<T>> children, Node<T> startNode, int tab) {
        if (!walking.contains(startNode)) {
            walking.add(startNode);
            for (int i = 0; i < tab; i++) {
                System.out.print("\t");
            }
            System.out.println( (tab == 0 ? startNode.getT() : "-> " + startNode.getT()) );
            tab++;
            for (Node<T> node : children) {
                doDeepWalking(walking, findChildrenOfNode(node), node, tab);
            }
        }
    }

}