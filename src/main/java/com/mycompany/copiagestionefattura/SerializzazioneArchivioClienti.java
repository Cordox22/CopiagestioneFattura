/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author User
 */
public class SerializzazioneArchivioClienti implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArchivioClienti archivioClienti;
    
    public SerializzazioneArchivioClienti(ArchivioClienti archivio){
        this.archivioClienti = archivio;
    }
    
    //Salvataggio in Binario Archivio Clienti
    public void salvaArchivioClientiBinario() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("clienti.ser"))) {
            out.writeObject(this.archivioClienti);
            System.out.println("\n\nFile clienti.ser serializzato con successo");
        }
    }
    //Caricamento in Binario Archivio Clienti
    public void caricaArchivioClientiBinario() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("clienti.ser"))) {
            this.archivioClienti = (ArchivioClienti) in.readObject();
            System.out.println("File clienti.ser caricato con successo");
        }
    }
}
