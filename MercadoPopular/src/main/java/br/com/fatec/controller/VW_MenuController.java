/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import java.net.URL;
import br.com.fatec.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruni
 */
public class VW_MenuController implements Initializable {

    @FXML
    private Button btnCliente;
    @FXML
    private Button btnProduto;
    @FXML
    private Button btnFornecedor;
    @FXML
    private Button btnEstoque;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCliente.setGraphic(new ImageView("/br/com/fatec/icons/iconeMenuCliente.png"));
        btnProduto.setGraphic(new ImageView("/br/com/fatec/icons/iconeMenuProduto.png"));
        btnFornecedor.setGraphic(new ImageView("/br/com/fatec/icons/iconeMenuFornecedor.png"));
        btnEstoque.setGraphic(new ImageView("/br/com/fatec/icons/iconeMenuEstoque.png"));
    }    

    @FXML
    private void btnCliente_Click(ActionEvent event) {
        Cliente open = new Cliente();
        try {
            open.start(new Stage());
            Stage stage = (Stage) btnCliente.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!!");
            alert.setContentText("Erro ao encontrar a pagina");
        }
    }

    @FXML
    private void btnProduto_Click(ActionEvent event) {
        Produto open = new Produto();
        try {
            open.start(new Stage());
            Stage stage = (Stage) btnProduto.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!!");
            alert.setContentText("Erro ao encontrar a pagina");
            alert.show();
        }
    }

    @FXML
    private void btnFornecedor_Click(ActionEvent event) {
        Fornecedor open = new Fornecedor();
        try {
            open.start(new Stage());
            Stage stage = (Stage) btnFornecedor.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!!");
            alert.setContentText("Erro ao encontrar a pagina");
        }
    }

    @FXML
    private void btnEstoque_Click(ActionEvent event) {
        Estoque open = new Estoque();
        try {
            open.start(new Stage());
            Stage stage = (Stage) btnEstoque.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!!");
            alert.setContentText("Erro ao encontrar a pagina");
        }
    }
    
}
