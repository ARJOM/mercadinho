package com.app.budega.model;

import java.time.LocalDate;
import java.util.Objects;

public class Pedido {

    private String idPedido;
    private LocalDate dataCompra;
    private float valorCompra;
    private Fornecedor fornecedor;

    public Pedido(String idPedido, LocalDate dataCompra, float valorCompra, Fornecedor fornecedor) {
        this.idPedido = idPedido;
        this.dataCompra = dataCompra;
        this.valorCompra = valorCompra;
        this.fornecedor = fornecedor;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(getIdPedido(), pedido.getIdPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPedido());
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido='" + idPedido + '\'' +
                ", dataCompra=" + dataCompra +
                ", valorCompra=" + valorCompra +
                ", fornecedor=" + fornecedor +
                '}';
    }
}
