/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.classe;

/**
 *
 * @author DevChefMio
 */
public class ItemPedido {
    public int idItemPedido;
    public Produto produto;
    public Pedido pedido;
    public double preco;
    public int quantidade;

    public ItemPedido(int idItemPedido, Produto produto, Pedido pedido, double preco, int quantidade) {
        this.idItemPedido = idItemPedido;
        this.produto = produto;
        this.pedido = pedido;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getIdItemPedido() {
        return idItemPedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setIdItemPedido(int idItemPedido) {
        this.idItemPedido = idItemPedido;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
