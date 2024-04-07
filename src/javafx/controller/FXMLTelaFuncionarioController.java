/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.classe.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.dao.FuncionarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.model.database.Database;
import javafx.model.database.DatabaseFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DevChefMio
 */
public class FXMLTelaFuncionarioController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<Funcionario> tableViewFuncionario;
    @FXML
    private TableColumn<Funcionario, Integer> tableColumnId;
    @FXML
    private TableColumn<Funcionario, String> tableColumnNome;
    @FXML
    private TableColumn<Funcionario, String> tableColumnCpf;
    @FXML
    private Button buttonRegistrar;

    private List<Funcionario> listFuncionario;
    private ObservableList<Funcionario> observableListFuncionario;
    
    private final Database database = DatabaseFactory.getDatabase("postgresql");
    private final Connection connection = database.conectar();
    private final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        funcionarioDAO.setConnection(connection);
        carregarTableViewFuncionarios();
//        makeStageDraggable();
//        selecionarItemTableViewFuncionarios(null);
    }    
    
     public void carregarTableViewFuncionarios() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        listFuncionario = funcionarioDAO.listar();

        observableListFuncionario = FXCollections.observableArrayList(listFuncionario);
        tableViewFuncionario.setItems(observableListFuncionario);
    }
     
     @FXML
    public void handleButtonInserir() throws IOException {
        Funcionario funcionario = new Funcionario();
        boolean buttonConfirmarClicked = showFXMLCadastroFuncionarioDialog(funcionario);
        if (buttonConfirmarClicked) {
            funcionarioDAO.inserir(funcionario);
            carregarTableViewFuncionarios();
        }
    }
    
     public boolean showFXMLCadastroFuncionarioDialog(Funcionario funcionario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLCadastroFuncionarioController.class.getResource("/javafx/view/FXMLCadastroFuncionario.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Funcionarios");
         //dialogStage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLCadastroFuncionarioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }
     
}
