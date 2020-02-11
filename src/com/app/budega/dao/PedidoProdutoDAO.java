package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.PedidoProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PedidoProdutoDAO {
    private Conexao conexao;

    public PedidoProdutoDAO() {
        conexao = new Conexao();
    }

    public boolean salvar(PedidoProduto pedidoProduto) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO pedidoproduto(pedido, produto, quantidade, precounitario)" +
                            "VALUES (?,?,?,?)");
            pstmt.setString(1, pedidoProduto.getPedido());
            pstmt.setString(2, pedidoProduto.getProduto());
            pstmt.setInt(3, pedidoProduto.getQuantidade());
            pstmt.setFloat(4, pedidoProduto.getPrecoUnitario());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean atualizar(PedidoProduto pedidoProduto) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE pedidoproduto SET pedido = ?, produto = ?, quantidade = ?, precounitario = ?");
            pstmt.setString(1, pedidoProduto.getPedido());
            pstmt.setString(2, pedidoProduto.getProduto());
            pstmt.setInt(3, pedidoProduto.getQuantidade());
            pstmt.setFloat(4, pedidoProduto.getPrecoUnitario());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(PedidoProduto pedidoProduto) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM pedidoproduto WHERE pedido = ?");
            pstmt.setString(1, pedidoProduto.getPedido());

            return pstmt.executeUpdate() > 0;
        }
    }

    public PedidoProduto buscarPorPedido(String pedido) throws SQLException,
            ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM pedidoproduto WHERE pedido = ?");
            pstmt.setString(1, pedido);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String produto = rs.getString("produto");
                Integer quantidade = rs.getInt("quantidade");
                Float precounitario = rs.getFloat("precounitario");

                return new PedidoProduto(produto, pedido, quantidade, precounitario);
            }
            return null;
        }
    }

    private Set<PedidoProduto> getPedidoProduto() throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM pedidoproduto"
            );

            Set<PedidoProduto> pedidosProdutos = new HashSet<>();

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String pedido = rs.getString("pedido");
                String produto = rs.getString("produto");
                Integer quantidade = rs.getInt("quantidade");
                Float precounitario = rs.getFloat("precounitario");
                pedidosProdutos.add(new PedidoProduto(pedido, produto, quantidade, precounitario));
            }
            return pedidosProdutos;
        }
    }
}
