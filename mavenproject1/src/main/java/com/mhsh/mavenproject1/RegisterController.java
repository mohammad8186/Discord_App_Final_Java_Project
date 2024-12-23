package com.mhsh.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import static sockets.message.MessageType.RES_REGISTER_FAIL;
import static sockets.message.MessageType.RES_REGISTER_SUCCESS;
import sockets.message.request.MsgRegister;

public class RegisterController extends  CustomInitializable {

    @FXML
    private TextField emailTextField  =new TextField();
    
    @FXML
    private Pane pane  = new Pane();
    
    
    @FXML
    private Pane mainPane;

    @FXML
    private Button loginButton = new Button();

    @FXML
    private TextField mobileTextField = new TextField();

    @FXML
    private PasswordField passwordTextField = new PasswordField();

    @FXML
    private CheckBox privacyCheckBox =new CheckBox();

    @FXML
    private Hyperlink toLoginWindow = new Hyperlink();

    @FXML
    private TextField usernameTextField = new TextField();
    
   
    
    public String username;
    public String password;
    public String mobile;
    public String email;
    public String status;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getClient().setRegisterController(this);
       usernameTextField.setText("Hartage8186");
        passwordTextField.setText("09902048023");
       emailTextField.setText("gdfh@gmail.com");
        mobileTextField.setText("09902048023");
        
    }
    
   
    @FXML
    void RegisterEntered(ActionEvent event) {
   
           
        
        try {
            
            
            getClient().output.writeUnshared(new MsgRegister( usernameTextField.getText() , passwordTextField.getText()  , emailTextField.getText() , mobileTextField.getText()));
            } catch (IOException e) {
              e.printStackTrace();
            }
            
           
      
    }
    
         public void open_login_window(){
        try {
             
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();Stage stage_close = (Stage) mainPane.getScene().getWindow();
             stage_close.close();
        } 
        
    catch(Exception e) {
        e.printStackTrace();
    }
         }
         
          public void registerSuccess(){
       /*      getClient().username = usernameTextField.getText();
            getClient().password =  passwordTextField.getText();
             getClient().email =   emailTextField.getText();              
              getClient().mobile =    mobileTextField.getText();
*/
       usernameTextField.setText("");
       passwordTextField.setText("");
       emailTextField.setText("");
         mobileTextField.setText("");
        open_login_window();
     }      

    

    @FXML
    void toLoginWindowEntered(ActionEvent event) {
        
            
    try {
        
          Stage stage_close = (Stage) mainPane.getScene().getWindow();
        stage_close.close();
            usernameTextField.setText("");
            passwordTextField.setText("");
            emailTextField.setText("");
            mobileTextField.setText("");

        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
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

    
 

