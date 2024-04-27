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

    carregarProdutos();
}


    private void carregarProdutos() {
        List<Produto> produtos = produtoDAO.listar();

        labelNomeProduto.getStylesheets().addAll(getClass().getResource("/css/css.css").toExternalForm());
    
    
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        
        
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.getStylesheets().addAll(getClass().getResource("/css/css.css").toExternalForm());
            
        root.getStyleClass().add("anchor-pane");
        root.getStylesheets().addAll(getClass().getResource("/css/css.css").toExternalForm());
            

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

            Button button = new Button();
            button.setText("Adicionar ao Carrinho");
            button.setPrefHeight(40.0);
            button.setPrefWidth(229.0);
            button.getStyleClass().add("button-add-carrinho");
            button.getStylesheets().addAll(getClass().getResource("/css/css.css").toExternalForm());
            button.setOnAction(event -> {
                Node source = (Node) event.getSource();
                GridPane parentPane = (GridPane) source.getParent();
                Produto produtoAtual = (Produto) parentPane.getChildren().get(0).getUserData();
                adicionarAoCarrinho(produtoAtual);
            });

            labelNomeProduto = new Label(produto.getNome());
            labelDescricao = new Label(produto.getDescricao());

            labelNomeProduto.getStyleClass().add("label-visualizar");
            labelNomeProduto.getStylesheets().addAll(getClass().getResource("/css/css.css").toExternalForm());

            labelDescricao.getStyleClass().add("label-visualizar");
            labelDescricao.getStylesheets().addAll(getClass().getResource("/css/css.css").toExternalForm());


            gridPane.add(imageView, columnIndex, rowIndex);
            gridPane.add(labelNomeProduto, columnIndex, rowIndex + 1);
            gridPane.add(labelDescricao, columnIndex, rowIndex + 2);
            gridPane.add(button, columnIndex, rowIndex + 3);

            columnIndex++;
            if (columnIndex == 5) {
                columnIndex = 0;
                rowIndex += 4;
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