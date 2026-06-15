/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.copiagestionefattura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class CopiaGestioneFattura  {

    public static void main(String[] args) throws DatiInvalidiException, NumeroFatturaInvalidoException, IOException{
        Scanner input = new Scanner(System.in);
        int scelta = 0;
        String nota = "", data;
        
        // 1. Creo gli archivi
        ArchivioClienti ac = new ArchivioClienti();
        ArchivioFatture af = new ArchivioFatture(ac);
        
        //Creazione archivioClienti e archivioFatture
        CsvArchivioClienti archivioClienti = new CsvArchivioClienti(ac);
        CsvArchivioFatture archivioFatture = new CsvArchivioFatture(af);  

        try {
            archivioClienti.caricaClientiDaFile();
        } 
        catch (DatiInvalidiException e) {
            System.out.println("Dati clienti.csv non validi");
        }
        catch(IOException r){
            System.out.println("Caricamento clienti.csv fallito");
        }
        
        try {
            archivioFatture.caricaFattureDaFile();
        } 
        catch (DatiInvalidiException e) {
            System.out.println("Dati fatture.csv non validi");
        }
        catch(IOException r){
            System.out.println("Caricamento fatture.csv fallito");
        }
        
        do{
            System.out.println("\nMenu di scelte:");
            System.out.println("Premi 1 per aggiungere un cliente nuovo");
            System.out.println("Premi 2 per emettere fattura");
            System.out.println("Premi 3 per cercare il codice fiscale di un cliete tramite nome e cognome");
            System.out.println("Premi 4 per l'elenco clienti ordianti per cognome e nome");
            System.out.println("Premi 5 per l'elenco clienti ordinati per comune");
            System.out.println("Premi 6 per l'elenco fatture ordinate per data");
            System.out.println("Premi 7 per stampare tutte le fatture collegate ad un cliente");
            System.out.println("Premi 8 per modificare i dati di un clienti");
            System.out.println("Premi 9 per eliminare un cliente");
            System.out.println("Premi 10 per la stampa di tutti i clienti");
            System.out.println("Premi 11 per la stampa di tutte le fatture");
            System.out.println("Premi 12 per cercare una fattura per anno");
            System.out.println("Premi 13 per modificare le righe della fattura non ancora emessa");
            System.out.println("Premi 14 per uscire");
            System.out.print("Cosa scegli: ");
            
            try{
                scelta = Integer.parseInt(input.nextLine());
                switch (scelta) {
                    case 1 -> {
                        String nome, cognome, indirizzo, civico, cap, comune, telefono, pi, cf;
                        System.out.print("\n\nInserisci il nome cliente: ");
                        nome = input.nextLine();
                        System.out.print("Inserisci il cognome cliente: ");
                        cognome = input.nextLine();
                        System.out.print("Inserisci l'indirizzo del cliente: ");
                        indirizzo = input.nextLine();
                        System.out.print("Inserisci il numero civico: ");
                        civico = input.nextLine();
                        System.out.print("Inserisci il C.A.P.: ");
                        cap = input.nextLine();
                        System.out.print("Inserisci il comune di residenza: ");
                        comune = input.nextLine();
                        System.out.print("Inserisci il numero di telefono: ");
                        telefono = input.nextLine();
                        System.out.print("Inserisci la Partita Iva: ");
                        pi = input.nextLine();
                        System.out.print("Inserisci il Codice Fiscale: ");
                        cf = input.nextLine();
                        System.out.println("\n\n");
                        ac.aggiungiCliente(nome, cognome, indirizzo, civico, cap, comune, telefono, pi, cf);
                    }
                    case 2 -> { 
                        final double percIva = 0.22;
                        
                        System.out.print("Inserire una nota di emissione fattura tardiva\naltrimenti lasciare vuoto: ");
                        data = input.nextLine();
                        if(!data.trim().isEmpty()){
                            nota = "Emissione fattura tardiva. Prestazione eseguita in data " + data;
                        }
                        System.out.print("Inserisci il Codice Fiscale: ");
                        String codiceFiscale = input.nextLine();
                        
                        // 3. Inserisco la fattura
                        int numeroFattura;
                        try {
                            numeroFattura = af.inserimento(nota, codiceFiscale);
                            // 4. Recupero la fattura appena creata
                            Fattura fattura = af.getFattura(numeroFattura);
                            Cliente c = ac.cercaPerCF(codiceFiscale);
                            
                            double prezzoPrestazione = 0;
                            double totaleFattura = 0; 
                            String fine; 
                            do{
                                System.out.print("Inserisci la prestazione: ");
                                String descrizione = input.nextLine();
                                try{
                                    System.out.print("Inserisci il prezzo della prestazione: ");
                                    prezzoPrestazione = Double.parseDouble(input.nextLine());
                                    totaleFattura += prezzoPrestazione;
                                    prezzoPrestazione = Math.round(prezzoPrestazione / (1 + percIva) * 100) / 100.0;
                                }
                                catch(NumberFormatException e){ 
                                    System.out.println("Prezzo prestazione non valido " + e.getMessage());
                                }
                                fattura.aggiungiRiga(descrizione, prezzoPrestazione);
                                
                                System.out.print("Vuoi inserire un'altra voce?(s/n) ");
                                fine = input.nextLine();
                                //Inserisco le prestazioni dentro l'arraylist 
                            }
                            while(fine.equals("s"));
                            // setto il totale della fattura 
                            fattura.setTotaleFattura(totaleFattura);
                            // chiamo il calcolo della fattura 
                            fattura.calcoloArtigiano();
                            //Stampo a schermo - creo pdf - stampo su stampante
                            StampanteFattura.stampaASchermo(fattura, c);
                            
                            //Salvo il file fattura.csv
                            try{
                                archivioFatture.salvaFattureSuFile(fattura);
                            }catch(IOException e){
                                System.out.println("Salvataggio fatture.csv fallito");
                            }
                        } 
                        catch (DatiInvalidiException e) {
                            System.out.println("Dati della fattura non validi " + e.getMessage());
                        }
                        catch(NumeroFatturaInvalidoException e){
                            System.out.println("Numero di fattura invalido " + e.getMessage());
                        }
                    }
                    case 3 -> {
                        String nome, cognome;
                        List<Cliente> elenco;
                        System.out.print("Inserisci il nome cliente: ");
                        nome = input.nextLine();
                        System.out.print("Inserisci il cognome cliente: ");
                        cognome = input.nextLine();
                        try{
                            elenco = ac.cercaPerNomeCognome(nome, cognome);
                            ac.stampaElencoOrdinato(elenco);
                        }
                        catch(DatiInvalidiException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> {
                        List<Cliente> elenco;
                        elenco = ac.getClientiOrdinati();
                        ac.stampaElencoOrdinato(elenco);
                    }
                    case 5 -> {
                        List<Cliente> elenco;
                        elenco = ac.getClientiOrdinati(new OrdinamentoComune());
                        System.out.println("Clienti ordinati per comune:\n");
                        ac.stampaElencoOrdinato(elenco);
                    }
                    case 6 -> {
                        List<Fattura> elenco; 
                        elenco = af.OrdinamentoPerData(new OrdinamentoPerData());
                        System.out.println("Fatture ordinate per data:\n");
                        af.stampaFatture(elenco);
                    }
                    case 7 -> {
                        String cf; 
                        System.out.print("\nInserisci il Codice Fiscale per visualizzare tutte le fatture collegate ad un cliente: ");
                        cf = input.nextLine();

                        try {
                            af.stampaFattureCollegateAiClienti(cf);
                        }
                        catch (DatiInvalidiException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 8 -> {
                        String cf;
                        System.out.print("\nInserisci il Codice Fiscale del cliente da modificare: ");
                        cf = input.nextLine();
                        try {
                            ac.modificaCliente2(cf);
                        }
                        catch (DatiInvalidiException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 9 -> {
                        String cf; 
                        System.out.print("\nInserisci il Codice Fiscale del cliente da modificare: ");
                        cf = input.nextLine();

                        try{
                            ac.rimuoviCliente(cf);
                        }
                        catch(DatiInvalidiException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    case 10 -> {
                        ac.stampaClienti();
                    }
                    case 11 -> { 
                        System.out.println("\n\n");
                        List<Fattura> lista = new ArrayList();  
                        lista = af.fattureOrdinatePerNumero();
                        for(Fattura f : lista){
                            String codice = f.getCodiceFiscale();
                            Cliente c = ac.cercaPerCF(codice);
                            System.out.println(c.intestazioneFattura());
                            System.out.println(f);
                            System.out.println("=============================================================\n");
                        }
                    }
                    case 12 -> {
                        boolean flag = true; 
                        System.out.print("Inserisci l'anno delle fatture da stampare: ");
                        String anno = input.nextLine();
                        
                        List<Fattura> arFatture = new ArrayList(af.getArchivio().values());
                        
                        for(Fattura f : arFatture){
                            if(f.getData().startsWith(anno)){
                                flag = false;
                                System.out.println(f);
                                System.out.println("=============================================================\n");
                            }
                        }
                        
                        if(flag){
                            System.out.println("\nNessuna fattura trovata con data: " + anno + "\nInserire una data diversa");
                        }
                    }
                    case 13 -> {
                        boolean flag = false;
                        System.out.print("Numero fattura da modificare: ");
                        int num = Integer.parseInt(input.nextLine());

                        Fattura f = af.getFattura(num);

                        List<RigaPrestazione> righe = f.getRighe();
                        for (int i = 0; i < righe.size(); i++) {
                            System.out.println((i + 1) + ") " + righe.get(i).getDescrizione() + " - " + righe.get(i).getImporto());
                        }
                        String nuovaDesc = "";
                        int idx = 0;
                        double lordo = 0.0;
                        try{
                            System.out.print("Quale riga vuoi modificare? ");
                            idx = Integer.parseInt(input.nextLine());
                            System.out.print("Nuova descrizione: ");
                            nuovaDesc = input.nextLine();
                            try{
                                System.out.print("Nuovo prezzo lordo: ");
                                lordo = Double.parseDouble(input.nextLine());
                            }
                            catch(NumberFormatException e){
                                System.out.println("Importo lordo immesso non valido" + e.getMessage());
                            }
                            flag = true;
                        }
                        catch(NumberFormatException e){
                            System.out.println("Numero di riga scelto non valido" + e.getMessage());
                        }

                        if(flag){
                            f.modificaRiga(idx - 1, nuovaDesc, lordo);
                            f.calcoloArtigiano();
                        }
                    }
                    case 14 ->{
                        System.out.println("Arrivederci");
                    }
                }
            }
            catch(NumberFormatException e){
                System.out.println("Scelta non riconosciuta - " + e.getMessage());
            }
        }
        while(scelta != 14);
      
        //Salvataggio in Binario 
        SerializzazioneArchivioFatture serializzazioneFatture = new SerializzazioneArchivioFatture(af);
        SerializzazioneArchivioClienti serilizzazioneClienti = new SerializzazioneArchivioClienti(ac);
        
        try {
            serilizzazioneClienti.salvaArchivioClientiBinario();
        } catch (IOException e) {
            System.out.println("Salvataggio clienti.ser fallito");
        }
        
        try {
            archivioClienti.salvaClientiSuFile();
        } catch (IOException e) {
            System.out.println("Salvataggio clienti.csv Fallito");
        }
        
        try {
            serializzazioneFatture.salvaArchivioFattureBinario();
        } catch (IOException e) {
            System.out.println("Salvataggio fatture.ser fallito");
        }
    }
}
