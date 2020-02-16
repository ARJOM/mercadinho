package com.app.budega.controller;

import com.app.budega.App.Main;
import com.app.budega.dao.FornecedorDAO;
import com.app.budega.model.Fornecedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import util.MaskTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AtualizaFornecedorController implements Initializable {

    private static Fornecedor fornecedorRetornado;

    @FXML
    private TextField campoNome;

    @FXML
    private MaskTextField campoContato;

    @FXML
    private Label labelCnpj;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO colocar a mascara sem bloquear o teclado
//        campoContato.setMask("SNNSNNNNN-NNNN");
        setFornecedor();
    }

    public static void setFornecedorRetornado(Fornecedor fornecedorSetado) {
        AtualizaFornecedorController.fornecedorRetornado = fornecedorSetado;
    }

    public void acaoAtualizar(ActionEvent actionEvent) {
        if(campoContato.getText().equals("") || campoNome.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atualização de Fornecedor");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        } else {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            String nome = campoNome.getText();
            String telefone = campoContato.getText();

            Fornecedor fornecedor = new Fornecedor(fornecedorRetornado.getCnpj(), nome, telefone);

            try{
                if(fornecedorDAO.atualizar(fornecedor)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Atualização de Fornecedor");
                    alert.setHeaderText("Dados atualizados com sucesso.");
                    alert.setContentText(labelCnpj.getText());

                    Optional<ButtonType> resultado = alert.showAndWait();
                    if (resultado.get() == ButtonType.OK){
                        Main main = new Main();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Atualização de Fornecedor");
                    alert.setHeaderText("Um erro impediu os dados de serem atualizados.");
                    alert.setContentText("Tente novamente.");
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atualização de Fornecedor");
                alert.setHeaderText("Não foi possível atualizar o produto.");
                alert.setContentText(labelCnpj.getText());

                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atualização de Fornecedor");
                alert.setHeaderText("Erro desconhecido");
                alert.setContentText("Tente novamente.");

                alert.showAndWait();
            }
        }
    }

    public void setFornecedor(){
        campoNome.setText(fornecedorRetornado.getNome());
        campoContato.setText(fornecedorRetornado.getTelefone());
        labelCnpj.setText(fornecedorRetornado.getCnpj());
    }

}
