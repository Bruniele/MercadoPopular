package br.com.fatec;

import br.com.fatec.DAO.CategoriaDAO;
import br.com.fatec.model.Categoria;
import br.com.fatec.persistencia.Banco;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("VW_Menu"));
        
        //Definindo o ícone na scene(cena)
        Image aplicationIcon = new Image(getClass().getResourceAsStream("/br/com/fatec/image/mercadoPopular.png"));
        stage.getIcons().add(aplicationIcon);
        
        //Não deixa maximizar a tela
        stage.setResizable(false);
        
        stage.setTitle("Mercado Popular");
        
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
        //Testar a conexão com o banco
        try {
            Banco.conectar();
            Banco.desconectar();
            System.out.println("Tudo certo!!!");
        }
        catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        
        /*
        //Adicionar uma categoria sem a tela
        CategoriaDAO dao = new CategoriaDAO();
        Categoria c = new Categoria(8, "Limpeza");
        
        try {
            if(dao.insere(c))
                System.out.println("Inclusão OK");
            else 
                System.out.println("Erro na Inclusão");
        }
        catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        */
        
        launch();
    }
}