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
                    "INSERT INTO caixa(idcaixa, valor, cliente, descricao, funcionario)" +
                            "VALUES (?,?,?,?,?)");
            pstmt.setInt(1, caixa.getIdCaixa());
            pstmt.setFloat(2, caixa.getValor());
            pstmt.setDate(3,
                    java.sql.Date.valueOf(caixa.getDataCaixa()));
            pstmt.setString(4, caixa.getDescricao());
            pstmt.setString(5, caixa.getFuncionario());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Caixa buscarPorId(int idcaixa) throws SQLException,
            ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(
                    "SELECT * FROM caixa WHERE idcaixa = ?");
            pstm.setInt(1, idcaixa);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                float valor = rs.getFloat("valor");
                LocalDate datacaixa = rs.getDate("datacaixa").toLocalDate();
                String descricao = rs.getString("descricao");
                String funcionario = rs.getString("funcionario");

                return new Caixa(idcaixa, valor, datacaixa, descricao,funcionario);
            }
            return null;
        }
    }

    public Set<Caixa> getAbates() throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM caixa");
            Set<Caixa> caixas = new HashSet<>();

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                int idcaixa = rs.getInt("idcaixa");
                float valor = rs.getFloat("valor");
                LocalDate datacaixa = rs.getDate("datacaixa").toLocalDate();
                String descricao = rs.getString("descricao");
                String funcionario = rs.getString("funcionario");

                caixas.add(new Caixa(idcaixa, valor, datacaixa, descricao,funcionario));
            }
            return caixas;
        }
    }
}
