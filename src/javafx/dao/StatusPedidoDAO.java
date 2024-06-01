package javafx.dao;

import javafx.domain.StatusPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StatusPedidoDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(StatusPedido statusPedido) {
        String sql = "INSERT INTO status_pedido (status_pedido, pedido_numero) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, statusPedido.getstatusPedido());
            stmt.setInt(2, statusPedido.getPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(StatusPedido statusPedido) {
        String sql = "UPDATE status_pedido SET status_pedido=? WHERE id_pedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, statusPedido.getstatusPedido());
            stmt.setInt(2, statusPedido.getPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(StatusPedido statusPedido) {
        String sql = "DELETE FROM status_pedido WHERE id_pedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, statusPedido.getPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
