package com.mhsh.mavenproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PendingContoller extends CustomInitializable {

    @FXML
    private Label countPendingNumberLabel;

    @FXML
    private TextField searchTextField;
    
    @FXML
    private VBox elementsVbox;
    
    private ListView<HBox> HboxesListView;
    
    private ObservableList<HBox> HboxesObservableList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getClient().setPendingContoller(this);
        
        
        
        
        
    }

}
