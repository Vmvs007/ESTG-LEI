/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

import interfaces.QueueADT;
import java.util.Iterator;


/**
 *
 * @author Vitor Santos - 8170312
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private Node<T> front;
    private Node<T> rear;
    private int count = 0;

    @Override
    public void enqueue(T element) {
        Node<T> temp = new Node<T>(element);
        if (isEmpty()) {
            front = temp;
            rear = front;
            count++;
        } else {
            rear.setNext(temp);
            rear = temp;
            count++;
        }

    }

    @Override
    public T dequeue() {
        Node<T> aux = new Node<T>();
        aux = front;
        front = (front.getNext());
        count--;
        return aux.getElem();
    }

    @Override
    public T first() {
        return front.getElem();
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
        Node<T> aux = new Node<T>();
        aux = front;

        while (aux != null) {
            results = results + (aux.toString()) + "\n";
            aux = aux.getNext();
        }
        return results;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
