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

public class ClienteDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Cliente cliente) {
        String sqlInsert = "INSERT INTO cliente (pessoa_cliente) VALUES (?)";
        try {
            PreparedStatement insertStmt = connection.prepareStatement(sqlInsert);
            insertStmt.setInt(1, cliente.getIdPessoa());
            insertStmt.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET pessoa_cliente=? WHERE id_cliente=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE id_cliente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Cliente> listar() {
        String sql = "SELECT * "
                + "FROM endereco AS e "
                + "JOIN pessoa AS p ON e.id_endereco = p.endereco_pessoa "
                + "JOIN cliente AS c ON p.id_pessoa = c.pessoa_cliente";

        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
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

                Endereco endereco = new Endereco(idEndereco, cep, bairro, rua, numero, complemento);

                Cliente cliente = new Cliente(idPessoa, nome, email, cpf, telefone, idCliente, endereco);

                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    
    public Cliente buscarPorCPF(String cpf) {
    String sql = "SELECT * "
            + "FROM endereco AS e "
            + "JOIN pessoa AS p ON e.id_endereco = p.endereco_pessoa "
            + "JOIN cliente AS c ON p.id_pessoa = c.pessoa_cliente "
            + "WHERE p.cpf = ?";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int idCliente = rs.getInt("id_cliente");
            int idPessoa = rs.getInt("id_pessoa");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String telefone = rs.getString("telefone");
            int idEndereco = rs.getInt("id_endereco");
            String cep = rs.getString("cep");
            String bairro = rs.getString("bairro");
            String rua = rs.getString("rua");
            int numero = rs.getInt("numero");
            String complemento = rs.getString("complemento");

            Endereco endereco = new Endereco(idEndereco, cep, bairro, rua, numero, complemento);

            return new Cliente(idPessoa, nome, email, cpf, telefone, idCliente, endereco);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null; // Retorna null se não encontrar nenhum cliente com o CPF informado
}

}
