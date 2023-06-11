/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.controller;

import br.com.fatec.DAO.FuncionarioDAO;
import br.com.fatec.DAO.ProdutoDAO;
import br.com.fatec.DAO.VendaDAO;
import br.com.fatec.Menu;
import br.com.fatec.model.Cliente;
import br.com.fatec.model.Funcionario;
import br.com.fatec.model.Produto;
import br.com.fatec.model.Venda;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bruni
 */
public class VW_VendaController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtCodigoVenda;
    @FXML
    private TextField txtCodigoProduto;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private DatePicker txtValidade;
    @FXML
    private TextField txtValorTotal;
    @FXML
    private TextField txtTotalRecebido;
    @FXML
    private TextField txtTroco;
    @FXML
    private TextField txtCodigoCliente;
    @FXML
    private ComboBox<Cliente> cbxCliente;
    @FXML
    private TextField txtCodigoFuncionario;
    @FXML
    private ComboBox<Funcionario> cbxFuncionario;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnInserir;

    //variaveis auxiliares
    private Venda venda;
    private BigDecimal valorTotal;

    private Produto produto;
    private ProdutoDAO prodDAO = new ProdutoDAO();

    private Funcionario funcionario;
    private FuncionarioDAO funcDAO = new FuncionarioDAO();

    private Cliente cliente;
    private VendaDAO vendDAO = new VendaDAO();

    //auxiliar para a comboBox do Fornecedor
    private ObservableList<Funcionario> funcionarios
            = FXCollections.observableArrayList();
    
    //auxiliar para a comboBox do Cliente
    private ObservableList<Cliente> clientes =  
        FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnVoltar.setGraphic(new ImageView("/br/com/fatec/icons/iconeVoltar.png"));
        btnLimpar.setGraphic(new ImageView("/br/com/fatec/icons/iconeLimpar.png"));
        btnInserir.setGraphic(new ImageView("/br/com/fatec/icons/iconeInserir.png"));

        preencheCombo();

        //colocar a coleção do Funcionário na comboBox
        cbxFuncionario.setItems(funcionarios);
        
        //colocar a coleção do Cliente dentro da comboBox
        cbxCliente.setItems(clientes);
        
        clientes.add(new Cliente(1, "Maria Teixeira de Souza", 40, "119.875.643-52", 
            "(11)97217-8094", "mariateixeira@gmail.com"));
        clientes.add(new Cliente(2, "João Siqueira Matos", 55, "155.018.423-18", 
            "(11)97009-8341", "joaosiqueiramatos@gmail.com"));
        
        habilitaInclusao();
        configuraLostFocusFuncionario();
        configuraChangeValueComboFuncionario();
        configuraLostFocusCliente();
        eventoChangeValueComboCliente();
        
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
        // Limpar campos de texto
        txtCodigoVenda.clear();
        txtCodigoProduto.clear();
        txtNomeProduto.clear();
        txtPreco.clear();
        txtQuantidade.clear();
        txtValidade.getEditor().clear();
        txtValorTotal.clear();
        txtTotalRecebido.clear();
        txtTroco.clear();
        txtCodigoCliente.clear();
        txtCodigoFuncionario.clear();

        // Reinicializar ComboBoxes
        cbxCliente.getSelectionModel().clearSelection();
        cbxCliente.setValue(null);
        cbxFuncionario.getSelectionModel().clearSelection();
        cbxFuncionario.setValue(null);
                
        habilitaInclusao();
        txtCodigoVenda.requestFocus();
    }

    @FXML
    private void btnInserir_Click(ActionEvent event) {
        if(!validarCampos()) {
            mensagem("Preencha todos os campos", 
                    Alert.AlertType.WARNING, "Aviso");
            return; //sai fora do método
        }        
        
        //recebe todos os dados da tela
        venda = moveDadosTelaModel();

        //vamos inserir
        try {
            if(vendDAO.insere(venda)) {
                mensagem("Venda Incluída com Sucesso", 
                        Alert.AlertType.INFORMATION, "Sucesso");
                limparCampos();
                txtCodigoVenda.requestFocus();
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
    private void onCodProdKeyReleased(KeyEvent event) {

        String codigoProdutoStr = txtCodigoProduto.getText().trim();

        // Verifica se o campo de código do produto está vazio
        if (codigoProdutoStr.isEmpty()) {
            // Exibe mensagem de erro
//            mensagem("Por favor, informe um código de produto válido!",
//                    Alert.AlertType.ERROR, "Erro");
            return;
        }

        // Verifica se o código do produto contém apenas dígitos numéricos
        if (!codigoProdutoStr.matches("\\d+")) {
            // Exibe mensagem de erro
            mensagem("Por favor, informe um código de produto válido!",
                    Alert.AlertType.ERROR, "Erro");
            return;
        }

        produto = new Produto();
        produto.setCodigoProduto(Integer.parseInt(codigoProdutoStr));

        try {
            produto = prodDAO.buscaID(produto);

            if (produto == null) {
                mensagem("Produto Não Existe!!!",
                        Alert.AlertType.ERROR, "Erro");
            } else {
                moveDadosModelTela(produto);
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR, "Erro");
        }

        if (event.getCode() == KeyCode.TAB) {
            txtQuantidade.requestFocus();
            event.consume();
        }
    }

    @FXML
    private void onQtdKeyReleased(KeyEvent event) {
        String quantidadeStr = txtQuantidade.getText().trim();

        // Verifica se a string da quantidade está vazia
        if (quantidadeStr.isEmpty()) {
            return;
        }

        produto = new Produto();

        int qtdDigitada;
        try {
            qtdDigitada = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            mensagem("Por favor, informe uma quantidade válida!",
                    Alert.AlertType.ERROR, "Erro");
            return;
        }

        produto.setCodigoProduto(Integer.parseInt(txtCodigoProduto.getText()));

        try {
            produto = prodDAO.buscaID(produto);

            if (produto == null) {
                mensagem("Produto Não Existe!!!",
                        Alert.AlertType.ERROR, "Erro");
            } else {
                if (qtdDigitada > produto.getQuantidade()) {
                    mensagem("Quantidade digitada maior que a quantidade de produto disponível!",
                            Alert.AlertType.ERROR, "Erro");
                    txtQuantidade.clear(); // Apagar o valor digitado
                } else {
                    // Mostrar na tela
                    valorTotal(produto, qtdDigitada);
                }
            }
        } catch (SQLException ex) {
            mensagem("Erro na Pesquisa: " + ex.getMessage(),
                    Alert.AlertType.ERROR, "Erro");
        }

        if (event.getCode() == KeyCode.TAB) {
            txtTotalRecebido.requestFocus();
            event.consume();
        }
    }

    @FXML
    private void onTotalKeyReleased(KeyEvent event) {
        troco();
    }

    private void troco() {
        String valorTotalStr = txtValorTotal.getText().trim();
        String valorPagoStr = txtTotalRecebido.getText().trim();

        // Verifica se as strings de valor total e valor pago estão vazias
        if (valorTotalStr.isEmpty() || valorPagoStr.isEmpty()) {
            return;
        }

        double valorTotal;
        try {
            valorTotal = Double.parseDouble(valorTotalStr);
        } catch (NumberFormatException e) {
            return;
        }

        double valorPago;
        try {
            valorPago = Double.parseDouble(valorPagoStr);
        } catch (NumberFormatException e) {
            return;
        }

        double troco = valorPago - valorTotal;

        if (troco < 0) {
            mensagem("Valor pago insuficiente!", Alert.AlertType.WARNING, "Aviso");
            return;
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String trocoFormatado = decimalFormat.format(troco);

        txtTroco.setText(trocoFormatado);
    }

    private void mensagem(String texto, Alert.AlertType tipo, String titulo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(texto);
        ButtonType btnOk = new ButtonType("OK");
        alerta.getButtonTypes().setAll(btnOk);
        alerta.showAndWait();
    }
    
    private boolean validarCampos() {
        if(txtCodigoVenda.getText().length() == 0 ||
            txtCodigoProduto.getText().length() == 0 ||
            txtNomeProduto.getText().length() == 0 ||
            txtPreco.getText().length() == 0 ||
            txtQuantidade.getText().length() == 0 ||
            txtValidade.getValue() == null ||
            txtValorTotal.getText().length() == 0 ||
            txtTotalRecebido.getText().length() == 0 ||
            txtTroco.getText().length() == 0 ||
            txtCodigoCliente.getText().length() == 0 ||
            txtCodigoFuncionario.getText().length() == 0 ||
            cbxCliente.getValue() == null ||
            cbxFuncionario.getValue() == null ||
            cbxFuncionario.getValue() == null) {
            return false;
        } else {
            return true;
        }
    }
    
    private void limparCampos() {
        // Limpar campos de texto
        txtCodigoVenda.clear();
        txtCodigoProduto.clear();
        txtNomeProduto.clear();
        txtPreco.clear();
        txtQuantidade.clear();
        txtValidade.getEditor().clear();
        txtValorTotal.clear();
        txtTotalRecebido.clear();
        txtTroco.clear();
        txtCodigoCliente.clear();
        txtCodigoFuncionario.clear();

        // Reinicializar ComboBoxes
        cbxCliente.getSelectionModel().clearSelection();
        cbxCliente.setValue(null);
        cbxFuncionario.getSelectionModel().clearSelection();
        cbxFuncionario.setValue(null);
        
        txtCodigoVenda.requestFocus();
    }
    
    private Venda moveDadosTelaModel() {
        venda = new Venda();
        venda.setCodigoVenda(Integer.parseInt(txtCodigoVenda.getText()));
        venda.setCodigoProduto(Integer.parseInt(txtCodigoFuncionario.getText()));
        venda.setNomeProduto(txtNomeProduto.getText());
        venda.setPreco(Float.parseFloat(txtPreco.getText()));
        venda.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
        venda.setValidade(txtValidade.getValue());
       
        venda.setValorTotal(Float.parseFloat(txtValorTotal.getText()));
        venda.setTotalRecebido(Float.parseFloat(txtTotalRecebido.getText()));
        String trocoText = txtTroco.getText();
        trocoText = trocoText.replaceAll("[^0-9.]", "");
        float troco = Float.parseFloat(trocoText);
        venda.setTroco(troco);
        venda.setCliente(cbxCliente.getValue());
        venda.setFuncionario(cbxFuncionario.getValue());
        
        return venda;
    }

    private void habilitaInclusao() {
        btnLimpar.setDisable(false);
        btnInserir.setDisable(false);
    }

    private void habilitaAlteracaoExclusao() {
        btnInserir.setDisable(true);
        btnLimpar.setDisable(false);
    }

    private void moveDadosModelTela(Produto p) {
        txtNomeProduto.setText((p.getNomeProduto()));
        txtPreco.setText(Float.toString(p.getPreco()));
        txtValidade.setValue((p.getValidade()));
    }
    
    private void moveDadosModelTelaCliente(Cliente c) {
        txtCodigoCliente.setText(Integer.toString(c.getCodigoCliente()));
        
    }
    
    private Cliente moveDadosTelaModelCliente() {
        cliente = new Cliente();
        cliente.setCodigoCliente(Integer.parseInt(txtCodigoCliente.getText()));
        cliente.setNome(cbxCliente.getValue().getNome());


        return cliente;
    }

    private void valorTotal(Produto p, int qtdDigitada) {
        int qtd = Integer.parseInt(txtQuantidade.getText());
        float valorTotal = p.getPreco() * qtd;

        txtValorTotal.setText(String.valueOf(valorTotal));
    }

    private void preencheCombo() {
        try {
            funcionarios.addAll(funcDAO.lista(""));
        } catch (SQLException ex) {
            mensagem("Erro no preenchimento da Combo: "
                    + ex.getMessage(), Alert.AlertType.ERROR, "Erro");
        }
    }

    private void configuraLostFocusFuncionario() {
        txtCodigoFuncionario.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (txtCodigoFuncionario.getText().length() == 0) {
                        cbxFuncionario.getSelectionModel().clearSelection();
                    } else {
                        funcionario = new Funcionario();
                        funcionario.setCodigoFuncionario(Integer.parseInt(txtCodigoFuncionario.getText()));
                        cbxFuncionario.getSelectionModel().select(funcionario);
                    }
                }
            }
        });
    }

    private void configuraChangeValueComboFuncionario() {
        cbxFuncionario.valueProperty().addListener((value, velho, novo) -> {
            if (novo != null) {
                txtCodigoFuncionario.setText(Integer.toString(novo.getCodigoFuncionario()));
            }
        });
    }
    
    private void configuraLostFocusCliente() {
        txtCodigoCliente.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                    Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    if (txtCodigoCliente.getText().length() == 0) {
                        cbxCliente.getSelectionModel().clearSelection();
                    } else {
                        cliente = new Cliente();
                        cliente.setCodigoCliente(Integer.parseInt(txtCodigoCliente.getText()));
                        cbxCliente.getSelectionModel().select(cliente);
                    }
                }
            }
        });
    }
    
    private void eventoChangeValueComboCliente() {
        cbxCliente.valueProperty().addListener((ov, velho, novo) -> {
            if(novo == null) return;
            moveDadosModelTelaCliente(novo);
        });
    }  

}
