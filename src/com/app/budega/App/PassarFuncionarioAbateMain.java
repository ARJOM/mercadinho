package com.app.budega.App;

import com.app.budega.controller.CadastroAbateDividaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PassarFuncionarioAbateMain extends Application {

    public PassarFuncionarioAbateMain(String funcionario) {
        CadastroAbateDividaController.setFuncionario(funcionario);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/CadastroAbateDivida.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Budega App");
        stage.setScene(scene);
        stage.show();
    }
}
