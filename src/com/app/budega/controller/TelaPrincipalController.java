package com.app.budega.controller;

import com.app.budega.dao.CaixaDao;
import com.app.budega.model.Caixa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {

    @FXML
    private TableView<Caixa> tabelaCaixa;

    @FXML
    private TableColumn<Caixa, Double> colunaID;

    @FXML
    private TableColumn<Caixa, Float> colunaValor;

    @FXML
    private TableColumn<Caixa, String> colunaFuncionario;

    @FXML
    private TableColumn<Caixa, LocalDateTime> colunaData;

    @FXML
    private TableColumn<Caixa, String> colunaDesc;

    @FXML
    private Pane cardNovaVenda;

    @FXML
    private Label cardNovoRegistro;

    @FXML
    private Pane cardListarVenda;

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

    private void initTable() throws SQLException, ClassNotFoundException {
        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("dataCaixa"));
        colunaDesc.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        tabelaCaixa.setItems(atualizaTable());
    }

    private ObservableList<Caixa> atualizaTable() throws SQLException, ClassNotFoundException {
        CaixaDao caixaDao = new CaixaDao();
        return FXCollections.observableArrayList(caixaDao.getRegistros());
    }
}