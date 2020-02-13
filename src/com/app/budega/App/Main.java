package com.app.budega.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/ListarFuncionarios.fxml"));
        root.getStylesheets().add(getClass().getResource("../view/style/Main.css").toExternalForm());
        primaryStage.setTitle("Budega App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

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
