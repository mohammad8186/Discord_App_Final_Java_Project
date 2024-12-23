
package com.mhsh.mavenproject1;

import static com.mhsh.mavenproject1.App.getClient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static sockets.message.MessageType.RES_CHANGE_USERNAME_FAIL;
import static sockets.message.MessageType.RES_CHANGE_USERNAME_SUCCESS;
import sockets.message.request.MsgChangeUsername;
import sockets.message.response.MsgChangeUsernameSuccess;


public class EditUsernameController  extends  CustomInitializable {
    
   
    
    @FXML
    private Pane manePane ;
    
    @FXML
    private Button editButton ;
    @FXML
    private TextField editUsername;
    
    public static String newUsername;
    
    public  ProfileController  profileController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       getClient().setEditUsernameController(this);
           
        editButton.setOnAction((ActionEvent event) -> {
           
             profileController  = getClient().getProfileController();
            
             newUsername = editUsername.getText();
           try {
                     getClient().output.writeUnshared(new MsgChangeUsername ( getClient().username,newUsername,getClient().password));
            } catch (IOException e) {
                e.printStackTrace();
            }
          
        //    profileController.setUsernameText(newUsername);
            
            
           Stage stage = (Stage)  manePane.getScene().getWindow();
           stage.close();
        });
         
               
            
            
        
          
            
   
        }
      
       
       
        
    }
    
    
    

    

/*




 try {

      editButton.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
            newUsername = editUsername.getText();
            ClientBuilder.client.output.writeUnshared(new MsgChangeUsername(newUsername , ClientBuilder.client.password));
            
            if(ReceivedMessagesHandler.msg.msgType == RES_CHANGE_USERNAME_SUCCESS){
                ClientBuilder.client.username = newUsername;
                Text text_username = new Text();
                text_username.setFont(Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 12));
                text_username.setFill(Color.WHITE);
                ProfileController.setUsernameText(text_username.toString());
                Stage stage = (Stage) editUsername.getScene().getWindow();
                stage.close();
                
                
                
            }
            
            else if(ReceivedMessagesHandler.msg.msgType == RES_CHANGE_USERNAME_FAIL){
                Text text = new Text("Username is Incorrect");
                text.setStyle("-fx-text-inner-color: red;");
                editUsername.setText(text.toString());
                
                
          
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
}
      
*/