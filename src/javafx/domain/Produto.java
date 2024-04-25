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
public class Produto {
    private int idProduto;
    private ProdutoFornecedor produtoFornecedor;
    private Fornecedor fornecedor;
    private String nome;
    private double peso;
    private String tamanho;
    private String codBarras;
    private String cor;
    private String marca;
    private Categoria categoria;
    private String descricao;
    private double preco;
    private int idProdutoFornecedor;
    private int idFornecedor; 
    private String nomeFornecedor;
    private String urlImagem;
    private Produto produto;
    
    public Produto(){
    }

    public Produto(int idProduto, ProdutoFornecedor produtoFornecedor, String nome, double peso, String tamanho, String codBarras,
        String cor, String marca, Categoria categoria, String descricao, double preco, Fornecedor fornecedor, int idFornecedor, String nomeFornecedor) {
        this.idProduto = idProduto;
        this.produtoFornecedor = produtoFornecedor;
        this.categoria = categoria;
        this.nome = nome;
        this.peso = peso;
        this.tamanho = tamanho;
        this.codBarras = codBarras;
        this.cor = cor;
        this.marca = marca;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.fornecedor = fornecedor;
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
    }
    
     public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public ProdutoFornecedor getProdutoFornecedor() {
        return produtoFornecedor;
    }
    
    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
    
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
    
    
     public void setProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        this.produtoFornecedor = produtoFornecedor;
    }
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
     public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    
     public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
     public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    
     public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
     public int setIdProdutoFornecedor() {
        return idProdutoFornecedor;
    }

    public void setIdProdutoFornecedor(int idProdutoFornecedor) {
        this.idProdutoFornecedor = idProdutoFornecedor;
    }

    
    @Override
    public String toString() {
        return this.nomeFornecedor;
    }
}
