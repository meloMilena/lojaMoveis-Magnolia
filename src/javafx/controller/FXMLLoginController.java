/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLLoginController implements Initializable {
    @FXML
    private PasswordField passwordSenha;
    @FXML
    private TextField textFiedlEmail;
    @FXML
    private AnchorPane anchorPane;
    
    private double xOffset = 0;
    private double yOffset = 0;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDraggable();
    }   
    
    private void makeStageDraggable() {
        anchorPane.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        anchorPane.setOnMouseDragged((MouseEvent event) -> {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }
    
    @FXML
    private void clicarBotaoLogin(ActionEvent event) throws IOException {
        String usuario = textFiedlEmail.getText();
        String senha = passwordSenha.getText();


        if (usuario.equals("admin@gmail.com") && senha.equals("1234")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/view/FXMLMenuPrincipal.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) anchorPane.getScene().getWindow();
            currentStage.close();

            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.setResizable(false);;
            newStage.initStyle(StageStyle.UNDECORATED);
            newStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de autenticação");
            alert.setHeaderText(null);
            alert.setContentText("Credenciais inválidas. Por favor, tente novamente.");
            alert.showAndWait();
        }
    }
}
