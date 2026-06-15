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
public class IteratoreFatture implements Iterator<Map.Entry<Integer, Fattura>>{
    private final Iterator<Map.Entry<Integer, Fattura>> iteratore; 
    
    public IteratoreFatture(Map<Integer, Fattura> fatture){
        this.iteratore = fatture.entrySet().iterator();
    }
    
    @Override
    public boolean hasNext(){
        return iteratore.hasNext();
    }
    
    @Override 
    public Map.Entry<Integer, Fattura> next(){
        return this.iteratore.next();
    }
}
