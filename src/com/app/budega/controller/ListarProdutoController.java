package com.app.budega.controller;

import com.app.budega.dao.ProdutoDAO;
import com.app.budega.model.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListarProdutoController implements Initializable {

    @FXML
    private TableView<Produto> tabelaProdutos;

    @FXML
    private TableColumn<Produto, String> colunaCodBarras;

    @FXML
    private TableColumn<Produto, String> colunaNome;

    @FXML
    private TableColumn<Produto, Double> colunaPreco;

    @FXML
    private TableColumn<Produto, Double> colunaQuantidade;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initTable() throws SQLException, ClassNotFoundException {
        colunaCodBarras.setCellValueFactory(new PropertyValueFactory("codBarras"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
//        colunaPreco.setCellValueFactory(new PropertyValueFactory("preco"));
//        colunaQuantidade.setCellFactory(new PropertyValueFactory("quantidade"));
        tabelaProdutos.setItems(atualizaTable());
    }

    public ObservableList<Produto> atualizaTable() throws SQLException, ClassNotFoundException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return FXCollections.observableArrayList(produtoDAO.getProdutos());
    }
}
