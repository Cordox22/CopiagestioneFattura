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
public class SerializzazioneArchivioFatture implements Serializable {
    private static final long serialVersionUID = 1L;    
    private ArchivioFatture archivioFatture;
    
    public SerializzazioneArchivioFatture(ArchivioFatture archFatture){
        this.archivioFatture = archFatture;
    }
    
    //Salvataggio in Binario Archivio Fatture
    public void salvaArchivioFattureBinario() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("fatture.ser"))) {
            out.writeObject(this.archivioFatture);
            System.out.println("File fatture.ser serializzato con successo");
        }
    }
    
    //Caricamento in Binario Archivio Fatture
    public void caricaArchivioFattureBinario() throws IOException, ClassNotFoundException {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("fatture.ser"))) {
            this.archivioFatture =  (ArchivioFatture)in.readObject();
            System.out.println("File fatture.ser caricato con successo");
        }
    }
}
