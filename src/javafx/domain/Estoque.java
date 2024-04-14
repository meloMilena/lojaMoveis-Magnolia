/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.domain;

public class Estoque {
    private int idEstoque;
    private int quantidade;
    private int idProduto;
    private Produto produto;

    public Estoque(int idEstoque, int quantidade, Produto produto) {
        this.idEstoque = idEstoque;
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Estoque() {
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }
    
     public int getIdProdut() {
        return idProduto;
    }

    public void setIdProdut(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
