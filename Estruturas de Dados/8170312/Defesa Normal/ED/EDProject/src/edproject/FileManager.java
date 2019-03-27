/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edproject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.toIntExact;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author vmvs0
 */
public class FileManager {

    public User[] readFile(String path) {
        //Intancia JSONParser
        JSONParser jParser = new JSONParser();
        User[] usersNull = null;

        try {
            //Instancia um JSONObject e coloca nele todo o conteudo do ficheiro
            JSONObject file = (JSONObject) jParser.parse(new FileReader(path));

            //Instancia um JSONArray de grafoSocial
            JSONArray socialGraph = (JSONArray) file.get("grafoSocial");

            //Instancia um Array de Users com o tamanho do numero de Users em socialGraph
            User[] users = new User[socialGraph.size()];

            //Instancia variaveis de User
            int id, idade, visualizacoes, contadorUsers = 0;
            String nome, email;
            //Corre uma vez para cada User em socialGraph
            for (Object us : socialGraph) {
                if (us instanceof JSONObject) {
                    id = toIntExact((Long) ((JSONObject) us).get("id")); //Guarda num inteiro (id) o JSONObject ("id")
                    nome = (String) ((JSONObject) us).get("nome"); //Guarda numa String (nome) o JSONObject ("nome");
                    idade = toIntExact((Long) ((JSONObject) us).get("idade")); //Guarda num inteiro (idade) o JSONObject ("idade")
                    email = (String) ((JSONObject) us).get("email"); //Guarda numa String (email) o JSONObject ("email");
                    visualizacoes = toIntExact((Long) ((JSONObject) us).get("visualizacoes")); //Guarda num inteiro (visualizacoes) o JSONObject ("visualizacoes")

                    //Instancia um JSONArray de formacaoAcademica
                    JSONArray formacao = (JSONArray) ((JSONObject) us).get("formacaoAcademica");

                    //Instancia um Array de FormacaoAcademica em cada User
                    FormacaoAcademica[] formacaoAca = new FormacaoAcademica[formacao.size()];

                    //Instancia variaveis de FormacaoAcademica
                    int anoFormacao, contadorFormacao = 0;
                    String formAca;
                    //Corre uma vez para cada FormacaoAcademica em cada User
                    for (Object fo : formacao) {
                        if (fo instanceof JSONObject) {
                            anoFormacao = toIntExact((Long) ((JSONObject) fo).get("ano")); //Guarda num inteiro (anoCargo) o JSONObject ("ano")
                            formAca = (String) ((JSONObject) fo).get("formacao"); //Guarda numa String (formAca) o JSONObject ("formacao");

                            formacaoAca[contadorFormacao] = new FormacaoAcademica(anoFormacao, formAca);
                            contadorFormacao++; //Incrementa contador de Formacoes
                        }
                    }

                    //Instancia um JSONArray de cargosProfissionais
                    JSONArray cargos = (JSONArray) ((JSONObject) us).get("cargosProfissionais");

                    //Instancia um Array de CargosProfissionais em cada User
                    CargosProfissionais[] cargosPro = new CargosProfissionais[cargos.size()];

                    //Instancia variaveis de CargosProfissionais
                    int anoCargo, contadorCargos = 0;
                    String cargo, empresa;

                    //Corre uma vez para cada CargoProfissional em cada User
                    for (Object fo : cargos) {
                        if (fo instanceof JSONObject) {
                            anoCargo = toIntExact((Long) ((JSONObject) fo).get("ano")); //Guarda num inteiro (anoCargo) o JSONObject ("ano")
                            cargo = (String) ((JSONObject) fo).get("cargo"); //Guarda numa String (cargo) o JSONObject ("cargo");
                            empresa = (String) ((JSONObject) fo).get("empresa"); //Guarda numa String (empresa) o JSONObject ("empresa");

                            cargosPro[contadorCargos] = new CargosProfissionais(anoCargo, cargo, empresa);
                            contadorCargos++; //Incrementa contador de Cargos
                        }
                    }

                    //Instancia um JSONArray de skills
                    JSONArray SkillsArray = (JSONArray) ((JSONObject) us).get("skills");

                    //Instancia as variaveis de skills
                    int contadorSkills = 0;
                    String[] skills = (new String[SkillsArray.size()]);

                    //Corre uma vez para cada skill em cada User
                    for (contadorSkills = 0; contadorSkills < SkillsArray.size(); contadorSkills++) {
                        skills[contadorSkills] = SkillsArray.get(contadorSkills).toString();
                    }

                    //Instancia um JSONArray de contactos
                    JSONArray contactos = (JSONArray) ((JSONObject) us).get("contacts");

                    //Instancia um Array de Contactos em cada User
                    int[] contacts = new int[contactos.size()];

                    //Instancia variaveis de Contactos
                    int contadorContactos = 0;
                    int contact;

                    //Corre uma vez para cada Contacto em cada User
                    for (Object fo : contactos) {
                        if (fo instanceof JSONObject) {
                            contact= toIntExact((Long) ((JSONObject) fo).get("userid")); //Guarda num inteiro (contact) o JSONObject ("contact")

                            contacts[contadorContactos] = contact;
                            contadorContactos++; //Incrementa contador de Contactos
                        }
                    }

                    //Instancia um JSONArray de mencoes
                    JSONArray mencoesJ =  (JSONArray) ((JSONObject) us).get("mencoes");

                    //Instancia um Array de Mencoes em cada User
                    int[] mencoes = new int[mencoesJ.size()];

                    //Instancia variaveis de Mencoes
                    int contadorMencoes = 0;
                    int mencao;

                    //Corre uma vez para cada Mencao em cada User
                    for (Object fo : mencoesJ) {
                        if (fo instanceof JSONObject) {
                               mencao = toIntExact((Long) ((JSONObject) fo).get("userid")); //Guarda num inteiro (mencao) o JSONObject ("mencoes")

                            mencoes[contadorMencoes] = mencao;
                            contadorMencoes++; //Incrementa contador de Mencoes
                        }
                    }
                    users[contadorUsers] = new User(id, nome, idade, visualizacoes, email, formacaoAca, cargosPro, contacts, mencoes);
                }

                contadorUsers++; // Incrementa contador de Users
            }
            return users;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException e) {
        }

        //Caso não tenha nenhum User em socialGraph
        return usersNull;
    }
}
