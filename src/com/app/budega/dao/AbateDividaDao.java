package com.app.budega.dao;

import com.app.budega.connection.Conexao;
import com.app.budega.model.AbateDivida;
import com.app.budega.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AbateDividaDao {

    private Conexao conexao;

    public AbateDividaDao() {
        conexao = new Conexao();
    }

    public boolean salvar(AbateDivida abate) throws SQLException,
            ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO abatedivida(idabate, cliente, funcionario, valor)" +
                            "VALUES (?,?,?,?)");
            pstmt.setString(1, abate.getIdAbate());
            pstmt.setString(2, abate.getCliente());
            pstmt.setString(3, abate.getFuncionario());
            pstmt.setFloat(4, abate.getValor());

            return pstmt.executeUpdate() > 0;
        }
    }

    public AbateDivida buscarPorId(String idabate) throws SQLException,
            ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(
                    "SELECT * FROM abatedivida WHERE idabate = ?");
            pstm.setString(1, idabate);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                String cliente = rs.getString("cliente");;
                String funcionario = rs.getString("funcionario");
                float valor = rs.getFloat("valor");

                return new AbateDivida(idabate, cliente, funcionario, valor);
            }
            return null;
        }
    }

    public Set<AbateDivida> getAbates() throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM abatedivida");
            Set<AbateDivida> abates = new HashSet<>();

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String idAbate = rs.getString("idabate");
                String cliente = rs.getString("cliente");;
                String funcionario = rs.getString("funcionario");
                float valor = rs.getFloat("valor");

                abates.add(new AbateDivida(idAbate, cliente, funcionario, valor));
            }
            return abates;
        }
    }
}
