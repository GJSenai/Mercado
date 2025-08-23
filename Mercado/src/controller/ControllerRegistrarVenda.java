package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TableColumn<Produto, String> columnIndiceProduto;

	@FXML
	private TableColumn<Produto, String> columnPreco;

	@FXML
	private TableColumn<Produto, String> columnNomeProduto;

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
			txtPrecoUN.setText(String.format("R$ %.2f", precoUn));
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
			txtPrecoUN.setText(String.format("R$ %.2f", precoUn));
			txtPrecoUN.setEditable(false);

		} else {
			txtUN.setValue("");
			txtPrecoUN.setText("");
		}

	}

	@FXML
	void actionQuantidade(KeyEvent event) {
		if (!txtPrecoUN.getText().isEmpty())
			if (txtQuantidade.getText().matches("^\\d+$")) {
				{
					if (!txtQuantidade.getText().isEmpty()) {
						double quantidade = Double.parseDouble(txtQuantidade.getText());
						double precoUni = Double
								.parseDouble(txtPrecoUN.getText().replace("R$ ", "").replace(",", ".").trim());
						if (quantidade >= 15) {
							double desconto = (precoUni * quantidade) * 0.10;
							txtDesconto.setText("" + String.format("%.2f", desconto));
						} else if (quantidade < 15) {
							txtDesconto.setText("0.00");
						}
					}
				}
			}
		}
	
	private void carregarTableProdutos(ArrayList<Produto> ArrayProdutos) {
	       ObservableList<Produto> produtosVendidos =
	       FXCollections.observableArrayList(ArrayProdutos);
	       columnIndiceProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
	       columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
	       columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("estoque"));    
	       columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
	       columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
	       columnTipoUn.setCellValueFactory(new PropertyValueFactory<>("tipoUn"));
	       
	       tableProdutos.setItems(produtosVendidos);
	   }
		
	}
private ArrayList<Produto> arrayProdutos = new ArrayList<>();
	@FXML
	void actionAdicionar(ActionEvent event) {
		  if(txtNomeCliente.getText().isEmpty() || txtProduto.getText().isEmpty() ||
	               txtCpf.getText().isEmpty() || txtPrecoUN.getText().isEmpty() ||
	               txtQuantidade.getText().isEmpty()) {
	           Alert erro = new Alert(AlertType.ERROR);
	           erro.setTitle("Erro!");
	           erro.setContentText("Erro! verifique os campos e tente novamente!");
	           erro.show();
	       }else {
	           Produto produto = new Produto();
	           produto.setNomeProd(txtProduto.getText());
	           produto.setTipoUn(txtTipoUN.getValue());
	           produto.setPrecoUn(txtPrecoUN.getText());
	           produto.setPrecoTotal(txtValorTotal.getText());
	           produto.setEstoque(txtQuantidade.getText());
	           
	       double desconto = Double.parseDouble(txtDesconto.getText().replace(",", "."));
	       double precoUni = Double.parseDouble(txtPrecoUN.getText().replace(",", "."));
	       double quantidade = Double.parseDouble(txtQuantidade.getText());

	       double precoTotal = precoUni * quantidade - desconto;
	       produto.setPrecoTotal("R$ "+ precoTotal);
	       Double totalCompra = Double.parseDouble(txtValorTotal.getText().replace(",", "."));
	       totalCompra = totalCompra + precoTotal;
	       txtValorTotal.setText("" + String.format("%.2f", totalCompra));
	       }
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

		TextFields.bindAutoCompletion(txtCliente, controllerLogin.listaClientes)
				.setOnAutoCompleted(event -> actionCPFClick(null));
		TextFields.bindAutoCompletion(txtCpf, controllerLogin.listaClientes2)
				.setOnAutoCompleted(event -> actionNomeClick(null));
		TextFields.bindAutoCompletion(txtProduto, controllerLogin.listaProdutos)
				.setOnAutoCompleted(event -> actionProdutoClick(null));
		
		

	}

}
