package com.mhsh.mavenproject1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class FriendShipController extends  CustomInitializable {

    @FXML
    private Label addFriendsButton;

    @FXML
    private Button friendsButton;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label pendingButton;
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
          getClient().setFriendShipController(this);
    }

        @FXML
    void addFriendsButtonClicked(MouseEvent event) {
         try {
            //TODO
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FriendShipController.class.getResource("addFriendFXML.fxml"));
            Pane pane = loader.load();
            mainPane.setCenter(pane);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

    @FXML
    void friendsButtonCilciked(ActionEvent event) {

    }

    @FXML
    void pendingButtonClicked(MouseEvent event) {

    }

  

    

}