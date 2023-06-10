/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.ProdutoDAO;
import br.com.fatec.Menu;
import br.com.fatec.model.Categoria;
import br.com.fatec.model.Fornecedor;
import br.com.fatec.model.Produto;
import br.com.fatec.persistencia.Banco;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private TextField txtPreco;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TableView<Produto> tabela;
    @FXML
    private TableColumn<Produto, Integer> clmID;
    @FXML
    private TableColumn<Produto, String> clmNomeProduto;
    @FXML
    private TableColumn<Produto, Float> clmPreco;
     @FXML
    private TableColumn<Produto, Integer> clmQtd;
     
    ObservableList<Produto> listP;   
    ObservableList<Produto> dataList ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnVoltar.setGraphic(new ImageView("/br/com/fatec/icons/iconeVoltar.png"));
        loadData();
        searchRecord();
         
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

    private void searchRecord()
    {
        clmID.setCellValueFactory(new PropertyValueFactory<>("codigoProduto"));
        clmNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        clmPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        clmQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        dataList = ProdutoDAO.getDataProduto();
        tabela.setItems(dataList);
        
        FilteredList<Produto> filterData = new FilteredList<>(dataList, p-> true);
           txtId.textProperty().addListener((observable,oldvalue,newvalue)->{
               filterData.setPredicate(pers->{
                   if(newvalue == null || newvalue.isEmpty()){
                       return true;
                   }
                   String  typedText = newvalue.toLowerCase();
                   if(String.valueOf(pers.getCodigoProduto()).toLowerCase().indexOf(typedText)!= -1){
                       return  true;
                   }
                   return false;
               });
           });
        txtPreco.textProperty().addListener((observable,oldvalue,newvalue)->{
        filterData.setPredicate(pers->{
                   if(newvalue == null || newvalue.isEmpty()){
                       return true;
                   }
                   String  typedText = newvalue.toLowerCase();
                    if(String.valueOf(pers.getPreco()).toLowerCase().indexOf(typedText)!= -1){
                       return  true;
                   }
                   return false;
               });
           });
        txtNomeProduto.textProperty().addListener((observable,oldvalue,newvalue)->{
        filterData.setPredicate(pers->{
                   if(newvalue == null || newvalue.isEmpty()){
                       return true;
                   }
                   String  typedText = newvalue.toLowerCase();
                    if(pers.getNomeProduto().toLowerCase().indexOf(typedText)!= -1){
                       return  true;
                   }
                   return false;
               });
           });
        SortedList<Produto> sortedData = new SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tabela.comparatorProperty());
        tabela.setItems(sortedData);
    }
    private void loadData(){
        clmID.setCellValueFactory(new PropertyValueFactory<>("codigoProduto"));
        clmNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        clmPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        clmQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        listP = ProdutoDAO.getDataProduto();
        tabela.setItems(listP);
    }
}
