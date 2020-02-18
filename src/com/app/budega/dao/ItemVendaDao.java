package com.app.budega.dao;

import com.app.budega.conexao.Conexao;
import com.app.budega.model.Caixa;
import com.app.budega.model.ItemVenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ItemVendaDao {

    private Conexao conexao;

    public ItemVendaDao() {
        this.conexao = new Conexao();
    }

    public boolean salvar(ItemVenda itemVenda) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO itemvenda(iditemvenda, produto, preco, quantidade, subtotal, idvenda)" +
                            "VALUES (proximoiditemvenda(),?,?,?,0,?)");
            pstmt.setString(1, itemVenda.getProduto());
            pstmt.setFloat(2, itemVenda.getValorUnitario());
            pstmt.setInt(3, itemVenda.getQuantidade());
            pstmt.setString(4, itemVenda.getVenda());

            return pstmt.executeUpdate() > 0;
        }
    }

    public ItemVenda buscarPorId(String id) throws SQLException, ClassNotFoundException {
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT * FROM itemvenda WHERE iditemvenda = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                String produto = rs.getString("produto");
                float preco = rs.getFloat("preco");
                int quantidade = rs.getInt("quantidade");
                String idvenda = rs.getString("idvenda");

                return new ItemVenda(id, quantidade, preco, produto, idvenda);
            }
            return null;
        }
    }

    public Set<ItemVenda> getItens() throws SQLException, ClassNotFoundException{
        try(Connection connection = conexao.getConnection()){
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM itemvenda");
            Set<ItemVenda> itens = new HashSet<>();

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString("iditemvenda");
                float preco = rs.getFloat("preco");
                String produto = rs.getString("produto");
                int quantidade = rs.getInt("quantidade");
                String idvenda = rs.getString("idvenda");

                itens.add(new ItemVenda(id, quantidade, preco, produto, idvenda));
            }
            return itens;
        }
    }

    public boolean deletar(ItemVenda itemVenda) throws SQLException,
            ClassNotFoundException{
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM itemvenda WHERE id = ?");
            pstmt.setString(1, itemVenda.getIdItemVenda());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean atualizar(ItemVenda itemVenda) throws SQLException,
            ClassNotFoundException {
        try (Connection connection = conexao.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE caixa SET produto = ?, preco = ?, quantidade = ?, subtotal = ?, idvenda = ? WHERE iditemvenda = ?");
            pstmt.setString(1, itemVenda.getProduto());
            pstmt.setFloat(2, itemVenda.getValorUnitario());
            pstmt.setInt(3, itemVenda.getQuantidade());
            pstmt.setFloat(4, itemVenda.getSubTotal());
            pstmt.setString(5, itemVenda.getVenda());
            pstmt.setString(6, itemVenda.getIdItemVenda());

            return pstmt.executeUpdate() > 0;
        }
    }

}
