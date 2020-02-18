package com.app.budega.controller;

import com.app.budega.App.*;
//import com.app.budega.App.LogarFuncionarioMain;
import com.app.budega.App.PassarFuncionarioAbateMain;
import com.app.budega.App.PassarFuncionarioCaixaMain;
import com.app.budega.dao.CaixaDao;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.dao.VendaDAO;
import com.app.budega.model.Caixa;
import com.app.budega.model.Funcionario;
import com.app.budega.model.Venda;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    private Pane cardNovoRegistro;

    @FXML
    private Pane cardListarVenda;

    @FXML
    private Label labelFuncionario;

    @FXML
    private Button btnGerenciarFuncionarios;

    @FXML
    private Button btnCadastrarFuncionario;

    @FXML
    private Button btnGerenciarClientes;

    @FXML
    private Button btnCadastrarCliente;

    @FXML
    private Button btnGerenciarDependentes;

    @FXML
    private Button btnCadastrarDependentes;

    @FXML
    private Button btnGerenciarProdutos;

    @FXML
    private Button btnCadastrarProduto;

    @FXML
    private Button btnGerenciarFornecedores;

    @FXML
    private Button btnCadastrarFornecedor;

    @FXML
    private Label labelData;

    private SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a");

    @Override
    public void initialize(URL url, ResourceBundle rs){
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                try {
                    initTable();
                    nomeFuncionario();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        },0,5000);

        KeyFrame frame = new KeyFrame(Duration.millis(1000),e -> capturaHora());
        Timeline timeLine = new Timeline(frame);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
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

    public void nomeFuncionario() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            Funcionario funcionario = funcionarioDAO.buscarPorCpf(TelaPrincipalController.funcionario);
            labelFuncionario.setText(funcionario.getNome());
        } catch (SQLException | ClassNotFoundException e){
            labelFuncionario.setText("?????????");
        }

    }

    public void novoCliente() {
        CadastroClienteMain cadastroClienteMain = new CadastroClienteMain();
        try {
            cadastroClienteMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoFuncionario() {
        CadastrarFuncionarioMain cadastrarFuncionarioMain = new CadastrarFuncionarioMain();
        try {
            cadastrarFuncionarioMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoDependente() {
        CadastrarDependentesMain cadastrarDependentesMain = new CadastrarDependentesMain();
        try {
            cadastrarDependentesMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoProduto() {
        CadastrarProdutoMain cadastrarProdutoMain = new CadastrarProdutoMain();
        try {
            cadastrarProdutoMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoFornecedor() {
        CadastrarFornecedorMain cadastrarFornecedorMain = new CadastrarFornecedorMain();
        try {
            cadastrarFornecedorMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarClientes() {
        ListarClientesMain listarClientesMain = new ListarClientesMain();
        try {
            listarClientesMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarFuncionarios() {
        ListarFuncionariosMain listarFuncionariosMain = new ListarFuncionariosMain();
        try {
            listarFuncionariosMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarDependentes() {
        ListarDependentesMain listarDependentesMain = new ListarDependentesMain();
        try {
            listarDependentesMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarProdutos() {
        ListarProdutosMain listarProdutosMain = new ListarProdutosMain();
        try {
            listarProdutosMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarFornecedores() {
        ListarFornecedoresMain listarFornecedoresMain = new ListarFornecedoresMain();
        try {
            listarFornecedoresMain.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoRegistro(MouseEvent mouseEvent) {
        try {
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

    public void chamarNovaVenda() {
        try {
            VendaDAO vendaDAO = new VendaDAO();

            String id = vendaDAO.proximoId();

            LocalDate data = LocalDate.now();
            Venda venda = new Venda("0", funcionario, 0, data);
            if(vendaDAO.salvar(venda)) {
                NovaVendaMain novaVendaMain = new NovaVendaMain(id);
                novaVendaMain.start(new Stage());
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Nova Venda");
                alert.setHeaderText("Não foi possível criar uma nova venda.");
                alert.setContentText("Tente se autenticar e repita a ação.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nova Venda");
            alert.setHeaderText("Não foi possível abrir a janela.");
            alert.setContentText("Tente se autenticar e repita a ação.");
            alert.showAndWait();
        }
    }

    private void capturaHora(){
        Date hora = new Date();
        labelData.setText(format.format(hora));
    }
}