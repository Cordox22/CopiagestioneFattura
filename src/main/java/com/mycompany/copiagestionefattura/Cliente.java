/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Cliente implements Comparable<Cliente>, Serializable{
    private static final long serialVersionUID = 1L;
    private String nome; 
    private String cognome; 
    private String indirizzo; 
    private String civico; 
    private String cap; 
    private String comune; 
    private String telefono; 
    private String pIva; 
    private String codiceFiscale;
    private boolean flag; 
    
    public Cliente(String nome, String cognome, String indirizzo, String civico, String cap, String comune,
        String telefono, String pIva, String codiceFiscale, boolean flag) throws DatiInvalidiException{
        if((nome == null) || (nome.isBlank())){
            throw new DatiInvalidiException("Il nome del cliente e' obbligatorio\n");
        }
        if((cognome == null) || (cognome.isBlank())){
            throw new DatiInvalidiException("Il cognome del cliente e' obbligatorio\n");
        }
        if((indirizzo == null) || (indirizzo.isBlank())){
            throw new DatiInvalidiException("L'indirizzo del cliente e' obbligatorio\n");
        }
        if((civico == null) || (civico.isBlank())){
            throw new DatiInvalidiException("Il civico del cliente e' obbligatorio\n");
        }
        if((cap == null) || (cap.isBlank())){
            throw new DatiInvalidiException("Il CAP del cliente e' obbligatorio\n");
        } 
        if((comune == null) || (comune.isBlank())){
            throw new DatiInvalidiException("Il comune del cliente e' obbligatorio\n");
        } 
        if((telefono == null) || (telefono.isBlank())){
            throw new DatiInvalidiException("Il telefono del cliente e' obbligatorio\n");
        } 
        if((codiceFiscale == null) || (codiceFiscale.isBlank())){
            throw new DatiInvalidiException("Il codice fiscale del cliente e' obbligatorio\n");
        } 
        
        this.nome = nome; 
        this.cognome = cognome; 
        this.indirizzo = indirizzo; 
        this.civico = civico; 
        this.cap = cap; 
        this.comune = comune; 
        this.telefono = telefono; 
        this.pIva = pIva; 
        this.codiceFiscale = codiceFiscale.toUpperCase(); 
        this.flag = flag; 
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getCivico() {
        return civico;
    }
    public void setCivico(String civico) {
        this.civico = civico;
    }
    public String getCap() {
        return cap;
    }
    public void setCap(String cap) {
        this.cap = cap;
    }
    public String getComune() {
        return comune;
    }
    public void setComune(String comune) {
        this.comune = comune;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getpIva() {
        return pIva;
    }
    public void setpIva(String pIva) {
        this.pIva = pIva;
    }
    public boolean getFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    
    public String intestazioneFattura(){
        StringBuilder sb = new StringBuilder();
        sb.append("Dati Cliente\n\n");
        sb.append(this.nome).append(this.cognome);
        sb.append("\n").append(this.indirizzo).append(this.civico);
        sb.append("\n").append(this.cap).append(this.comune);
        sb.append("\n").append("P.IVA ").append(this.pIva);
        sb.append("\n").append("C.F: ").append(this.codiceFiscale).append("\n");
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.cognome);
        hash = 23 * hash + Objects.hashCode(this.indirizzo);
        hash = 23 * hash + Objects.hashCode(this.civico);
        hash = 23 * hash + Objects.hashCode(this.cap);
        hash = 23 * hash + Objects.hashCode(this.comune);
        hash = 23 * hash + Objects.hashCode(this.telefono);
        hash = 23 * hash + Objects.hashCode(this.pIva);
        hash = 23 * hash + Objects.hashCode(this.codiceFiscale);
        hash = 23 * hash + (this.flag ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.civico, other.civico)) {
            return false;
        }
        if (!Objects.equals(this.cap, other.cap)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.pIva, other.pIva)) {
            return false;
        }
        if (this.flag != other.flag) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cognome, other.cognome)) {
            return false;
        }
        if (!Objects.equals(this.indirizzo, other.indirizzo)) {
            return false;
        }
        if (!Objects.equals(this.comune, other.comune)) {
            return false;
        }
        return Objects.equals(this.codiceFiscale, other.codiceFiscale);
    }
    
    @Override 
    public int compareTo(Cliente altro){
        int comp = this.cognome.compareTo(altro.cognome);
        if(comp == 0){
            comp = this.nome.compareTo(altro.nome);
        }
        
        return comp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(this.nome);
        sb.append("\nCognome: ").append(this.cognome);
        sb.append("\nVia: ").append(this.indirizzo);
        sb.append(" ").append(this.civico);
        sb.append("\n").append(this.cap);
        sb.append(" ").append(this.comune);
        if(this.flag){
            sb.append("\nP.I. ").append(this.pIva);
        }
        sb.append("\nC.F. ").append(this.codiceFiscale);
      
        return sb.toString();
    }
}
