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
public class ProdutoFornecedor {
    private int idProdutoFornecedor;
    private Produto produto;
    private Fornecedor fornecedor;
    private int quantidade;

    public ProdutoFornecedor(int idProdutoFornecedor, Produto produto, Fornecedor fornecedor, int quantidade) {
        this.idProdutoFornecedor = idProdutoFornecedor;
        this.produto = produto;
        this.fornecedor = fornecedor;
        this.quantidade = quantidade;
    }

    public int getIdProdutoFornecedor() {
        return idProdutoFornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setIdProdutoFornecedor(int idProdutoFornecedor) {
        this.idProdutoFornecedor = idProdutoFornecedor;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
