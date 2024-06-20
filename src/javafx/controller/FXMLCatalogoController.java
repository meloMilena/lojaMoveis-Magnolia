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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class FXMLCatalogoController implements Initializable {


    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;

    @FXML
    private AnchorPane root; 

    @FXML
    private ScrollPane scrollPane; 
    @FXML
    private ImageView imageView;
    @FXML
    private Label labelNomeProduto;
    @FXML
    private Label labelPreco;
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

    GridPane gridPane = new GridPane();
    gridPane.setStyle("-fx-background-color: white;");
    gridPane.setHgap(50);
    gridPane.setVgap(10);
    gridPane.setAlignment(Pos.TOP_CENTER);
    AnchorPane.setTopAnchor(gridPane, 0.0);

    int rowIndex = 0;
    int columnIndex = 0;

    for (Produto produto : produtos) {
        String imageUrl = "/imgProduto/" + produto.getUrlImagem(); // Caminho dentro do JAR

        ImageView imageView = new ImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        try {
            // Tentar carregar a imagem do produto
            Image image = new Image(imageUrl);
            imageView.setImage(image);
        } catch (Exception e) {
            // Se ocorrer exceção, exibir imagem padrão de erro.png
            String erroImageUrl = "/imgProduto/erro.png"; // Caminho dentro do JAR
            Image image = new Image(erroImageUrl);
            imageView.setImage(image);
        }

        labelNomeProduto = new Label(produto.getNome());
        labelPreco = new Label("R$ " + String.valueOf(produto.getPreco()));

        labelNomeProduto.getStyleClass().add("label-visualizar");
        labelNomeProduto.getStylesheets().addAll(getClass().getResource("/css/css.css").toExternalForm());

        labelPreco.getStyleClass().add("label-visualizar");
        labelPreco.getStylesheets().addAll(getClass().getResource("/css/css.css").toExternalForm());

        gridPane.add(imageView, columnIndex, rowIndex);
        gridPane.add(labelNomeProduto, columnIndex, rowIndex + 1);
        gridPane.add(labelPreco, columnIndex, rowIndex + 2);

        columnIndex++;
        if (columnIndex == 5) {
            columnIndex = 0;
            rowIndex += 3; // Ajuste para acomodar apenas a imagem, nome e descrição
        }
    }

    scrollPane = new ScrollPane(gridPane);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    AnchorPane.setTopAnchor(scrollPane, 0.0);
    AnchorPane.setBottomAnchor(scrollPane, 0.0);
    AnchorPane.setLeftAnchor(scrollPane, 0.0);
    AnchorPane.setRightAnchor(scrollPane, 0.0);

    root.getChildren().add(scrollPane);
}

}
