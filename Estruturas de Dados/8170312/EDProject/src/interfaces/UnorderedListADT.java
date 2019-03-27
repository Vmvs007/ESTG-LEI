/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Vitor Santos - 8170312
 */
public interface UnorderedListADT<T> extends ListADT<T> {

    public void addToFront(T element);

    public void addToRear(T element);

    public void addAfter(T element,T target);

}