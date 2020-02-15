package com.app.budega.model;
import javafx.collections.ObservableList;
import javafx.scene.control.ToggleGroup;

import java.util.Objects;

public class Dependente {

    private String id;
    private String responsavel;
    private String nome;
    private String parentesco;
    private Boolean permissao;

    public Dependente(String id, String responsavel, String nome, String parentesco, Boolean permissao) {
        this.id = id;
        this.responsavel = responsavel;
        this.nome = nome;
        this.parentesco = parentesco;
        this.permissao = permissao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
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
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "id='" + id + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", nome='" + nome + '\'' +
                ", parentesco='" + parentesco + '\'' +
                ", permissao=" + permissao +
                '}';
    }
}
