/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author vmvs0
 */
public class EDProject {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        //Lê o path para o ficheiro JSON
        String path = ("socialGraph_Work.json"), testeEmail;

        User[] user;
        int i = 0;

        FileManager fm = new FileManager();
        user = fm.readFile(path);

        while (i < user.length) {
            System.out.println(user[i].toString());
            System.out.println("");
            i++;
            testeEmail = user[i].getEmail();
            System.out.println("Queres o email: " +testeEmail);
        }
    }
}
