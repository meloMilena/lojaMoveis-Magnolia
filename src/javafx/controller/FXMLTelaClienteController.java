package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.ClienteDAO;
import javafx.dao.EnderecoDAO;
import javafx.dao.PessoaDAO;
import javafx.domain.Cliente;
import javafx.domain.Endereco;
import javafx.domain.Pessoa;
import javafx.event.ActionEvent;
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
 */
public class FXMLTelaClienteController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Cliente> tableViewCliente;
    @FXML
    private TableColumn<Cliente, Integer> tableColumnId;
    @FXML
    private TableColumn<Cliente, String> tableColumnNome;
    @FXML
    private TableColumn<Cliente, String> tableColumnCpf;
    @FXML
    private TableColumn<Cliente, String> tableColumnTelefone;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRegistrar;

    private List<Cliente> listCliente;
    private ObservableList<Cliente> observableListCliente;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final PessoaDAO pessoaDAO = new PessoaDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (connection == null) {
            System.err.println("Connection is null in FXMLTelaClienteController.");
            return;
        }
        clienteDAO.setConnection(connection);
        pessoaDAO.setConnection(connection);
        enderecoDAO.setConnection(connection);
        carregarTableViewClientes();
    }

    public void carregarTableViewClientes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        listCliente = clienteDAO.listar();
        
        observableListCliente = FXCollections.observableArrayList(listCliente);
        tableViewCliente.setItems(observableListCliente);
    }

    @FXML
    public void handleButtonRemover() {
        Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            clienteDAO.remover(cliente);
            pessoaDAO.remover(cliente.getPessoa());
            carregarTableViewClientes();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Cliente cliente = tableViewCliente.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            boolean buttonConfirmarClicked = showFXMLCadastroClienteDialog(cliente, cliente.getPessoa(), cliente.getPessoa().getEndereco());
            if (buttonConfirmarClicked) {
                pessoaDAO.alterar(cliente.getPessoa());
                clienteDAO.alterar(cliente);
                enderecoDAO.alterar(cliente.getPessoa().getEndereco());
                carregarTableViewClientes();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonInserir() throws IOException {
        Cliente cliente = new Cliente();
        Pessoa pessoa = new Pessoa();
        Endereco endereco = new Endereco();
        boolean buttonConfirmarClicked = showFXMLCadastroClienteDialog(cliente, pessoa, endereco);
        if (buttonConfirmarClicked) {
            clienteDAO.inserir(cliente);
            carregarTableViewClientes();
        }
    }

    private boolean showFXMLCadastroClienteDialog(Cliente cliente, Pessoa pessoa, Endereco endereco) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroClienteController.class.getResource("/javafx/view/FXMLCadastroCliente.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Clientes");
        dialogStage.setScene(new Scene(page));

        FXMLCadastroClienteController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCliente(cliente, pessoa, endereco);

        dialogStage.showAndWait();
        return controller.isButtonConfirmarClicked();
    }
}
