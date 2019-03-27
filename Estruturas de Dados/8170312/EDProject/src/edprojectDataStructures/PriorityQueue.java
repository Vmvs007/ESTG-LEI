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
public class PriorityQueue<T> extends LinkedHeap<PriorityQueueNode> {

    /**
     * Creates an empty priority queue.
     */
    public PriorityQueue() {
        super();
    }

    /**
     * Adds the given element to this PriorityQueue.
     *
     * @param object the element to be added to the priority queue
     * @param priority the integer priority of the element to be added
     */
    public void addElement(T object, int priority) {
        PriorityQueueNode<T> node
                = new PriorityQueueNode<T>(object, priority);
        super.addElement(node);
    }

    /**
     * Removes the next highest priority element from this priority queue and
     * returns a reference to it.
     *
     * @return a reference to the next highest priority element in this queue
     */
    public T removeNext() {
        PriorityQueueNode<T> temp
                = (PriorityQueueNode<T>) super.removeMin();
        return temp.getElement();
    }
}

