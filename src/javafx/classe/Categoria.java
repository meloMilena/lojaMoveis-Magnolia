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
public class Categoria {
    private int idCategoria;
    private String descricao;
    private String nome;

    public Categoria(int idCategoria, String descricao, String nome) {
        this.idCategoria = idCategoria;
        this.descricao = descricao;
        this.nome = nome;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
