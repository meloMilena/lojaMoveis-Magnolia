<<<<<<< HEAD:src/javafx/classe/Funcionario.java
package javafx.classe;
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.domain;
>>>>>>> dc6e608e48fe0d5d9da9adf0fe2e487435f053d9:src/javafx/domain/Funcionario.java

public class Funcionario {
    private Pessoa pessoa;
    private int idFuncionario;
    private double salario;
    private Endereco endereco;
    private Cliente clienteFuncionario;
    
    public Funcionario(){
    } 
    
    public Funcionario(int idPessoa, String nome, String email, String cpf, String telefone, int idFuncionario,
        double salario, Endereco endereco, Cliente clienteFuncionario) {
        this.pessoa = new Pessoa(idPessoa, nome, email, cpf, telefone, endereco);
        this.idFuncionario = idFuncionario;
        this.salario = salario;
        this.clienteFuncionario = clienteFuncionario;
    }
    
    public Cliente getClienteFuncionario() {
        return clienteFuncionario;
    }

    public void setClienteFuncionario(Cliente clienteFuncionario) {
        this.clienteFuncionario = clienteFuncionario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public int getIdPessoa() {
        return pessoa.getIdPessoa();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setIdPessoa(int idPessoa) {
        pessoa.setIdPessoa(idPessoa);
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

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
