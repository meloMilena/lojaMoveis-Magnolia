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

public class EnderecoDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Endereco endereco) {
        String sql = "INSERT INTO endereco (cep, bairro, rua, numero, complemento) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getRua());
            stmt.setInt(4, endereco.getNumero());
            stmt.setString(5, endereco.getComplemento());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Endereco endereco) {
        String sql = "UPDATE endereco SET cep=?, bairro=?, rua=?, numero=?, complemento=? WHERE id_endereco=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, endereco.getCep());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getRua());
            stmt.setInt(4, endereco.getNumero());
            stmt.setString(5, endereco.getComplemento());
            stmt.setInt(6, endereco.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Endereco endereco) {
        String sql = "DELETE FROM endereco WHERE id_endereco=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, endereco.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public int obterUltimoIdInserido() {
    String sql = "SELECT MAX(id_endereco) AS ultimo_id FROM endereco";
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("ultimo_id");
        }
    } catch (SQLException ex) {
        Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return -1; // Retorna -1 se não houver nenhum ID inserido ou houver algum erro
}

    public List<Endereco> listar() {
        String sql = "SELECT * FROM endereco";
        List<Endereco> listaEnderecos = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Endereco endereco = new Endereco(
                        resultado.getInt("id_endereco"),
                        resultado.getString("cep"),
                        resultado.getString("bairro"),
                        resultado.getString("rua"),
                        resultado.getInt("numero"),
                        resultado.getString("complemento")
                );
                listaEnderecos.add(endereco);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEnderecos;
    }
}
