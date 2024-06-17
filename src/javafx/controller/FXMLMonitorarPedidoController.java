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
public class FXMLMonitorarPedidoController implements Initializable {

    @FXML
    private AnchorPane anchorPaneCarregar;
    @FXML
    private TableView<?> tableViewFuncionario;
    @FXML
    private TableColumn<?, ?> tableColumnId;
    @FXML
    private TableColumn<?, ?> tableColumnNome;
    @FXML
    private TableColumn<?, ?> tableColumnCpf;
    @FXML
    private TableColumn<?, ?> tableColumnTelefone;
    @FXML
    private Button buttonAlterar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAlterar(ActionEvent event) {
    }
    
}
