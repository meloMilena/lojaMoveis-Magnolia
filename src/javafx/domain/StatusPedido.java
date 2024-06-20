/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.domain;

/**
 *
 * @author DevChefMio
 */
public class StatusPedido {

    private int idStatusPedido;
    private int statusPedido;
    private String estadoPedido;
    private Cliente cliente;
    private Produto produto;
    private Pedido pedidos;
    private int pedido;

    public StatusPedido(int idStatusPedido, String estadoPedido, int statusPedido, Cliente cliente, Produto produto, Pedido pedidos, int pedido) {
        this.idStatusPedido = idStatusPedido;
        this.estadoPedido = estadoPedido;
        this.statusPedido = statusPedido;
        this.cliente = cliente;
        this.produto = produto;
        this.pedidos = pedidos;
        this.pedido = pedido;
    }

    public StatusPedido() {
    }

    public int getIdStatusPedido() {
        return idStatusPedido;
    }

    public void setIdStatusPedido(int idStatusPedido) {
        this.idStatusPedido = idStatusPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public int getstatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(int statusPedido) {
        this.statusPedido = statusPedido;
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedido pedidos) {
        this.pedidos = pedidos;
    }
}
