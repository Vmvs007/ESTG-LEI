package edproject;

import edprojectDataStructures.LinkedQueue;
import java.util.Iterator;

public class Main {
//Exercicio 3 parte 2;
    public static void main(String[] args) {
	LinkedQueue nova1=new LinkedQueue();
	LinkedQueue nova2=new LinkedQueue();
	LinkedQueue nova3=new LinkedQueue();
	nova1.enqueue(1);
	nova1.enqueue(2);
	nova1.enqueue(3);
	nova1.enqueue(4);
	nova1.enqueue(5);
	nova2.enqueue(3);
	nova2.enqueue(4);
	nova2.enqueue(5);
	nova2.enqueue(6);
	nova2.enqueue(7);
	while(!nova2.isEmpty() && !nova1.isEmpty()){
        if(((int)nova1.first())<((int)nova2.first())){
            nova3.enqueue(nova1.dequeue());
        }else{
            nova3.enqueue(nova2.dequeue());
        }
	}
	if(nova2.isEmpty() && !nova1.isEmpty()){
	    while(!nova1.isEmpty()){
	        nova3.enqueue(nova1.dequeue());
        }
    }else if(nova1.isEmpty() && !nova2.isEmpty()){
	    while(!nova2.isEmpty()){
	        nova3.enqueue(nova2.dequeue());
        }

        }

        System.out.println(nova3.toString());

    }


//ficha 7 exercicio 2

 /*   public static void main(String[] args) {
        DoubleOrderedList nova1=new DoubleOrderedList();
        nova1.add("teste1");
        nova1.add("teste2");
        nova1.add("teste3");
        nova1.add("teste4");
        nova1.print(nova1.front);

    }

*/
    }

