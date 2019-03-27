/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edproject;

/**
 *
 * @author vmvs0
 */
public class FormacaoAcademica {
    private int anoFormacao;
    private String formacao;

    public FormacaoAcademica(int anoFormacao, String formacao) {
        this.anoFormacao = anoFormacao;
        this.formacao = formacao;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public int getAnoFormacao() {
        return anoFormacao;
    }

    public void setAnoFormacao(int anoFormacao) {
        this.anoFormacao = anoFormacao;
    }

    @Override
    public String toString() {
        return "<html>"
               + "Formacao Academica {" + "<br>"
               + "Ano Formacao = " + anoFormacao + "<br>"
               + "Formacao = " + formacao + "<br>"
               + "}" + "<br>";
    }
    
    
}
