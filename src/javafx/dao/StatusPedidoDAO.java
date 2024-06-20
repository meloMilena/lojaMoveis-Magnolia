package javafx.dao;

import javafx.domain.Cliente;
import javafx.domain.Pedido;
import javafx.domain.StatusPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public List<StatusPedido> listar() {
        String sql = "SELECT "
                + "    pe.id_pedido, "
                + "    sp.id_status_pedido, "
                + "    s.descricao AS estado_pedido, "
                + "    pe.cpf AS cpf_cliente "
                + "FROM "
                + "    status_pedido sp "
                + "INNER JOIN "
                + "    status s ON sp.status_pedido = s.id_status "
                + "INNER JOIN "
                + "    pedido pe ON sp.pedido_numero = pe.id_pedido";

        List<StatusPedido> statusPedidos = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                int idStatusPedido = rs.getInt("id_status_pedido");
                String estadoPedido = rs.getString("estado_pedido");
                String cpfCliente = rs.getString("cpf_cliente");

                StatusPedido statusPedido = new StatusPedido();
                statusPedido.setIdStatusPedido(idStatusPedido);
                statusPedido.setEstadoPedido(estadoPedido);

                Pedido pedido = new Pedido();
                pedido.setIdPedido(idPedido);
                pedido.setCpf(cpfCliente); // Define o CPF do cliente diretamente do pedido

                statusPedido.setPedidos(pedido); // Armazena o pedido no status do pedido

                statusPedidos.add(statusPedido);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusPedidos;
    }

}
