package com.app.budega.App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/CadastroFuncionario.fxml"));
        root.getStylesheets().add(getClass().getResource("../view/style/CadastroFuncionario.css").toExternalForm());
        primaryStage.setTitle("Budega App");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}