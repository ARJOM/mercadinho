package com.app.budega.model;

public class Parentesco {
    private int idParentesco;
    private String parentesco;

    public Parentesco(int idParentesco, String parentesco) {
        this.idParentesco = idParentesco;
        this.parentesco = parentesco;
    }

    public int getIdParentesco() {
        return idParentesco;
    }

    public void setIdParentesco(int idParentesco) {
        this.idParentesco = idParentesco;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    @Override
    public String toString() {
        return getParentesco();
    }
}
