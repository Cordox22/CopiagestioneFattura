/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copiagestionefattura;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author User
 */
public class IteratoreCliente implements Iterator<Map.Entry<String, Cliente>>{
    private final Iterator<Map.Entry<String, Cliente>> iteratore;
    
    public IteratoreCliente(Map<String, Cliente> clienti){
        this.iteratore = clienti.entrySet().iterator();
    }
    
    @Override
    public boolean hasNext(){
        return this.iteratore.hasNext();
    }
    
    @Override
    public Map.Entry<String, Cliente> next(){
        return this.iteratore.next();
    }
}
