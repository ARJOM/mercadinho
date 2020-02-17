package com.app.budega.controller;

import com.app.budega.dao.FornecedorDAO;
import com.app.budega.model.Fornecedor;
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

public class ListarFornecedorController implements Initializable {

    @FXML
    private TableView<Fornecedor> tabelaFornecedor;

    @FXML
    private TableColumn<Fornecedor, String > colunaCnpj;

    @FXML
    private TableColumn<Fornecedor, String> colunaNome;

    @FXML
    private TableColumn<Fornecedor, String> colunaContato;

    @Override
    public void initialize(URL url, ResourceBundle rs){
        try {
            initTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initTable() throws SQLException, ClassNotFoundException {
        colunaCnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaContato.setCellValueFactory(new PropertyValueFactory("telefone"));
        tabelaFornecedor.setItems(atualizaTable());
    }

    public ObservableList<Fornecedor> atualizaTable() throws SQLException, ClassNotFoundException {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        return FXCollections.observableArrayList(fornecedorDAO.getFornecedor());
    }

}
