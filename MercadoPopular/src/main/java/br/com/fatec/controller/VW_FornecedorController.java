package br.com.fatec.controller;

import br.com.fatec.DAO.FornecedorDAO;
import br.com.fatec.Formatter.TextFieldFormatter;
import br.com.fatec.Menu;
import br.com.fatec.model.Endereco;
import br.com.fatec.model.Fornecedor;
import br.com.fatec.services.ViacepService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class VW_FornecedorController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtCodigoFornecedor;
    @FXML
    private TextField txtNomeFornecedor;
    @FXML
    private TextField txtSite;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtLogradouro;
    @FXML
    private TextField txtBairro;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtUf;
    @FXML
    private TextField txtNumero;
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

    //Variáveis auxiliares
    private Fornecedor fornecedor;
    FornecedorDAO fornecedorDAO = new FornecedorDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        habilitarInclusao();

        btnVoltar.setGraphic(new ImageView("/br/com/fatec/icons/iconeVoltar.png"));
        btnLimpar.setGraphic(new ImageView("/br/com/fatec/icons/iconeLimpar.png"));
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
    private void btnLimpar_Click(ActionEvent event) {
        txtCodigoFornecedor.clear();
        txtNomeFornecedor.clear();
        txtSite.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtCep.clear();
        txtLogradouro.clear();
        txtBairro.clear();
        txtCidade.clear();
        txtUf.clear();
        txtNumero.clear();
        txtCodigoFornecedor.requestFocus();
        habilitarInclusao();
    }

    @FXML
    private void btnInserir_Click(ActionEvent event) {
        try {
            if (!validarCampos()) {
                mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
                return; // Sai fora do método
            }

            int codigoFornecedor = Integer.parseInt(txtCodigoFornecedor.getText());

            if (fornecedorDAO.buscaID(codigoFornecedor)) {
                mensagem("Código do fornecedor já existe!", Alert.AlertType.WARNING);
                txtCodigoFornecedor.clear();
                return; // Sai fora do método
            }

            moveDadosTelaModel();

            fornecedorDAO.insere(fornecedor);

            limparTextField();
            mensagem("Dados incluídos com sucesso", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException ex) {
            mensagem("Código do fornecedor inválido!", Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            mensagem("Erro na inclusão: " + ex.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro genérico na inclusão: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnExcluir_Click(ActionEvent event) {
        if (!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Mensagem ao Usuário");
        alert.setHeaderText("Aviso de Exclusão");
        alert.setContentText("Confirma a Exclusão deste Fornecedor?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.NO) {
            return;
        }

        fornecedor = moveDadosTelaModel();

        try {
            if (fornecedorDAO.remove(fornecedor)) {
                mensagem("Dados excluidos com sucesso", Alert.AlertType.INFORMATION);
                limparTextField();
                habilitarInclusao();
                txtCodigoFornecedor.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na Esclusão" + ex.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro genérico na exclusão", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {

        if (!validarCampos()) {
            mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
            return; //sai fora do método
        }

        fornecedor = moveDadosTelaModel();

        try {
            if (fornecedorDAO.altera(fornecedor)) {
                mensagem("Dados Alterados com sucesso", Alert.AlertType.INFORMATION);
                limparTextField();
                habilitarInclusao();
                txtCodigoFornecedor.requestFocus();
            }
        } catch (SQLException ex) {
            mensagem("Erro na alteração " + ex.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception ex) {
            mensagem("Erro genérico na alteração " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) throws SQLException {
        String codigoFornecedorText = txtCodigoFornecedor.getText();

        if (codigoFornecedorText.isEmpty()) {
            mensagem("O campo de código do fornecedor está vazio!", Alert.AlertType.WARNING);
            return;
        }

        fornecedor = new Fornecedor();

        fornecedor.setCodigoFornecedor(Integer.parseInt(txtCodigoFornecedor.getText()));

        try {

            fornecedor = fornecedorDAO.buscaID(fornecedor);

            if (fornecedor == null) {
                mensagem("Fornecedor não existe!!!", Alert.AlertType.ERROR);
            } else {
                moveDadosModelTela(fornecedor);
                habilitarAlteracaoExclusao();
            }

        } catch (NumberFormatException | SQLException e) {
            mensagem("Erro na Pesquisa" + e.getMessage(),
                    Alert.AlertType.ERROR);
        }
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
    private void handleCepFieldKeyTyped(KeyEvent event) {
        String cep = txtCep.getText();
        if (cep.length() == 8) {
            preencherCampos(cep);
        }
    }

    private void preencherCampos(String cep) {
        ViacepService viacepService = new ViacepService();

        try {
            Endereco endereco = viacepService.getEndereco(cep);
            txtLogradouro.setText(endereco.getLogradouro());
            txtBairro.setText(endereco.getBairro());
            txtCidade.setText(endereco.getLocalidade());
            txtUf.setText(endereco.getUf());
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void limparTextField() {
        txtCodigoFornecedor.clear();
        txtNomeFornecedor.clear();
        txtSite.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtCep.clear();
        txtLogradouro.clear();
        txtBairro.clear();
        txtCidade.clear();
        txtUf.clear();
        txtNumero.clear();
        txtCodigoFornecedor.requestFocus();
    }

    private void habilitarInclusao() {
        btnLimpar.setDisable(false);
        btnInserir.setDisable(false);
        btnExcluir.setDisable(true);
        btnAlterar.setDisable(true);
    }

    private void habilitarAlteracaoExclusao() {
        btnInserir.setDisable(true);
        btnLimpar.setDisable(false);
        btnExcluir.setDisable(false);
        btnAlterar.setDisable(false);
    }

    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }

    private Fornecedor moveDadosTelaModel() {
        fornecedor = new Fornecedor();
        fornecedor.setCodigoFornecedor(Integer.parseInt(txtCodigoFornecedor.getText()));
        fornecedor.setNomeFornecedor(txtNomeFornecedor.getText());
        fornecedor.setSite(txtSite.getText());
        fornecedor.setEmail(txtEmail.getText());
        fornecedor.setTelefone(txtTelefone.getText());
        fornecedor.setCep(txtCep.getText());
        fornecedor.setLogradouro(txtLogradouro.getText());
        fornecedor.setBairro(txtBairro.getText());
        fornecedor.setLocalidade(txtCidade.getText());
        fornecedor.setUf(txtUf.getText());
        fornecedor.setNumero(txtNumero.getText());

        return fornecedor;
    }

    private void moveDadosModelTela(Fornecedor v) {
        txtNomeFornecedor.setText(fornecedor.getNomeFornecedor());
        txtSite.setText(fornecedor.getSite());
        txtEmail.setText(fornecedor.getEmail());
        txtTelefone.setText(fornecedor.getTelefone());
        txtCep.setText(fornecedor.getCep());
        txtLogradouro.setText(fornecedor.getLogradouro());
        txtBairro.setText(fornecedor.getBairro());
        txtCidade.setText(fornecedor.getLocalidade());
        txtUf.setText(fornecedor.getUf());
        txtNumero.setText(fornecedor.getNumero());
    }

    private boolean validarCampos() {
        if (txtCodigoFornecedor.getText().length() == 0
                || txtNomeFornecedor.getText().length() == 0
                || txtSite.getText().length() == 0
                || txtEmail.getText().length() == 0
                || txtTelefone.getText().length() == 0
                || txtCep.getText().length() == 0
                || txtLogradouro.getText().length() == 0
                || txtBairro.getText().length() == 0
                || txtCidade.getText().length() == 0
                || txtUf.getText().length() == 0
                || txtNumero.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
