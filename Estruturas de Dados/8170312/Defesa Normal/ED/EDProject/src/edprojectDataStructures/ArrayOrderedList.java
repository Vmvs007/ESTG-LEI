package edprojectDataStructures;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    public ArrayOrderedList(){
        super();
    }
    public ArrayOrderedList(int inicialCapacity){
        super(inicialCapacity);
    }
    @Override
    public void add(T element) {
        if(element instanceof Comparable){
            int i=0;
            boolean scan=false;
            while(i<rear && scan==false){
                if(((Comparable)list[i]).compareTo(element)<0){
                    i++;
                }else{
                    scan=true;
                }
            }
            if(scan){

            }

        }else{

        }
    }
}
