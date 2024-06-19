package javafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.EstoqueDAO;
import javafx.dao.ProdutoDAO;
import javafx.domain.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.StringConverter;

public class FXMLAdicionarProdutoController implements Initializable {
    @FXML
    private ComboBox<Produto> comboBoxProduto;
    @FXML
    private TextField textFIeldCodBarras;
    @FXML
    private TextField textFieldQuantidade;

    private ObservableList<Produto> observableListProduto;
    private List<Produto> listProduto;
    
    private Produto produto;
    
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();

    private Stage dialogStage;
    private FXMLRegistrarPedidoController registrarPedidoController;
    private boolean buttonConfirmarClicked = false;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
        estoqueDAO.setConnection(connection);
        
        listProduto = produtoDAO.listar();
        observableListProduto = FXCollections.observableArrayList(listProduto);

        comboBoxProduto.setItems(observableListProduto);

        // Modificação do StringConverter para retornar o objeto Produto completo
        comboBoxProduto.setConverter(new StringConverter<Produto>() {
            @Override
            public String toString(Produto produto) {
                if (produto == null) {
                    return null;
                } else {
                    return produto.getNome(); // Ajuste isso conforme necessário
                }
            }

            @Override
            public Produto fromString(String string) {
                for (Produto produto : observableListProduto) {
                    if (produto.getNome().equals(string)) {
                        return produto;
                    }
                }
                return null;
            }
        });

        comboBoxProduto.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                textFIeldCodBarras.setText(newVal.getCodBarras());
            }
        });

        textFIeldCodBarras.setOnAction(event -> {
            String codBarras = textFIeldCodBarras.getText();
            Produto produtoEncontrado = listProduto.stream()
                    .filter(p -> p.getCodBarras().equals(codBarras))
                    .findFirst()
                    .orElse(null);

            if (produtoEncontrado != null) {
                comboBoxProduto.setValue(produtoEncontrado);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Produto não encontrado");
                alert.setHeaderText(null);
                alert.setContentText("O produto com o código de barras " + codBarras + " não existe.");
                alert.showAndWait();
                comboBoxProduto.setValue(null);
            }
        });        
    }    
    
    public void setProduto(Produto produto) {
        this.produto = produto;
        this.comboBoxProduto.setValue(produto);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setRegistrarPedidoController(FXMLRegistrarPedidoController controller) {
        this.registrarPedidoController = controller;
    }

@FXML
private void handleButtonAdicionar() {
    Produto produtoSelecionado = comboBoxProduto.getValue();
    String quantidadeText = textFieldQuantidade.getText(); // Obtém a quantidade do TextField

    if (produtoSelecionado != null && !quantidadeText.isEmpty()) { // Verifica se o produto e a quantidade foram selecionados
        int quantidade = Integer.parseInt(quantidadeText); // Converte a quantidade para inteiro
        produtoSelecionado.setQuantidade(quantidade); // Define a quantidade no produto selecionado
        System.out.println(produtoSelecionado.getIdProduto());
        int quantidadeEstoque = estoqueDAO.listar(produtoSelecionado.getIdProduto()); // Obtém a quantidade do estoque para o produto selecionado
        
        System.out.println(quantidadeEstoque);
        if (quantidadeEstoque >= quantidade) { // Verifica se a quantidade do estoque é suficiente
            registrarPedidoController.adicionarProdutoNaTabela(produtoSelecionado); // Adiciona o produto na tabela do pedido
            buttonConfirmarClicked = true;
            dialogStage.close();
        } else {
            exibirAlerta("Estoque insuficiente", "O produto possui apenas " + quantidadeEstoque + " unidades em estoque.");
        }
    } else {
        exibirAlerta("Nenhum produto selecionado", "Por favor, selecione um produto e informe a quantidade.");
    }
}






    private void exibirAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}