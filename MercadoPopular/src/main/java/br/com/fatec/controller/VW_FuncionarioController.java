package br.com.fatec.controller;

import br.com.fatec.DAO.FuncionarioDAO;
import br.com.fatec.Formatter.TextFieldFormatter;
import br.com.fatec.Menu;
import br.com.fatec.model.Endereco;
import br.com.fatec.model.Funcionario;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class VW_FuncionarioController implements Initializable {

    @FXML
    private Button btnVoltar;
    @FXML
    private TextField txtCodigoFuncionario;
    @FXML
    private TextField txtNomeFuncionario;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtRg;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtSetor;
    @FXML
    private TextField txtSalario;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisar;

    //Variáveis auxiliares
    private Funcionario funcionario;

    private Endereco endereco;

    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    @FXML
    private Button btnLimpar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        habilitarInclusao();

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
        try {
            if (!validarCampos()) {
                mensagem("Preencher todos os campos", Alert.AlertType.INFORMATION);
                return;
            }

            int codigoFuncionario = Integer.parseInt(txtCodigoFuncionario.getText());

            if (funcionarioDAO.BuscaID(codigoFuncionario)) {
                mensagem("Código do fornecedor já existe!", Alert.AlertType.WARNING);
                txtCodigoFuncionario.clear();
                return;
            }
            moveDadosTelaModel();

            funcionarioDAO.insere(funcionario);

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

    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) {
        funcionario = new Funcionario();

        funcionario.setCodigoFuncionario(Integer.parseInt(txtCodigoFuncionario.getText()));

        try {

            funcionario = funcionarioDAO.buscaID(funcionario);

            if (funcionario == null) {
                mensagem("Funcionário não existe!!!", Alert.AlertType.ERROR);
            } else {
                moveDadosModelTela(funcionario);
                habilitarAlteracaoExclusao();
            }

        } catch (NumberFormatException | SQLException e) {
            mensagem("Erro na Pesquisa" + e.getMessage(),
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnLimpar_Click(ActionEvent event) {
        txtCodigoFuncionario.clear();
        txtNomeFuncionario.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtRg.clear();
        txtCpf.clear();
        txtSetor.clear();
        txtSalario.clear();
        habilitarInclusao();
    }

    //CELULAR
    @FXML
    private void onCelularKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)#####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtTelefone);
        tff.formatter();
    }

    //RG
    @FXML
    private void onRgKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtRg);
        tff.formatter();
    }

    //CPF
    @FXML
    private void onCpfKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txtCpf);
        tff.formatter();
    }

    private void mensagem(String texto, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo, texto, ButtonType.OK);
        alerta.showAndWait();
    }

    private void habilitarInclusao() {
        btnInserir.setDisable(false);
        btnExcluir.setDisable(true);
        btnAlterar.setDisable(true);
    }

    private void habilitarAlteracaoExclusao() {
        btnInserir.setDisable(true);
        btnExcluir.setDisable(false);
        btnAlterar.setDisable(false);
    }

    private void limparTextField() {
        txtCodigoFuncionario.clear();
        txtNomeFuncionario.clear();
        txtEmail.clear();
        txtTelefone.clear();
        txtRg.clear();
        txtCpf.clear();
        txtSetor.clear();
        txtSalario.clear();
    }

    private Funcionario moveDadosTelaModel() {

        funcionario = new Funcionario();
        funcionario.setCodigoFuncionario(Integer.parseInt(txtCodigoFuncionario.getText()));
        funcionario.setNome(txtNomeFuncionario.getText());
        funcionario.setEmail(txtEmail.getText());
        funcionario.setTelefone(txtTelefone.getText());
        funcionario.setRg(txtRg.getText());
        funcionario.setCpf(txtCpf.getText());
        funcionario.setSetor(txtSetor.getText());
        funcionario.setSalario(Double.parseDouble(txtSalario.getText()));

        return funcionario;
    }

    private void moveDadosModelTela(Funcionario v) {
        txtNomeFuncionario.setText(funcionario.getNome());
        txtEmail.setText(funcionario.getEmail());
        txtTelefone.setText(funcionario.getTelefone());
        txtRg.setText(funcionario.getRg());
        txtCpf.setText(funcionario.getCpf());
        txtSetor.setText(funcionario.getSetor());
        txtSalario.setText(formatarDinheiro(funcionario.getSalario()));
    }

    private boolean validarCampos() {
        if (txtCodigoFuncionario.getText().length() == 0
                || txtNomeFuncionario.getText().length() == 0
                || txtEmail.getText().length() == 0
                || txtTelefone.getText().length() == 0
                || txtRg.getText().length() == 0
                || txtCpf.getText().length() == 0
                || txtSetor.getText().length() == 0
                || txtSalario.getText().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static String formatarDinheiro(double valor) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return format.format(valor);
    }

}

//
//    private String aplicarMascaraTelefone(String telefone) {
//        telefone = telefone.replaceAll("[^0-9]", "");
//
//        if (telefone.length() == 10) {
//            return "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 6) + "-" + telefone.substring(6);
//        } else if (telefone.length() == 11) {
//            return "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 7) + "-" + telefone.substring(7);
//        } else {
//            return telefone;
//        }
//    }
//
//    private String aplicarMascaraRG(String rg) {
//        rg = rg.replaceAll("[^0-9]", "");
//
//        if (rg.length() == 10) {
//            return rg.substring(0, 2) + "." + rg.substring(2, 5) + "." + rg.substring(5, 8) + "-" + rg.substring(8);
//        } else {
//            return rg;
//        }
//    }
//
//    private String aplicarMascaraCpf(String cpf) {
//        cpf = cpf.replaceAll("[^0-9]", "");
//
//        if (cpf.length() == 11) {
//            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
//        } else {
//            return cpf;
//        }
//    }
//
//    public static String formatarDinheiro(double valor) {
//        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
//        return format.format(valor);
//    }
//
//    private String aplicarMascaraCEP(String cep) {
//        //04190 000
//        cep = cep.replaceAll("[^0-9]", "");
//
//        if (cep.length() == 8) {
//            return cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5);
//        } else {
//            return cep;
//        }
//    }
//
//    private String removerCaracteresEspeciais(String cep) {
//        return cep.replaceAll("[^0-9]", "");
//    }

