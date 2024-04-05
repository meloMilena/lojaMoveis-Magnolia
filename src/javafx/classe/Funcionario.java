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
public class Funcionario {
    private Pessoa pessoa;
    private int idFuncionario;
    private double salario;
    private String status;

    public Funcionario(int idPessoa, String nome, String email, String cpf, String telefone, int idFuncionario,
            double salario, String status, Endereco endereco) {
        this.pessoa = new Pessoa(idPessoa, nome, email, cpf, telefone, endereco);
        this.idFuncionario = idFuncionario;
        this.salario = salario;
        this.status = status;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public int getIdPessoa() {
        return pessoa.getIdPessoa();
    }

    public Endereco getEndereco() {
        return pessoa.getEndereco();
    }

    public void setEndereco(Endereco endereco) {
        pessoa.setEndereco(endereco);
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
