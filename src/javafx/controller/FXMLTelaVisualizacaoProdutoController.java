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
import javafx.stage.Stage;

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
    @FXML
    private Label labelMarca;
    @FXML
    private Label labelTamanho;
    @FXML
    private Label labelCodBarras;
    @FXML
    private Label labelCategoria;
    
    private Stage dialogStage;
    private Produto produto;
    
    public Stage getDialogStage() {
        return dialogStage;
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
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
            String imagePath = "src\\imgProduto\\" + produto.getUrlImagem();
            Image image = new Image(new File(imagePath).toURI().toString());
            imagemProduto.setImage(image);

            labelNomeProduto.setText(produto.getNome());
            labelDescricaoProduto.setText(produto.getDescricao());
            labelPrecoProduto.setText(String.valueOf(produto.getPreco()));
            labelMarca.setText(String.valueOf(produto.getMarca()));
            labelTamanho.setText(String.valueOf(produto.getTamanho()));
            labelFornecedorProduto.setText(String.valueOf(produto.getFornecedor().getNome()));
            labelCategoria.setText(String.valueOf(produto.getCategoria().getNome()));
            labelCodBarras.setText(String.valueOf(produto.getCodBarras()));
        }
    }
    
    @FXML
    public void handleButtonConfirmar() {
        Stage stage = (Stage) imagemProduto.getScene().getWindow(); // Obtém a referência para o Stage da janela atual
        stage.close(); // Fecha a janela
    }

}
