package com.app.budega.controller;

import com.app.budega.App.LogarFuncionarioMain;
import com.app.budega.App.PassarFuncionarioAbateMain;
import com.app.budega.App.PassarFuncionarioCaixaMain;
import com.app.budega.dao.CaixaDao;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Caixa;
import com.app.budega.model.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {

    private static String funcionario;

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

    @FXML
    private Label labelFuncionario;

    @Override
    public void initialize(URL url, ResourceBundle rs){
        try {
            System.out.println(funcionario);
            initTable();
            nomeFuncionario();
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

    public static String getFuncionario() {
        return funcionario;
    }

    public static void setFuncionario(String funcionario) {
        TelaPrincipalController.funcionario = funcionario;
    }

    public void abaterDivida(){
        try{
            PassarFuncionarioAbateMain passarFuncionarioAbateMain = new PassarFuncionarioAbateMain(funcionario);
            passarFuncionarioAbateMain.start(new Stage());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Abate de Divida");
            alert.setHeaderText("Não foi possível abrir a janela.");
            alert.setContentText("Tente se autenticar e repita a ação.");
            alert.showAndWait();
        }

    }

    public void registrarCaixa(ActionEvent actionEvent) {
        try{
            PassarFuncionarioCaixaMain passarFuncionarioCaixaMain = new PassarFuncionarioCaixaMain(funcionario);
            passarFuncionarioCaixaMain.start(new Stage());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro de Caixa");
            alert.setHeaderText("Não foi possível abrir a janela.");
            alert.setContentText("Tente se autenticar e repita a ação.");
            alert.showAndWait();
        }
    }

    public void nomeFuncionario() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            Funcionario funcionario = funcionarioDAO.buscarPorCpf(TelaPrincipalController.funcionario);
            labelFuncionario.setText(funcionario.getNome());
        } catch (SQLException | ClassNotFoundException e){
            labelFuncionario.setText("?????????");
        }

    }
}