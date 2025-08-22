package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.prefs.Preferences;

import application.Main;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Cliente;
import model.Funcionario;
import model.Produto;

public class controllerLogin {

	@FXML
	private Button btLogin;

	@FXML
	private Button btSairLogin;

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
	void actionLogin(ActionEvent event) throws IOException {
		String user = txtUser.getText();
		String password = txtSenha.getText();
		funcionario = funcDAO.autencticarUser(user, password);

		if (funcionario.getCpfFuncionario() != null && funcionario.getSenha() != null) {
			if (user.equals("") || password.equals("")) {
				showError("Erro de login", "Falha ao tentar realizar login",
						"Verifique as informações e tente novamente");
			} else if (funcionario.getCpfFuncionario().equals(user) && funcionario.getSenha().equals(password)) {
				// Lembrar login
				Preferences prefs = Preferences.userNodeForPackage(controllerLogin.class);
				if (cbLembrarLogin.isSelected()) {
					prefs.put(PREF_KEY_USER, user);
				} else {
					prefs.remove(PREF_KEY_USER);
				}
				showInfo("Seja bem-vindo!", "Bem-vindo de volta!",
						"Olá! Seja bem-vindo " + funcionario.getNomeFuncionario() + "!");
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

	public static ClienteDAO clienteDAO = new ClienteDAO();
	ArrayList<Cliente> arrayClientes = clienteDAO.read();
	public static String[] listaClientes = new String[clienteDAO.read().size()];
	public static String[] listaClientes2 = new String[clienteDAO.read().size()];
	public static ProdutoDAO produtoDao = new ProdutoDAO();
	ArrayList<Produto> arrayProdutos = produtoDao.read();
	public static String[] listaProdutos = new String[produtoDao.read().size()];

	@FXML
	void actionSairLogin(ActionEvent event) {
		// 1. Cria a caixa de diálogo de confirmação.
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sair");
		alert.setHeaderText("Você está prestes a fechar a aplicação.");
		alert.setContentText("Deseja realmente sair?");

		// 2. Mostra a caixa de diálogo e espera pela resposta do usuário.
		Optional<ButtonType> result = alert.showAndWait();

		// 3. Verifica se o usuário clicou em "OK".
		if (result.isPresent() && result.get() == ButtonType.OK) {
			// Se sim, fecha a aplicação.
			System.out.println("Saindo da aplicação...");
			Platform.exit();
		}

	}

	@FXML
	public void initialize() {
		Preferences prefs = Preferences.userNodeForPackage(controllerLogin.class);
		String savedUser = prefs.get(PREF_KEY_USER, "");
		if (!savedUser.isEmpty()) {
			txtUser.setText(savedUser);
			cbLembrarLogin.setSelected(true);
		}
		txtSenha.setOnAction(e -> {
			try {
				actionLogin(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		for (int i = 0; i < clienteDAO.read().size(); i++) {
			Cliente cliente = new Cliente();
			cliente = arrayClientes.get(i);
			listaClientes[i] = cliente.getNomeCliente();
			listaClientes2[i] = cliente.getCpfCliente();
		}
		for (int i = 0; i < produtoDao.read().size(); i++) {
			Produto produto = new Produto();
			produto = arrayProdutos.get(i);
			listaProdutos[i] = produto.getNomeProd();
		}
	}
}
