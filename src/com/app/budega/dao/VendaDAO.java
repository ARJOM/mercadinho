package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Venda;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class VendaDAO {
    private Conexao conexao;

    public VendaDAO() {
        conexao = new Conexao();
    }

    public boolean salvar(Venda venda) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO venda(idvenda, funcionario, total, datavenda)" +
                            "VALUES (proximoidvenda(),?,?,?)");
            pstmt.setString(1, venda.getFuncionario());
            pstmt.setFloat(2, venda.getTotal());
            pstmt.setDate(3, Date.valueOf(venda.getData()));

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean atualizar(Venda venda) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE venda SET funcionario = ?, total = ?, datavenda = ? WHERE idvenda = ?");
            pstmt.setString(1, venda.getFuncionario());
            pstmt.setFloat(2, venda.getTotal());
            pstmt.setDate(3, Date.valueOf(venda.getData()));
            pstmt.setString(4, venda.getIdVenda());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(Venda venda) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM venda WHERE idvenda = ?");
            pstmt.setString(1, venda.getIdVenda());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Venda buscarPorVenda(String idvenda) throws SQLException,
            ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM venda WHERE idvenda = ?");
            pstmt.setString(1, idvenda);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String funcionario = rs.getString("funcionario");
                Float total = rs.getFloat("total");
                LocalDate datavenda = rs.getDate("datavenda").toLocalDate();

                return new Venda(idvenda, funcionario, total, datavenda);
            }
            return null;
        }
    }

    private Set<Venda> getVenda() throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM venda"
            );

            Set<Venda> vendas = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String idvenda = rs.getString("idvenda");
                String funcionario = rs.getString("funcionario");
                Float total = rs.getFloat("total");
                LocalDate datavenda = rs.getDate("datavenda").toLocalDate();
                vendas.add(new Venda(idvenda, funcionario, total, datavenda));
            }
            return vendas;
        }
    }
}
