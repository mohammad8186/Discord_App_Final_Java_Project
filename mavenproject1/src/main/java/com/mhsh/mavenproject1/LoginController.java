package com.mhsh.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static sockets.message.MessageType.RES_LOGIN_FAIL;
import static sockets.message.MessageType.RES_LOGIN_SUCCESS;
import sockets.message.request.MsgGetUserInformation;
import sockets.message.request.MsgLogin;


public class LoginController extends  CustomInitializable {
    @FXML
    Pane mainPane;
    @FXML
    private TextField emaiPhonTextField = new TextField() ;
    

    @FXML
    private Button loginButton = new Button() ;

    @FXML
    private PasswordField passwordTextField = new PasswordField();
    
    @FXML
    Pane pane = new Pane();

    @FXML
    private Hyperlink toRegisterWindo = new Hyperlink();
    
    public String emaiPhon;
    public String password;
  
    
    
   
  
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            getClient().setLoginController(this);
            
            emaiPhonTextField.setText("gdfh@gmail.com");
            passwordTextField.setText("091841747Msh");
            
        
        
        
    
      
        
    }
    
   

    @FXML
    void loginEntered(ActionEvent event) {
        
             
            
        try {
           
            
            
            getClient().output.writeUnshared(new MsgLogin(passwordTextField.getText()  , emaiPhonTextField.getText()));
            
            
            
            
                
            
        } catch (IOException e) {
           e.printStackTrace();
        }
                    }
       public void open_profile_wondow(){
           
        try {
             Stage stage_close = (Stage) mainPane.getScene().getWindow();
             stage_close.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChatController.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
      e.printStackTrace();
        }
    } 
            

        
                    
            
     public void loginSuccess(){
         
         emaiPhonTextField.setText("");
         passwordTextField.setText("");
         open_profile_wondow();
     }       
            
         
     

    @FXML
    void toRegisterWindowEntered(ActionEvent event) {
     try {
         
          Stage stage_close = (Stage) mainPane.getScene().getWindow();
          stage_close.close();
           emaiPhonTextField.setText("");
           passwordTextField.setText("");
     
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.setResizable(false);
        stage.show();
       
    } catch(Exception e) {
        e.printStackTrace();
    }
}


}
