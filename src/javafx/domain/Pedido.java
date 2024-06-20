/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pedido {
    private int idPedido;
    private double valor;
    private LocalDate dataEntrega;
    private String cpf;
    private String telefone;
    private LocalDate data;
    private Produto produto;
    private Funcionario funcionario;
    private Endereco endereco;
    

    public Pedido(int idPedido, double valor, LocalDate dataEntrega, String cpf, LocalDate data,
             Produto produto, Funcionario funcionario, String telefone, Endereco endereco) {
        this.idPedido = idPedido;
        this.valor = valor;
        this.dataEntrega = dataEntrega;
        this.cpf = cpf;
        this.data = data;
        this.produto = produto;
        this.funcionario = funcionario;
        this.telefone = telefone;
    }

    public Pedido() {
       
    }
    
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }
    
     public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getTelefone() {
        return telefone;
    }


    public int getIdPedido() {
        return idPedido;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getData() {
        return data;
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

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
