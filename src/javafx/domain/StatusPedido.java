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
public class StatusPedido {
    private int idStatusPedido;
    private String statusPedido;

    public StatusPedido(int idStatusPedido, String statusPedido) {
        this.idStatusPedido = idStatusPedido;
        this.statusPedido = statusPedido;
    }

    public StatusPedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdStatusPedido() {
        return idStatusPedido;
    }

    public void setIdStatusPedido(int idStatusPedido) {
        this.idStatusPedido = idStatusPedido;
    }

    public String getstatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }
}
