package edprojectDataStructures;

public class LinkedQueue<T> implements QueueADT<T> {
    private Node<T> front,rear;
    private int count=0;
    public LinkedQueue()
    {
        count = 0;
        front = rear = null;
    }
    @Override
    public void enqueue(T element) {
        Node<T> node = new Node<T>(element);

        if (isEmpty())
            front = node;
        else
            rear.setNext (node);

        rear = node;
        count++;
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            return null;

        T result = front.getElement();
        front = front.getNext();
        count--;

        if (isEmpty())
            rear = null;

        return result;
    }

    @Override
    public T first() {
        if (isEmpty())
           return null;

        return front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    @Override
    public int size() {
        return count;
    }
    @Override
    public String toString(){
        Node<T> aux=front;
        String results="";
        while(aux!=null){
            results=results+aux.getElement();
            aux=aux.getNext();
        }
        return results;
    }
}
