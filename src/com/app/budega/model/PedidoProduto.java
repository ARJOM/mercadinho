package com.app.budega.model;

import java.util.Objects;

public class PedidoProduto {

    private Produto produto;
    private Pedido pedido;
    private int quantidade;
    private float precoUnitario;

    public PedidoProduto(Produto produto, Pedido pedido, int quantidade, float precoUnitario) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoProduto)) return false;
        PedidoProduto that = (PedidoProduto) o;
        return Objects.equals(getProduto(), that.getProduto()) &&
                Objects.equals(getPedido(), that.getPedido());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduto(), getPedido());
    }

    @Override
    public String toString() {
        return "PedidoProduto{" +
                "produto=" + produto +
                ", pedido=" + pedido +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                '}';
    }
}
