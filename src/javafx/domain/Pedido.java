/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.domain;

import java.time.LocalDateTime;

public class Pedido {
    private int idPedido;
    private double valor;
    private LocalDateTime dataEntrega;
    private String cpf;
    private LocalDateTime data;
    private Endereco endereco;
    private Produto produto;

    public Pedido(int idPedido, double valor, LocalDateTime dataEntrega, String cpf, LocalDateTime data,
            Endereco endereco, Produto produto) {
        this.idPedido = idPedido;
        this.valor = valor;
        this.dataEntrega = dataEntrega;
        this.cpf = cpf;
        this.data = data;
        this.endereco = endereco;
        this.produto = produto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
