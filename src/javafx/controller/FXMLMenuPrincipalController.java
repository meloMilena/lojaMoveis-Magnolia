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
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

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
    private MenuItem menuItemCliente;

   @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            handleMenuItemCatalogo();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
