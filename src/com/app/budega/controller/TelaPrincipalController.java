package com.app.budega.controller;

import com.app.budega.App.*;
//import com.app.budega.App.LogarFuncionarioMain;
import com.app.budega.App.PassarFuncionarioAbateMain;
import com.app.budega.App.PassarFuncionarioCaixaMain;
import com.app.budega.dao.CaixaDao;
import com.app.budega.dao.FuncionarioDAO;
import com.app.budega.model.Caixa;
import com.app.budega.model.Funcionario;
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

import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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

    public void nomeFuncionario() {
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            Funcionario funcionario = funcionarioDAO.buscarPorCpf(TelaPrincipalController.funcionario);
            labelFuncionario.setText(funcionario.getNome());
        } catch (SQLException | ClassNotFoundException e){
            labelFuncionario.setText("?????????");
        }

    }

    public void novoCliente(javafx.event.ActionEvent event) {
        CadastroClienteMain cadastroClienteMain = new CadastroClienteMain();
        try {
            cadastroClienteMain.start(new Stage());
            Stage stageNovoCliente = (Stage) btnCadastrarCliente.getScene().getWindow();
            stageNovoCliente.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoFuncionario(ActionEvent event) {
        CadastrarFuncionarioMain cadastrarFuncionarioMain = new CadastrarFuncionarioMain();
        try {
            cadastrarFuncionarioMain.start(new Stage());
            Stage stageNovoFuncionario = (Stage) btnCadastrarFuncionario.getScene().getWindow();
            stageNovoFuncionario.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoDependente(ActionEvent event) {
        CadastrarDependentesMain cadastrarDependentesMain = new CadastrarDependentesMain();
        try {
            cadastrarDependentesMain.start(new Stage());
            Stage stageNovoDependente = (Stage) btnCadastrarDependentes.getScene().getWindow();
            stageNovoDependente.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoProduto(ActionEvent event) {
        CadastrarProdutoMain cadastrarProdutoMain = new CadastrarProdutoMain();
        try {
            cadastrarProdutoMain.start(new Stage());
            Stage stageNovoProduto = (Stage) btnCadastrarProduto.getScene().getWindow();
            stageNovoProduto.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoFornecedor(ActionEvent event) {
        CadastrarFornecedorMain cadastrarFornecedorMain = new CadastrarFornecedorMain();
        try {
            cadastrarFornecedorMain.start(new Stage());
            Stage stageNovoFornecedor = (Stage) btnCadastrarFornecedor.getScene().getWindow();
            stageNovoFornecedor.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarClientes(ActionEvent event) {
        ListarClientesMain listarClientesMain = new ListarClientesMain();
        try {
            listarClientesMain.start(new Stage());
            Stage stageGerenciarClientes = (Stage) btnGerenciarClientes.getScene().getWindow();
            stageGerenciarClientes.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarFuncionarios(ActionEvent event) {
        ListarFuncionariosMain listarFuncionariosMain = new ListarFuncionariosMain();
        try {
            listarFuncionariosMain.start(new Stage());
            Stage stageGerenciarFuncionarios = (Stage) btnGerenciarFuncionarios.getScene().getWindow();
            stageGerenciarFuncionarios.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarDependentes(ActionEvent event) {
        ListarDependentesMain listarDependentesMain = new ListarDependentesMain();
        try {
            listarDependentesMain.start(new Stage());
            Stage stageGerenciarDependentes = (Stage) btnGerenciarDependentes.getScene().getWindow();
            stageGerenciarDependentes.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarProdutos(ActionEvent event) {
        ListarProdutosMain listarProdutosMain = new ListarProdutosMain();
        try {
            listarProdutosMain.start(new Stage());
            Stage stageGerenciarProduto = (Stage) btnGerenciarProdutos.getScene().getWindow();
            stageGerenciarProduto.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerenciarFornecedores(ActionEvent event) {
        ListarFornecedoresMain listarFornecedoresMain = new ListarFornecedoresMain();
        try {
            listarFornecedoresMain.start(new Stage());
            Stage stageGerenciarFornecedores = (Stage) btnGerenciarFornecedores.getScene().getWindow();
            stageGerenciarFornecedores.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void novoRegistro(MouseEvent mouseEvent) {
        CadastroCaixaMain cadastroCaixaMain = new CadastroCaixaMain();
        try {
            cadastroCaixaMain.start(new Stage());
            Stage stageNovoRegistro = (Stage) cardNovoRegistro.getScene().getWindow();
            stageNovoRegistro.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}