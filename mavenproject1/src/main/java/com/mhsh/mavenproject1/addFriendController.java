package com.mhsh.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sockets.message.request.MsgRequestFriendship;

public class addFriendController extends  CustomInitializable{

    @FXML
    private Button sendRequestButton;

    @FXML
    private Label successfullySendLabel;

    @FXML
    private TextField usernameTextField;
    
    public String sendUsername;
    
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        
            getClient().setAddfriendController(this);
            usernameTextField.setText("adibiskiQks");
             sendRequestButton.setOnAction((ActionEvent event) -> {
                try {
                    getClient().output.writeUnshared(new MsgRequestFriendship(getClient().username ,usernameTextField.getText()));
                } catch (IOException ex) {
                    Logger.getLogger(addFriendController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }); 
        }
        
      
       
    
    
    public void send_RequestFriendShip_Success(String reciever){
        successfullySendLabel.setText("You successfully sent a RequestFriendShip to the user "  +reciever );
        
    }
      
        
       /*   try {
                ClientBuilder.client.output.writeUnshared(new MsgChangePassword (ClientBuilder.client.username, oldPassword,newPassword));
            } catch (IOException e) {
                e.printStackTrace();
            }
        */
        
        
        
        
        
    }


