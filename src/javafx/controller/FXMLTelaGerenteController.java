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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.EnderecoDAO;
import javafx.dao.GerenteDAO;
import javafx.dao.PessoaDAO;
import javafx.domain.Endereco;
import javafx.domain.Funcionario;
import javafx.domain.Gerente;
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

public class FXMLTelaGerenteController implements Initializable {
    @FXML
    private TableView<Gerente> tableViewGerente;
    @FXML
    private TableColumn<Gerente, Integer> tableColumnId;
    @FXML
    private TableColumn<Gerente, String> tableColumnStatus;
    @FXML
    private TableColumn<Gerente, String> tableColumnNome;
    @FXML
    private TableColumn<Gerente, String> tableColumnCPF;
    @FXML
    private TableColumn<Gerente, String> tableColumnEmail;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRegistrar;
    
    private List<Gerente> listGerente;
    private ObservableList<Gerente> observableListlistGerente;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final GerenteDAO gerenteDAO = new GerenteDAO();
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();
    @FXML
    private Button buttonRemover1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gerenteDAO.setConnection(connection);
        pessoaDAO.setConnection(connection);
        enderecoDAO.setConnection(connection);
        carregarTableViewGerentes();
    }    
    
    public void carregarTableViewGerentes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idGerente"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        listGerente = gerenteDAO.listar();

        observableListlistGerente = FXCollections.observableArrayList(listGerente);
        tableViewGerente.setItems(observableListlistGerente);
    }
    
    @FXML
    public void handleButtonInserir() throws IOException {
        Gerente gerente = new Gerente();
        Pessoa pessoa = new Pessoa();
        Endereco endereco = new Endereco();
        boolean buttonConfirmarClicked = FXMLCadastroGerenteController(gerente, pessoa, endereco);
        if (buttonConfirmarClicked) {
                gerenteDAO.inserir(gerente);
                carregarTableViewGerentes();
            }
    }
    
     @FXML
     public void handleButtonAlterar() throws IOException {
        Gerente gerente = tableViewGerente.getSelectionModel().getSelectedItem();
        if (gerente != null) {
            boolean buttonConfirmarClicked = FXMLCadastroGerenteController(gerente, gerente.getPessoa(), gerente.getPessoa().getEndereco());
            if (buttonConfirmarClicked) {
                pessoaDAO.alterar(gerente.getPessoa());
                gerenteDAO.alterar(gerente);
                enderecoDAO.alterar(gerente.getPessoa().getEndereco());
                carregarTableViewGerentes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        }
    }
    
    @FXML
    public void handleButtonRemover() throws IOException {
        Gerente gerente = tableViewGerente.getSelectionModel().getSelectedItem();
        if (gerente != null) {
            gerenteDAO.remover(gerente);
            carregarTableViewGerentes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um gerente na Tabela!");
            alert.show();
        }
    }
    
    public boolean FXMLCadastroGerenteController(Gerente gerente, Pessoa pessoa, Endereco endereco) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroGerenteController.class.getResource("/javafx/view/FXMLCadastroGerente.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Gerente");

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLCadastroGerenteController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setGerente(gerente, pessoa, endereco);

        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
}
