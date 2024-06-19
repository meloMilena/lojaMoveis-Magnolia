package javafx.dao;

import javafx.domain.Estoque;
import javafx.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.domain.Funcionario;

public class EstoqueDAO {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Estoque estoque) {
        String sql = "INSERT INTO estoque (quantidade, prod_estoque) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setInt(2, estoque.getIdProduto());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public boolean remover(Estoque estoque) {
        String sql = "DELETE FROM estoque WHERE prod_estoque=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, estoque.getIdProduto());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


    public boolean atualizarQuantidadeProduto(Estoque estoque) {
        String sql = "UPDATE estoque SET quantidade = ? WHERE prod_estoque = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getQuantidade());
            stmt.setInt(2, estoque.getIdProduto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
public int listar(int idProduto) {
    String query = "SELECT e.quantidade AS quantidade_estoque " +
                   "FROM estoque e " +
                   "WHERE e.prod_estoque = ?";

    int quantidadeProduto = 0;
    try {
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, idProduto);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            quantidadeProduto = rs.getInt("quantidade_estoque");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return quantidadeProduto;
}

    

  
}
