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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.ClienteDAO;
import javafx.dao.EstoqueDAO;
import javafx.dao.FuncionarioDAO;
import javafx.dao.ItemPedidoDAO;
import javafx.dao.PedidoDAO;
import javafx.dao.ProdutoDAO;
import javafx.dao.StatusPedidoDAO;
import javafx.domain.Cliente;
import javafx.domain.Endereco;
import javafx.domain.Estoque;
import javafx.domain.Funcionario;
import javafx.domain.ItemPedido;
import javafx.domain.Pedido;
import javafx.domain.Produto;
import javafx.domain.StatusPedido;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevChefMio
 */
public class FXMLRegistrarPedidoController implements Initializable {
    @FXML
    private TextField textFieldCPF;
    @FXML
    private TextField textFieldNome;
    @FXML
    private DatePicker dataDataAtual;
    @FXML
    private DatePicker dataDataEntrega;
    @FXML
    private TextField textFieldCEP;
    @FXML
    private TextField textFieldContato;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldRua;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private TextField textFieldComplemento;
    @FXML
    private TableView<Produto> tableViewProdutos;
    @FXML
    private TableColumn<Produto, String> tableColumnProduto;
    @FXML
    private TableColumn<Produto, Double> tableColumnValor;
    @FXML
    private TableColumn<Produto, String> tableColumnQuantidade;
    @FXML
    private TableColumn<Produto, Double> tableColumnTotalValo;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonAdcProduto;
    @FXML
    private Label labelTotal;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ComboBox<Funcionario> comboboxFuncionario;
    
    private final PedidoDAO pedidoDAO = new PedidoDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private final ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private final StatusPedidoDAO statusPedidoDAO = new StatusPedidoDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
   
    private Funcionario funcionario;
    private Pedido pedido;
    private ItemPedido itemPedido;
    private StatusPedido statusPedido;
    private Estoque estoque;
    
    private ObservableList<Produto> observableListProdutos;
    private List<Produto> listProduto;
    
    private ObservableList<Funcionario> observableListFuncionario;
    private List<Funcionario> listFuncionario;
    @FXML
    private Button handleButtonConfirmar;
    
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pedidoDAO.setConnection(connection);
        produtoDAO.setConnection(connection);
        clienteDAO.setConnection(connection);
        funcionarioDAO.setConnection(connection);
        itemPedidoDAO.setConnection(connection);
        statusPedidoDAO.setConnection(connection);
        estoqueDAO.setConnection(connection);
        
        pedido = new Pedido();

        listFuncionario = funcionarioDAO.listar();
        observableListFuncionario = FXCollections.observableArrayList(listFuncionario);
        comboboxFuncionario.setItems(observableListFuncionario);

        // Adiciona um listener para o evento de liberar tecla (KeyReleased)
        textFieldCPF.setOnKeyReleased(event -> {
            buscarClientePorCPF();
        });
        
    }

    public ObjectProperty<Double> carregarTableViewProdutos(List<Produto> produtos) {
        tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnValor.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableColumnTotalValo.setCellValueFactory(cellData -> {
            double total = cellData.getValue().getPreco() * cellData.getValue().getQuantidade();
            return new SimpleDoubleProperty(total).asObject();
        });

        observableListProdutos = FXCollections.observableArrayList(produtos);
        tableViewProdutos.setItems(observableListProdutos);
        return null;
    }


    @FXML
    private void handleAdicionarProdto() throws IOException {
        // Load the FXML layout for adding a product
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/javafx/view/FXMLAdicionarProduto.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create a new stage for the popup dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Adicionar Produto");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(anchorPane.getScene().getWindow()); // Ensure anchorPane is initialized
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the controller for the dialog stage
        FXMLAdicionarProdutoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setRegistrarPedidoController(this); // Pass the reference to FXMLRegistrarPedidoController

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
    }

    @FXML
    private void buscarClientePorCPF() {
        String cpf = textFieldCPF.getText();
        clienteDAO.setConnection(connection);
        Cliente cliente = clienteDAO.buscarPorCPF(cpf);
        if (cliente != null) {
            textFieldContato.setText(cliente.getTelefone());
            textFieldNome.setText(cliente.getNome());
            Endereco endereco = cliente.getEndereco();
            if (endereco != null) {
                textFieldCEP.setText(endereco.getCep());
                textFieldRua.setText(endereco.getRua());
                textFieldNumero.setText(String.valueOf(endereco.getNumero()));
                textFieldBairro.setText(endereco.getBairro());
                textFieldComplemento.setText(endereco.getComplemento());
            }
        } else {
            textFieldNome.setText("");
            textFieldRua.setText("");
            textFieldNumero.setText("");
            textFieldBairro.setText("");
            textFieldComplemento.setText("");
        }
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        this.comboboxFuncionario.setValue(pedido.getFuncionario());
        this.textFieldCEP.setText(pedido.getCpf());
        buscarClientePorCPF(); // Chama a função para buscar cliente por CPF e preencher os campos
        this.textFieldContato.setText(pedido.getTelefone());
        this.dataDataAtual.setValue(pedido.getData());
        this.dataDataEntrega.setValue(pedido.getDataEntrega());
    }



