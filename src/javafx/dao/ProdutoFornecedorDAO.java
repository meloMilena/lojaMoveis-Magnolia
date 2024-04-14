/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.domain.Fornecedor;
import javafx.domain.ProdutoFornecedor;

public class ProdutoFornecedorDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public boolean inserir(ProdutoFornecedor produtoFornecedor) {
        String sql = "INSERT INTO produto_fornecedor(quantidade, fornecedor_prod) VALUES(?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produtoFornecedor.getQuantidade());
            stmt.setInt(2, produtoFornecedor.getFornecedor().getIdFornecedor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }
    
     public boolean remover(ProdutoFornecedor produtoFornecedor) {
        String sql = "DELETE FROM produto_fornecedor WHERE id_produto_fornecedor=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, produtoFornecedor.getIdProdutoFornecedor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoFornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
   public List<ProdutoFornecedor> listar() {
    String sql = "SELECT pd.*, f.id_fornecedor AS id_fornecedor, f.nome AS nomeFornecedor\n"
            + "FROM produto_fornecedor pd\n"
            + "JOIN fornecedor f ON pd.fornecedor_prod = f.id_fornecedor;";

    List<ProdutoFornecedor> retorno = new ArrayList<>();
    try {
        PreparedStatement stmt1 = connection.prepareStatement(sql);
        ResultSet resultado1 = stmt1.executeQuery();

        while (resultado1.next()) {
              ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor();
              Fornecedor fornecedor = new Fornecedor();

              produtoFornecedor.setIdProdutoFornecedor(resultado1.getInt("id_produto_fornecedor"));
              produtoFornecedor.setQuantidade(resultado1.getInt("quantidade"));
              produtoFornecedor.setIdProdutoFornecedor(resultado1.getInt("fornecedor_prod"));

              fornecedor.setIdFornecedor(resultado1.getInt("id_fornecedor"));
              fornecedor.setNome(resultado1.getString("nomeFornecedor"));
              produtoFornecedor.setFornecedor(fornecedor);

              retorno.add(produtoFornecedor);
        }

    } catch (SQLException ex) {
        Logger.getLogger(ProdutoFornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
}

   
public List<Fornecedor> listarFornecedores() {
    String sql = "SELECT * FROM fornecedor";
    List<Fornecedor> fornecedores = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            // Crie um objeto Fornecedor e configure o nome e o ID a partir do resultado
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setIdFornecedor(resultado.getInt("id_fornecedor"));
            fornecedor.setNome(resultado.getString("nome"));
            fornecedores.add(fornecedor);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProdutoFornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return fornecedores;
}


    public int obterUltimoIdInserido() {
    String sql = "SELECT MAX(id_produto_fornecedor) AS ultimo_id FROM produto_fornecedor";
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


    
     
}
