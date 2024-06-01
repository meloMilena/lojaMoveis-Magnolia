
package javafx.domain;


public class Funcionario {
    private Pessoa pessoa;
    private int idFuncionario;
    private double salario;
    private Endereco endereco;
    private int idPessoa;
    
    public Funcionario(){
    } 
    
    public Funcionario(int idPessoa, String nome, String email, String cpf, String telefone, int idFuncionario,
        double salario, Endereco endereco) {
        this.pessoa = new Pessoa(idPessoa, nome, email, cpf, telefone, endereco);
        this.idFuncionario = idFuncionario;
        this.salario = salario;
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
    
    public void setPessoa(Pessoa pessoa) {
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
    
    @Override
    public String toString() {
        return this.pessoa.getNome(); // Return the product name and supplier name
    }
}
