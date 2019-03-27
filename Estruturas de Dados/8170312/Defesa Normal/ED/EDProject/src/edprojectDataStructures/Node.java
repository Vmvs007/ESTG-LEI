package edprojectDataStructures;

public class Node<T> {
    private T elem;
    private Node<T> next;

    public Node() {

    }

    public Node(T elem) {
        this.elem = elem;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    public T getElement(){
        return this.elem;
    }

    public Node<T> getNext(){
        return this.next;
    }

    @Override
    public String toString(){
        return "Node{"+ "elem="+elem+"}";
    }
}
