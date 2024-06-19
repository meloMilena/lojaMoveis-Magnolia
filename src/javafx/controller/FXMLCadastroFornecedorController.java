package javafx.controller;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.dao.EnderecoDAO;
import javafx.dao.FornecedorDAO;
import javafx.domain.Endereco;
import javafx.domain.Fornecedor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLCadastroFornecedorController implements Initializable {

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldCNAE;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldRazaoSocial;
    @FXML
    private TextField textFieldCNPJ;
    @FXML
    private TextField textFieldCep;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private TextField textFieldRua;
    @FXML
    private TextField textFieldNumero;
    @FXML
    private TextField textFieldComplemento;
    @FXML
    private Button buttonSalvar;

    private Stage dialogStage;
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private boolean buttonConfirmarClicked = false;

    private final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();

    private Fornecedor fornecedor;
    private Endereco endereco;

    
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fornecedorDAO.setConnection(connection);
        enderecoDAO.setConnection(connection);
    }
    
   public void setFornecedor(Fornecedor fornecedor, Endereco endereco) {
        this.fornecedor = fornecedor;
        this.endereco = endereco;

        this.textFieldNome.setText(fornecedor.getNome());
        this.textFieldCNAE.setText(fornecedor.getCnae());
        this.textFieldTelefone.setText(fornecedor.getTelefone());
        this.textFieldEmail.setText(fornecedor.getEmail());
        this.textFieldRazaoSocial.setText(fornecedor.getRazaoSocial());
        this.textFieldCNPJ.setText(fornecedor.getCnpj());
        this.textFieldCep.setText(endereco.getCep());
        this.textFieldBairro.setText(endereco.getBairro());
        this.textFieldRua.setText(endereco.getRua());
        this.textFieldNumero.setText(String.valueOf(endereco.getNumero()));
        this.textFieldComplemento.setText(endereco.getComplemento());
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }
    

    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {
            endereco = new Endereco();
            endereco.setCep(textFieldCep.getText());
            endereco.setBairro(textFieldBairro.getText());
            endereco.setRua(textFieldRua.getText());
            endereco.setNumero(Integer.parseInt(textFieldNumero.getText()));
            endereco.setComplemento(textFieldComplemento.getText());

            if (enderecoDAO.inserir(endereco)) {
                int idEndereco = enderecoDAO.obterUltimoIdInserido();

                fornecedor = new Fornecedor();
                fornecedor.setNome(textFieldNome.getText());
                fornecedor.setCnae(textFieldCNAE.getText());
                fornecedor.setRazaoSocial(textFieldRazaoSocial.getText());
                fornecedor.setCnpj(textFieldCNPJ.getText());
                fornecedor.setEmail(textFieldEmail.getText());
                fornecedor.setTelefone(textFieldTelefone.getText());
                fornecedor.setIdEndereco(idEndereco);

                if (fornecedorDAO.inserir(fornecedor)) {
                    int idFornecedor = fornecedorDAO.obterUltimoIdInserido();
                    
                    buttonConfirmarClicked = true;
                    dialogStage.close();
                } else {
                    exibirErro("Falha ao inserir fornecedor", "Ocorreu um erro ao inserir o fornecedor. Por favor, tente novamente.");
                }
            } else {
                exibirErro("Falha ao inserir endereço", "Ocorreu um erro ao inserir o endereço. Por favor, tente novamente.");
            }
        }
    }

    private void exibirErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro no cadastro");
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.show();
    }

    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (textFieldNome.getText() == null || textFieldNome.getText().isEmpty()) {
            errorMessage += "Nome inválido!\n";
        }
        if (textFieldCNAE.getText() == null || textFieldCNAE.getText().isEmpty()) {
            errorMessage += "CNAE inválido!\n";
        }
        if (textFieldEmail.getText() == null || textFieldEmail.getText().isEmpty()) {
            errorMessage += "Email inválido!\n";
        }
        if (textFieldTelefone.getText() == null || textFieldTelefone.getText().isEmpty()) {
            errorMessage += "Telefone inválido!\n";
        }
        if (textFieldRazaoSocial.getText() == null || textFieldRazaoSocial.getText().isEmpty()) {
            errorMessage += "Razão Social inválida!\n";
        }
        if (textFieldCNPJ.getText() == null || textFieldCNPJ.getText().isEmpty()) {
            errorMessage += "CNPJ inválido!\n";
        }
        if (textFieldCep.getText() == null || textFieldCep.getText().isEmpty()) {
            errorMessage += "CEP inválido!\n";
        }
        if (textFieldRua.getText() == null || textFieldRua.getText().isEmpty()) {
            errorMessage += "Rua inválida!\n";
        }
        if (textFieldNumero.getText() == null || textFieldNumero.getText().isEmpty()) {
            errorMessage += "Número inválido!\n";
        }
        if (textFieldBairro.getText() == null || textFieldBairro.getText().isEmpty()) {
            errorMessage += "Bairro inválido!\n";
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
