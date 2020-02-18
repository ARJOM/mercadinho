package com.app.budega.model;

import com.app.budega.dao.ProdutoDAO;

import java.sql.SQLException;
import java.util.Objects;

public class ItemVenda {

    private String idItemVenda;
    private int quantidade;
    private float valorUnitario;
    private float subTotal;
    private String produto;
    private String venda;

    public ItemVenda(String idItemVenda, int quantidade, float valorUnitario, String produto, String venda) throws SQLException, ClassNotFoundException {
        this.idItemVenda = idItemVenda;
        this.quantidade = quantidade;
        this.produto = produto;
        this.venda = venda;
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto1 = produtoDAO.buscarPorCodBarras(produto);
        this.valorUnitario = produto1.getPreco();
        this.subTotal = this.quantidade*this.valorUnitario;
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

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getVenda() {
        return venda;
    }

    public void setVenda(String venda) {
        this.venda = venda;
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
