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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class ListarFuncionarioController implements Initializable {

    @FXML
    private TableView<Funcionario> tabelaFuncionario;

    @FXML
    private TableColumn<Funcionario, String > colunaCpf;

    @FXML
    private TableColumn<Funcionario, String> colunaNome;

    @FXML
    private TextField campoBuscar;

    @FXML
    private Button btnAtualizarFuncionario;

    @Override
    public void initialize(URL url, ResourceBundle rs){
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                try {
                    initTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        },0,5000);
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
     public ObservableList<Funcionario> acaoBuscar() throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        String cpf = campoBuscar.getText();
        return FXCollections.observableArrayList(funcionarioDAO.buscarPorCpf(cpf));

    }

    @FXML
    void deleteFuncionario(ActionEvent event) {
        try{
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
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }catch (NullPointerException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Funcionario");
            alert.setHeaderText("Nenhum funcionario selecionado.");
            alert.setContentText("Selecione uma linha para excluir.");
            alert.show();
        }
    }

    @FXML
    void atualizarFuncionario(ActionEvent event){
        Funcionario funcionario = tabelaFuncionario.getSelectionModel().getSelectedItem();
        if(funcionario != null){
            AtualizarFuncionarioMain atualizarFuncionarioMain = new AtualizarFuncionarioMain(funcionario);
            try {
                atualizarFuncionarioMain.start(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Funcionario");
            alert.setHeaderText("Nenhum funcionario selecionado.");
            alert.setContentText("Selecione uma linha para atualizar.");
            alert.show();
        }

    }
}


