/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhsh.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author Shahin
 */
public abstract  class CustomInitializable implements Initializable{
    protected ClientFx getClient(){
        return App.getClient();
    }

   

  
    
}
