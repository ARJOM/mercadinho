package com.app.budega.model;

import java.time.LocalDate;

public class VendaFiado extends Venda {

    private Cliente cliente;

    public VendaFiado(String idVenda, Funcionario funcionario, float total, LocalDate data, Cliente cliente) {
        super(idVenda, String.valueOf(funcionario), total, data);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "VendaFiado{" +
                "idVenda='" + getIdVenda() + '\'' +
                ", funcionario=" + getFuncionario() +
                ", total=" + getTotal() +
                ", data=" + getData() +
                "cliente=" + cliente +
                '}';
    }
}
