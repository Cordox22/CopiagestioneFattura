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
public class OrdinamentoPerData implements Comparator<Fattura> {

    @Override
    public int compare(Fattura f1, Fattura f2) {
        return f1.getData().compareTo(f2.getData());
    }
}
