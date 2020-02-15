package com.app.budega.controller;

import com.app.budega.App.Main;
import com.app.budega.dao.ProdutoDAO;
import com.app.budega.model.Funcionario;
import com.app.budega.model.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AtualizaProdutoController implements Initializable {

    private static Produto produtoRetornado;

    @FXML
    private TextField campoNome;

    @FXML
    private Button btnAtualizaProduto;

    @FXML
    private Label btnCancelar;

    @FXML
    private TextField campoPreco;

    @FXML
    private TextField campoQuantidade;

    @FXML
    private Label labelCodBarras;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProduto();
    }

    @FXML
    public void acaoAtualizar(){
        if (campoNome.getText().equals("") || campoPreco.getText().equals("")
                || campoQuantidade.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Produto");
            alert.setHeaderText("Todos os Campos com * devem ser preenchidos.");
            alert.setContentText("Verifique os campos e tente novamente.");
            alert.showAndWait();
        } else {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            String nome = campoNome.getText();
            float preco = Float.parseFloat(campoPreco.getText());
            int quantidade = Integer.parseInt(campoQuantidade.getText());

            Produto produto = new Produto(produtoRetornado.getCodBarras(), nome, preco, quantidade);

            try {
                if (produtoDAO.atualizar(produto)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Atualização de Produto");
                    alert.setHeaderText("Dados atualizados com sucesso.");
                    alert.setContentText(labelCodBarras.getText());

                    Optional<ButtonType> resultado = alert.showAndWait();
                    if (resultado.get() == ButtonType.OK){
                        Main main = new Main();
                    }
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atualização de Produto");
                alert.setHeaderText("Não foi possível atualizar o produto.");
                alert.setContentText(labelCodBarras.getText());

                alert.showAndWait();
            } catch (ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atualização de Produto");
                alert.setHeaderText("Erro desconhecido");
                alert.setContentText("Tente novamente.");

                alert.showAndWait();
            }
        }
    }

    public static void setProdutoRetornado(Produto produtoRetornado){
        AtualizaProdutoController.produtoRetornado = produtoRetornado;
    }

    public void setProduto(){
        labelCodBarras.setText(produtoRetornado.getCodBarras());
        campoNome.setText(produtoRetornado.getNome());
        campoPreco.setText(String.valueOf(produtoRetornado.getPreco()));
        campoQuantidade.setText(String.valueOf(produtoRetornado.getQuantidade()));
    }
}
