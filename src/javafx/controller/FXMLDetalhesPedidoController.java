/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Usuário
 */
public class FXMLDetalhesPedidoController implements Initializable {

    @FXML
    private TableView<?> tableViewCliente;
    @FXML
    private TableColumn<?, ?> tableColumnId;
    @FXML
    private TableColumn<?, ?> tableColumnQuantidade;
    @FXML
    private TableColumn<?, ?> tableColumnProduto;
    @FXML
    private TableColumn<?, ?> tableColumnPreco;
    @FXML
    private Button buttonAlterar;
    
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;

    /**
     * Initializes the controller class.
     */
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAlterar(ActionEvent event) {
    }
    
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
}
