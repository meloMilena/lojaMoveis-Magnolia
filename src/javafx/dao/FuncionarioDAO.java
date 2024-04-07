package javafx.dao;

import javafx.domain.Endereco;
import javafx.domain.Funcionario;
import javafx.domain.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.domain.Cliente;

public class FuncionarioDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Funcionario funcionario) {
        String sql = "INSERT INTO funcionario (salario, pessoa_funcionario, cliente_funcionario) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, funcionario.getSalario());
            stmt.setInt(3, funcionario.getPessoa().getIdPessoa());
            stmt.setInt(4, funcionario.getClienteFuncionario().getIdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Funcionario funcionario) {
        String sql = "UPDATE funcionario SET salario=?, pessoa_funcionario=?, cliente_funcionario=? WHERE id_funcionario=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, funcionario.getSalario());
            stmt.setInt(3, funcionario.getPessoa().getIdPessoa());
            stmt.setInt(4, funcionario.getClienteFuncionario().getIdCliente());
            stmt.setInt(5, funcionario.getIdFuncionario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
       public List<Funcionario> listar() {
String sql = "SELECT f.*, p.*, e.*, c.* FROM funcionario f " +
             "INNER JOIN pessoa p ON f.pessoa_funcionario = p.id_pessoa " +
             "INNER JOIN endereco e ON p.endereco_pessoa = e.id_endereco " +
             "INNER JOIN cliente c ON f.cliente_funcionario = c.id_cliente";

    List<Funcionario> funcionarios = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int idFuncionario = rs.getInt("id_funcionario");
            double salario = rs.getDouble("salario");
            int idPessoa = rs.getInt("id_pessoa");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String cpf = rs.getString("cpf");
            String telefone = rs.getString("telefone");
            int idEndereco = rs.getInt("id_endereco");
            String cep = rs.getString("cep");
            String bairro = rs.getString("bairro");
            String rua = rs.getString("rua");
            int numero = rs.getInt("numero");
            String complemento = rs.getString("complemento");
            int idCliente = rs.getInt("id_cliente");
            Cliente cliente = new Cliente();
          
            Endereco endereco = new Endereco(idEndereco, cep, bairro, rua, numero, complemento);
       
            Funcionario funcionario = new Funcionario(idPessoa, nome, email, cpf, telefone, idFuncionario, salario, endereco, cliente);
            funcionarios.add(funcionario);
        }
    } catch (SQLException ex) {
        Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return funcionarios;
}

}
