/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.util.Comparator;

/**
 *
 * @author User
 */
public class OrdinamentoComune implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        int comp = c1.getComune().compareTo(c2.getComune());
        if(comp == 0){
            comp = c1.getCognome().compareTo(c2.getCognome());
            if(comp == 0){
                comp = c1.getNome().compareTo(c2.getNome());
            }
        }
        return comp;
    }
}