public void handleButtonConfirmar() {
    pedido.setData(dataDataAtual.getValue());
    pedido.setDataEntrega(dataDataEntrega.getValue());
    pedido.setValor(setCalcularTotalProdutos()); 
    pedido.setCpf(textFieldCPF.getText());
    pedido.setFuncionario(comboboxFuncionario.getValue());
    pedido.setTelefone(textFieldContato.getText());

    if (pedidoDAO.inserir(pedido)) {
        int idPedido = pedidoDAO.obterUltimoIdInserido();

        for (Produto produto : tableViewProdutos.getItems()) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(new Pedido(idPedido, setCalcularTotalProdutos(), dataDataAtual.getValue(), textFieldCPF.getText(), dataDataEntrega.getValue(), produto, comboboxFuncionario.getValue(), textFieldContato.getText(), null));
            itemPedido.setProduto(produto);
            itemPedido.setPreco(produto.getPreco());
            itemPedido.setQuantidade(produto.getQuantidade());
            itemPedidoDAO.inserir(itemPedido);
        }
        
        // Após inserir todos os itens do pedido, insira o status do pedido
        statusPedido = new StatusPedido();
        statusPedido.setStatusPedido(1);
        statusPedido.setPedido(idPedido);
        
        if (statusPedidoDAO.inserir(statusPedido)) {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        } else {
            // Exibir mensagem de erro se a inserção do status do pedido falhar
            exibirErro("Falha ao inserir status do pedido", "Ocorreu um erro ao inserir o status do pedido. Por favor, tente novamente.");
        }
        
    } else {
        // Exibir mensagem de erro se a inserção do pedido falhar
        exibirErro("Falha ao inserir pedido", "Ocorreu um erro ao inserir o pedido. Por favor, tente novamente.");
    }
}


private double setCalcularTotalProdutos() {
    double total = 0.0;
    for (Produto produto : tableViewProdutos.getItems()) {
        total += produto.getPreco() * produto.getQuantidade();
    }
    return total;
}

private void exibirErro(String titulo, String mensagem) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erro no cadastro");
    alert.setHeaderText(titulo);
    alert.setContentText(mensagem);
    alert.show();
}

    
       


public void adicionarProdutoNaTabela(Produto produto) {
    if (observableListProdutos == null) {
        observableListProdutos = FXCollections.observableArrayList();
        tableViewProdutos.setItems(observableListProdutos);
    }
    observableListProdutos.add(produto);
    carregarTableViewProdutos(observableListProdutos); // Carrega os dados na tabela
    calcularTotalProdutos(); // Calcula o total após adicionar o produto
}

@FXML
private void handleButtonRemoverProduto() {
    // Verifica se há uma linha selecionada na tabela
    int selectedIndex = tableViewProdutos.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        // Remove o produto selecionado da lista de produtos
        tableViewProdutos.getItems().remove(selectedIndex);
        calcularTotalProdutos(); // Calcula o total após remover o produto
    } else {
        // Caso nenhum produto seja selecionado, exiba uma mensagem de alerta
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(anchorPane.getScene().getWindow());
        alert.setTitle("Nenhum Produto Selecionado");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, selecione um produto na tabela.");
        alert.showAndWait();
    }
}

private void calcularTotalProdutos() {
    double total = 0.0;
    for (Produto produto : tableViewProdutos.getItems()) {
        total += produto.getPreco() * produto.getQuantidade();
    }
    labelTotal.setText(String.format("Total: R$ %.2f", total));
}
}
