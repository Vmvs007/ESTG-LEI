/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so_f1;

import java.text.ParseException;
import jdk.nashorn.internal.parser.JSONParser;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import org.json.simple.JSONObject;
import java.io.IOException;
/*import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;*/
import org.json.simple.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Vitor Santos
 */
public class Operacoes {

    public float soma(float primeiro, float segundo) {

        return primeiro + segundo;
    }

    public float subtracao(float primeiro, float segundo) {

        return primeiro - segundo;
    }

    public float multiplicacao(float primeiro, float segundo) {

        return (primeiro * segundo);
    }

    public float divisao(float primeiro, float segundo) {
        return (primeiro / segundo);
    }

    public void Filereader(String[] args, String ficheiro) {
        float primeiro[] = new float[2];

        try {
            Charset ENCODING = StandardCharsets.UTF_8;
            Path path = Paths.get("C:\\Users\\Ricardo\\Desktop\\ESTG\\2ºAno\\SO\\SO_F1\\" + ficheiro);
            List<String> linhas = Files.readAllLines(path, ENCODING);
            for (int i = 0; i < linhas.size(); i++) {
                System.err.println("Linha_n" + i + linhas.get(i));
                primeiro[i] = Float.parseFloat(linhas.get(i));
            }

        } catch (IOException ex) {
            System.out.println("Erro no acesso ao ficheiro...");
        }

        System.out.println(soma(primeiro[0], primeiro[1]));
        System.out.println(subtracao(primeiro[0], primeiro[1]));
        System.out.println(multiplicacao(primeiro[0], primeiro[1]));
        System.out.println(divisao(primeiro[0], primeiro[1]));
    }
}
