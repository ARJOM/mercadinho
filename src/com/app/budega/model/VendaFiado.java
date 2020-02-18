package com.app.budega.model;

import java.time.LocalDate;

public class VendaFiado {

    private String idvenda;
    private String cliente;
    private String dependente;

    public VendaFiado(String idVenda, String cliente, String dependente) {
        this.idvenda = idVenda;
        this.cliente = cliente;
        this.dependente = dependente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDependente() {
        return dependente;
    }

    public String getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(String idvenda) {
        this.idvenda = idvenda;
    }

    public void setDependente(String dependente) {
        this.dependente = dependente;
    }

    @Override
    public String toString() {
        return "VendaFiado{" +
                "idVenda='" + idvenda +
                "cliente=" + cliente +
                "dependente=" + dependente +
                '}';
    }
}
