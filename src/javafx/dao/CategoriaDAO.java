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
import javafx.domain.Categoria;

/**
 *
 * @author DevChefMio
 */
public class CategoriaDAO {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
public List<Categoria> listar() {
    String sql = "SELECT * FROM categoria";

    List<Categoria> retorno = new ArrayList<>();
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet resultado = stmt.executeQuery();
        
         while (resultado.next()) {
                Categoria categoria = new Categoria();
                
                categoria.setIdCategoria(resultado.getInt("id_categoria"));
                categoria.setDescricao(resultado.getString("descricao"));
                categoria.setNome(resultado.getString("nome"));

                retorno.add(categoria);
            }
    } catch (SQLException ex) {
        Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return retorno;
}

    
}
