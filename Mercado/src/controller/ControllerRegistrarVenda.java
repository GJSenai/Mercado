package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Cliente;
import model.Produto;

public class ControllerRegistrarVenda implements Initializable {

	@FXML
	private Button btAdicionar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btRegistrar;

	@FXML
	private TableColumn<Produto, String> columnDesconto;

	@FXML
	private TableColumn<Produto, String> columnItem;

	@FXML
	private TableColumn<Produto, String> columnPreco;

	@FXML
	private TableColumn<Produto, String> columnProduto;

	@FXML
	private TableColumn<Produto, String> columnQuantidade;

	@FXML
	private TableColumn<Produto, String> columnPrecoTotal;

	@FXML
	private Label lblNumeroVenda;

	@FXML
	private TableView<?> tableProdutos;

	@FXML
	private TextField txtCliente;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtDesconto;

	@FXML
	private ChoiceBox<String> txtFormaPagamento;

	@FXML
	private TextField txtPrecoUN;

	@FXML
	private TextField txtProduto;

	@FXML
	private TextField txtQuantidade;

	@FXML
	private TextField txtTotalCompra;

	@FXML
	private ChoiceBox<String> txtUN;

	@FXML
	private TextField txtVendedor;

	@FXML
	void actionCPFClick(MouseEvent event) {
		if (txtCliente.getText().length() > 2) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setNomeCliente(txtCliente.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();
			clientes = clienteDAO.search(cliente.getNomeCliente());
			cliente = clientes.get(0);
			txtCpf.setText(cliente.getCpfCliente());

		} else {
			txtCpf.setText("");
		}

	}

	@FXML
	void actionNomeClick(MouseEvent event) {
		if (txtCpf.getText().length() > 2) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setCpfCliente(txtCpf.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();
			clientes = clienteDAO.search(cliente.getCpfCliente());
			cliente = clientes.get(0);
			txtCliente.setText(cliente.getNomeCliente());

		} else {
			txtCliente.setText("");
		}
	}

	@FXML
	void actionCPFType(KeyEvent event) {
		if (txtCliente.getText().length() > 2) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setNomeCliente(txtCliente.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();
			clientes = clienteDAO.search(cliente.getNomeCliente());
			cliente = clientes.get(0);
			txtCpf.setText(cliente.getCpfCliente());
		} else {
			txtCpf.setText("");
		}

	}

	@FXML
	void actionNomeType(KeyEvent event) {
		if (txtCpf.getText().length() > 2) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setCpfCliente(txtCpf.getText());
			ArrayList<Cliente> clientes = new ArrayList<>();
			clientes = clienteDAO.search(cliente.getCpfCliente());
			cliente = clientes.get(0);
			txtCliente.setText(cliente.getNomeCliente());

		} else {
			txtCliente.setText("");
		}

	}

	@FXML
	void actionProdutoClick(MouseEvent event) {
		if (txtProduto.getText().length() > 2) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = new Produto();
			produto.setNomeProd(txtProduto.getText());
			ArrayList<Produto> produtos = new ArrayList<>();
			produtos = produtoDAO.search(produto.getNomeProd());
			produto = produtos.get(0);
			txtUN.setValue(produto.getTipoUn());
			double precoUn = Double.parseDouble(produto.getPrecoUn());
			txtPrecoUN.setText(String.format("R$ %.2f",precoUn));
			txtPrecoUN.setEditable(false);

		} else {
			txtUN.setValue("");
			txtPrecoUN.setText("");
		}
	}

	@FXML
	void actionProdutoType(KeyEvent event) {

		if (txtProduto.getText().length() > 2) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = new Produto();
			produto.setNomeProd(txtProduto.getText());
			ArrayList<Produto> produtos = new ArrayList<>();
			produtos = produtoDAO.search(produto.getNomeProd());
			produto = produtos.get(0);
			txtUN.setValue(produto.getTipoUn());
			double precoUn = Double.parseDouble(produto.getPrecoUn());
			txtPrecoUN.setText(String.format("R$ %.2f",precoUn));
			txtPrecoUN.setEditable(false);

		} else {
			txtUN.setValue("");
			txtPrecoUN.setText("");
		}

	}

	@FXML
	void actionAdicionar(ActionEvent event) {

	}

	@FXML
	void actionCancelar(ActionEvent event) {
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	void actionRegistrar(ActionEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		txtUN.getItems().addAll("UN", "LT", "KG", "gm");
		txtUN.setValue("UN");
		txtFormaPagamento.getItems().addAll("Pix", "Dinheiro", "Cartão");
		txtFormaPagamento.setValue("Dinheiro");
		txtVendedor.setText(controllerLogin.funcionario.getNomeFuncionario());
		txtVendedor.setEditable(false);
		txtPrecoUN.setEditable(false);
		txtTotalCompra.setEditable(false);

		

		TextFields.bindAutoCompletion(txtCliente, controllerLogin.listaClientes).setOnAutoCompleted(event -> actionCPFClick(null));
		TextFields.bindAutoCompletion(txtCpf, controllerLogin.listaClientes2).setOnAutoCompleted(event -> actionNomeClick(null));
		TextFields.bindAutoCompletion(txtProduto, controllerLogin.listaProdutos).setOnAutoCompleted(event -> actionProdutoClick(null));

	}

}
