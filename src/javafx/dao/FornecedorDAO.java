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

    public boolean inserir(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (nome, cnae, razao_social, cnpj, email, telefone, endereco_fornecedor) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnae());
            stmt.setString(3, fornecedor.getRazaoSocial());
            stmt.setString(4, fornecedor.getCnpj());
            stmt.setString(5, fornecedor.getEmail());
            stmt.setString(6, fornecedor.getTelefone());
            stmt.setInt(7, fornecedor.getIdEndereco());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Fornecedor fornecedor) {
        String sql = "UPDATE fornecedor SET nome=?, cnae=?, razao_social=?, cnpj=?, email=?, telefone=?, endereco_fornecedor=? "
                + "WHERE id_fornecedor=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnae());
            stmt.setString(3, fornecedor.getRazaoSocial());
            stmt.setString(4, fornecedor.getCnpj());
            stmt.setString(5, fornecedor.getEmail());
            stmt.setString(6, fornecedor.getTelefone());
            stmt.setInt(7, fornecedor.getEndereco().getId());
            stmt.setInt(8, fornecedor.getIdFornecedor());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Fornecedor fornecedor) {
        String sql = "DELETE FROM fornecedor WHERE id_fornecedor=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, fornecedor.getIdFornecedor());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int obterUltimoIdInserido() {
        String sql = "SELECT MAX(id_fornecedor) AS ultimo_id FROM fornecedor";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ultimo_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1; // Returns -1 if no ID is found or if there's an error
    }

    public List<Fornecedor> listar() {
        String sql = "SELECT * FROM fornecedor AS f "
                + "JOIN endereco AS e ON f.endereco_fornecedor = e.id_endereco";

        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idFornecedor = rs.getInt("id_fornecedor");
                String nome = rs.getString("nome");
                String cnae = rs.getString("cnae");
                String razaoSocial = rs.getString("razao_social");
                String cnpj = rs.getString("cnpj");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
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
                fornecedor.setCnae(cnae);
                fornecedor.setRazaoSocial(razaoSocial);
                fornecedor.setCnpj(cnpj);
                fornecedor.setEmail(email);
                fornecedor.setTelefone(telefone);
                fornecedor.setEndereco(endereco);

                fornecedores.add(fornecedor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fornecedores;
    }

}
