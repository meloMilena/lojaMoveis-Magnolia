package javafx.controller;

import javafx.scene.control.ScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.domain.Produto;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.dao.ProdutoDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FXMLCatalogoController implements Initializable {


    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;

      @FXML
    private AnchorPane root; // Sua janela raiz

    @FXML
    private ScrollPane scrollPane; // Adicione isso ao seu FXML
    @FXML
    private ImageView imageView;
    @FXML
    private Label labelNomeProduto;
    @FXML
    private Label labelDescricao;
    @FXML
    private Button button;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
        
        root.getStylesheets().add(getClass().getResource("/css/css.css").toExternalForm());
        
        labelNomeProduto.getStyleClass().add("label-visualizar");
        labelDescricao.getStyleClass().add("label-visualizar");
        button.getStyleClass().add("button-add-carrinho");
          
        carregarProdutos();
    }

    private void carregarProdutos() {
        List<Produto> produtos = produtoDAO.listar();

        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);


       // Dentro do método carregarProdutos
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setMargin(gridPane, new Insets(0, 0, 20, 0));

        int rowIndex = 0;
        int columnIndex = 0;

        for (Produto produto : produtos) {
            String imageUrl = getClass().getResource("/imgProduto/" + produto.getUrlImagem()).toExternalForm();

            System.out.println("Caminho da imagem: " + imageUrl);

            imageView = new ImageView(imageUrl);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);

            Button button = new Button("Adicionar ao Carriinho");
            button.setOnAction(event -> {
                Node source = (Node) event.getSource();
                GridPane parentPane = (GridPane) source.getParent();
                Produto produtoAtual = (Produto) parentPane.getChildren().get(0).getUserData();
                adicionarAoCarrinho(produtoAtual);
            });

            labelNomeProduto = new Label(produto.getNome());
            labelDescricao = new Label(produto.getDescricao());

            gridPane.add(imageView, columnIndex, rowIndex);
            gridPane.add(labelNomeProduto, columnIndex, rowIndex + 1);
            gridPane.add(labelDescricao, columnIndex, rowIndex + 2);
            gridPane.add(button, columnIndex, rowIndex + 3);

            columnIndex++;
            if (columnIndex == 5) { // Número de colunas que você deseja exibir, ajuste conforme necessário
                columnIndex = 0;
                rowIndex += 4; // Avança para a próxima linha
            }
        }

        scrollPane.setContent(gridPane);
        
    }

    private void adicionarAoCarrinho(Produto produto) {
        // Lógica para adicionar o produto ao carrinho
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Adicionar ao Carrinho");
        alert.setHeaderText(null);
        alert.setContentText("Produto \"" + produto.getNome() + "\" adicionado ao carrinho!");
        alert.showAndWait();
    }
}