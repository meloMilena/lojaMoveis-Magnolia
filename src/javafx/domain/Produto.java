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
    private Fornecedor fornecedor;
    private String nome;
    private Double peso;
    private String tamanho;
    private String codBarras;
    private String cor;
    private String marca;
    private Categoria categoria;

    public Produto(int idProduto, Fornecedor fornecedor, String nome, Double peso, String tamanho, String codBarras,
        String cor, String marca, Categoria categoria) {
        this.idProduto = idProduto;
        this.fornecedor = fornecedor;
        this.nome = nome;
        this.peso = peso;
        this.tamanho = tamanho;
        this.codBarras = codBarras;
        this.cor = cor;
        this.marca = marca;
        this.categoria = categoria;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
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
        return cor;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
