package com.app.budega.controller;

import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Funcionario;
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

public class ListarFuncionarioController implements Initializable {

    @FXML
    private TableView<Funcionario> tabelaFuncionario;

    @FXML
    private TableColumn<Funcionario, String > colunaCpf;

    @FXML
    private TableColumn<Funcionario, String> colunaNome;

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
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tabelaFuncionario.setItems(atualizaTable());
    }

    public ObservableList<Funcionario> atualizaTable() throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return FXCollections.observableArrayList(funcionarioDAO.getFuncionarios());
    }


}


