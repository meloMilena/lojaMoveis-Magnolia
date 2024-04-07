/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.classe.Funcionario;
import javafx.dao.FuncionarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevChefMio
 */
public class FXMLCadastroFuncionarioController implements Initializable {

    private Stage dialogStage;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private boolean buttonConfirmarClicked = false;
    private Funcionario funcionario;
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldCep;
    @FXML
    private TextField textFieldComplemento;
    @FXML
    private TextField textFieldRua;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private Button buttonSalvar;
    @FXML
    private TextField textFieldSalario;

    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Funcionario getCliente() {
        return this.funcionario;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        funcionarioDAO.setConnection(connection);
    }    
    
     public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
//        this.textFieldNome.setText(funcionario.getNome());
//        this.textFieldCpf.setText(funcionario.getCpf());
//        this.textFieldTelefone.setText(funcionario.getTelefone());
//        this.textFieldEmail.setText(funcionario.getEmail());
//        this.textFieldCargo.setText(funcionario.getCargo());
//        this.textFieldSalario.setText(String.valueOf(funcionario.getSalario()));
//        this.textFieldSenha.setText(funcionario.getSenha());
    }
     
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
    
}
