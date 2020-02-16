package com.app.budega.App;

import com.app.budega.controller.AtualizaDependenteController;
import com.app.budega.controller.AtualizaProdutoController;
import com.app.budega.model.Dependente;
import com.app.budega.model.Produto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AtualizarDependenteMain extends Application {

    public AtualizarDependenteMain(Dependente dependenteSetado) {
        AtualizaDependenteController.setDependenteRetornado(dependenteSetado);
    }

    public static void main(String [] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/AtualizaDependente.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Budega App");
        stage.setScene(scene);
        stage.show();
    }

}
