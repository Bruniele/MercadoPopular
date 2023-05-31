/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.FornecedorDAO;
import br.com.fatec.Formatter.TextFieldFormatter;
import br.com.fatec.Menu;
import br.com.fatec.model.Fornecedor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruni
 */
public class VW_FornecedorController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtNomeFornecedor;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCodigoFornecedor;
    @FXML
    private TextField txtNumero;
    @FXML
    private TextField txtCep;
    

    @FXML
    private Button btnInserir;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisar;
    /**
     * Initializes the controller class.
     */
    
    FornecedorDAO fornecedorDAO = new FornecedorDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void btnInserir_Click(ActionEvent event) throws SQLException {
        Integer codFornecedor = Integer.parseInt(txtCodigoFornecedor.getText());
        String nomeFornecedor = txtNomeFornecedor.getText();
        String endereco = txtEndereco.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();
        String numero = txtNumero.getText();
//        Integer numero = Integer.parseInt(txtNumero.getText
        String cep = txtCep.getText();
        
        Fornecedor fornecedor = new Fornecedor();
        
        
        fornecedor.setCodigoFornecedor(codFornecedor);
        fornecedor.setNomeFornecedor(nomeFornecedor);
        fornecedor.setEndereco(endereco);
        fornecedor.setEmail(email);
        fornecedor.setTelefone(telefone);
        fornecedor.setNumero(numero);
        fornecedor.setCep(cep);
        
        fornecedorDAO.insere(fornecedor);
        
        
        txtCodigoFornecedor.clear();
        txtNomeFornecedor.clear();
        txtEndereco.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtTelefone.clear();
        txtNumero.clear();
        txtCep.clear();
     

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



    @FXML
    private void onCelularKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtTelefone);
        tff.formatter();
    }

    @FXML
    private void onNumeroKeyReased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("######");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtNumero);
        tff.formatter();
    }

    @FXML
    private void onCepKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtCep);
        tff.formatter();
    }

}
