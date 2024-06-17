package javafx.dao;

import javafx.domain.Cliente;
import javafx.domain.Pedido;
import javafx.domain.StatusPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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