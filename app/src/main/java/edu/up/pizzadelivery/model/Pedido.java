package edu.up.pizzadelivery.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable {

    private Usuario usuario;
    private List<ItemPedido> itensPedido;
    private FormaPagamento formaPagamento;
    private Endereco endEntrega;
    private Date Data;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Endereco getEndEntrega() {
        return endEntrega;
    }

    public void setEndEntrega(Endereco endEntrega) {
        this.endEntrega = endEntrega;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date data) {
        Data = data;
    }


}