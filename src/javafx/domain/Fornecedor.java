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
public class Fornecedor {
    private int idFornecedor;
    private String nome;
    private String email;
    private String cnpj;
    private String cnae;
    private String telefone;
    private String razaoSocial;
    private Endereco endereco;
    private int enderecoFornecedor;
    
    public Fornecedor() {
    }

    public Fornecedor(int idFornecedor, String nome, String cnae, String razaoSocial, String cnpj, String email, String telefone, Endereco endereco) {
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.cnae = cnae;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCnae() {
        return cnae;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
    public int getIdEndereco() {
        return enderecoFornecedor;
    }

    public void setIdEndereco(int enderecoFornecedor) {
        this.enderecoFornecedor = enderecoFornecedor;
    }

    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    @Override
    public String toString() {
        return this.nome;
    }

}
