/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.CategoriaDAO;
import br.com.fatec.DAO.FornecedorDAO;
import br.com.fatec.DAO.ProdutoDAO;
import br.com.fatec.Menu;
import br.com.fatec.model.Produto;
import br.com.fatec.model.Categoria;
import br.com.fatec.model.Fornecedor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private TextField txtCodigoProduto;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private DatePicker txtValidade;
    @FXML
    private TextField txtCodigoFornecedor;
    @FXML
    private ComboBox<Fornecedor> cbxFornecedor;
    @FXML
    private TextField txtCodigoCategoria;
    @FXML
    private ComboBox<Categoria> cbxCategoria; 
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisar;
    
    //variaveis auxiliares
    private br.com.fatec.model.Produto produto;
    private Fornecedor fornecedor;
    private FornecedorDAO fornDAO = new FornecedorDAO();
    private Categoria categoria;
    private CategoriaDAO cateDAO = new CategoriaDAO();
    private ProdutoDAO prodDAO = new ProdutoDAO();
    
    //auxiliar para a comboBox do Fornecedor
    private ObservableList<Fornecedor> fornecedores =  
            FXCollections.observableArrayList(); 
    
    //auxiliar para a comboBox da Categoria
    private ObservableList<Categoria> categorias =  
            FXCollections.observableArrayList(); 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnVoltar.setGraphic(new ImageView("/br/com/fatec/icons/iconeVoltar.png"));
        btnLimpar.setGraphic(new ImageView("/br/com/fatec/icons/iconeLimpar.png"));
        btnInserir.setGraphic(new ImageView("/br/com/fatec/icons/iconeInserir.png"));
        btnExcluir.setGraphic(new ImageView("/br/com/fatec/icons/iconeExcluir.png"));
        btnAlterar.setGraphic(new ImageView("/br/com/fatec/icons/iconeAlterar.png"));
        btnPesquisar.setGraphic(new ImageView("/br/com/fatec/icons/iconePesquisar.png"));
        
        preencheCombo();
        
        //colocar a coleção do Fornecedor na comboBox
        cbxFornecedor.setItems(fornecedores);
        
        //colocar a coleção da Categoria na comboBox
        cbxCategoria.setItems(categorias);
        
        habilitaInclusao();
        
        configuraLostFocusFornecedor();
        configuraLostFocusCategoria();
        configuraChangeValueComboFornecedor();
        configuraChangeValueComboCategoria();
        
    }    
    
    /**
     * Move os dados da tela para o modelo
     * @return model preenchido com os dados
     */
    private Produto moveDadosTelaModel() {
        produto = new br.com.fatec.model.Produto();
        produto.setCodigoProduto(Integer.parseInt(txtCodigoProduto.getText()));
        produto.setNomeProduto(txtNomeProduto.getText());
        produto.setDescricao(txtDescricao.getText());
        produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        produto.setPreco(Float.parseFloat(txtPreco.getText()));
        produto.setFornecedor(cbxFornecedor.getValue());
        produto.setCategoria(cbxCategoria.getValue());
        
        return produto;
    }
    
    /**
     * Exibe uma mensagem na tela
     * @param texto - Mensagem a ser exibida
     * @param tipo - Tipo do Icone que aparecerá
     * @param titulo - Título da janela
     */
    private void mensagem(String texto, Alert.AlertType tipo, String titulo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(texto);
        ButtonType btnOk = new ButtonType("OK");
        alerta.getButtonTypes().setAll(btnOk);
        alerta.showAndWait();
    }
    
    /**
     * Rotina para preencher a combo com dados
     * do banco de dados
     */
    private void preencheCombo() {
        try {
            fornecedores.addAll(fornDAO.lista(""));
            categorias.addAll(cateDAO.lista(""));
        } catch (SQLException ex) {
            mensagem("Erro no preenchimento da Combo: " + 
                    ex.getMessage(), Alert.AlertType.ERROR, "Erro");
        }
    }

    @FXML
    private void btnVoltar_Click(ActionEvent event) {
        Menu open = new Menu();
        try {
            open.start(new Stage());
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            mensagem("Erro ao encontrar a página",
                Alert.AlertType.ERROR, "Erro");
        }
    }
    
    @FXML
    private void btnLimpar_Click(ActionEvent event) {
        limparCampos();
    }

    @FXML
    private void btnInserir_Click(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencha todos os campos", 
                    Alert.AlertType.WARNING, "Aviso");
            return; //sai fora do método
        }        
        
        //recebe todos os dados da tela
        produto = moveDadosTelaModel();

        //vamos inserir
        try {
            if(prodDAO.insere(produto)) {
                mensagem("Produto Incluído com Sucesso", 
                        Alert.AlertType.INFORMATION, "Sucesso");
                limparCampos();
                txtCodigoProduto.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Inclusão: " + ex.getMessage(),
                    Alert.AlertType.ERROR, "Erro");
        } catch (Exception ex) {
            mensagem("Erro Genérico na Inclusão" + ex.getMessage(),
                    Alert.AlertType.ERROR, "Erro");
        }
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
 
    
    /**
     * Valida os campos da tela
     * @return 
     */
    private boolean validarCampos() {
        if(txtCodigoProduto.getText().length() == 0 ||
           txtNomeProduto.getText().length() == 0 ||
           txtPreco.getText().length() == 0 ||
           txtDescricao.getText().length() == 0 ||
           txtQuantidade.getText().length() == 0 ||
           txtValidade.getValue() == null ||
           txtCodigoFornecedor.getText().length() == 0 ||
           cbxFornecedor.getValue() == null ||
           txtCodigoCategoria.getText().length() == 0 ||
           cbxCategoria.getValue() == null) {
            return false;
        } else {
            return true;
        }
    }
    
    private void limparCampos() {
        txtCodigoProduto.setText("");
        txtNomeProduto.setText("");
        txtPreco.setText("");
        txtDescricao.setText("");
        txtQuantidade.setText("");
        txtValidade.setValue(null);
        txtCodigoFornecedor.setText("");
        cbxFornecedor.getSelectionModel().clearSelection();
        txtCodigoCategoria.setText("");
        cbxCategoria.getSelectionModel().clearSelection();
    }
    
    private void habilitaInclusao() {
        btnLimpar.setDisable(false);
        btnInserir.setDisable(false);
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
    }
    
    private void configuraLostFocusFornecedor() {
        //programa o LOSTFOCUS do codigo do Fornecedor
        //para fazer a busca dele dentro da combo
        txtCodigoFornecedor.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                if(!newValue) { //perdeu o FOCO
                    //verifica se tem algo digitado
                    if(txtCodigoFornecedor.getText().length() == 0)
                        cbxFornecedor.getSelectionModel().clearSelection();
                    else {
                        fornecedor = new Fornecedor();
                        fornecedor.setCodigoFornecedor(Integer.parseInt(txtCodigoFornecedor.getText()));
                        //busca na combo
                        cbxFornecedor.getSelectionModel().select(fornecedor);
                    }
                }
             }
        });
    }
    
    private void configuraLostFocusCategoria() {
        //programa o LOSTFOCUS do codigo do Categoria
        //para fazer a busca dele dentro da combo
        txtCodigoCategoria.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, 
                    Boolean oldValue, Boolean newValue) {
                if(!newValue) { //perdeu o FOCO
                    //verifica se tem algo digitado
                    if(txtCodigoCategoria.getText().length() == 0)
                        cbxCategoria.getSelectionModel().clearSelection();
                    else {
                        categoria = new Categoria();
                        categoria.setCodigoCategoria(Integer.parseInt(txtCodigoCategoria.getText()));
                        //busca na combo
                        cbxCategoria.getSelectionModel().select(categoria);
                    }
                }
             }
        });
    }
    
    private void configuraChangeValueComboFornecedor() {
        //programando o evento change da combo para
        //exibir seu conteudo nos texts
        cbxFornecedor.valueProperty().addListener((value, velho, novo) -> {
            if(novo != null)
                txtCodigoFornecedor.setText(Integer.toString(novo.getCodigoFornecedor()));
        });
    }
    
    private void configuraChangeValueComboCategoria() {
        //programando o evento change da combo para
        //exibir seu conteudo nos texts
        cbxCategoria.valueProperty().addListener((value, velho, novo) -> {
            if(novo != null)
                txtCodigoCategoria.setText(Integer.toString(novo.getCodigoCategoria()));
        });
    }
  
}
