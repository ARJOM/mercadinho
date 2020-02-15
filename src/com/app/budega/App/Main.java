package com.app.budega.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;
    private static Scene mainScene;
    private static Scene mainAtualizaFuncionaro;
    private static Scene mainListarFuncionaro;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        stage = primaryStage;
//
//        Parent fxmlListar = FXMLLoader.load(getClass().getResource("../view/fxml/CadastroDependente.fxml"));
//        mainListarFuncionaro = new Scene(fxmlListar);
//
//        Parent fxmlAtualizaFuncionario = FXMLLoader.load(getClass().getResource("../view/fxml/AtualizaFuncionario.fxml"));
//        mainAtualizaFuncionaro = new Scene(fxmlAtualizaFuncionario);
//
//        primaryStage.setScene(mainListarFuncionaro);
//        primaryStage.show();
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/CadastroDependente.fxml"));
        root.getStylesheets().add(getClass().getResource("../view/style/Main.css").toExternalForm());
        primaryStage.setTitle("Budega App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void mudarTelas(String src){
        switch (src){
            case "listarFuncionario":
                stage.setScene(mainListarFuncionaro);
                break;
            case "atualizarFuncionario":
                stage.setScene(mainAtualizaFuncionaro);
        }
    }

    public static void main(String[] args) {
        launch(args);

//        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
//
//        try{
//            funcionarioDAO.Cadastrar(new Funcionario("078.680.933-74","Admin","Admin1234"));
//        }catch (SQLException e){
//            e.printStackTrace();
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }
    }
}
