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

    }

    @FXML
    void actionCPFType(KeyEvent event) {

    }

	@FXML
	void actionAdicionar(ActionEvent event) {

	}

	@FXML
	void actionCancelar(ActionEvent event) {

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

		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<Cliente> arrayClientes = clienteDAO.read();
		String[] listaClientes = new String[clienteDAO.read().size()];
		for (int i = 0; i < clienteDAO.read().size(); i++) {
			Cliente cliente = new Cliente();
			cliente = arrayClientes.get(i);
			listaClientes[i] = cliente.getNomeCliente();
		}
		
		TextFields.bindAutoCompletion(txtCliente, listaClientes);
		
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<Produto> arrayProdutos = produtoDAO.read();
		String[] listaProdutos = new String[produtoDAO.read().size()];
		for (int i = 0; i < produtoDAO.read().size(); i++) {
			Produto produto = new Produto();
			produto = arrayProdutos.get(i);
			listaProdutos[i] = produto.getNomeProd();
		}
		
		TextFields.bindAutoCompletion(txtProduto, listaProdutos);
		
		
	}
	
	

}
