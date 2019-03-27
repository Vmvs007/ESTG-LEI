/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

import interfaces.StackADT;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class LinkedStack<T> implements StackADT<T> {

    private Node<T> top;
    private int count = 0;

    @Override
    public void push(T element) {
        Node<T> temp = new Node<T>(element);

        temp.setNext(top);
        top = temp;
        count++;

    }

    @Override
    public T pop() {
        Node<T> aux = new Node<T>();
        aux = this.top;
        this.top = top.getNext();
        count--;

        return aux.getElem();

    }

    @Override
    public T peek() {
        return this.top.getElem();

    }

    @Override
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        String results = "";
        int i = 0;
        Node<T> aux = new Node<T>();
        aux = this.top;
        while (aux != null) {
            results = results + aux.toString() + "\n";
            aux = aux.getNext();
        }
        return results;
    }

}