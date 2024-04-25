package javafx.controller;

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
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FXMLCatalogoController implements Initializable {

    @FXML
    private TilePane tilePane;

    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoDAO.setConnection(connection);
          
        carregarProdutos();
    }

    private void carregarProdutos() {
        List<Produto> produtos = produtoDAO.listar();

        for (Produto produto : produtos) {
              String imageUrl = getClass().getResource("src\\imgProduto\\" + produto.getUrlImagem()).toExternalForm();

            System.out.println("Caminho da imagem: " + imageUrl);

            ImageView imageView = new ImageView(imageUrl);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);

            Button button = new Button("Adicionar ao Carrinho");
            button.setOnAction(event -> {
                Node source = (Node) event.getSource();
                HBox parentBox = (HBox) source.getParent();
                Produto produtoAtual = (Produto) parentBox.getUserData();
                adicionarAoCarrinho(produtoAtual);
            });

            HBox card = new HBox(imageView, button);
            card.setSpacing(10);
            card.setUserData(produto); // Armazenando o produto no HBox para acesso posterior

            tilePane.getChildren().add(card);
        }
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
