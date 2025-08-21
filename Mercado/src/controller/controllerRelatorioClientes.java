package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Cliente;

public class controllerRelatorioClientes implements Initializable {

	@FXML
	private TableColumn<Cliente, String> columnCpf;

	@FXML
	private TableColumn<Cliente, String> columnEndereco;

	@FXML
	private TableColumn<Cliente, String> columnIndice;

	@FXML
	private TableColumn<Cliente, String> columnNome;

	@FXML
	private TableColumn<Cliente, String> columnTelefone;

	@FXML
	private TableColumn<Cliente, String> columnDataNasc;

	@FXML
	private TableColumn<Cliente, String> columnEmail;

	@FXML
	private TableView<Cliente> tableClientes;

	@FXML
	private Text txtUser;

	@FXML
	private TextField txtPesquisarCliente;

	@FXML
	private Button btCastrastrarCliente;

	@FXML
	private Button btClientes;

	@FXML
	private Button btEditarCliente;

	@FXML
	private Button btExcluirCliente;

	@FXML
	private Button btFuncionarios;

	@FXML
	private Button btHome;

	@FXML
	private Button btPesquisarCliente;

	@FXML
	private Button btProdutos;

	@FXML
	private Button btRegistrarVenda;

	@FXML
	private Button btSair;

	@FXML
	private Button btVendas;

	@FXML
	void actionCadastrarCliente(ActionEvent event) throws IOException {
		Main.TelaCadastroCliente();
		carregarTableCliente();
	}

	@FXML
	void actionClientes(ActionEvent event) {

	}

	public static Cliente clienteEditar = new Cliente();

	@FXML
	void actionEditarCliente(ActionEvent event) throws IOException {
		int linha = tableClientes.getSelectionModel().getSelectedIndex();
		if (linha == -1) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro!");
			erro.setContentText("Erro! Selecione ao menos um cliente para editar");
			erro.show();
		} else {
			clienteEditar = tableClientes.getItems().get(linha);
			Main.TelaCadastroCliente();

			if (txtPesquisarCliente.getText().isEmpty()) {
				carregarTableCliente();
			} else {
				pesquisarTableCliente();
			}
		}
	}

	@FXML
	void actionExcluirCliente(ActionEvent event) {
		int linha = tableClientes.getSelectionModel().getSelectedIndex();
		if (linha == -1) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro!");
			erro.setContentText("Erro! Selecione ao menos um cliente para excluir");
			erro.show();
		} else {
			Alert msg = new Alert(AlertType.CONFIRMATION);
			msg.setTitle("Excluir cliente");
			msg.setContentText("Deseja realmente excluir este cliente?");

			Optional<ButtonType> confirmacao = msg.showAndWait();
			if (confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
				Alert msg2 = new Alert(AlertType.INFORMATION);
				msg2.setTitle("Cliente excluido!");
				msg2.setContentText("O cliente foi excluido com sucesso!");
				msg2.show();
				Cliente cliente = new Cliente();
				ClienteDAO clienteDAO = new ClienteDAO();
				cliente = tableClientes.getItems().get(linha);
				clienteDAO.delete(cliente.getCpfCliente());
				carregarTableCliente();
			}
		}

	}

	@FXML
	void actionFuncionarios(ActionEvent event) {

	}

	@FXML
	void actionHome(ActionEvent event) throws IOException {
		Main.TelaHome();
	}

	@FXML
	void actionPesquisarCliente(ActionEvent event) {
		if (txtPesquisarCliente.getText().equals("")) {
			carregarTableCliente();
		} else {
			pesquisarTableCliente();
		}
	}

	@FXML
	void actionProdutos(ActionEvent event) {

	}

	@FXML
	void actionRegistrarVenda(ActionEvent event) throws IOException {
		Main.TelaRegistrarVenda();
	}

	@FXML
	void actionSair(ActionEvent event) throws IOException {
		Alert msg = new Alert(AlertType.CONFIRMATION);
		msg.setTitle("Sair do Sistema");
		msg.setHeaderText("Deseja realmente sair do Sistema?");
		msg.setContentText("Você esta saindo do Sistema. Clique em \"OK\" para confirmar!");

		Optional<ButtonType> resultado = msg.showAndWait();

		if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
			Main.TelaLogin();
		}

	}

	@FXML
	void actionVendas(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		txtUser.setText(controllerLogin.funcionario.getNomeFuncionario());

		carregarTableCliente();

		txtPesquisarCliente.setOnAction(e -> actionPesquisarCliente(null));

		clienteEditar = null;

	}

	private ObservableList<Cliente> listaClientes;

	public void carregarTableCliente() {
		ClienteDAO clienteDAO = new ClienteDAO();
		listaClientes = FXCollections.observableArrayList(clienteDAO.read());

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

		tableClientes.setItems(listaClientes);

	}

	public void pesquisarTableCliente() {
		ClienteDAO clienteDAO = new ClienteDAO();
		listaClientes = FXCollections.observableArrayList(clienteDAO.search(txtPesquisarCliente.getText()));

		columnIndice.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
		columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

		tableClientes.setItems(listaClientes);

	}

}
