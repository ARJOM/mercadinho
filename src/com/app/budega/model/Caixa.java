package com.app.budega.model;

import java.time.LocalDate;
import java.util.Objects;

public class Caixa {

    private int idCaixa;
    private float valor;
    private LocalDate dataCaixa;
    private String descricao;
    private String funcionario;

    public Caixa(int idCaixa, float valor, LocalDate dataCaixa, String descricao, String funcionario) {
        this.idCaixa = idCaixa;
        this.valor = valor;
        this.dataCaixa = dataCaixa;
        this.descricao = descricao;
        this.funcionario = funcionario;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDate getDataCaixa() {
        return dataCaixa;
    }

    public void setDataCaixa(LocalDate dataCaixa) {
        this.dataCaixa = dataCaixa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Caixa)) return false;
        Caixa caixa = (Caixa) o;
        return getIdCaixa() == caixa.getIdCaixa();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCaixa());
    }

    @Override
    public String toString() {
        return "Caixa{" +
                "idCaixa=" + idCaixa +
                ", valor=" + valor +
                ", dataCaixa=" + dataCaixa +
                ", descricao='" + descricao + '\'' +
                ", funcionario=" + funcionario +
                '}';
    }
}
