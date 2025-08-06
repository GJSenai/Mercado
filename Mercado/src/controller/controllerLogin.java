package controller;

import java.io.IOException;

import application.Main;
import dao.FuncionarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    
    static Funcionario funcionario = new Funcionario();
    FuncionarioDAO funcDAO = new FuncionarioDAO();

    @FXML
    void actionLogin(ActionEvent event) throws IOException {
    	
    	String user = txtUser.getText();
    	String password = txtSenha.getText();
    	funcionario = funcDAO.autencticarUser(user, password);
    		if(funcionario.getCpfFuncionario() !=null && 
    				funcionario.getSenha() !=null) {
    		if(user.equals("")||password.equals("")) {
    			Alert erro = new Alert (AlertType.ERROR);
    			erro.setTitle("Erro de login");
    			erro.setHeaderText("Falha ao tentar realizar login");
    			erro.setContentText("Verifique as informações e tente novamente");
    			erro.show();
    		}else if(funcionario.getCpfFuncionario().equals(user) && 
    				funcionario.getSenha().equals(password)) {
    			Alert saudacao = new Alert(AlertType.INFORMATION);
    			saudacao.setTitle("Seja bem-vindo!");
    			saudacao.setHeaderText("Bem-vindo de volta!");
    			saudacao.setContentText("Olá! Seja bem-vindo "+ funcionario.getNomeFuncionario()+"!");
    			saudacao.show();
    			Main.TelaHome();
    		}
    		}else {
    			Alert erro = new Alert (AlertType.ERROR);
    			erro.setTitle("Erro de login");
    			erro.setHeaderText("Falha ao tentar realizar login");
    			erro.setContentText("Verifique as informações e tente novamente");
    			erro.show();

    }
   
  }

}
