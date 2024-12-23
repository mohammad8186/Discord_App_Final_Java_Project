package com.mhsh.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sockets.message.request.MsgChangePassword;

public class EditPasswordController implements Initializable {
    

  
    @FXML
    private Button editButton;

    @FXML
    private Pane manePane;

    @FXML
    private TextField newPasswordTextfield;

    @FXML
    private TextField oldPasswordTextfield;
    
    public  String oldPassword;
    public   String newPassword;;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
   

        editButton.setOnAction((ActionEvent event) -> {
           
             
               oldPassword = oldPasswordTextfield.getText();
           newPassword = newPasswordTextfield.getText();
             
        /*    try {
                ClientBuilder.client.output.writeUnshared(new MsgChangePassword (ClientBuilder.client.username, oldPassword,newPassword));
            } catch (IOException e) {
                e.printStackTrace();
            }
          */
           
            
            
           Stage stage = (Stage)  manePane.getScene().getWindow();
           stage.close();
        });

}
}
