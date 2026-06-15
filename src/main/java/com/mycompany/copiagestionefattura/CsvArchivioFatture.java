/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author User
 */
public class CsvArchivioFatture {
    private final ArchivioFatture fatture;
    
    public CsvArchivioFatture(ArchivioFatture archFatture){
        this.fatture = archFatture;
    }
    
    public void salvaFattureSuFile(Fattura fattura) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("fatture.csv", true))) {
            String riga = fattura.getNumero() + ";" +
                          fattura.getData() + ";" +
                          fattura.getCodiceFiscale() + ";" +
                          fattura.getImponibile() + ";" +
                          fattura.getIva() + ";" +
                          fattura.getTotaleIvato();
            bw.write(riga);
            bw.newLine();
        }
    }
    
    public void caricaFattureDaFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("fatture.csv"))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                // Implementazione base - può essere espansa
            }
        } catch (IOException e) {
            System.out.println("File fatture.csv non trovato o non leggibile");
        }
    }
}
