package javafx.dao;

import javafx.domain.Pedido;
import javafx.domain.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.domain.Cliente;

public class PedidoDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean inserir(Pedido pedido) {
        String sql = "INSERT INTO pedido (valor_total, data_entrega, cpf, data, contato, funcionario_pedido) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDouble(1, pedido.getValor());
            stmt.setObject(2, pedido.getDataEntrega());
            stmt.setString(3, pedido.getCpf());
            stmt.setObject(4, pedido.getData());
            stmt.setObject(5, pedido.getTelefone());
            stmt.setObject(6, pedido.getFuncionario().getIdFuncionario());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Pedido pedido) {
        String sql = "DELETE FROM pedido WHERE id_pedido=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getIdPedido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public int obterUltimoIdInserido() {
        String sql = "SELECT MAX(id_pedido) AS ultimo_id FROM pedido";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1; // Retorna -1 se não houver nenhum ID inserido ou houver algum erro
    }

    public List<Pedido> listar() {
    String sql = "SELECT *, "
               + "p.nome AS nome_produto, "
               + "p.descricao AS descricao_produto, "
               + "c.nome AS nome_cliente, "
               + "p.url_imagem AS imagem_produto, "
               + "p.marca AS marca_produto, "
               + "p.cor AS cor_produto, "
               + "p.tamanho AS tamanho_produto "
               + "FROM item_pedido AS ip "
               + "JOIN produto AS p ON ip.produto_pedido = p.id_produto "
               + "JOIN pedido AS pe ON ip.pedido_id = pe.id_pedido "
               + "JOIN cliente AS cli ON pe.funcionario_pedido = cli.id_cliente "
               + "JOIN pessoa AS c ON cli.pessoa_cliente = c.id_pessoa "
               + "JOIN categoria AS pd ON p.categoria = pd.id_categoria";
    List<Pedido> listaPedidos = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        while (resultado.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(resultado.getInt("id_pedido"));
            pedido.setValor(resultado.getDouble("valor_total"));
            pedido.setDataEntrega(resultado.getDate("data_entrega").toLocalDate());
            pedido.setCpf(resultado.getString("cpf"));
            pedido.setData(resultado.getDate("data").toLocalDate());
            pedido.setTelefone(resultado.getString("contato"));
            
            Produto produto = new Produto();
            produto.setIdProduto(resultado.getInt("id_produto"));
            produto.setNome(resultado.getString("nome_produto"));
            produto.setDescricao(resultado.getString("descricao_produto"));
            produto.setUrlImagem(resultado.getString("imagem_produto"));
            produto.setMarca(resultado.getString("marca_produto"));
            produto.setCor(resultado.getString("cor_produto"));
            produto.setTamanho(resultado.getString("tamanho_produto"));
            
            Cliente cliente = new Cliente();
            cliente.setNome(resultado.getString("nome_cliente"));
            
            pedido.setProduto(produto);
           // pedido.setCliente(cliente);;
            
            listaPedidos.add(pedido);
        }
    } catch (SQLException ex) {
        Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listaPedidos;
}

}
