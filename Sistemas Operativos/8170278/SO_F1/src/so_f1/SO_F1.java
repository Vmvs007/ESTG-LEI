/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so_f1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * /**
 *
 * @author Vitor Santos
 */
public class SO_F1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        float primeiro;
        float segundo;
        String ficheiro;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
/*
        System.out.println("Introduza o primeiro numero");
        primeiro = Float.parseFloat(br.readLine());

        System.out.println("Introduza o segundo numero");
        segundo = Float.parseFloat(br.readLine());
*/

        System.out.println("Introduza o nome do ficheiro: ");
        ficheiro = br.readLine();
        
        Operacoes teste = new Operacoes();
/*
        System.out.println("Soma:" + teste.soma(primeiro, segundo));
        System.out.println("Subtracao:" + teste.subtracao(primeiro, segundo));
        System.out.println("Multiplicacao:" + teste.multiplicacao(primeiro, segundo));
        System.out.println("Divisao:" + teste.divisao(primeiro, segundo));
        */
        teste.Filereader(args,ficheiro);
        

    }

}
