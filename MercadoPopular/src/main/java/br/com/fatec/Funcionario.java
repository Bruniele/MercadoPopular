package br.com.fatec;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Funcionario extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("VW_Funcionario"));

        //Definindo o ícone na scene(cena)
        Image aplicationIcon = new Image(getClass().getResourceAsStream("/br/com/fatec/image/mercadoPopular.png"));
        stage.getIcons().add(aplicationIcon);

        //Não deixa maximizar a tela
        stage.setResizable(false);

        stage.setTitle("Funcionario");

        stage.setScene(scene);
        stage.show();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
