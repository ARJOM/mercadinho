package com.app.budega.controller;

import com.app.budega.App.AtualizarProdutoMain;
import com.app.budega.dao.ProdutoDAO;
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
import util.MaskTextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ListarProdutoController implements Initializable {

    @FXML
    private TableView<Produto> tabelaProdutos;

    @FXML
    private TableColumn<Produto, String> colunaCodBarras;

    @FXML
    private TableColumn<Produto, String> colunaNome;

    @FXML
    private TableColumn<Produto, Float> colunaPreco;

    @FXML
    private TableColumn<Produto, Double> colunaQuantidade;

    @FXML
    private MaskTextField campoBuscarCodBarras;

    private ProdutoDAO produtoDAO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        campoBuscarCodBarras.setMask("NNNNNNNNNNNNNNNNNNNN");
    }

    public void initTable() throws SQLException, ClassNotFoundException {
        colunaCodBarras.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tabelaProdutos.setItems(atualizaTable());
    }

    public ObservableList<Produto> atualizaTable() throws SQLException, ClassNotFoundException {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return FXCollections.observableArrayList(produtoDAO.getProdutos());
    }

    @FXML
    public void atualizarProduto(ActionEvent actionEvent){
        try{
            Produto produto = tabelaProdutos.getSelectionModel().getSelectedItem();
            AtualizarProdutoMain atualizarProdutoMain = new AtualizarProdutoMain(produto);
            atualizarProdutoMain.start(new Stage());
        }catch(NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Produto");
            alert.setHeaderText("Nenhum produto selecionado.");
            alert.setContentText("Selecione uma linha para atualizar.");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Listar Produto");
            alert.setHeaderText("Erro ao abrir a janela de atualização.");
            alert.setContentText("Tente novamente mais tarde.");
            alert.show();
        }
    }

    @FXML
    public void deleteProduto(ActionEvent actionEvent){
        try{
            Produto produto = tabelaProdutos.getSelectionModel().getSelectedItem();

            produtoDAO = new ProdutoDAO();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletar Produtos");
            alert.setHeaderText("Deseja realmente excluir ?");
            alert.setContentText(produto.getNome());

            Optional<ButtonType> resultado = alert.showAndWait();

            if (resultado.get() == ButtonType.OK){
                produtoDAO.deletar(produto);
                initTable();
            }
        } catch (SQLException | ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Listar Produto");
            alert.setHeaderText("Um erro desconhecido ocorreu.");
            alert.setContentText("Tente novamente mais tarde");
            alert.show();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Listar Produtos");
            alert.setHeaderText("Nenhum produto selecionado.");
            alert.setContentText("Selecione uma linha para excluir.");
            alert.show();
        }

    }

    public void buscarCodBarras(ActionEvent event) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        String codBarras = campoBuscarCodBarras.getText();

        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Produto");
            alert.setHeaderText("Nome: " + produtoDAO.buscarPorCodBarras(codBarras).getNome()+
                    "\n" + "Quantidade: " + produtoDAO.buscarPorCodBarras(codBarras).getQuantidade()+
                    "\n" + "Preço Unitario: " + produtoDAO.buscarPorCodBarras(codBarras).getPreco());
            alert.setContentText("Busca concluida.");
            alert.show();

            campoBuscarCodBarras.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Buscar Produto");
            alert.setHeaderText("A busca não pode ser excutada com o campo vazio.");
            alert.setContentText("Verifique o campo e tente novamente.");
            alert.show();
        }
    }
}
