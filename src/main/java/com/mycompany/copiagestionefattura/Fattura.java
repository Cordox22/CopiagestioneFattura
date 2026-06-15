/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


/**
 *
 * @author User
 */
public class Fattura implements Comparable<Fattura>, Serializable{
    private static final long serialVersionUID = 1L;
    private int numero; 
    private double totaleFattura; 
    private double iva; 
    private double imponibile; 
    private double ritenuta; 
    private double totaleIvato; 
    private double subtotale; 
    private boolean flag; 
    private String data; 
    private String nota; 
    private String codiceFiscale; 
    private boolean chiusa; 
    private final List<RigaPrestazione> righe = new ArrayList();
    
    public Fattura(){}

    public Fattura(int numero, String data,boolean flag, String nota, String codiceFiscale) {
        this.numero = numero;
        this.flag = flag;
        this.data = data;
        this.nota = nota;
        this.codiceFiscale = codiceFiscale;
        //this.calcoloFattura();
    }

    public Fattura(int numero, double totaleFattura, double iva, double imponibile, double ritenuta, double totaleIvato,
        double subtotale, boolean flag, String data, String codiceFiscale, String nota, boolean chiusa) {
        this.numero = numero;
        this.totaleFattura = totaleFattura;
        this.iva = iva;
        this.imponibile = imponibile;
        this.ritenuta = ritenuta;
        this.totaleIvato = totaleIvato;
        this.subtotale = subtotale;
        this.flag = flag;
        this.data = data;
        this.codiceFiscale = codiceFiscale; 
        this.nota = nota; 
        this.chiusa = chiusa;
    }
    
    
    public void aggiungiRiga(String descrizione, double importo){
        this.righe.add(new RigaPrestazione(descrizione, importo));
    }
    
    public void modificaRiga(int indice, String nuovaDescrizione, double nuovoImporto) {
        //La riga non deve sapere nulla. La fattura decide.
        if (this.chiusa) {
            throw new UnsupportedOperationException("Fattura già emessa: non modificabile");
        }

        if (indice < 0 || indice >= this.righe.size()) {
            throw new IllegalArgumentException("Indice riga non valido");
        }

        final double IVA = 0.22;
        double imponib = Math.round(nuovoImporto / (1 + IVA) * 100) / 100.0;

        RigaPrestazione r = this.righe.get(indice);
        r.setDescrizione(nuovaDescrizione);
        r.setImporto(imponib);
    }
    
    public int getNumero() {
        return this.numero;
    }
    public double getIva(){
        return this.iva;
    }
    public double getTotaleFattura() {
        return this.totaleFattura;
    }
    public double getImponibile() {
        return this.imponibile;
    }
    public double getRitenuta() {
        return this.ritenuta;
    }
    public double getTotaleIvato() {
        return this.totaleIvato;
    }
    public double getSubtotale() {
        return this.subtotale;
    }
    public boolean getFlag() {
        return this.flag;
    }
    public String getData() {
        return this.data;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    public String getNota() {
        return nota;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public List<RigaPrestazione> getRighe() {
        return Collections.unmodifiableList(righe);
    }
    public void setTotaleFattura(double totaleFattura) {
        this.totaleFattura = totaleFattura;
    }
    public List<RigaPrestazione> getListaRighe(){
        return this.righe;
    }
    public boolean isChiusa() {
        return chiusa;
    }
    public void setChiusa(boolean chiusa) {
        this.chiusa = chiusa;
    }
    
    /*public void calcoloProfessionista(){
        final double IVA = 0.22;
        final double RIT = 0.20;
        final double TOLLERANZA = 0.01;
        final int MAX_ITER = 50;
        
        int i = 0;
        double imponibile = this.totaleFattura;
        double iva, ritenuta = 0, subtotale, scarto, totaleIvato = 0;
        do{
            iva = imponibile * IVA;
            if(this.flag){
                totaleIvato = imponibile + iva;
                ritenuta = imponibile * RIT;
                subtotale = totaleIvato - ritenuta;
            }
            else{
                subtotale = imponibile + iva;
            }
            scarto = this.totaleFattura - subtotale;
            imponibile += scarto;
            i++;
        }while((Math.abs(scarto) > TOLLERANZA)&&(i < MAX_ITER));
        
        this.imponibile = Math.round(imponibile * 100) / 100.0; 
        this.iva = Math.round(iva * 100) / 100.0;
        this.totaleIvato = Math.round(totaleIvato * 100) / 100.0;
        this.ritenuta = Math.round(ritenuta * 100) / 100.0;
        //this.subtotale = Math.round(subtotale * 100) / 100.0;
        this.subtotale = this.totaleIvato - this.ritenuta;
    }*/
    
    public void calcoloArtigiano(){
        this.flag = false;
        final double IVA = 22;
        final double TOLLERANZA = 0.01;
         
        this.imponibile = 0; 
        for(RigaPrestazione app : this.righe){
            this.imponibile += Math.round(app.getImporto() * 100) / 100.0;
        }
        this.iva = Math.round(this.imponibile * IVA) / 100.0;
        this.totaleIvato = this.imponibile + this.iva;
        this.subtotale = this.totaleIvato;
        
        if(Math.abs(this.subtotale - this.getTotaleFattura()) > TOLLERANZA){
            System.out.println("\nIl calcolo eccede dalla tolleranza\n");
        }
    }
    
    @Override
    public int compareTo(Fattura altro){
        
        return Integer.compare(this.numero, altro.numero);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ITALY);
        LocalDate ld = LocalDate.parse(this.data);       // converte la stringa ISO in LocalDate
        String dataInItaliano = ld.format(fmt);     // applica il formatter italiano
        
        sb.append(String.format("SORA %-15s %30s %d%n%n", dataInItaliano, "Fattura n.", this.numero));
        //Nota
        if(!this.nota.isEmpty()){
            sb.append(String.format("%s%n%n", this.nota));
        }
        //righe fattura
        sb.append(String.format("%-40s %10s%n", "Descrizione", "Importo"));
        sb.append(String.format("%-40s %10s%n", "----------------------------------------", "----------"));

        for (RigaPrestazione r : this.getRighe()) {
            sb.append(String.format("%-40s %10.2f%n", r.getDescrizione(), r.getImporto()));
        }
        sb.append("\n");
        
        //calcolo
        sb.append(String.format("%-40s %10.2f%n", "Imponibile", this.imponibile));
        sb.append(String.format("%-40s %10.2f%n", "I.V.A. 22%", this.iva));
        sb.append(String.format("%-40s %10s%n", "----------------------------------------", "----------"));
        sb.append(String.format("%-40s %10.2f%n", "Totale", this.totaleIvato));
        
        if(flag){
            sb.append(String.format("%-40s %10.2f-%n", "Ritenuta d'acconto 20%", this.ritenuta));
            sb.append(String.format("%-40s %10s%n", "----------------------------------------", "----------"));
            sb.append(String.format("%-40s %10.2f%n%n", "Netto a pagare", this.subtotale));
        }
        
        System.out.println("\n\n---------------------------------------------------");
        return sb.toString();
    }
}
