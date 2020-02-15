package com.app.budega.controller;

import com.app.budega.dao.CaixaDao;
import com.app.budega.model.Caixa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CadastroCaixaController implements Initializable {

    @FXML
    private TextField campoValor;

    @FXML
    private TextField campoDescricao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void acaoRegistrar(ActionEvent actionEvent){
        if (campoValor.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro de Caixa");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        } else{

            CaixaDao caixaDao = new CaixaDao();

            float valor = Float.parseFloat(campoValor.getText());
            String descricao = campoDescricao.getText();

            LocalDate data = LocalDate.now();

            // TODO pegar cpf do funcionario logado

            Caixa caixa = new Caixa(0, valor, data, descricao, null);

            try{
                if (caixaDao.salvar(caixa)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registro do Caixa");
                    alert.setHeaderText("Registro realizado com sucesso.");
                    alert.showAndWait();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Registro do Caixa");
                    alert.setHeaderText("Erro ao registrar.");
                    alert.setContentText("Por favor tente novamente.");
                    alert.showAndWait();
                }
            } catch (SQLException | ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Produto");
                alert.setHeaderText("Erro ao cadastrar");
                alert.setContentText("Erro desconhecido");
                alert.showAndWait();
            }
        }
    }

}
