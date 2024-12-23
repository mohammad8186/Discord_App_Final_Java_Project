package com.mhsh.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditEmailController implements Initializable{

    @FXML
    private Button editButton;

    @FXML
    private TextField editEmail;

    @FXML
    private Pane mainPane;   
    
    public String newEmail;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
     
   
        editButton.setOnAction((ActionEvent event) -> {
           
             
             newEmail = editEmail.getText();
           
         /*   try {
                ClientBuilder.client.output.writeUnshared(new MsgChangeEmail ( newEmail,ClientBuilder.client.username));
            } catch (IOException e) {
                e.printStackTrace();
            }
          */
         //   ProfileController.getInstance().setEmailText(newEmail);
            
            
           Stage stage = (Stage)  mainPane.getScene().getWindow();
           stage.close();
        });
         
    }
}
               