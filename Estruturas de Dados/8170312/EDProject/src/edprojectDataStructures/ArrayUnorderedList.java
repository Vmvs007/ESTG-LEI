/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edprojectDataStructures;

import interfaces.UnorderedListADT;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    public ArrayUnorderedList(int capacity) {
        super(capacity);
    }

    public ArrayUnorderedList() {
        super();
    }

    @Override
    public void addToFront(T element) {
        int i = 0, fim;

        fim = size();

        while (fim > 0) {
            list[fim] = list[fim - 1];
            fim--;
        }
        list[0] = element;
        count++;

    }

    @Override
    public void addToRear(T element) {
        list[size()] = element;
        count++;
    }

    @Override
    public void addAfter(T element, T target) {
        int i = 0, fim;
        while (i < size()) {
            fim = size();
            if (list[i].equals(target)) {
                while (fim > i) {
                    list[fim+1] = list[fim];
                    fim--;
                }
                list[i+1] = element;
                count++;
            } 
            i++;
        }

    }

}

