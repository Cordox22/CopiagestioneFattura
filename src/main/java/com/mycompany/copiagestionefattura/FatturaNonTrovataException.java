/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.mycompany.copiagestionefattura;

/**
 *
 * @author User
 */
public class FatturaNonTrovataException extends Exception {

    public FatturaNonTrovataException() {
    }
    
    public FatturaNonTrovataException(String msg) {
        super(msg);
    }
}
