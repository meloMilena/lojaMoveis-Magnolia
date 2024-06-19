package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.domain.Fornecedor;
import javafx.domain.Endereco;
import javafx.dao.FornecedorDAO;
import javafx.dao.EnderecoDAO;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;

public class FXMLTelaFornecedorController implements Initializable {

    @FXML
    private TableView<Fornecedor> tableViewFornecedor;
    @FXML
    private TableColumn<Fornecedor, Integer> tableColumnId;
    @FXML
    private TableColumn<Fornecedor, String> tableColumnNome;
    @FXML
    private TableColumn<Fornecedor, String> tableColumnCNAE; 
    @FXML
    private TableColumn<Fornecedor, String> tableColumnTelefone;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonInserir;

    private List<Fornecedor> listaFornecedores;
    private ObservableList<Fornecedor> observableListFornecedores;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedorDAO.setConnection(connection); // ajuste conforme seu método de conexão
        enderecoDAO.setConnection(connection); // ajuste conforme seu método de conexão
        carregarTableViewFornecedores();
    }

    private void carregarTableViewFornecedores() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idFornecedor"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCNAE.setCellValueFactory(new PropertyValueFactory<>("cnae"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        listaFornecedores = fornecedorDAO.listar(); // método listar do FornecedorDAO

        observableListFornecedores = FXCollections.observableArrayList(listaFornecedores);
        tableViewFornecedor.setItems(observableListFornecedores);
    }

    @FXML
    public void handleButtonRemover(ActionEvent event) {
        Fornecedor fornecedorSelecionado = tableViewFornecedor.getSelectionModel().getSelectedItem();
        if (fornecedorSelecionado != null) {
            if (fornecedorDAO.remover(fornecedorSelecionado)) {
                enderecoDAO.remover(fornecedorSelecionado.getEndereco()); // exemplo de remoção de endereço associado
                carregarTableViewFornecedores();
            } else {
                exibirAlerta("Erro ao remover fornecedor", "Erro ao remover fornecedor.");
            }
        } else {
            exibirAlerta("Nenhum fornecedor selecionado", "Por favor, selecione um fornecedor para remover.");
        }
    }

    @FXML
    public void handleButtonAlterar() {
        Fornecedor fornecedor = tableViewFornecedor.getSelectionModel().getSelectedItem();
        if (fornecedor != null) {
            boolean buttonConfirmarClicked = FXMLCadastroFornecedorController(fornecedor, fornecedor.getEndereco());
            if (buttonConfirmarClicked) {
                fornecedorDAO.alterar(fornecedor); 
                enderecoDAO.alterar(fornecedor.getEndereco()); 
                carregarTableViewFornecedores();
            }
        } else {
            exibirAlerta("Nenhum fornecedor selecionado", "Por favor, selecione um fornecedor para alterar.");
        }
    }

    @FXML
    public void handleButtonInserir(ActionEvent event) {
        Fornecedor fornecedor = new Fornecedor();
        Endereco endereco = new Endereco();
        boolean buttonConfirmarClicked = FXMLCadastroFornecedorController(fornecedor, endereco);
        if (buttonConfirmarClicked) {
            fornecedorDAO.inserir(fornecedor);
            carregarTableViewFornecedores();
        }
    }

    private boolean FXMLCadastroFornecedorController(Fornecedor fornecedor, Endereco endereco) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMLCadastroFuncionarioController.class.getResource("/javafx/view/FXMLCadastroFornecedor.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro de Fornecedor");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            FXMLCadastroFornecedorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFornecedor(fornecedor, endereco);

            dialogStage.showAndWait();

            return controller.isButtonConfirmarClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.show();
    }
}
