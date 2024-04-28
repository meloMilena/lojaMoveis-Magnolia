/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.CategoriaDAO;
import javafx.dao.EnderecoDAO;
import javafx.dao.EstoqueDAO;
import javafx.dao.FornecedorDAO;
import javafx.dao.GerenteDAO;
import javafx.dao.PessoaDAO;
import javafx.dao.ProdutoDAO;
import javafx.dao.ProdutoFornecedorDAO;
import javafx.domain.Categoria;
import javafx.domain.Endereco;
import javafx.domain.Estoque;
import javafx.domain.Fornecedor;
import javafx.domain.Gerente;
import javafx.domain.Pessoa;
import javafx.domain.Produto;
import javafx.domain.ProdutoFornecedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevChefMio
 */
public class FXMLTelaProdutoController implements Initializable {
    @FXML
    private TableColumn<Produto, Integer> tableColumnID;
    @FXML
    private TableColumn<Produto, String> tableColumnProduto;
    @FXML
    private TableColumn<Produto, String> tableColumnDesc;
    @FXML
    private TableColumn<Produto, Double> tableColumnPrecoUni;
    @FXML
    private TableColumn<Produto, String> tableColumnFornecedor;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRegistrar;
    @FXML
    private TableView<Produto> tableViewProduto;
    private ImageView imagemProduto;

    private List<Produto> listProduto;
    private ObservableList<Produto> observableListlistProduto;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    
    @FXML
    private Button buttonVisualizarProduto;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
        categoriaDAO.setConnection(connection);
        fornecedorDAO.setConnection(connection);
        estoqueDAO.setConnection(connection);
        produtoFornecedorDAO.setConnection(connection);
        carregarTableViewProduto();
        
        tableViewProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        handleTableViewProdutoSelection();
        });
    }    

     public void carregarTableViewProduto() {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        tableColumnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tableColumnPrecoUni.setCellValueFactory(new PropertyValueFactory<>("preco"));
        tableColumnFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));

        listProduto = produtoDAO.listar();

        observableListlistProduto = FXCollections.observableArrayList(listProduto);
        tableViewProduto.setItems(observableListlistProduto);
    }
     
    @FXML
    public void handleButtonInserir() throws IOException {
        Produto produto = new Produto();
        ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor();
        Categoria categoria = new Categoria();
        Fornecedor fornecedor = new Fornecedor();
        boolean buttonConfirmarClicked = FXMLCadastroProdutoController(produto, produtoFornecedor, fornecedor);
        if (buttonConfirmarClicked) {
            carregarTableViewProduto();
        }
    }
    
    public void handleTableViewProdutoSelection() {
        Produto produtoSelecionado = tableViewProduto.getSelectionModel().getSelectedItem();
    }
    
    @FXML
    public void handleButtonVisualizarProduto(ActionEvent event) throws IOException {
        Produto produtoSelecionado = tableViewProduto.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/view/FXMLTelaVisualizacaoProduto.fxml"));
            Parent root = loader.load();

            FXMLTelaVisualizacaoProdutoController controller = loader.getController();
            controller.setProduto(produtoSelecionado); 

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Visualizar Produto");
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecione um produto para visualizar.");
            alert.show();
        }
    }

    @FXML
    public void handleButtonAlterar() throws IOException {
        Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
        ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor();
        Categoria categoria = new Categoria();
        Fornecedor fornecedor = new Fornecedor();
        if (produto != null) {
            boolean buttonConfirmarClicked = FXMLCadastroProdutoController(produto, produtoFornecedor, fornecedor);
            if (buttonConfirmarClicked) {
                produtoDAO.alterar(produto.getProduto());
                carregarTableViewProduto();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        }
    }
     
@FXML
public void handleButtonRemover() throws IOException {
    Produto produto = tableViewProduto.getSelectionModel().getSelectedItem();
    if (produto != null) {
        int idProduto = produto.getIdProduto();
        Estoque estoque = new Estoque();
        estoque.setIdProduto(idProduto);
          
        ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor();
        produtoFornecedor.setIdProdutoFornecedor(idProduto);
     
        estoqueDAO.remover(estoque);
        produtoDAO.remover(produto);
        produtoFornecedorDAO.remover(produtoFornecedor);

        carregarTableViewProduto();
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Por favor, escolha um produto na Tabela!");
        alert.show();
    }
}

    
     public boolean FXMLCadastroProdutoController(Produto produto, ProdutoFornecedor produtoFornecedor, Fornecedor fornecedor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroProdutoController.class.getResource("/javafx/view/FXMLCadastroProduto.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Produtos");

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        FXMLCadastroProdutoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduto(produto, produtoFornecedor, fornecedor);

        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
    
}
