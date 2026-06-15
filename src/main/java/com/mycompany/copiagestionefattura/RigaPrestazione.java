/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class RigaPrestazione implements Serializable {
    private static final long serialVersionUID = 1L;
    private String descrizione;
    private double importo; 
    
    public RigaPrestazione(String descrizione, double importo){
        this.descrizione = descrizione; 
        this.importo = importo; 
    }
    
    public String getDescrizione(){
        return this.descrizione;
    }
    public double getImporto(){
        return this.importo;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public void setImporto(double importo) {
        this.importo = importo;
    }
}
