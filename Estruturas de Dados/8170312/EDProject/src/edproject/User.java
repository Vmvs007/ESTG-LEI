/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edproject;

import java.util.Arrays;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class User {

    private int id;
    private String nome;
    private int idade;
    private String email;
    private int visualizacoes;
    private FormacaoAcademica[] formacao;
    private CargosProfissionais[] cargos;
    private String[] skills;
    private int[] contacts;
    private int[] mencoes;

    public User() {
    }

    public User(int id, String nome, int idade, String email, int visualizacoes, FormacaoAcademica[] formacao, CargosProfissionais[] cargos, String[] skills, int[] contacts, int[] mencoes) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.visualizacoes = visualizacoes;
        this.formacao = formacao;
        this.cargos = cargos;
        this.skills = skills;
        this.contacts = contacts;
        this.mencoes = mencoes;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkill(String[] skills) {
        this.skills = skills;
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

    public CargosProfissionais[] getCargos() {
        return cargos;
    }

    public void setCargos(CargosProfissionais[] cargos) {
        this.cargos = cargos;
    }

    public FormacaoAcademica[] getFormacao() {
        return formacao;
    }

    public void setFormacao(FormacaoAcademica[] formacao) {
        this.formacao = formacao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(int visualizacoes) {
        this.visualizacoes = visualizacoes;
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
                + "Skills = " + Arrays.toString(skills) + "<br>"
                + "Contactos = " + Arrays.toString(contacts)+ "<br>"
                + "Mencoes = " + Arrays.toString(mencoes) + "<br>"
                + "}" + "<br>"
                + "</html>";
    }

}
