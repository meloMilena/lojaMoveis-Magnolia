package javafx.dao;

import javafx.domain.ItemPedido;
import javafx.domain.Pedido;
import javafx.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemPedidoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

  public boolean inserir(ItemPedido itemPedido) {
    String sql = "INSERT INTO item_pedido(quantidade, preco, produto_pedido, pedido_id) VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, itemPedido.getQuantidade()); // Define a quantidade do item
        stmt.setDouble(2, itemPedido.getPreco()); // Define o preço do item
        stmt.setInt(3, itemPedido.getProduto().getIdProduto()); // Define o ID do produto associado ao item
        stmt.setInt(4, itemPedido.getPedido().getIdPedido()); // Define o ID do pedido associado ao item
        stmt.execute();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}


    public boolean remover(ItemPedido itemPedido) {
        String sql = "DELETE FROM itempedido WHERE id_itempedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, itemPedido.getIdItemPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ItemPedido> listar() {
        String sql = "SELECT * FROM itempedido";
        List<ItemPedido> listaItemPedidos = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(resultado.getInt("id_pedido"));

                Produto produto = new Produto();
                produto.setIdProduto(resultado.getInt("id_produto"));

                ItemPedido itemPedido = new ItemPedido(
                        resultado.getInt("id_itempedido"),
                        produto,
                        pedido,
                        resultado.getDouble("preco"),
                        resultado.getInt("quantidade")
                );
                listaItemPedidos.add(itemPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaItemPedidos;
    }
}
