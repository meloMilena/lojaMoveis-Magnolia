package javafx.controller;

import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.domain.Produto;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLTelaVisualizacaoProdutoController implements Initializable {

    @FXML
    private ImageView imagemProduto;
    @FXML
    private Label labelNomeProduto;
    @FXML
    private Label labelDescricaoProduto;
    @FXML
    private Label labelPrecoProduto;
    @FXML
    private Label labelFornecedorProduto;

    private Produto produto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialização do controlador
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        exibirInformacoesProduto();
    }

    private void exibirInformacoesProduto() {
        if (produto != null) {
            // Exibir a imagem do produto
            String imagePath = "src\\imgProduto\\" + produto.getUrlImagem();
            Image image = new Image(new File(imagePath).toURI().toString());
            imagemProduto.setImage(image);

            // Exibir outras informações do produto
            labelNomeProduto.setText(produto.getNome());
            labelDescricaoProduto.setText(produto.getDescricao());
            labelPrecoProduto.setText(String.valueOf(produto.getPreco()));
        }
    }
}
