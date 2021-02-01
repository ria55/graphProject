package graphProject;

public class Node<T> {

    /**
     * It is necessary because {@link Graph} stores the edges of its nodes in a 2D array.
     * This counter will set the position of the node in that 2D array. (Or what...)
     */
    private static int counter = 0;

    private T t;
    private int id;

    public void set(T t) {
        this.t = t;
        id = counter;
        counter++;
    }

    public T getT() {
        return t;
    }

    public int getId() {
        return id;
    }

}