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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Usuário
 */
public class FXMLTelaClienteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<?> tableViewCliente;
    @FXML
    private TableColumn<?, ?> tableColumnId;
    @FXML
    private TableColumn<?, ?> tableColumnNome;
    @FXML
    private TableColumn<?, ?> tableColumnCpf;
    @FXML
    private TableColumn<?, ?> tableColumnTelefone;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRegistrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonRemover(ActionEvent event) {
    }

    @FXML
    private void handleButtonAlterar(ActionEvent event) {
    }

    @FXML
    private void handleButtonInserir(ActionEvent event) {
    }
    
}
