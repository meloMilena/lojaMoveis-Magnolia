/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.domain;

public class Cliente {
    private Pessoa pessoa;
    private int idCliente;
    private Endereco endereco;
    private int idPessoa;
    
    public Cliente() {
       
    }

    public Cliente(int idPessoa, String nome, String email, String cpf, String telefone, int idCliente,
            Endereco endereco) {
        this.pessoa = new Pessoa(idPessoa, nome, email, cpf, telefone, endereco);
        this.idCliente = idCliente;   
    }


    public Pessoa getPessoa() {
        return pessoa;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {;
        this.pessoa = pessoa;
    }

    public String getNome() {
        return pessoa.getNome();
    }

    public void setNome(String nome) {
        pessoa.setNome(nome);
    }

    public String getEmail() {
        return pessoa.getEmail();
    }

    public void setEmail(String email) {
        pessoa.setEmail(email);
    }

    public String getCpf() {
        return pessoa.getCpf();
    }

    public void setCpf(String cpf) {
        pessoa.setCpf(cpf);
    }

    public String getTelefone() {
        return pessoa.getTelefone();
    }

    public void setTelefone(String telefone) {
        pessoa.setTelefone(telefone);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
