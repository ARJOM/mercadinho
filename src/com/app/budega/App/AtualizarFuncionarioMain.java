package com.app.budega.App;

import com.app.budega.controller.AtualizaFuncionarioController;
import com.app.budega.model.Funcionario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AtualizarFuncionarioMain extends Application {

    public AtualizarFuncionarioMain(Funcionario funcionarioSetado) {
        AtualizaFuncionarioController.setFuncionarioRetornado(funcionarioSetado);
    }

    public static void main(String [] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/AtualizaFuncionario.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Budega App");
        stage.setScene(scene);
        stage.show();
    }

}
