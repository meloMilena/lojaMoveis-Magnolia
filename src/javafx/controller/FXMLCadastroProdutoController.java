package javafx.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.CategoriaDAO;
import javafx.dao.EstoqueDAO;
import javafx.dao.FornecedorDAO;
import javafx.dao.ProdutoDAO;
import javafx.dao.ProdutoFornecedorDAO;
import javafx.domain.Categoria;
import javafx.domain.CodigoBarrasGenerator;
import javafx.domain.Estoque;
import javafx.domain.Fornecedor;
import javafx.domain.Produto;
import javafx.domain.ProdutoFornecedor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLCadastroProdutoController implements Initializable {
    @FXML
    private TextField textFieldMarca;
    @FXML
    private ComboBox<Categoria> comboBoxCategoria;
    @FXML
    private ComboBox<Fornecedor> comboBoxFornecedor;
    @FXML
    private TextArea textAreaDescricao;
    @FXML
    private TextField textFieldCodBarras;
    @FXML
    private TextField textFieldNomeProduto;
    @FXML
    private TextField textFieldCorVar;
    @FXML
    private TextField textFieldPreco;
    @FXML
    private Button buttonImage;
    @FXML
    private ImageView imagemProduto;
    @FXML
    private TextField textFieldQuantidade;
     @FXML
    private TextField textFieldPeso;
    @FXML
    private TextField textFieldTamanho;

    private File selectedImageFile;
    
    private Produto produto;
    private ProdutoFornecedor produtoFornecedor;
    private Categoria categoria;
    private Fornecedor fornecedor;
    private String nomeImagem = null;

    private ObservableList<Categoria> observableListCategoria;
    private List<Categoria> listCategoria;
    
    private ObservableList<Fornecedor> observableListFornecedor;
    private List<Fornecedor> listFornecedor;

    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;

    private final ProdutoFornecedorDAO produtoFornecedorDAO = new ProdutoFornecedorDAO();
    private final ProdutoDAO produtoDAO = new ProdutoDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();
    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private final EstoqueDAO estoqueDAO = new EstoqueDAO();
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MAX_LENGTH = 5;
   
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Produto getProduto() {
        return produto;
    }
    
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        produtoFornecedorDAO.setConnection(connection);
        produtoDAO.setConnection(connection);
        categoriaDAO.setConnection(connection);
        fornecedorDAO.setConnection(connection);
        estoqueDAO.setConnection(connection);

        listCategoria = categoriaDAO.listar();
        observableListCategoria = FXCollections.observableArrayList(listCategoria);

        listFornecedor = produtoFornecedorDAO.listarFornecedores();
        observableListFornecedor = FXCollections.observableArrayList(listFornecedor);

        comboBoxCategoria.setItems(observableListCategoria);
        comboBoxFornecedor.setItems(observableListFornecedor);
    }

    @FXML
    private void selecionarImagem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem do Produto");

        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.gif"),
            new FileChooser.ExtensionFilter("Todos os Arquivos", "*.*")
        );

        selectedImageFile = fileChooser.showOpenDialog(new Stage());

        if (selectedImageFile != null) {
            imagemProduto.setImage(new javafx.scene.image.Image(selectedImageFile.toURI().toString()));
        }
    }
    
    public void setProduto(Produto produto, ProdutoFornecedor produtoFornecedor, Fornecedor fornecedor) {
        this.produto = produto;
        this.produtoFornecedor = produtoFornecedor;
        this.fornecedor = fornecedor;
        
        String codigoBarras = CodigoBarrasGenerator.generateCodigoBarras();
        
        this.textFieldMarca.setText(produto.getMarca());
        this.comboBoxFornecedor.setValue(produto.getFornecedor());
        this.textAreaDescricao.setText(produto.getDescricao());
        this.textFieldCodBarras.setText(codigoBarras);
        this.textFieldNomeProduto.setText(produto.getNome());
        this.textFieldCorVar.setText(produto.getCor());
        this.textFieldPreco.setText(String.valueOf(produto.getPreco()));
        this.textFieldQuantidade.setText(String.valueOf(produtoFornecedor.getQuantidade()));
        this.textFieldPeso.setText(String.valueOf(produto.getPeso()));
        this.textFieldTamanho.setText(produto.getTamanho());
        this.comboBoxCategoria.setValue(produto.getCategoria());
        
    }
     
    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            if (selectedImageFile != null) {
            
                try {
                    String destino = "src\\imgProduto";
                    File destinationDirectory = new File(destino);

                    if (!destinationDirectory.exists()) {
                        destinationDirectory.mkdirs();
                    }

                    Path sourcePath = selectedImageFile.toPath();
                    Path targetPath = Paths.get(destino, selectedImageFile.getName());

                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("Arquivo copiado com sucesso para: " + targetPath.toString());

                    nomeImagem = selectedImageFile.getName();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Erro ao copiar o arquivo.");
                }
            } 
              
            ProdutoFornecedor novoProdutoFornecedor = new ProdutoFornecedor();
