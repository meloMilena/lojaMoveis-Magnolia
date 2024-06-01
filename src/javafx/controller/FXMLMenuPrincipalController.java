/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLMenuPrincipalController implements Initializable {
    @FXML
    private Menu menuRegistrarPedido;
    @FXML
    private Menu menuCadastro;
    @FXML
    private MenuItem menuItemFuncionario;
    @FXML
    private AnchorPane anchorPaneCarregar;
    @FXML
    private MenuItem menuItemGerente;
    @FXML
    private Button buttonCatalogo;
    @FXML
    private Button buttonMonitor;
    @FXML
    private Button buttonRegistrarPedido;
    @FXML
    private MenuItem menuItemProduto;
    @FXML
    private MenuItem menuItemCliente;
    @FXML
    private AnchorPane anchorPane;

    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            makeStageDraggable();
            handleMenuItemCatalogo();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private void handleMenuItemCadastroFuncionario() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/javafx/view/FXMLTelaFuncionario.fxml"));
        anchorPaneCarregar.getChildren().setAll(a);
    }
    
    @FXML
    private void handleMenuItemCadastroGerente() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/javafx/view/FXMLTelaGerente.fxml"));
        anchorPaneCarregar.getChildren().setAll(a);
    }
    
    @FXML
    private void handleMenuItemCatalogo() {
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/view/FXMLCatalogo.fxml"));
           AnchorPane a = loader.load();
           FXMLCatalogoController catalogoController = loader.getController();
           catalogoController.initialize(null, null); // Manually initialize the controller
           anchorPaneCarregar.getChildren().setAll(a);
       } catch (IOException ex) {
           ex.printStackTrace();
           // Handle the exception accordingly, e.g., show an error message to the user
       } catch (Exception e) {
           e.printStackTrace();
           // Handle any other unexpected exceptions
       }
    }
    
    @FXML
    private void handleRegistraPedido() throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/javafx/view/FXMLRegistrarPedido.fxml"));
        AnchorPane root = fxmlLoader.load();

        // Create a new stage
        Stage stage = new Stage();
        stage.setTitle("Registrar Pedido");

        // Create a new scene with the loaded FXML
        Scene scene = new Scene(root);
        stage.setScene(scene);

        // Set the new stage modality to block input events from being delivered to its owner
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(anchorPane.getScene().getWindow());

        // Show the new stage and wait until it is closed
        stage.showAndWait();
    }

    @FXML
    private void handleMenuItemProduto() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/javafx/view/FXMLTelaProduto.fxml"));
        anchorPaneCarregar.getChildren().setAll(a);
    }

    @FXML
    private void handleMenuItemCadastroCliente(ActionEvent event) throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/javafx/view/FXMLTelaCliente.fxml"));
        anchorPaneCarregar.getChildren().setAll(a);
    }
}
