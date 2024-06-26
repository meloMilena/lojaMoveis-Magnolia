/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLMenuPrincipal.fxml"));
        
        Scene scene = new Scene(root);
      
        stage.setScene(scene);
        stage.setTitle("Magnolia");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED); //Deixa a barra superior da tela invisivel
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
