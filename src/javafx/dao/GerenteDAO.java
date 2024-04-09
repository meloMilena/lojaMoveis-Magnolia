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
import javafx.domain.Gerente;

public class GerenteDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

   public boolean inserir(Gerente gerente) {
    String sqlInsert = "INSERT INTO gerente (salario, status, pessoa_gerente) VALUES (?, ?, ?)";
    String sqlUpdate = "UPDATE gerente SET status='Inativo' WHERE status='Ativo'";
    
    try {
        // Atualiza todos os gerentes ativos para "Inativo" antes de inserir o novo gerente
        PreparedStatement updateStmt = connection.prepareStatement(sqlUpdate);
        updateStmt.executeUpdate();
        
        PreparedStatement insertStmt = connection.prepareStatement(sqlInsert);
        insertStmt.setDouble(1, gerente.getSalario());
        insertStmt.setString(2, "Ativo");
        insertStmt.setInt(3, gerente.getIdPessoa());
        insertStmt.execute();
        
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

    
    private void definirStatusUltimoGerenteComoInativo() throws SQLException {
    // Consulta o último gerente cadastrado
    String sqlUltimoGerente = "SELECT id_gerente FROM gerente ORDER BY id_gerente DESC LIMIT 1";
    String sqlAtualizarStatus = "UPDATE gerente SET status='Inativo' WHERE id_gerente=?";
    try (PreparedStatement stmtUltimoGerente = connection.prepareStatement(sqlUltimoGerente);
         ResultSet rs = stmtUltimoGerente.executeQuery()) {
        if (rs.next()) {
            int idUltimoGerente = rs.getInt("id_gerente");
            // Atualiza o status do último gerente para "Inativo"
            PreparedStatement stmtAtualizarStatus = connection.prepareStatement(sqlAtualizarStatus);
            stmtAtualizarStatus.setInt(1, idUltimoGerente);
            stmtAtualizarStatus.executeUpdate();
        }
    }
}


    public boolean alterar(Gerente gerente) {
        String sql = "UPDATE gerente SET salario=?, pessoa_gerente=? WHERE id_gerente=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, gerente.getSalario());
            stmt.setInt(2, gerente.getIdPessoa());
            stmt.setInt(3, gerente.getIdGerente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean remover(Gerente gerente) {
        String sql = "DELETE FROM gerente WHERE id_gerente=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, gerente.getIdGerente());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
public List<Gerente> listar() {
    String sql = "SELECT * " +
                 "FROM endereco AS e " +
                 "JOIN pessoa AS p ON e.id_endereco = p.endereco_pessoa " +
                 "JOIN gerente AS f ON p.id_pessoa = f.pessoa_gerente";

    List<Gerente> gerentes = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int idGerente = rs.getInt("id_gerente");
            double salario = rs.getDouble("salario");
            int idPessoa = rs.getInt("id_pessoa");
            String status = rs.getString("status");
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

            Gerente gerente = new Gerente(idPessoa, nome, email, cpf, telefone, idGerente, salario, status, endereco);
         
            
            gerentes.add(gerente);
        }
    } catch (SQLException ex) {
        Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return gerentes;
}



}
