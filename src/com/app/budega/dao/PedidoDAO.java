package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Pedido;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PedidoDAO {

    private Conexao conexao;

    public PedidoDAO() {
        conexao = new Conexao();
    }

    public boolean salvar(Pedido pedido) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO pedido(idpedido, datacompra, valorcompra, fornecedor)" +
                            "VALUES (?,?,?,?)");
            pstmt.setString(1, pedido.getIdPedido());
            pstmt.setString(2, String.valueOf(pedido.getDataCompra()));
            pstmt.setFloat(3, pedido.getValorCompra());
            pstmt.setString(4, pedido.getFornecedor());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean atualizar(Pedido pedido) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE pedido SET datacompra = ?, valorcompra = ?, fornecedor = ? WHERE idpedido = ?");
            pstmt.setString(1, String.valueOf(pedido.getDataCompra()));
            pstmt.setFloat(2, pedido.getValorCompra());
            pstmt.setString(3, pedido.getFornecedor());
            pstmt.setString(4, pedido.getIdPedido());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(Pedido pedido) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM pedido WHERE idpedido = ?");
            pstmt.setString(1, pedido.getIdPedido());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Pedido buscarPorId(String idpedido) throws SQLException,
            ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM pedido WHERE idpedido = ?");
            pstmt.setString(1, idpedido);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                LocalDate datacompra = LocalDate.parse(String.valueOf(rs.getDate("datacompra")));
                Float valorcompra = rs.getFloat("valorcompra");
                String fornecedor = rs.getString("fornecedor");

                return new Pedido(idpedido, datacompra, valorcompra, fornecedor);
            }
            return null;
        }
    }

    public Set<Pedido> getPedidos() throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM pedido"
            );

            Set<Pedido> pedidos = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String idpedido = rs.getString("idpedido");
                LocalDate datacompra = LocalDate.parse(rs.getString("datacompra"));
                Float valorcompra = rs.getFloat("valorcompra");
                String fornecedor = rs.getString("fornecedor");
                pedidos.add(new Pedido(idpedido, datacompra, valorcompra, fornecedor));
            }
            return pedidos;
        }
    }
}
