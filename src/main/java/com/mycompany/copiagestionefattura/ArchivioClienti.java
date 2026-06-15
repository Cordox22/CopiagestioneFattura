/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class ArchivioClienti implements Iterable<Map.Entry<String, Cliente>>, Serializable{
    private static final long serialVersionUID = 1L;
    private Map<String, Cliente> archivio; 
    
    public ArchivioClienti(){
        this.archivio = new HashMap<>();
    }
    
    public Map<String, Cliente> getArchivio(){
        return this.archivio;
    }
    
    public Cliente cercaPerCF(String cf) throws DatiInvalidiException{
        Cliente c = this.archivio.get(cf);
        
        if(c == null){
            throw new DatiInvalidiException("Il cliente con Codice Fiscale: " + cf + " non esiste");
        }
        
        return c; 
    }
   
    public List<Cliente> cercaPerNomeCognome(String nome, String cognome) throws DatiInvalidiException{
        List<Cliente> elencoClienti = new ArrayList();
        boolean flag = false; 
        nome = nome.trim();
        cognome = cognome.trim();
        
        for(Cliente c : this.archivio.values()){
            if((c.getNome().equalsIgnoreCase(nome)) && (c.getCognome().equalsIgnoreCase(cognome))){
                elencoClienti.add(c);
                flag = true; 
            }
        }
        if(!flag){
            throw new DatiInvalidiException("Cliente " + nome + " " + cognome + " non trovato");
        }
        return elencoClienti;
    }
   
    //qui passo tutti i dati del cliente istanzio l ogg e lo metto nell hashmap
    public void aggiungiCliente(String nome, String cognome, String indirizzo,
        String civico, String cap, String comune, String telefono, String pIva, String cf) throws DatiInvalidiException{
        
        boolean flag = true;
        if(pIva.equals("")){
            flag = false;
        }
        if(this.archivio.containsKey(cf)){
            throw new DatiInvalidiException("Codice Fiscale gia' presente");
        }
        try{
            this.archivio.put(cf.toUpperCase(), new Cliente(nome.trim(), cognome.trim(), indirizzo.trim()
                , civico.trim(), cap.trim(), comune.trim(), telefono.trim(), pIva.trim(), cf.toUpperCase().trim(), flag));
            System.out.println("Cliente inserito correttamente");
        }
        catch(DatiInvalidiException e){
            System.out.println("Dati inseriti non validi");
        }
    }
    
    public void rimuoviCliente(String codiceFiscale)throws DatiInvalidiException{
        if(!this.archivio.containsKey(codiceFiscale.toUpperCase())){
            throw new DatiInvalidiException("Codice Fiscale sbagliato e/o inesistente");
        }
        
        this.archivio.remove(codiceFiscale.toUpperCase()); 
    }
    
    
   
    public void modificaCliente2(String codiceFiscale) throws DatiInvalidiException {

        Scanner input = new Scanner(System.in);

        // 1. Controllo esistenza cliente
        if (!this.archivio.containsKey(codiceFiscale)) {
            throw new DatiInvalidiException("Codice Fiscale sbagliato e/o inesistente");
        }

        Cliente c = this.archivio.get(codiceFiscale);

        System.out.println("Cliente da modificare:");
        System.out.println(c);

        boolean finito = false;

        while (!finito) {
            System.out.println("\n--- MENU MODIFICA CLIENTE ---");
            System.out.println("1) Nome");
            System.out.println("2) Cognome");
            System.out.println("3) Indirizzo");
            System.out.println("4) Civico");
            System.out.println("5) CAP");
            System.out.println("6) Comune");
            System.out.println("7) Telefono");
            System.out.println("8) Partita IVA");
            System.out.println("9) Codice Fiscale");
            System.out.println("0) Fine modifiche");
            System.out.print("Scelta: ");

            String scelta = input.nextLine();

            if (scelta.equals("1")) {
                System.out.print("Nuovo nome (vuoto = invariato): ");
                String nome = input.nextLine();
                if (!nome.isEmpty()) {
                    if(nome.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]+")) {
                        c.setNome(nome);
                    } 
                    else {
                        System.out.println("Nome non valido.");
                    }
                }
            }
            
            //Nome/Cognome	Solo lettere e spazi	"Mario Rossi"
            if (scelta.equals("2")) {
                System.out.print("Nuovo cognome: ");
                String cognome = input.nextLine();
                if (!cognome.isEmpty()) {
                    if (cognome.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]+")) {
                        c.setCognome(cognome);
                    } 
                    else {
                        System.out.println("Cognome non valido.");
                    }
                }
            }

            if (scelta.equals("3")) {
                System.out.print("Nuovo indirizzo: ");
                String indirizzo = input.nextLine();
                if (!indirizzo.isEmpty()) {
                    c.setIndirizzo(indirizzo);
                }
            }
            
            //Civico	Numeri + lettere + "/"	"12A", "5/3"
            if (scelta.equals("4")) {
                System.out.print("Nuovo civico: ");
                String civico = input.nextLine();
                if (!civico.isEmpty()) {
                    if (civico.matches("[0-9A-Za-z/]+")) {
                        c.setCivico(civico);
                    } 
                    else {
                        System.out.println("Civico non valido.");
                    }
                }
            }
            
            //CAP	5 cifre	"06123"
            if (scelta.equals("5")) {
                System.out.print("Nuovo CAP: ");
                String cap = input.nextLine();
                if (!cap.isEmpty()) {
                    if (cap.matches("[0-9]{5}")) {  //espressione regolare -> regExp //{5} accetta solo 5 caratteri
                        c.setCap(cap);
                    } 
                    else {
                        System.out.println("CAP non valido (deve essere di 5 cifre).");
                    }
                }
            }

            if (scelta.equals("6")) {
                System.out.print("Nuovo comune: ");
                String comune = input.nextLine();
                if (!comune.isEmpty()) {
                    if (comune.matches("[A-Za-zÀ-ÖØ-öø-ÿ ]+")) {
                        c.setComune(comune);
                    } 
                    else {
                        System.out.println("Comune non valido.");
                    }
                }
            }
            
            //Telefono	6–15 cifre	"3471234567"
            if (scelta.equals("7")) {
                System.out.print("Nuovo telefono: ");
                String telefono = input.nextLine();
                if (!telefono.isEmpty()) {
                    if (telefono.matches("[0-9]{6,15}")) {
                        c.setTelefono(telefono);
                    } 
                    else {
                        System.out.println("Telefono non valido (solo cifre, 6-15 numeri).");
                    }
                }
            }
            
            //Partita IVA	11 cifre	"01234567890"
            if (scelta.equals("8")) {
                System.out.print("Nuova Partita IVA: ");
                String pi = input.nextLine();
                if (!pi.isEmpty()) {
                    if (pi.matches("[0-9]{11}")) {
                        c.setpIva(pi);
                    } 
                    else {
                        System.out.println("Partita IVA non valida (11 cifre).");
                    }
                }
            }
            
            //Codice Fiscale	16 alfanumerici	"RSSMRA80A01H501U"
            if (scelta.equals("9")) {
                System.out.print("Nuovo Codice Fiscale: ");
                String nuovoCF = input.nextLine();

                if (!nuovoCF.isEmpty()) {
                    if (!nuovoCF.matches("[A-Za-z0-9]{16}")) {
                        System.out.println("Codice Fiscale non valido (16 caratteri alfanumerici).");
                    } 
                    else {
                        // Rimuovo la vecchia chiave
                        this.archivio.remove(codiceFiscale);

                        // Aggiorno l'oggetto
                        c.setCodiceFiscale(nuovoCF);

                        // Reinserisco con la nuova chiave
                        this.archivio.put(nuovoCF, c);

                        // Aggiorno la variabile locale
                        codiceFiscale = nuovoCF;
                    }
                }
            }

            if (scelta.equals("0")) {
                finito = true;
            }
        }

        System.out.println("\nCliente aggiornato:");
        System.out.println(c);
    }

    public Cliente getCliente(String codiceFiscale)throws DatiInvalidiException{
        Cliente cli = null; 
        cli = this.archivio.get(codiceFiscale.toUpperCase());
        
        if(cli == null){
            throw new DatiInvalidiException("Codice Fiscale sbagliato e/o inesistente");
        }
        
        return cli;
    }
    
    public List<Cliente> getClientiOrdinati(){
        List<Cliente> lista = new ArrayList<>(this.archivio.values());
        Collections.sort(lista);
        
        return lista;
    }
    
    public List<Cliente> getClientiOrdinati(Comparator<Cliente> comp){
        List<Cliente> lista = new ArrayList<>(this.archivio.values());
        lista.sort(comp);
        
        return lista;
    }
    
    public void stampaElencoOrdinato(List<Cliente> elenco){
        System.out.println("\n");
        for(Cliente c : elenco){
            System.out.println(c + "\n");
        }
    }
     
    public void stampaFattureClienti(Map<String, Cliente> elenco){
        System.out.println("Cliente:");
        for(Cliente c : elenco.values()){
            System.out.println(c);
        }
    }
    
    /*public void stampaClienti(){
        System.out.println("Elenco di tutti i clienti:\n");
        for(Cliente c : this.archivio.values()){
            System.out.println(c);
        }
    }*/
    
    public void stampaClienti(){
        int cont = 1; 
        for(var entry : this){
            System.out.println("\n\n-------------- " + cont + " --------------");
            System.out.println(entry.getValue());
            cont++;
        }
    }
    
    @Override
    public Iterator<Map.Entry<String, Cliente>> iterator(){
        return new IteratoreCliente(this.archivio);
    }
}
