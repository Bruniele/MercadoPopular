/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.CategoriaDAO;
import br.com.fatec.DAO.FornecedorDAO;
import br.com.fatec.DAO.ProdutoDAO;
import br.com.fatec.Menu;
import br.com.fatec.Produto;
import br.com.fatec.model.Categoria;
import br.com.fatec.model.Fornecedor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruni
 */
public class VW_ProdutoController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtDescricao;
    @FXML
    private ComboBox<Categoria> cbxCategoria; 
    @FXML
    private ComboBox<Fornecedor> cbxFornecedor;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TextField txtCodigoFornecedor;
    @FXML
    private TextField txtCodigoCategoria;

     
    private Produto produto;
    private Fornecedor fornecedor;
    private Categoria categoria;
    ProdutoDAO produtoDAO = new ProdutoDAO();
    FornecedorDAO fornecedoDAO = new FornecedorDAO();
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    
    private ObservableList<Fornecedor> fornecedores = 
            FXCollections.observableArrayList();
    
    private ObservableList<Categoria> categorias = 
            FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherComboFornecedor();
        cbxFornecedor.setItems(fornecedores);
        
        
        preencherComboCategoria();
        cbxCategoria.setItems(categorias);
        
        btnVoltar.setGraphic(new ImageView("/br/com/fatec/icons/iconeVoltar.png"));
        btnInserir.setGraphic(new ImageView("/br/com/fatec/icons/iconeInserir.png"));
        btnExcluir.setGraphic(new ImageView("/br/com/fatec/icons/iconeExcluir.png"));
        btnAlterar.setGraphic(new ImageView("/br/com/fatec/icons/iconeAlterar.png"));
        btnPesquisar.setGraphic(new ImageView("/br/com/fatec/icons/iconePesquisar.png"));
        
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
    private void btnInserir_Click(ActionEvent event) {
    }

    @FXML
    private void btnExcluir_Click(ActionEvent event) {
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) {
    }
 
    
    
    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }
    
    
    private void preencherComboFornecedor(){
        try{
            fornecedores.addAll(fornecedoDAO.lista(""));
        }catch(SQLException ex){
            mensagem("Erro no preenchimento da Combo: " + 
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void preencherComboCategoria(){
        try{
            categorias.addAll(categoriaDAO.lista(""));
        }catch(SQLException ex){
            mensagem("Erro no preenchimento da Combo: " + 
                    ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
  
}
