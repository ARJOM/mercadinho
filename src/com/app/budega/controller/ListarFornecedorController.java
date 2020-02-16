package com.app.budega.controller;

import com.app.budega.App.AtualizarFornecedorMain;
import com.app.budega.App.AtualizarProdutoMain;
import com.app.budega.dao.FornecedorDAO;
import com.app.budega.model.Fornecedor;
import com.app.budega.model.Produto;
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

public class ListarFornecedorController implements Initializable {

    @FXML
    private TableView<Fornecedor> tabelaFornecedor;

    @FXML
    private TableColumn<Fornecedor, String > colunaCnpj;

    @FXML
    private TableColumn<Fornecedor, String> colunaNome;

    @FXML
    private TableColumn<Fornecedor, String> colunaContato;

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
        colunaCnpj.setCellValueFactory(new PropertyValueFactory("cnpj"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaContato.setCellValueFactory(new PropertyValueFactory("telefone"));
        tabelaFornecedor.setItems(atualizaTable());
    }

    public ObservableList<Fornecedor> atualizaTable() throws SQLException, ClassNotFoundException {
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        return FXCollections.observableArrayList(fornecedorDAO.getFornecedor());
    }

    public void deleteProduto(ActionEvent actionEvent) {
        try {
            Fornecedor fornecedor = tabelaFornecedor.getSelectionModel().getSelectedItem();

            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar Fornecedor");
            alert.setHeaderText("Deseja realmente excluir ?");
            alert.setContentText(fornecedor.getNome());

            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.get() == ButtonType.OK){
                fornecedorDAO.deletar(fornecedor);
                initTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletar Fornecedor");
            alert.setHeaderText("Um erro desconhecido ocorreu.");
            alert.setContentText("Tente novamente mais tarde");
            alert.show();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Deletar Fornecedor");
            alert.setHeaderText("Nenhum fornecedor selecionado.");
            alert.setContentText("Selecione uma linha para excluir.");
            alert.show();
        }
    }

    public void atualizarProduto(ActionEvent actionEvent) {
        try{
            Fornecedor fornecedor = tabelaFornecedor.getSelectionModel().getSelectedItem();
            AtualizarFornecedorMain atualizarFornecedorMain = new AtualizarFornecedorMain(fornecedor);
            atualizarFornecedorMain.start(new Stage());
        }catch(NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Fornecedores");
            alert.setHeaderText("Nenhum fornecedor selecionado.");
            alert.setContentText("Selecione uma linha para atualizar.");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Listar Fornecedores");
            alert.setHeaderText("Erro ao abrir a janela de atualização.");
            alert.setContentText("Tente novamente mais tarde.");
            alert.show();
        }
    }
}
