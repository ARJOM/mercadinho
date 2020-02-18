package com.app.budega.controller;

import com.app.budega.dao.ProdutoDAO;
import com.app.budega.model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.MaskTextField;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CadastroProdutoController implements Initializable {

    private ProdutoDAO produtoDAO;

    @FXML
    private MaskTextField campoCodBarras;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoPreco;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Label btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        campoCodBarras.setMask("NNNNNNNNNNNNNNNNNNNN");
    }

    public void acaoCadastrar(ActionEvent actionEvent){

        if (campoCodBarras.getText().equals("") || campoNome.getText().equals("") || campoPreco.getText().equals("")
        || campoQuantidade.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Produto");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");

            alert.showAndWait();
        } else {

            produtoDAO = new ProdutoDAO();

            String codBarras = campoCodBarras.getText();
            String nome = campoNome.getText();
            float preco = Float.parseFloat(campoPreco.getText());
            int quantidade = Integer.parseInt(campoQuantidade.getText());

            Produto produto = new Produto(codBarras, nome, preco, quantidade);

            try{
                if(produtoDAO.salvar(produto)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cadastro de Produto");
                    alert.setHeaderText("Produto cadastrado com sucesso.");
                    alert.showAndWait();

                    Stage stageEncerrar = (Stage) btnCadastrar.getScene().getWindow();
                    stageEncerrar.close();
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cadastro de Produto");
                    alert.setHeaderText("Erro ao cadastrar.");
                    alert.setContentText("Por favor tente novamente.");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Produto");
                alert.setHeaderText("Erro ao cadastrar");
                alert.setContentText("Produto já cadastrado");
                alert.showAndWait();
            } catch (ClassNotFoundException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cadastro de Produto");
                alert.setHeaderText("Erro ao cadastrar");
                alert.setContentText("Erro desconhecido");
                alert.showAndWait();
            }
        }
    }

    public void acaoCancelar(MouseEvent mouseEvent) {
        Stage stageCancelar = (Stage) btnCancelar.getScene().getWindow();
        stageCancelar.close();
    }
}
