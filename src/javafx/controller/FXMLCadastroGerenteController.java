/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.dao.EnderecoDAO;
import javafx.dao.FuncionarioDAO;
import javafx.dao.GerenteDAO;
import javafx.dao.PessoaDAO;
import javafx.domain.Endereco;
import javafx.domain.Funcionario;
import javafx.domain.Gerente;
import javafx.domain.Pessoa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevChefMio
 */
public class FXMLCadastroGerenteController implements Initializable {
    
    @FXML
    private Button buttonSalvar;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldSalario;
    @FXML
    private TextField textFieldCep;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private TextField textFieldRua;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldComplemento;
    
    private Stage dialogStage;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private boolean buttonConfirmarClicked = false;
    
    private final GerenteDAO gerenteDAO = new GerenteDAO();
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();
    
    private Gerente gerente;
    private Pessoa pessoa;
    private Endereco endereco;
    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Gerente getCliente() {
        return this.gerente;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gerenteDAO.setConnection(connection);
        pessoaDAO.setConnection(connection);
        enderecoDAO.setConnection(connection);
    }  
    
    public void setGerente(Gerente gerente, Pessoa pessoa, Endereco endereco) {
        this.gerente = gerente;
        this.pessoa = pessoa;
        this.endereco = endereco;
        
        this.textFieldNome.setText(pessoa.getNome());
        this.textFieldCpf.setText(pessoa.getCpf());
        this.textFieldTelefone.setText(pessoa.getTelefone());
        this.textFieldEmail.setText(pessoa.getEmail());
        this.textFieldSalario.setText(String.valueOf(gerente.getSalario()));
        this.textFieldCep.setText(endereco.getCep());
        this.textFieldComplemento.setText(endereco.getComplemento());
        this.textFieldRua.setText(endereco.getRua());
        this.textFieldNumero.setText(String.valueOf(endereco.getNumero()));
        this.textFieldBairro.setText(endereco.getBairro());
    }
    
    @FXML
    public void handleButtonConfirmar() {
        
        System.out.println("Entrou");
        if (validarEntradaDeDados()) {
            endereco.setCep(textFieldCep.getText());
            endereco.setBairro(textFieldBairro.getText());
            endereco.setRua(textFieldRua.getText());
            endereco.setNumero(Integer.parseInt(textFieldNumero.getText()));
            endereco.setComplemento(textFieldComplemento.getText());

            if (enderecoDAO.inserir(endereco)) {
                int idEndereco = enderecoDAO.obterUltimoIdInserido();

                pessoa.setNome(textFieldNome.getText());
                pessoa.setCpf(textFieldCpf.getText());
                pessoa.setTelefone(textFieldTelefone.getText());
                pessoa.setEmail(textFieldEmail.getText());
                pessoa.setIdEndereco(idEndereco);

                if (pessoaDAO.inserir(pessoa)) {
                    int idPessoa = pessoaDAO.obterUltimoIdInserido();

                    gerente.setSalario(Double.parseDouble(textFieldSalario.getText()));
                    gerente.setStatus("Ativo");
                    gerente.setIdPessoa(idPessoa);
                    buttonConfirmarClicked = true;
                    dialogStage.close();

                } else {
                    // Exibir mensagem de erro se a inserção da pessoa falhar
                    exibirErro("Falha ao inserir pessoa", "Ocorreu um erro ao inserir a pessoa. Por favor, tente novamente.");
                }
            } else {
                // Exibir mensagem de erro se a inserção do endereço falhar
                exibirErro("Falha ao inserir endereço", "Ocorreu um erro ao inserir o endereço. Por favor, tente novamente.");
            }
        }
    }

    @FXML
    public void handleButtonCancelar() {
        getDialogStage().close();
    }
    
    
    private void exibirErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no cadastro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.show();
    }

     
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

   
    private boolean validarEntradaDeDados() {
    String errorMessage = "";

    if (textFieldNome.getText() == null || textFieldNome.getText().isEmpty()) {
        errorMessage += "Nome inválido!\n";
    }
    if (textFieldCpf.getText() == null || textFieldCpf.getText().isEmpty()) {
        errorMessage += "CPF inválido!\n";
    }
    if (textFieldTelefone.getText() == null || textFieldTelefone.getText().isEmpty()) {
        errorMessage += "Telefone inválido!\n";
    }
    if (textFieldEmail.getText() == null || textFieldEmail.getText().isEmpty()) {
        errorMessage += "Email inválido!\n";
    }
    if (textFieldSalario.getText() == null || textFieldSalario.getText().isEmpty()) {
        errorMessage += "Salário inválido!\n";
    }

    if (errorMessage.isEmpty()) {
        return true;
    } else {
        // Mostrar a mensagem de erro
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no cadastro");
        alert.setHeaderText("Campos inválidos, por favor, corrija...");
        alert.setContentText(errorMessage);
        alert.show();
        return false;
    }
}
    
}
