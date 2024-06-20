package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.ClienteDAO;
import javafx.dao.PedidoDAO;
import javafx.dao.PessoaDAO;
import javafx.dao.ProdutoDAO;
import javafx.dao.StatusPedidoDAO;
import javafx.domain.Cliente;
import javafx.domain.Pedido;
import javafx.domain.Pessoa;
import javafx.domain.StatusPedido;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.database.DatabaseFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLMonitorarPedidoController implements Initializable {

    @FXML
    private TableView<StatusPedido> tableViewStatusPedido;
    @FXML
    private TableColumn<Pedido, Integer> tableColumnId;
    @FXML
    private TableColumn<StatusPedido, Integer> tableColumnIdStatusPedido;
    @FXML
    private TableColumn<StatusPedido, String> tableColumnStatusPedido;
    @FXML
    private TableColumn<Pedido, String> tableColumnCpf;

    private ObservableList<StatusPedido> observableListStatusPedido;

    private final StatusPedidoDAO statusPedidoDAO = new StatusPedidoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set connection for DAO
        Connection connection = DatabaseFactory.getDatabase("postgresql").conectar();
        statusPedidoDAO.setConnection(connection);

        // Initialize table columns
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        tableColumnIdStatusPedido.setCellValueFactory(new PropertyValueFactory<>("idStatusPedido"));
        tableColumnStatusPedido.setCellValueFactory(new PropertyValueFactory<>("estadoPedido"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));

        // Load data into TableView
        carregarTableViewStatusPedidos();
    }

    public void carregarTableViewStatusPedidos() {
        List<StatusPedido> listStatusPedido = statusPedidoDAO.listar();
        observableListStatusPedido = FXCollections.observableArrayList(listStatusPedido);
        tableViewStatusPedido.setItems(observableListStatusPedido);
    }

    @FXML
    public void handleButtonDetalhes() {
        StatusPedido statusPedido = tableViewStatusPedido.getSelectionModel().getSelectedItem();
        if (statusPedido != null) {
            try {
                boolean buttonConfirmarClicked = FXMLDetalhesPedidoController(statusPedido, statusPedido.getCliente());
                if (buttonConfirmarClicked) {
                    carregarTableViewStatusPedidos();
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Handle IOException if necessary
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um pedido na Tabela!");
            alert.show();
        }
    }

    public boolean FXMLDetalhesPedidoController(StatusPedido statusPedido, Cliente cliente) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/view/FXMLDetalhesPedidoController.fxml"));
        AnchorPane page = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Detalhes do Pedido");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLDetalhesPedidoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
       /* controller.setStatusPedido(statusPedido);
        controller.setCliente(cliente);
*/
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();
    }

}
