package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Caixa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CaixaDao {

    private Conexao conexao;

    public CaixaDao() {
        conexao = new Conexao();
    }

    public boolean salvar(Caixa caixa) throws SQLException,
            ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO caixa(id, valor, datacaixa, descricao, funcionario)" +
                            "VALUES (proximoidcaixa(),?,?,?,?)");
            pstmt.setFloat(1, caixa.getValor());
            pstmt.setDate(2,
                    java.sql.Date.valueOf(caixa.getDataCaixa()));
            pstmt.setString(3, caixa.getDescricao());
            pstmt.setString(4, caixa.getFuncionario());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Caixa buscarPorId(int id) throws SQLException,
            ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(
                    "SELECT * FROM caixa WHERE id = ?");
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                float valor = rs.getFloat("valor");
                LocalDate datacaixa = rs.getDate("datacaixa").toLocalDate();
                String descricao = rs.getString("descricao");
                String funcionario = rs.getString("funcionario");

                return new Caixa(id, valor, datacaixa, descricao,funcionario);
            }
            return null;
        }
    }

    public Set<Caixa> getRegistros() throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM caixa");
            Set<Caixa> caixas = new HashSet<>();

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                float valor = rs.getFloat("valor");
                LocalDate datacaixa = rs.getDate("datacaixa").toLocalDate();
                String descricao = rs.getString("descricao");
                String funcionario = rs.getString("funcionario");

                caixas.add(new Caixa(id, valor, datacaixa, descricao,funcionario));
            }
            return caixas;
        }
    }

    public boolean deletar(Caixa caixa) throws SQLException,
            ClassNotFoundException{
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM caixa WHERE id = ?");
            pstmt.setInt(1, caixa.getId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean atualizar(Caixa caixa) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE caixa SET valor = ?, datacaixa = ?, descricao = ?, funcionario = ? WHERE id = ?");
            pstmt.setFloat(1, caixa.getValor());
            pstmt.setDate(2, java.sql.Date.valueOf(caixa.getDataCaixa()));
            pstmt.setString(3, caixa.getDescricao());
            pstmt.setString(4, caixa.getFuncionario());
            pstmt.setInt(5, caixa.getId());

            return pstmt.executeUpdate() > 0;
        }
    }
}
