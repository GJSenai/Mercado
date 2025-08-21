package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;

public class controllerCadastroClientes implements Initializable {

	@FXML
	private Button btCancelar;

	@FXML
	private Button btSalvar;

	@FXML
	private TextField txtCpf;

	@FXML
	private DatePicker txtDataNasc;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtEndereco;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtTelefone;

	@FXML
	void actionCancelar(ActionEvent event) {
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	void actionSalvar(ActionEvent event) {
		if (txtNome.getText().isEmpty() || txtEndereco.getText().isEmpty() || txtDataNasc.getValue() == null) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro ao salvar!");
			erro.setContentText("Erro! Verifique se todas as informações foram preenchidas e tente novamente");
			erro.show();
		} else if (!validarCPF(txtCpf.getText())) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro ao salvar!");
			erro.setContentText("Erro! Verifique se o CPF é valido e tente novamente!");
			erro.show();
		} else {
			Cliente cliente = new Cliente();
			ClienteDAO clienteDAO = new ClienteDAO();

			cliente.setNomeCliente(txtNome.getText());
			cliente.setCpfCliente(txtCpf.getText());
			cliente.setDataNasc(txtDataNasc.getValue().toString());
			cliente.setEmail(txtEmail.getText());
			cliente.setTelefone(txtTelefone.getText());
			cliente.setEndereco(txtEndereco.getText());

			if (controllerRelatorioClientes.clienteEditar == null) {

				clienteDAO.create(cliente);

				Alert msg = new Alert(AlertType.INFORMATION);
				msg.setTitle("Sucesso!");
				msg.setContentText("Cliente cadastrado com sucesso!");
				msg.show();

				Stage stage = (Stage) btSalvar.getScene().getWindow();
				stage.close();
			} else {
				clienteDAO.update(cliente);
				Alert msg = new Alert(AlertType.INFORMATION);
				msg.setTitle("Sucesso!");
				msg.setContentText("Cliente atualizado com sucesso!");
				msg.show();
				controllerRelatorioClientes.clienteEditar = null;
				Stage stage = (Stage) btSalvar.getScene().getWindow();
				stage.close();
			}
		}
	}

	public static boolean validarCPF(String cpf) {
		cpf = cpf.replaceAll("\\D", ""); // Remove tudo que não é número
		if (!cpf.matches("\\d{11}") || cpf.matches("(\\d)\\1{10}"))
			return false;

		for (int j = 9; j < 11; j++) {
			int soma = 0;
			for (int i = 0; i < j; i++) {
				soma += (cpf.charAt(i) - '0') * ((j + 1) - i);
			}
			int digito = (soma * 10) % 11;
			if (digito == 10)
				digito = 0;
			if (digito != (cpf.charAt(j) - '0'))
				return false;
		}
		return true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		if (controllerRelatorioClientes.clienteEditar != null) {
			Cliente cliente = new Cliente();
			cliente = controllerRelatorioClientes.clienteEditar;
			txtNome.setText(cliente.getNomeCliente());
			txtEmail.setText(cliente.getEmail());
			txtEndereco.setText(cliente.getEndereco());
			txtTelefone.setText(cliente.getTelefone());
			txtCpf.setText(cliente.getCpfCliente());

			String dataNasc = cliente.getDataNasc();
			dataNasc = dataNasc.replace("-", "/");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(dataNasc, format);
			txtDataNasc.setValue(localDate);
		}
	}
}
