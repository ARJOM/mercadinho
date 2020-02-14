package com.app.budega.controller;

import com.app.budega.dao.ClienteDao;
import com.app.budega.model.Cliente;
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

public class ListarClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tabelaCliente;

    @FXML
    private TableColumn<Cliente, String> colunaCpf;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaContato;

    @FXML
    private TableColumn<Cliente, Float> colunaDivida;
    

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

    private void initTable() throws SQLException, ClassNotFoundException {
        colunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaContato.setCellValueFactory(new PropertyValueFactory<>("contato"));
        colunaDivida.setCellValueFactory(new PropertyValueFactory<>("divida"));
        tabelaCliente.setItems(atualizaTable());
    }

    private ObservableList<Cliente> atualizaTable() throws SQLException, ClassNotFoundException {
        ClienteDao clienteDao = new ClienteDao();
        return FXCollections.observableArrayList(clienteDao.getClientes());
    }
}
