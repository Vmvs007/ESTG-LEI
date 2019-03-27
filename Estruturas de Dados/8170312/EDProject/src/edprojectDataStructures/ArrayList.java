/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

import java.util.Iterator;
import interfaces.ListADT;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class ArrayList<T> implements ListADT<T> {

    protected final int DEFAULT_CAPACITY = 100;
    protected T[] list;
    protected int count;

    public ArrayList() {

        list = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    public ArrayList(int capacity) {
        list = (T[]) (new Object[capacity]);
    }

    @Override
    public T removeFirst() {
        int i = 0;

        T aux = this.list[0];
        this.list[0] = null;

        while (i < this.size() - 1) {
            this.list[i] = this.list[i + 1];
            i++;
        }
        this.list[size()] = null;
        count--;
        return aux;
    }

    @Override
    public T removeLast() {
        T aux = this.list[size()];
        this.list[size()] = null;
        count--;
        return aux;
    }

    @Override
    public T remove(T element) {
        int i = 0;
        T aux = null;
        while (i < size()) {
            if (list[i].equals(element)) {
                aux = this.list[i];
                while (i < size()) {
                    this.list[i] = this.list[i + 1];
                }
            }
            i++;
        }
        return null;
    }

    @Override
    public T first() {
        return this.list[0];
    }

    @Override
    public T last() {
        return this.list[size()];
    }

    @Override
    public boolean contains(T target) {
        int i = 0;
        while (i < size()) {
            if (list[i].equals(target)) {
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        String result = "";
        int i = 0;
        while (i < size()) {
            result = result + list[i].toString() + "\n";
            i++;

        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<T>(list, count);
    }

}


