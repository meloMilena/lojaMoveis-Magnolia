package javafx.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.domain.Categoria;
import javafx.domain.Endereco;
import javafx.domain.Fornecedor;
import javafx.domain.Gerente;
import javafx.domain.Produto;
import javafx.domain.ProdutoFornecedor;

public class ProdutoDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Produto produto) {
       String query = "INSERT INTO produto (nome, preco, peso, url_imagem, tamanho, cod_barras, cor, marca, descricao, prod_fornecedor, categoria) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       try (PreparedStatement statement = connection.prepareStatement(query)) {


           statement.setString(1, produto.getNome());
           statement.setDouble(2, produto.getPreco());
           statement.setDouble(3, produto.getPeso());
           statement.setString(4, produto.getUrlImagem());
           statement.setString(5, produto.getTamanho());
           statement.setString(6, produto.getCodBarras());
           statement.setString(7, produto.getCor());
           statement.setString(8, produto.getMarca());
           statement.setString(9, produto.getDescricao());
           statement.setInt(10, produto.setIdProdutoFornecedor());
           statement.setInt(11, produto.getCategoria().getIdCategoria()); 
           statement.execute();
           return true;
       } catch (SQLException ex) {
           Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
    
       public boolean alterar(Produto produto) {
        String sql = "UPDATE produto SET nome=?, preco=?, peso=?, url_imagem=?, tamanho=?, cor=?, marca=?, descricao=?, prod_fornecedor=?, categoria=? WHERE id_produto=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setDouble(2, produto.getPreco());
            statement.setDouble(3, produto.getPeso());
            statement.setString(4, produto.getUrlImagem());
            statement.setString(5, produto.getTamanho());
            statement.setString(6, produto.getCor());
            statement.setString(7, produto.getMarca());
            statement.setString(8, produto.getDescricao());
            statement.setInt(9, produto.setIdProdutoFornecedor());
            statement.setInt(10, produto.getCategoria().getIdCategoria()); 
            statement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
 
    public int obterUltimoIdInserido() {
        String sql = "SELECT MAX(id_produto) AS ultimo_id FROM produto";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1; // Retorna -1 se não houver nenhum ID inserido ou houver algum erro
    }
    
    
  public boolean remover(Produto produto) {
    String sql = "DELETE FROM produto WHERE id_produto=?";
    try {
        String sqlImagem = "SELECT url_imagem FROM produto WHERE id_produto=?";
        PreparedStatement stmtImagem = connection.prepareStatement(sqlImagem);
        stmtImagem.setInt(1, produto.getIdProduto());
        ResultSet rs = stmtImagem.executeQuery();
        String caminhoImagem = null;
        if (rs.next()) {
            caminhoImagem = rs.getString("url_imagem");
        }
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, produto.getIdProduto());
        stmt.execute();
        
        // Remover a imagem do produto, se existir
        if (caminhoImagem != null) {
            File imagem = new File(caminhoImagem);
            if (imagem.exists()) {
                imagem.delete();
            }
        }
        
        return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

  public List<Produto> listar() {
        String query = "SELECT *, f.nome AS nomefornecedor, c.nome AS nome_categoria, c.descricao AS descricao_categoria, e.quantidade AS quantidade_estoque " +
               "FROM produto p " +
               "INNER JOIN produto_fornecedor pf ON p.prod_fornecedor = pf.id_produto_fornecedor " +
               "INNER JOIN fornecedor f ON pf.fornecedor_prod = f.id_fornecedor " +
               "INNER JOIN categoria c ON p.categoria = c.id_categoria " +
               "INNER JOIN estoque e ON p.id_produto = e.prod_estoque";

         
    List<Produto> produtos = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setIdProduto(rs.getInt("id_produto"));
            produto.setNome(rs.getString("nome"));
            produto.setUrlImagem(rs.getString("url_imagem"));
            produto.setPreco(rs.getDouble("preco"));
            produto.setPeso(rs.getDouble("peso"));
            produto.setTamanho(rs.getString("tamanho"));
            produto.setCodBarras(rs.getString("cod_barras"));
            produto.setCor(rs.getString("cor"));
            produto.setMarca(rs.getString("marca"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setQuantidade(rs.getInt("quantidade_estoque"));

            ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor(); 
            produtoFornecedor.setQuantidade(rs.getInt("quantidade"));
            
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getInt("id_categoria"));
            categoria.setNome(rs.getString("nome_categoria"));
            categoria.setDescricao(rs.getString("descricao_categoria"));

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setIdFornecedor(rs.getInt("fornecedor_prod"));
            fornecedor.setNome(rs.getString("nomefornecedor"));
            
            produto.setFornecedor(fornecedor);
            produto.setCategoria(categoria);
            produtos.add(produto);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return produtos;
}

   
}
