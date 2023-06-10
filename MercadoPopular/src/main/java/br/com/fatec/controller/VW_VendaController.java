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
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisar;

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

        preencheCombo();

        //colocar a coleção do Funcionário na comboBox
        cbxFuncionario.setItems(funcionarios);

        habilitaInclusao();
        configuraLostFocusFuncionario();
        configuraChangeValueComboFuncionario();
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
                habilitaAlteracaoExclusao();
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
                    habilitaAlteracaoExclusao();
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

    private void habilitaInclusao() {
        btnLimpar.setDisable(false);
        btnInserir.setDisable(false);
        btnAlterar.setDisable(true);
        btnExcluir.setDisable(true);
    }

    private void habilitaAlteracaoExclusao() {
        btnInserir.setDisable(true);
        btnLimpar.setDisable(false);
        btnAlterar.setDisable(false);
        btnExcluir.setDisable(false);
    }

    private void moveDadosModelTela(Produto p) {
        txtNomeProduto.setText((p.getNomeProduto()));
        txtPreco.setText(Float.toString(p.getPreco()));
        txtValidade.setValue((p.getValidade()));
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

}
