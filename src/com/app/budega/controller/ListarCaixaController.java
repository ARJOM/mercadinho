package com.app.budega.controller;

import com.app.budega.dao.CaixaDao;
import com.app.budega.dao.ClienteDao;
import com.app.budega.model.Caixa;
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
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.ResourceBundle;

public class ListarCaixaController implements Initializable {

    @FXML
    private TableView<Caixa> tabelaCaixa;

    @FXML
    private TableColumn<Caixa, Double> colunaId;

    @FXML
    private TableColumn<Caixa, Float> colunaValor;

    @FXML
    private TableColumn<Caixa, LocalDateTime> colunaDataCaixa;

    @FXML
    private TableColumn<Caixa, String> colunaDescricao;

    @FXML
    private TableColumn<Caixa, String> colunaFuncionario;

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
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaDataCaixa.setCellValueFactory(new PropertyValueFactory<>("dataCaixa"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        tabelaCaixa.setItems(atualizaTable());
    }

    private ObservableList<Caixa> atualizaTable() throws SQLException, ClassNotFoundException {
        CaixaDao caixaDao = new CaixaDao();
        return FXCollections.observableArrayList(caixaDao.getRegistros());
    }
}