//            Produto novoProduto = new Produto();

            novoProdutoFornecedor.setQuantidade(Integer.parseInt(textFieldQuantidade.getText())); 
            novoProdutoFornecedor.setFornecedor(comboBoxFornecedor.getValue());

            if (produtoFornecedorDAO.inserir(novoProdutoFornecedor)) {
                int idProdutoFornecedor = produtoFornecedorDAO.obterUltimoIdInserido();

                produto.setNome(textFieldNomeProduto.getText());
                produto.setUrlImagem(nomeImagem);
                produto.setPreco(Double.parseDouble(textFieldPreco.getText()));
                produto.setPeso(Double.parseDouble(textFieldPeso.getText()));
                produto.setTamanho(textFieldTamanho.getText());
                produto.setDescricao(textAreaDescricao.getText());
                produto.setCodBarras(textFieldCodBarras.getText());;
                produto.setCor(textFieldCorVar.getText());
                produto.setMarca(textFieldMarca.getText());
                produto.setCategoria(comboBoxCategoria.getValue());
                produto.setIdProdutoFornecedor(idProdutoFornecedor);

                if (produtoDAO.inserir(produto)) {

                    int idProduto = produtoDAO.obterUltimoIdInserido();

                    Estoque estoque = new Estoque();
                    estoque.setIdProduto(idProduto);
                    estoque.setQuantidade(Integer.parseInt(textFieldQuantidade.getText()));

                    
                    if (estoqueDAO.inserir(estoque)) {
                        buttonConfirmarClicked = true;
                        dialogStage.close();
                    }
                   
                }
            }
        }
    }
   
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldNomeProduto.getText() == null || textFieldNomeProduto.getText().isEmpty()) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldCodBarras.getText() == null || textFieldCodBarras.getText().isEmpty()) {
            errorMessage += "Código de barras inválido!\n";
        }
        if (textFieldCorVar.getText() == null || textFieldCorVar.getText().isEmpty()) {
            errorMessage += "Cor inválida!\n";
        }
        if (textFieldMarca.getText() == null || textFieldMarca.getText().isEmpty()) {
            errorMessage += "Marca inválida!\n";
        }
        if (textFieldTamanho.getText() == null || textFieldTamanho.getText().isEmpty()) {
            errorMessage += "Tamanho inválido!\n";
        }
        if (textAreaDescricao.getText() == null || textAreaDescricao.getText().isEmpty()) {
            errorMessage += "Descrição inválida!\n";
        }
        if (textFieldPreco.getText() == null || textFieldPreco.getText().isEmpty() || !textFieldPreco.getText().matches("\\d+(\\.\\d+)?")) {
            errorMessage += "Preço inválido!\n";
        }
        if (comboBoxFornecedor.getValue() == null) {
            errorMessage += "Fornecedor inválido!\n";
        }
        if (comboBoxCategoria.getValue() == null) {
            errorMessage += "Categoria inválida!\n";
        }
        if (textFieldQuantidade.getText() == null || textFieldQuantidade.getText().isEmpty() || !textFieldQuantidade.getText().matches("\\d+")) {
            errorMessage += "Quantidade inválida!\n";
        }
        if (textFieldPeso.getText() == null || textFieldPeso.getText().isEmpty() || !textFieldPeso.getText().matches("\\d+(\\.\\d+)?")) {
            errorMessage += "Peso inválido!\n";
        }
        if (selectedImageFile == null) {
            errorMessage += "Selecione uma imagem para o produto!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no cadastro");
            alert.setHeaderText("Campos inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

}
