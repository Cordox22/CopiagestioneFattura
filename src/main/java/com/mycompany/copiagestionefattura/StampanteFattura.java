/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

/**
 *
 * @author User
 */
public class StampanteFattura {
    private static final String EMITTENTE = """
    \t\t\t\t\tAgrigarden 
    \t\t\t\t\tVia Roma, 13
    \t\t\t\t\t03039 Roma (RM)
    \t\t\t\t\tP.IVA 03796654603
    """;

    public static void stampaASchermo(Fattura f, Cliente c) {

        // 1. Intestazione cliente (a sinistra)
        System.out.println(c.intestazioneFattura());

        // 2. Intestazione emittente (a destra)
        System.out.println(EMITTENTE);

        /*for(RigaPrestazione r : f.getRighe()){
            System.out.printf("%-40s %10.2f%n", r.getDescrizione(), r.getImporto());
        }*/
        
        // 3. Corpo della fattura
        System.out.println(f.toString());
    }
}
