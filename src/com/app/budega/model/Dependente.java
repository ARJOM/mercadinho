package com.app.budega.model;
import java.util.Objects;

public class Dependente {

    private Cliente responsavel;
    private String nome;
    private String parentesco;
    private Boolean permissao;

    public Dependente(Cliente responsavel, String nome, String parentesco, Boolean permissao) {
        this.responsavel = responsavel;
        this.nome = nome;
        this.parentesco = parentesco;
        this.permissao = permissao;
    }

    public Cliente getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Cliente responsavel) {
        this.responsavel = responsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Boolean getPermissao() {
        return permissao;
    }

    public void setPermissao(Boolean permissao) {
        this.permissao = permissao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependente)) return false;
        Dependente that = (Dependente) o;
        return Objects.equals(getResponsavel(), that.getResponsavel()) &&
                Objects.equals(getNome(), that.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResponsavel(), getNome());
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "responsavel=" + responsavel +
                ", nome='" + nome + '\'' +
                ", parentesco='" + parentesco + '\'' +
                ", permissao=" + permissao +
                '}';
    }
}
