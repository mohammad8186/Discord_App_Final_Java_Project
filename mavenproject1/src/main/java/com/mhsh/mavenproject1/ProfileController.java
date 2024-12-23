package com.mhsh.mavenproject1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import sockets.message.request.MsgSetAvatar;

public class ProfileController extends  CustomInitializable {
    
    @FXML
    private Pane subPane;
    @FXML
    private Button changeImage;

    @FXML
    private Button changePassword;

    @FXML
    private Circle circleImage;

    @FXML
    private Button editEmail;

    @FXML
    private Button editMobile;

    @FXML
    private Button editUsername;

    @FXML
    private  Label  email;

    @FXML
    private Button logOut;

    @FXML
    private  Label mobile;

    @FXML
    private Button myAccount;
    
    @FXML
    private Label username;

    @FXML
    private  Label usernameNexttoImage;
    
    private static ProfileController instance;

    public ProfileController() {
        instance = this;
    }

    public static ProfileController getInstance() {
        return instance;
    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
         getClient().setProfileController(this);
        
        username.setText(getClient().username);
        usernameNexttoImage.setText(getClient().username);
        email.setText(getClient().email);
        mobile.setText(getClient().mobile);
        
         
     
    }


        
  
      
      public void changeUsernameSuccess(String username_Edit){
        usernameNexttoImage.setText(username_Edit);
        username.setText(username_Edit);
          
      }

 

   

 

    @FXML
    void changeImageClicked(ActionEvent event) {
         
        
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Choose Image for Account");
            File file = fc.showOpenDialog(null);
            Image image = new Image(file.toURI().toString());
            ImagePattern imagePattern = new ImagePattern(image);
            circleImage.setFill(imagePattern);
            MsgSetAvatar msgSetAvatar = new MsgSetAvatar(ClientBuilder.client.username ,  ImageIO.read(new File("C:\\Users\\Shahin\\Documents\\NetBeansProjects\\mavenproject1\\src\\main\\resources\\com\\mhsh\\mavenproject1\\imageprofileclient\\download.jpg")));
          
            ClientBuilder.client.output.writeUnshared(msgSetAvatar);
            
            
        } catch (IOException e) {
           e.printStackTrace();
        }
        

        
    }

    @FXML
    void changePasswordClicked(ActionEvent event) {
           
           try {
        
         
            
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditPasswordFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.setResizable(false);
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }

    }

    @FXML
    void editMobileClicked(ActionEvent event) {
        try{
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditMobileFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.setResizable(false);
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }

    
    }

   
    @FXML
    void editEmailClicked(ActionEvent event) {
              try {
        
         
            
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditEmailFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.setResizable(false);
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }

    }

    

    @FXML
    void editUsernameClicked(ActionEvent event) {
        
         try {
        
         
            
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditUsernameFXML.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.setResizable(false);
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }

    }

    @FXML
    void logOutClicked(ActionEvent event) {
        
              try {
        
         
            
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginFXML.fxml"));
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


