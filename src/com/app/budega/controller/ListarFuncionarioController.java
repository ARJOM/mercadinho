package com.app.budega.controller;

import com.app.budega.App.Main;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.App.AtualizarFuncionarioMain;
import com.app.budega.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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

    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();


    public void initTable() throws SQLException, ClassNotFoundException {
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        tabelaFuncionario.setItems(atualizaTable());
    }

    public ObservableList<Funcionario> atualizaTable() throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return FXCollections.observableArrayList(funcionarioDAO.getFuncionarios());
    }

    @FXML
    void deleteFuncionario(ActionEvent event) {
        Funcionario funcionario = tabelaFuncionario.getSelectionModel().getSelectedItem();
        String cpf = funcionario.getCpf();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar Funcionarios");
            alert.setHeaderText("Deseja realmente excluir ?");
            alert.setContentText(funcionario.getNome());

            Optional<ButtonType> resultado = alert.showAndWait();
            if (resultado.get() == ButtonType.OK){
                funcionarioDAO.deleteFuncionario(cpf);
                initTable();
            }else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Deletar Funcionarios");
                alert1.setHeaderText("Nenhum funcionario selecionado.");
                alert1.setContentText("Slecione um selecionado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void atualizarFuncionario(ActionEvent event){
        Funcionario funcionario = tabelaFuncionario.getSelectionModel().getSelectedItem();
        AtualizarFuncionarioMain atualizarFuncionarioMain = new AtualizarFuncionarioMain(funcionario);
        try {
            atualizarFuncionarioMain.start(new Stage());
            Main main = new Main();
        }catch (Exception e){

        }

    }
}


