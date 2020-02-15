package com.app.budega.controller;

import com.app.budega.dao.DependenteDAO;
import com.app.budega.model.Dependente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListarDependenteController implements Initializable {

    @FXML
    private TableView<Dependente> tabelaDependente;

    @FXML
    private TableColumn<Dependente, String> colunaResponsavel;

    @FXML
    private TableColumn<Dependente, String> colunaNome;

    @FXML
    private TableColumn<Dependente, String> colunaParentesco;

    @FXML
    private TableColumn<Dependente, Boolean> colunaPermissao;


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
        colunaResponsavel.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaParentesco.setCellValueFactory(new PropertyValueFactory<>("parentesco"));
        colunaPermissao.setCellValueFactory(new PropertyValueFactory<>("permissao"));
        tabelaDependente.setItems(atualizaTable());
    }

    private ObservableList<Dependente> atualizaTable() throws SQLException, ClassNotFoundException {
        DependenteDAO dependenteDAO = new DependenteDAO();
        return FXCollections.observableArrayList(dependenteDAO.getDependentes());
    }

}
