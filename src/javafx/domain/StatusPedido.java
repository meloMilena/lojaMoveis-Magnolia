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
    private int statusPedido;
    private int pedido;

    public StatusPedido(int idStatusPedido, int statusPedido, int pedido) {
        this.idStatusPedido = idStatusPedido;
        this.statusPedido = statusPedido;
        this.pedido = pedido;
    }


    public StatusPedido() {
    }

    public int getIdStatusPedido() {
        return idStatusPedido;
    }

    public void setIdStatusPedido(int idStatusPedido) {
        this.idStatusPedido = idStatusPedido;
    }

    public int getstatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(int statusPedido) {
        this.statusPedido = statusPedido;
    }
    
       public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }
}
