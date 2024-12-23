package com.mhsh.mavenproject1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    private static Scene scene;
    private static ClientFx client;

    public static ClientFx getClient(){
        return client;
    }
    
    @Override
    public void init(){
        client = new ClientFx();
        client.connect();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginFXML"));
        stage.setScene(scene);
        stage.show();
    }

   
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}