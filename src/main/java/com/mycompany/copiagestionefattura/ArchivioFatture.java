/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class ArchivioFatture implements Iterable<Map.Entry<Integer, Fattura>>, Serializable{
    private static final long serialVersionUID = 1L;
    private Map<Integer, Fattura> fatture;
    private ArchivioClienti archivioClienti; 
    private int nFatt;
    
    public ArchivioFatture(ArchivioClienti arch){
        this.fatture = new HashMap<>();
        this.archivioClienti = arch;
        this.nFatt = 0;
    }
    
    public Map<Integer, Fattura> getArchivio(){
        return this.fatture;
    }
    
    private int inserimentoInterno(String nota, boolean flag, String cf) throws NumeroFatturaInvalidoException, DatiInvalidiException{
      
        //DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd MMMM YYYY", Locale.ITALY);
        //String data = LocalDate.now().format(fmt);
        String data = LocalDate.now().toString();
        
        //1 Calcolo del numero
        this.nFatt = this.nFatt + 1; 
        int numero = this.nFatt;
        //2 Creo la fattura
        Fattura f = new Fattura(numero, data, flag, nota, cf);
        //3 Inserisco nell HashMap
        this.fatture.put(numero, f);
        
        return numero; 
    }
    
    public int inserimento(String nota, String cf) throws DatiInvalidiException, NumeroFatturaInvalidoException  {
        Cliente c = this.archivioClienti.cercaPerCF(cf);
        boolean flag = c.getFlag();
        
        int numeroFattura = inserimentoInterno(nota, flag, cf);
        
        return numeroFattura;
    }
    
    /*public String inserimento(double totaleFattura, String nota, String nome, String cognome, String cf) throws DatiInvalidiException, NumeroFatturaInvalidoException{
        Cliente c = this.archivioClienti.cercaPerNomeCognome(nome, cognome); 
        boolean flag = c.getFlag();
        
        String numeroFattura = inserimentoInterno(totaleFattura, nota, flag, cf);
        
        return numeroFattura;
    }*/
    
    public void inserimentoDaCsv(int numero, Fattura fattura) {
        this.fatture.put(numero, fattura);
        
        try{
            this.nFatt = fattura.getNumero();
        }
        catch(NumberFormatException e){
            // Non faccio nulla: numero non valido → nFatt resta invariato
        }
    }
    
    public Fattura getFattura(int numeroFattura) throws DatiInvalidiException{
        Fattura f = this.fatture.get(numeroFattura);
        
        if(f == null){
            throw new DatiInvalidiException("Il numero di fattura: " + numeroFattura + " non esiste");
        }
        
        return f;
    }
   
   public void rimuoviFattura(int numeroFattura) throws FatturaNonTrovataException{
        Fattura f = this.fatture.remove(numeroFattura);
        
        if(f == null){
            throw new FatturaNonTrovataException("Numero di fattura errato o inesistente");
        }
    }
  
    public Fattura cercaFattura(int numeroFattura) throws FatturaNonTrovataException{
        Fattura fattura = null; 
        fattura = this.fatture.get(numeroFattura);
       
        if(fattura == null){
            throw new FatturaNonTrovataException("Numero di fattura errato o inesistente");
        }
        
        return fattura; 
    }

    /*public List<Fattura> listaPerCliente(Comparator<Fattura> comp){
        List<Fattura> lista = new ArrayList<>(this.fatture.values());
        lista.sort(comp);
        
        return lista;
    }*/
    
    public void stampaFattureCollegateAiClienti(String cf) throws DatiInvalidiException{
        Map<String, Cliente> archivioClienti; 
        archivioClienti = this.archivioClienti.getArchivio();
        boolean flag = false;
        List<Fattura> elenco = new ArrayList<>();
        
        for (Fattura f : this.fatture.values()) {
            if (f.getCodiceFiscale().equals(cf)) {
                elenco.add(f);
                flag = true;
            }
        }
        if (!flag) {
            throw new DatiInvalidiException("Nessuna fattura collegata a questo codice fiscale: " + cf);
        }
        
        if(!archivioClienti.containsKey(cf)){
            throw new DatiInvalidiException("Codice Fiscale errato sbagliato e/o inesistente");
        }
        
        Cliente c = archivioClienti.get(cf);
        System.out.println("\nCliente:\n" + c);
        System.out.println("\nFatture collegate:\n");
        this.stampaFatture(elenco);
    }
    
    public List<Fattura> fattureOrdinatePerNumero() {
        List<Fattura> lista = new ArrayList<>(this.fatture.values());
        Collections.sort(lista);
        
        return lista;
    }
    
    public List<Fattura> OrdinamentoPerData(Comparator<Fattura> comp){
        List<Fattura> lista = new ArrayList<>(this.fatture.values());
        lista.sort(comp);
        
        return lista;
    }
    
    public void ordina(){}
    
    public void stampaFatture(List<Fattura> elenco){
        for(Fattura f : elenco){
            System.out.println(f);
        }
    }
    
    public void stampaFatture(){
        System.out.println("Elenco di tutte le fatture:\n");
        for(Fattura f : this.fatture.values()){
            System.out.println(f);
        }
    }
    
    @Override 
    public Iterator<Map.Entry<Integer, Fattura>> iterator(){
        return new IteratoreFatture(this.fatture);
    }
}
