package models;

import java.util.Objects;

public class ItemVenda {

    private String idItemVenda;
    private int quantidade;
    private float valorUnitario;
    private float subTotal;
    private Produto produto;

    public ItemVenda(String idItemVenda, int quantidade, float valorUnitario, Produto produto) {
        this.idItemVenda = idItemVenda;
        this.quantidade = quantidade;
        this.valorUnitario = produto.getPreco();
        this.subTotal = this.quantidade*this.valorUnitario;
        this.produto = produto;
    }

    public String getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(String idItemVenda) {
        this.idItemVenda = idItemVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemVenda)) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return Objects.equals(getIdItemVenda(), itemVenda.getIdItemVenda());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdItemVenda());
    }

    @Override
    public String toString() {
        return "ItemVenda{" +
                "idItemVenda='" + idItemVenda + '\'' +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                ", subTotal=" + subTotal +
                ", produto=" + produto +
                '}';
    }
}
