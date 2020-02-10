package com.app.budega.model;

import java.util.Objects;

public class AbateDivida {

    private String idAbate;
    private Cliente cliente;
    private Funcionario funcionario;
    private float valor;

    public AbateDivida(String idAbate, Cliente cliente, Funcionario funcionario, float valor) {
        this.idAbate = idAbate;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.valor = valor;
    }

    public String getIdAbate() {
        return idAbate;
    }

    public void setIdAbate(String idAbate) {
        this.idAbate = idAbate;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbateDivida)) return false;
        AbateDivida that = (AbateDivida) o;
        return Objects.equals(getIdAbate(), that.getIdAbate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAbate());
    }

    @Override
    public String toString() {
        return "AbateDivida{" +
                "idAbate='" + idAbate + '\'' +
                ", cliente=" + cliente +
                ", funcionario=" + funcionario +
                ", valor=" + valor +
                '}';
    }
}
