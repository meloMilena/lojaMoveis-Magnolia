package javafx.dao;

<<<<<<< HEAD
=======
import javafx.domain.Cliente;
import javafx.domain.Pedido;
>>>>>>> 3babcb7ac39e5117b7b02c3bce8fe017920b29a1
import javafx.domain.StatusPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< HEAD
import java.sql.SQLException;
=======
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
>>>>>>> 3babcb7ac39e5117b7b02c3bce8fe017920b29a1
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

<<<<<<< HEAD
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
=======
 /*   public List<StatusPedido> listar() {;
        String sql = "SELECT sp.id_status_pedido, sp.descricao, p.id_pedido, p.data_pedido, p.valor_total, " +
                     "c.id_cliente, c.nome AS nome_cliente " +
                     "FROM status_pedido sp " +
                     "INNER JOIN pedido p ON sp.id_status_pedido = p.status_pedido " +
                     "INNER JOIN cliente c ON p.cliente_pedido = c.id_cliente";

        List<StatusPedido> statusPedidos = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                StatusPedido statusPedido = new StatusPedido();
                statusPedido.setIdStatusPedido(rs.getInt("id_status_pedido"));

                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setDataPedido(rs.getDate("data_pedido").toLocalDate());
                pedido.setValorTotal(rs.getDouble("valor_total"));

                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNome(rs.getString("nome_cliente"));

                pedido.setCliente(cliente);
                statusPedido.setPedido(pedido);

                statusPedidos.add(statusPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusPedidos;
    }
}*/
}
>>>>>>> 3babcb7ac39e5117b7b02c3bce8fe017920b29a1
