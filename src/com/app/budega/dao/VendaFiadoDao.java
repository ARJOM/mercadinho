package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Venda;
import com.app.budega.model.VendaFiado;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class VendaFiadoDao{
    private Conexao conexao;

    public VendaFiadoDao() {
        conexao = new Conexao();
    }

    public boolean salvar(VendaFiado vendaFiado) throws SQLException, ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO vendafiado(idvenda, cliente, dependente)" +
                            "VALUES (?,?,?)");
            pstmt.setString(1, vendaFiado.getIdvenda());
            pstmt.setString(2, vendaFiado.getCliente());
            pstmt.setString(3, vendaFiado.getDependente());

            return pstmt.executeUpdate() > 0;
        }
    }
}
