/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.domain.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.EnderecoDAO;
import javafx.dao.FuncionarioDAO;
import javafx.dao.PessoaDAO;
import javafx.domain.Endereco;
import javafx.domain.Pessoa;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevChefMio
 */
public class FXMLTelaFuncionarioController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Funcionario> tableViewFuncionario;
    @FXML
    private TableColumn<Funcionario, Integer> tableColumnId;
    @FXML
    private TableColumn<Pessoa, String> tableColumnNome;
    @FXML
    private TableColumn<Pessoa, String> tableColumnCpf;
    @FXML
    private TableColumn<Pessoa, String> tableColumnTelefone;
    @FXML
    private Button buttonRegistrar;

    private List<Funcionario> listFuncionario;
    private ObservableList<Funcionario> observableListFuncionario;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonAlterar;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        funcionarioDAO.setConnection(connection);
        pessoaDAO.setConnection(connection);
        enderecoDAO.setConnection(connection);
        carregarTableViewFuncionarios();
        
//        selecionarItemTableViewFuncionarios(null);

        // Listen acionado diante de quaisquer alterações na seleção de itens do TableView
//        tableViewFuncionario.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue);
    }    
    
//     public void selecionarItemTableViewFuncionarios(Funcionario funcionario) {
//        if (funcionario != null) {
//            labelCodigo.setText(String.valueOf(funcionario.getIdFunc()));
//            labelNome.setText(funcionario.getNome());
//            labelCPF.setText(funcionario.getCpf());
//            labelTelefone.setText(funcionario.getTelefone());
//            labelEmail.setText(funcionario.getEmail());
//            labelCargo.setText(funcionario.getCargo());
//            labelSalario.setText(String.valueOf(funcionario.getSalario()));
//        } else {
//            labelCodigo.setText("");
//            labelNome.setText("");
//            labelCPF.setText("");
//            labelTelefone.setText("");
//            labelEmail.setText("");
//            labelCargo.setText("");
//            labelSalario.setText("");
//        }
//    }
//    
//    
    
     public void carregarTableViewFuncionarios() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        listFuncionario = funcionarioDAO.listar();

        observableListFuncionario = FXCollections.observableArrayList(listFuncionario);
        tableViewFuncionario.setItems(observableListFuncionario);
    }
     
    @FXML
    public void handleButtonInserir() throws IOException {
        Funcionario funcionario = new Funcionario();
        Pessoa pessoa = new Pessoa();
        Endereco endereco = new Endereco();
        boolean buttonConfirmarClicked = FXMLCadastroFuncionarioController(funcionario, pessoa, endereco);
        if (buttonConfirmarClicked) {
                funcionarioDAO.inserir(funcionario);
                carregarTableViewFuncionarios();
            }
    }
    
    @FXML
    public void handleButtonRemover() throws IOException {
        Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
            funcionarioDAO.remover(funcionario);
            carregarTableViewFuncionarios();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        }
    }
    
    @FXML
     public void handleButtonAlterar() throws IOException {
        Funcionario funcionario = tableViewFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
            boolean buttonConfirmarClicked = FXMLCadastroFuncionarioController(funcionario, funcionario.getPessoa(), funcionario.getPessoa().getEndereco());
            if (buttonConfirmarClicked) {
                pessoaDAO.alterar(funcionario.getPessoa());
                funcionarioDAO.alterar(funcionario);
                enderecoDAO.alterar(funcionario.getPessoa().getEndereco());
                carregarTableViewFuncionarios();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        }
    }

    
     public boolean FXMLCadastroFuncionarioController(Funcionario funcionario, Pessoa pessoa, Endereco endereco) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroFuncionarioController.class.getResource("/javafx/view/FXMLCadastroFuncionario.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Funcionarios");
         //dialogStage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLCadastroFuncionarioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario, pessoa, endereco);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
     
}
