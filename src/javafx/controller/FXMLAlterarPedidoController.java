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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Usuário
 */
public class FXMLAlterarPedidoController implements Initializable {

    @FXML
    private Button buttonSalvar1;
    @FXML
    private Button buttonSalvar;
    @FXML
    private TextField textFieldPedido;
    @FXML
    private TextField textFieldIDcliente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
    }
    
}
