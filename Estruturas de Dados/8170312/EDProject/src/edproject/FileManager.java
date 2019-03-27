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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.*;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class FileManager {

    /**
     * Método para ler um ficheiro JSON, incluindo os JSONObjects e JSONArrays contidos no mesmo
     * @param path
     * @return 
     */
    public User[] readFile(String path) {
        
        JSONParser parser = new JSONParser();
        User[] pessoasnull = null;
        
        try {
            
            //Declaração de variáveis para armazenamento de JSONObjects (id, idade, views, nome e email)
            int id, idade, visualizacoes;
            String nome, email;
            
            //Declaração do JSONObject que vai conter o ficheiro completo
            JSONObject social = (JSONObject) parser.parse(new FileReader(path));
            
            //Declaração do JSONArray que vai conter todos os Users (Grafo Social)
            JSONArray grafo = (JSONArray) social.get("grafoSocial");
            
            //Declaração de um array de Users com o tamanho igual ao número de Users no Grafo Social
            User[] users = new User[grafo.size()];
            
            //Declaração de um contador de Users
            int contaUsers = 0;
            
            //Ciclo que corre para cada User no Grafo Social
            for (Object us : grafo) {
                if (us instanceof JSONObject) {
                    id = toIntExact((Long) ((JSONObject) us).get("id")); //Guarda numa variável (Int id) o JSONObject (id)
                    nome = (String) ((JSONObject) us).get("nome"); //Guarda numa variável (String nome) o JSONObject (nome)
                    idade = toIntExact((Long) ((JSONObject) us).get("idade")); //Guarda numa variável (Int idade) o JSONObject (idade)
                    email = (String) ((JSONObject) us).get("email"); //Guarda numa variável (String email) o JSONObject (email)

                    //Declaração de um JSONArray que guarda as Formações Académicas de um User
                    JSONArray formacao = (JSONArray) ((JSONObject) us).get("formacaoAcademica");
                    //Declaração de um array de FormacoesAcademicas com o tamanho igual ao número de Formações Académicas do User
                    FormacaoAcademica[] formacaoArray = (new FormacaoAcademica[formacao.size()]);

                    int contaFormacao = 0, anoFormacao;
                    String formAca;
                    
                    //Ciclo que corre para cada Formacao do User
                    for (Object a : formacao) {
                        if (a instanceof JSONObject) {
                            anoFormacao = toIntExact((Long) ((JSONObject) a).get("ano"));
                            formAca = (String) ((JSONObject) a).get("formacao");
                            formacaoArray[contaFormacao] = new FormacaoAcademica(anoFormacao, formAca);
                            contaFormacao++;
                        }
                    }

                    //Declaração de um JSONArray que guarda os Cargos Profissionais de um User
                    JSONArray cargos = (JSONArray) ((JSONObject) us).get("cargosProfissionais");
                    //Declaração de um array de CargosProfissionais com o tamanho igual ao número de Cargos Profissionais do User
                    CargosProfissionais[] cargosArray = (new CargosProfissionais[cargos.size()]);

                    int contaCargos = 0, anoCargo;
                    String cargo, empresa;
                    
                    //Ciclo que corre para cada Cargo do User
                    for (Object a : cargos) {
                        if (a instanceof JSONObject) {
                            anoCargo = toIntExact((Long) ((JSONObject) a).get("ano"));
                            cargo = (String) ((JSONObject) a).get("cargo");
                            empresa = (String) ((JSONObject) a).get("empresa");
                            cargosArray[contaCargos] = new CargosProfissionais(anoCargo, cargo, empresa);
                            contaCargos++;
                        }
                    }
                    
                    //Declaração de um JSONArray que guarda as Skills de um User
                    JSONArray skillsArray = (JSONArray) ((JSONObject) us).get("skills");
                    //Declaração de um array de Strings com o tamanho igual ao número de Skills do User
                    String[] skills = (new String[skillsArray.size()]);

                    int contaSkills;
                    
                    //Ciclo que corre para cada Skill do User
                    for (contaSkills = 0; contaSkills < skillsArray.size(); contaSkills++) {
                        skills[contaSkills] = skillsArray.get(contaSkills).toString();

                    }

                    //Declaração de um JSONArray que guarda os Contactos de um User
                    JSONArray contacts = (JSONArray) ((JSONObject) us).get("contacts");
                    //Declaração de um array de Ints com o tamanho igual ao número de Contactos do User
                    int[] contactsArray = (new int[contacts.size()]);
                    
                    int contaContacts = 0;
                    
                    //Ciclo que corre para cada Contacto do User
                    for (Object a : contacts) {
                        if (a instanceof JSONObject) {
                            contactsArray[contaContacts] = toIntExact((Long) ((JSONObject) a).get("userid"));
                            contaContacts++;
                        }
                    }

                    //Declaração de um JSONArray que guarda as Menções de um User
                    JSONArray mencoes = (JSONArray) ((JSONObject) us).get("mencoes");
                    //Declaração de um array de Ints com o tamanho igual ao número de Menções do User
                    int[] mencoesArray = (new int[mencoes.size()]);
                    
                    int contaMencoes = 0;
                    
                    //Ciclo que corre para cada Menção do User
                    for (Object a : mencoes) {
                        if (a instanceof JSONObject) {
                            mencoesArray[contaMencoes] = toIntExact((Long) ((JSONObject) a).get("userid"));
                            contaMencoes++;
                        }
                    }
                    visualizacoes = toIntExact((Long) ((JSONObject) us).get("visualizacoes")); //Guarda numa variável (Int visualizacoes) o JSONObject (visualizacoes)
                    users[contaUsers] = new User(id, nome, idade, email, visualizacoes, formacaoArray, cargosArray, skills, contactsArray, mencoesArray);
                    contaUsers++;
                }
            }

            return users;
            
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFound");
        } catch (IOException | ParseException e) {
            System.out.println("Exception");
        }
        //Retorno em caso de não haverem Users no Grafo Social
        return pessoasnull;
    }

}
