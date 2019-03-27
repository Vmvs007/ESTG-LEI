/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edproject;

import java.util.Arrays;

/**
 *
 * @author vmvs0
 */
public class User {

    private int id;
    private String nome;
    private int idade;
    private int visualizacoes;
    private String email;
    private FormacaoAcademica[] formacao;
    private CargosProfissionais[] cargos;
    private int[] contacts;
    private int[] mencoes;

    public User(int id, String nome, int idade, int visualizacoes, String email, FormacaoAcademica[] formacao, CargosProfissionais[] cargos, int[] contacts, int[] mencoes) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.visualizacoes = visualizacoes;
        this.email = email;
        this.formacao = formacao;
        this.cargos = cargos;
        this.contacts = contacts;
        this.mencoes = mencoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(int visualizacoes) {
        this.visualizacoes = visualizacoes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FormacaoAcademica[] getFormacao() {
        return formacao;
    }

    public void setFormacao(FormacaoAcademica[] formacao) {
        this.formacao = formacao;
    }

    public CargosProfissionais[] getCargos() {
        return cargos;
    }

    public void setCargos(CargosProfissionais[] cargos) {
        this.cargos = cargos;
    }

    public int[] getContacts() {
        return contacts;
    }

    public void setContacts(int[] contacts) {
        this.contacts = contacts;
    }

    public int[] getMencoes() {
        return mencoes;
    }

    public void setMencoes(int[] mencoes) {
        this.mencoes = mencoes;
    }

    @Override
    public String toString() {
        return "<html>"
                + "Utilizador {" + "<br>"
                + "ID = " + id + "<br>"
                + "Nome = " + nome + "<br>"
                + "Idade = " + idade + "<br>"
                + "Visualizacoes = " + visualizacoes + "<br>"
                + "Email = " + email + "<br>"
                + "Formacao Academica = " + Arrays.toString(formacao) + "<br>"
                + "Cargos Profissionais = " + Arrays.toString(cargos)+ "<br>"
                + "Contactos = " + Arrays.toString(contacts)+ "<br>"
                + "Mencoes = " + Arrays.toString(mencoes) + "<br>"
                + "}" + "<br>"
                + "</html>";
    }

}
