/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package so_f2;

import java.io.*;

/**
 *
 * @author Vitor Santos
 */
public class Shell {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Modo_de_uso:_java_Shell_<comando>");
            System.exit(0);
        }
        //args[0] comando a executar
        ProcessBuilder pb = new ProcessBuilder(args[0]);
        Process process = pb.start();

        //obter acesso ao output do processo
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        //ler o output do processo
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();

        System.out.println("Inicio_de_comando_\"" + args[0] + "\".");
        try {
            process.waitFor();
            System.out.println("Fim_de_comando_\"" + args[0] + "\".");
        } catch (InterruptedException e) {
        }
    }
}
