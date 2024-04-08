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

public class PessoaDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (nome, email, cpf, telefone, endereco_pessoa) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getCpf());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setInt(5, pessoa.getIdEndereco());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean alterar(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome=?, email=?, cpf=?, telefone=?, endereco_pessoa=? WHERE id_pessoa=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setString(3, pessoa.getCpf());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setInt(5, pessoa.getEndereco().getId());
            stmt.setInt(6, pessoa.getIdPessoa());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int obterUltimoIdInserido() {
    String sql = "SELECT MAX(id_pessoa) AS ultimo_id FROM pessoa";
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

    
    public List<Pessoa> listar() {
        String sql = "SELECT * FROM pessoa INNER JOIN endereco ON pessoa.endereco_pessoa = endereco.id_endereco";
        List<Pessoa> listaPessoas = new ArrayList<>();
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
                Pessoa pessoa = new Pessoa(
                        resultado.getInt("id_pessoa"),
                        resultado.getString("nome"),
                        resultado.getString("email"),
                        resultado.getString("cpf"),
                        resultado.getString("telefone"),
                        endereco
                );
                listaPessoas.add(pessoa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPessoas;
    }
}
