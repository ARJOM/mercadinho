package models;

import java.time.LocalDate;
import java.util.Objects;

public class Venda {

    private String idVenda;
    private Funcionario funcionario;
    private float total;
    private LocalDate data;

    public Venda(String idVenda, Funcionario funcionario, float total, LocalDate data) {
        this.idVenda = idVenda;
        this.funcionario = funcionario;
        this.total = total;
        this.data = data;
    }

    public String getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(String idVenda) {
        this.idVenda = idVenda;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venda)) return false;
        Venda venda = (Venda) o;
        return Objects.equals(getIdVenda(), venda.getIdVenda());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVenda());
    }

    @Override
    public String toString() {
        return "Venda{" +
                "idVenda='" + idVenda + '\'' +
                ", funcionario=" + funcionario +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
