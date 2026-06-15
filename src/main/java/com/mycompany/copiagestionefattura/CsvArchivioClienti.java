/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

/**
 *
 * @author User
 */
public class CsvArchivioClienti {
    private final ArchivioClienti clienti;
    
    public CsvArchivioClienti(ArchivioClienti archClienti){
        this.clienti = archClienti;
    }
        
    public void salvaClientiSuFile() throws IOException{
        Map<String, Cliente> archivio = this.clienti.getArchivio();
        
        if(Files.exists(Paths.get("clienti.bak2"))){
            Files.delete(Paths.get("clienti.bak2"));
        }
        if(Files.exists(Paths.get("clienti.bak"))){
            Files.move(Paths.get("clienti.bak"), Paths.get("clienti.bak2"), StandardCopyOption.REPLACE_EXISTING);
        }
        if(Files.exists(Paths.get("clienti.csv"))){
            Files.move(Paths.get("clienti.csv"), Paths.get("clienti.back"), StandardCopyOption.REPLACE_EXISTING);
        }
        
        String titolo = "Nome;Cognome;Indirizzo;Civico;CAP;Comune;Telefono;P.Iva;Codice Fiscale";
        try(PrintWriter pw = new PrintWriter(new FileWriter("clienti.csv"))){
            pw.println(titolo);
            for(Cliente c : archivio.values()){
                pw.println(
                    c.getNome() + ";" +
                    c.getCognome() + ";" + 
                    c.getIndirizzo() + ";" +
                    c.getCivico() + ";" +
                    c.getCap() + ";" +
                    c.getComune() + ";" + 
                    c.getTelefono() + ";" + 
                    c.getpIva() + ";" + 
                    c.getCodiceFiscale()
                );
            }
            System.out.println("File clienti.csv salvato con successo!");
        }
        catch(IOException e){
            System.out.println("Errore durante il salvataggio del file cliente " + e.getMessage());
        }
    }
    
    public void caricaClientiDaFile() throws FileNotFoundException, IOException, DatiInvalidiException {
        
        try(BufferedReader br = new BufferedReader(new FileReader("clienti.csv"))){ 
            String riga = br.readLine();
            
            while((riga = br.readLine()) != null){
                String[] campi = riga.split(";");
                String nome = campi[0];
                String cognome = campi[1];
                String indirizzo = campi[2];
                String civico = campi[3];
                String cap = campi[4];
                String comune = campi[5];
                String telefono = campi[6];
                String pIva = campi[7];
                String codiceFiscale = campi[8];
                
                try{
                    this.clienti.aggiungiCliente(nome, cognome, indirizzo, civico, cap, comune, telefono, pIva, codiceFiscale);
                }
                catch(DatiInvalidiException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("File clienti.csv caricato con successo!");
    }
}
