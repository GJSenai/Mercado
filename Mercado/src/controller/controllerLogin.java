package controller;

import java.io.IOException;
import java.util.prefs.Preferences;

import application.Main;
import dao.FuncionarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Funcionario;

public class controllerLogin {

    @FXML
    private Button btLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUser;

    @FXML
    private CheckBox cbLembrarLogin;

    static Funcionario funcionario = new Funcionario();
    FuncionarioDAO funcDAO = new FuncionarioDAO();

    private static final String PREF_KEY_USER = "rememberedUser";

    @FXML
    public void initialize() {
        Preferences prefs = Preferences.userNodeForPackage(controllerLogin.class);
        String savedUser = prefs.get(PREF_KEY_USER, "");
        if (!savedUser.isEmpty()) {
            txtUser.setText(savedUser);
            cbLembrarLogin.setSelected(true);
        }
    }

    @FXML
    void actionLogin(ActionEvent event) throws IOException {
        String user = txtUser.getText();
        String password = txtSenha.getText();
        funcionario = funcDAO.autencticarUser(user, password);

        if (funcionario.getCpfFuncionario() != null && funcionario.getSenha() != null) {
            if (user.equals("") || password.equals("")) {
                showError("Erro de login", "Falha ao tentar realizar login", "Verifique as informações e tente novamente");
            } else if (funcionario.getCpfFuncionario().equals(user) && funcionario.getSenha().equals(password)) {
                // Lembrar login
                Preferences prefs = Preferences.userNodeForPackage(controllerLogin.class);
                if (cbLembrarLogin.isSelected()) {
                    prefs.put(PREF_KEY_USER, user);
                } else {
                    prefs.remove(PREF_KEY_USER);
                }
                showInfo("Seja bem-vindo!", "Bem-vindo de volta!", "Olá! Seja bem-vindo " + funcionario.getNomeFuncionario() + "!");
                Main.TelaHome();
            }
        } else {
            showError("Erro de login", "Falha ao tentar realizar login", "Verifique as informações e tente novamente");
        }
    }

    private void showError(String title, String header, String content) {
        Alert erro = new Alert(AlertType.ERROR);
        erro.setTitle(title);
        erro.setHeaderText(header);
        erro.setContentText(content);
        erro.show();
    }

    private void showInfo(String title, String header, String content) {
        Alert saudacao = new Alert(AlertType.INFORMATION);
        saudacao.setTitle(title);
        saudacao.setHeaderText(header);
        saudacao.setContentText(content);
        saudacao.show();
    }
}
