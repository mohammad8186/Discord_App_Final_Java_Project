package com.mhsh.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import sockets.message.request.MsgChatUser;

public class ChatController extends  CustomInitializable {

    @FXML
    private VBox VboxMessages;
    
    @FXML
    private TextField friendnameTextfield;


    @FXML
    private AnchorPane mainPane;
    
    
    @FXML
    private Label chatOwner;

    @FXML
    private TextField ricieveMessageButton;

    @FXML
    private Button sendButton;
    ////    @FXML
   // private ScrollPane scrollBarMessages;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getClient().setChatController(this);
      
        chatOwner.setText(getClient().username);
       
        
        
     /*   VboxMessages.heightProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                
              
            
            
            scrollBarMessages.setVvalue((Double) newValue );
                    }
        
        
        });
*/
        sendButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                
                try {
                   
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5 ,5 ,5,10));
                    Text text = new Text(ricieveMessageButton.getText());
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle("-fx_color: rgb(239 , 242 , 255 "+
                            "-fx-background-color: rgb(15,125,242"+
                            "-fx-background_radius: 20px");
                    
                    textFlow.setPadding(new Insets(5,10,5,10));
                    
                     text.setFill(Color.color(0.934 , 0.945 , 0.996));
                    
                     hBox.getChildren().add(textFlow);
                     
                     VboxMessages.getChildren().add(hBox);
                     
                      getClient().output.writeUnshared(new MsgChatUser(getClient().username , friendnameTextfield.getText() , ricieveMessageButton.getText()));
                     
                     ricieveMessageButton.clear();
                     
                     
                     
                     
                     
                     
                     
                    
                } catch (IOException e) {
                    e.printStackTrace();
                   
                }
             
            }
        
            
            
        });
    }
        public void receiveChat(String message){
               
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.setPadding(new Insets(5 ,5 ,5,10));
                    Text text = new Text(message);
                    TextFlow textFlow = new TextFlow(text);
                    textFlow.setStyle("-fx_color: rgb(200 , 242 , 255 "+
                            "-fx-background-color: rgb(8,125,242"+
                            "-fx-background_radius: 20px");
                    
                    textFlow.setPadding(new Insets(5,10,5,10));
                    
                     text.setFill(Color.color(0.934 , 0.945 , 0.996));
                    
                     hBox.getChildren().add(textFlow);
                     
                     VboxMessages.getChildren().add(hBox);
            
            
            
            
            
        }
        
    }


