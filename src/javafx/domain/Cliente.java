package javafx.domain;

public class Cliente {
    private Pessoa pessoa;
    private int idCliente;
    private Endereco endereco;
    private int idPessoa;
    
    public Cliente() {
       
    }

    public Cliente(int idPessoa, String nome, String cpf, String email, String telefone, int idCliente,
            Endereco endereco) {
        this.pessoa = new Pessoa(idPessoa, nome, email, cpf, telefone, endereco); // Criar Pessoa sem o objeto Endereco aqui
        this.idPessoa = idPessoa; // Atribuir o idPessoa
        this.idCliente = idCliente;
        this.endereco = endereco; // Atribuir o endereco passado por parâmetro
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
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
}
