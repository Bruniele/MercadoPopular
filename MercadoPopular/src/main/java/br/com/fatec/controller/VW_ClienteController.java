/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.Formatter.TextFieldFormatter;
import br.com.fatec.model.Cliente;
import br.com.fatec.Menu;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruni
 */
public class VW_ClienteController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCodigoCliente;
    @FXML
    private ComboBox<Cliente> cbxCliente;
    @FXML
    private Button btnLimpar;
    @FXML
    private TextField txtIdade;
    
    //Variáveis auxiliares
    //Coleção que conterá os dados para serem exibidos dentro
    //da comboBox
    ObservableList<Cliente> clientes =  
        FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnVoltar.setGraphic(new ImageView("/br/com/fatec/icons/iconeVoltar.png"));
        btnLimpar.setGraphic(new ImageView("/br/com/fatec/icons/iconeLimpar.png"));
        btnInserir.setGraphic(new ImageView("/br/com/fatec/icons/iconeInserir.png"));
        btnExcluir.setGraphic(new ImageView("/br/com/fatec/icons/iconeExcluir.png"));
        btnAlterar.setGraphic(new ImageView("/br/com/fatec/icons/iconeAlterar.png"));
        btnPesquisar.setGraphic(new ImageView("/br/com/fatec/icons/iconePesquisar.png"));
        
        //colocar a coleção dentro da comboBox
        cbxCliente.setItems(clientes);
        
        clientes.add(new Cliente(1, "Maria Teixeira de Souza", 40, "119.875.643-52", 
            "(11)97217-8094", "mariateixeira@gmail.com"));
        clientes.add(new Cliente(2, "João Siqueira Matos", 55, "155.018.423-18", 
            "(11)97009-8341", "joaosiqueiramatos@gmail.com"));
        
        //Programa o evento de seleção na comboBox
        eventoChangeValueCombo();
        
    }    
    
    private void eventoChangeValueCombo() {
        cbxCliente.valueProperty().addListener((ov, velho, novo) -> {
            if(novo == null) return;
            moveDadosModelTela(novo);
        });
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
    private void btnLimpar_Click(ActionEvent event) {
        limpaCampos();
    }

    @FXML
    private void btnInserir_Click(ActionEvent event) {
        //Verifica se os TextFields estão vazios
        if(txtCodigoCliente.getLength() == 0 || txtNome.getLength() == 0 
                || txtIdade.getLength() == 0 || txtCpf.getLength() == 0 
                || txtTelefone.getLength() == 0 || txtEmail.getLength() == 0){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText("Preencha todos os campos!");
            alerta.showAndWait();
            return;
        }
        
        Cliente c = moveDadosTelaModel();
        
        //Para contains funcionar o equals() e hashCode()
        //precisam ser programados em Cliente
        if(clientes.contains(c)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Cliente duplicado, tente outro");               
            alerta.showAndWait();
            return;
        }
        else {
            clientes.add(c);

            //Limpa os campos
            limpaCampos();
            //Manda o cursor para o campo do código do cliente
            txtCodigoCliente.requestFocus();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Sucesso");
            alerta.setHeaderText("Cliente cadastrado com sucesso!");
            alerta.showAndWait();
        }
    }
    
    private void limpaCampos() {
        txtCodigoCliente.clear();
        txtNome.clear();
        txtIdade.clear();
        txtCpf.clear();
        txtTelefone.clear();
        txtEmail.clear();
        cbxCliente.getSelectionModel().select(null);
        
        //Manda o cursor para o campo do código do cliente
        txtCodigoCliente.requestFocus();
    }

    @FXML
    private void btnExcluir_Click(ActionEvent event) {
        //Verifica se a comboBox esta vazia
        if(cbxCliente.getSelectionModel().getSelectedItem() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText("Escolha um cliente para excluir!");
            alerta.showAndWait();
            return;
        }
        
        Cliente c = moveDadosTelaModel();
        //Para contains funcionar o equals() e hashCode()
        //precisam ser programados em Cliente
        if(clientes.contains(c)) {
            Alert alertaExcluir = new Alert(Alert.AlertType.CONFIRMATION,
                "Código:     " + (Integer.parseInt(txtCodigoCliente.getText())) + "\n" +
                "Nome:       " + cbxCliente.getSelectionModel().getSelectedItem().getNome());
            ButtonType btnSim = new ButtonType("Sim");
            ButtonType btnNao = new ButtonType("Não");
            alertaExcluir.setTitle("Aviso de Exclusão");
            alertaExcluir.setHeaderText("Confirma a Exclusão deste Cliente?");
            alertaExcluir.getButtonTypes().setAll(btnSim, btnNao);
            alertaExcluir.showAndWait().ifPresent(b -> {
                if (b == btnSim) {
                    clientes.remove(c);
                    limpaCampos();
                    
                    //Manda o cursor para o campo do código do cliente
                    txtCodigoCliente.requestFocus();
                    
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Sucesso");
                    alerta.setHeaderText("Cliente removido com sucesso!");
                    alerta.showAndWait();
                    
                }else if (b == btnNao) {
                    return;
                }
            });
            
        }else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Não foi possivel excluir o Cliente");
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
        //Verifica se a comboBox esta vazia
        if(cbxCliente.getSelectionModel().getSelectedItem() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText("Escolha um cliente para alterar!");
            alerta.showAndWait();
            return;
        }
        
        Cliente c = moveDadosTelaModel();
        //Para contains funcionar o equals() e hashCode()
        //precisam ser programados em Cliente
        if(clientes.contains(c)) {
            clientes.remove(c);
            clientes.add(c);
            limpaCampos();
            //Manda o cursor para o campo do código do cliente
            txtCodigoCliente.requestFocus();
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Sucesso");
            alerta.setHeaderText("Cliente alterado com sucesso!");
            alerta.showAndWait();
            
        }else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Cliente não existe");
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) {
        if(cbxCliente.getSelectionModel().getSelectedItem() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Alerta");
            alerta.setHeaderText("Escolha um cliente para pesquisar!");
            alerta.showAndWait();
        }
        
        else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION,
                "Código:     " + (Integer.parseInt(txtCodigoCliente.getText())) + "\n" +
                "Nome:       " + cbxCliente.getSelectionModel().getSelectedItem().getNome() + "\n" +
                "Idade:        "  + (Integer.parseInt(txtIdade.getText())) + "\n" +
                "CPF:           " + txtCpf.getText() + "\n" +
                "Telefone:   " + txtTelefone.getText() + "\n" +
                "E-mail:       " + txtEmail.getText() 
                    ,
                    ButtonType.OK);
            alerta.setTitle("Pesquisa");
            alerta.setHeaderText("Dados do Cliente");
            alerta.showAndWait();
        }
    }
    
    /**
     * Move os dados que estão na tela para um objeto
     * @return Objeto que receberá os dados
     */
    private Cliente moveDadosTelaModel() {
        Cliente c = new Cliente();
        c.setCodigoCliente(Integer.parseInt(txtCodigoCliente.getText()));
        c.setNome(txtNome.getText());
        c.setIdade(Integer.parseInt(txtIdade.getText()));
        c.setCpf(txtCpf.getText());
        c.setTelefone(txtTelefone.getText());
        c.setEmail(txtEmail.getText());

        return c;
    }
    
    /**
     * Move os dados do objeto para a tela
     * @param c objeto que contem os dados para exibição 
     *          na tela
     */
    private void moveDadosModelTela(Cliente c) {
        txtCodigoCliente.setText(Integer.toString(c.getCodigoCliente()));
        txtNome.setText(c.getNome());
        txtIdade.setText(Integer.toString(c.getIdade()));
        txtCpf.setText(c.getCpf());
        txtTelefone.setText(c.getTelefone());
        txtEmail.setText(c.getEmail());
    }

    @FXML
    private void onCpfKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtCpf);
        tff.formatter();
    }

    @FXML
    private void onCelularKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtTelefone);
        tff.formatter();
    }
    
}
