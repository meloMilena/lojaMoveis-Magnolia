/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.domain.Endereco;
import javafx.domain.Fornecedor;


public class FornecedorDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
   public List<Fornecedor> listar() {
    String sql = "SELECT * FROM fornecedor AS f " +
                 "JOIN endereco AS e ON f.endereco_fornecedor = e.id_endereco";

    List<Fornecedor> fornecedores = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int idFornecedor = rs.getInt("id_fornecedor");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String cnpj = rs.getString("cnpj");
            String cnae = rs.getString("cnae");
            String telefone = rs.getString("telefone");
            String razaoSocial = rs.getString("razao_social");
            int idEndereco = rs.getInt("id_endereco");
            String rua = rs.getString("rua");
            String bairro = rs.getString("bairro");
            String cep = rs.getString("cep");
            String complemento = rs.getString("complemento");
            int numero = rs.getInt("numero");

            Endereco endereco = new Endereco();
            endereco.setId(idEndereco);
            endereco.setRua(rua);
            endereco.setBairro(bairro);
            endereco.setCep(cep);
            endereco.setNumero(numero);
            endereco.setComplemento(complemento);

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setIdFornecedor(idFornecedor);
            fornecedor.setNome(nome);
            fornecedor.setEmail(email);
            fornecedor.setCnpj(cnpj);
            fornecedor.setCnae(cnae);
            fornecedor.setTelefone(telefone);
            fornecedor.setRazaoSocial(razaoSocial);
            fornecedor.setEndereco(endereco);

            fornecedores.add(fornecedor);
        }
    } catch (SQLException ex) {
        Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return fornecedores;
}


}
