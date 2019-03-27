/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class Node<T> {

    private T elem;
    private Node<T> next;

    public Node() {
    }

    public Node(T elem) {
        this.elem = elem;
    }

    public void setElem(T elem) {
        this.elem = elem;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getElem() {
        return elem;
    }

    public Node<T> getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "Node{" + "elem=" + elem + '}';
    }

}
