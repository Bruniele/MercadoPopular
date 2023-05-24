/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.Menu;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruni
 */
public class VW_EstoqueController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox<?> cbxMarca;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private ComboBox<?> cbxCategoria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBuscar.setGraphic(new ImageView("/br/com/fatec/icons/iconePesquisar.png"));
        btnVoltar.setGraphic(new ImageView("/br/com/fatec/icons/iconeVoltar.png"));
    }    

    @FXML
    private void btnVoltar_Click(ActionEvent event) {
        Menu open = new Menu();
        try {
            open.start(new Stage());
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!!");
            alert.setContentText("Erro ao encontrar a pagina");
            alert.show();
        }
    }

    @FXML
    private void btnBuscar_Click(ActionEvent event) {
    }
    
}
