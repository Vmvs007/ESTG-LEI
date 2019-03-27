/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edproject;

/**
 *
 * @author Vitor Santos - 8170312
 */
public class CargosProfissionais {
    private int anoCargo;
    private String cargo;
    private String empresa;

    public CargosProfissionais(int anoCargo, String cargo, String empresa) {
        this.anoCargo = anoCargo;
        this.cargo = cargo;
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getAnoCargo() {
        return anoCargo;
    }

    public void setAnoCargo(int anoCargo) {
        this.anoCargo =anoCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "<html>"
               + "CargosProfissionais {" + "<br>"
               + "Ano Cargo = " + anoCargo + "<br>"
               + "Cargo = " + cargo + "<br>"
               + "Empresa = " + empresa + "<br>"
               + "}" + "<br>";
    }
    
    
    
}
