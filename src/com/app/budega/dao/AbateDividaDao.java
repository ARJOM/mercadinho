package com.app.budega.dao;

import com.app.budega.connection.Conexao;
import com.app.budega.model.AbateDivida;
import com.app.budega.model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
