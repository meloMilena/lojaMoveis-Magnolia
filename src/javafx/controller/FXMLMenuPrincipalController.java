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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLMenuPrincipalController implements Initializable {
    @FXML
    private Menu menuCatalogo;
    @FXML
    private Menu menuRegistrarPedido;
    @FXML
    private Menu menuMonitor;
    @FXML
    private Menu menuCadastro;
    @FXML
    private MenuItem menuItemFuncionario;
    @FXML
    private AnchorPane anchorPaneCarregar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            handleMenuItemCatalogo();
        } catch (IOException ex) {
            Logger.getLogger(FXMLCatalogoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
            
    @FXML
    private void handleMenuItemCadastroFuncionario() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/javafx/view/FXMLTelaFuncionario.fxml"));
        anchorPaneCarregar.getChildren().setAll(a);
    }
    
    @FXML
    private void handleMenuItemCatalogo() throws IOException {
        AnchorPane a = FXMLLoader.load(getClass().getResource("/javafx/view/FXMLCatalogo.fxml"));
        anchorPaneCarregar.getChildren().setAll(a);
    }
            
}
