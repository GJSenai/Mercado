package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import dao.VendaProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import model.Venda;
import model.VendaProduto;

public class ControllerRegistrarVenda implements Initializable {

	@FXML
	private Button btAdicionar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btRegistrar;

	@FXML
	private Button btRemover;

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
	private TableColumn<Produto, String> columnTipoUN;

	@FXML
	private Label lblNumeroVenda;

	@FXML
	private TableView<Produto> tableProdutos;

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
		ObservableList<Produto> produtosVendidos = FXCollections.observableArrayList(ArrayProdutos);
		columnIndiceProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProd"));
		columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		columnPreco.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnTipoUN.setCellValueFactory(new PropertyValueFactory<>("tipoUn"));

		tableProdutos.setItems(produtosVendidos);
	};

	private ArrayList<Produto> arrayProdutos = new ArrayList<>();

	@FXML
	void actionAdicionar(ActionEvent event) {
		if (txtCliente.getText().isEmpty() || txtProduto.getText().isEmpty() || txtCpf.getText().isEmpty()
				|| txtPrecoUN.getText().isEmpty() || txtQuantidade.getText().isEmpty()) {

			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro!");
			erro.setContentText("Erro! verifique os campos e tente novamente!");
			erro.show();
			return;
		}

		Produto produto = new Produto();
		produto.setNomeProd(txtProduto.getText());
		produto.setTipoUn(txtUN.getValue());
		produto.setPrecoUn(txtPrecoUN.getText());
		produto.setPrecoTotal(txtTotalCompra.getText());
		produto.setEstoque(txtQuantidade.getText());

		// Validação segura
		double desconto = 0.0;
		String descText = txtDesconto.getText().replace(",", ".").trim();
		if (!descText.isEmpty()) {
			desconto = Double.parseDouble(descText);
		}

		double precoUni = 0.0;
		String precoUnText = txtPrecoUN.getText().replace("R$ ", "").replace(",", ".").trim();
		if (!precoUnText.isEmpty()) {
			precoUni = Double.parseDouble(precoUnText);
		}

		double quantidade = 0.0;
		String qtdText = txtQuantidade.getText().trim();
		if (!qtdText.isEmpty()) {
			quantidade = Double.parseDouble(qtdText);
		}

		double precoTotal = precoUni * quantidade - desconto;
		produto.setPrecoTotal("R$ " + String.format("%.2f", precoTotal));

		double totalCompra = 0.0;
		String totalText = txtTotalCompra.getText().replace(",", ".").trim();
		if (!totalText.isEmpty()) {
			totalCompra = Double.parseDouble(totalText);
		}

		totalCompra = totalCompra + precoTotal;
		txtTotalCompra.setText("" + String.format("%.2f", totalCompra));
		produto.setIdProduto("" + arrayProdutos.size() + 1);
		arrayProdutos.add(produto);
		carregarTableProdutos(arrayProdutos);

		txtProduto.setText("");
		txtDesconto.setText("0,00");
		txtQuantidade.setText("");
		txtPrecoUN.setText("");
		txtUN.setValue("");
	}

	@FXML
	void actionCancelar(ActionEvent event) {
		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();
	}

	@FXML
	void actionRegistrar(ActionEvent event) {
		if (!txtFormaPagamento.getValue().toString().isEmpty() || !arrayProdutos.isEmpty()) {

			Venda venda = new Venda();
			venda.setIdFuncionario(controllerLogin.funcionario.getIdFuncionario());

			Cliente cliente = new Cliente();
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente.setCpfCliente(txtCpf.getText());
			cliente = clienteDAO.search(cliente.getCpfCliente()).get(0);
			venda.setIdCliente(cliente.getIdCliente());
			venda.setPrecoTotal(txtTotalCompra.getText().replace(",", "."));
			venda.setQuantTotal("" + arrayProdutos.size());
			venda.setFormaPag(txtFormaPagamento.getValue());

			VendaDAO vendaDAO = new VendaDAO();
			vendaDAO.create(venda);

			for (int i = 0; i < arrayProdutos.size(); i++) {
				venda = vendaDAO.read().getLast();

				VendaProduto vendaProduto = new VendaProduto();
				VendaProdutoDAO vendaProdutoDAO = new VendaProdutoDAO();
				Produto produto = new Produto();
				ProdutoDAO produtoDAO = new ProdutoDAO();
				produto = arrayProdutos.get(i);

				vendaProduto.setIdVenda(venda.getIdVenda());
				vendaProduto.setQuantidade(produto.getEstoque());
				vendaProduto.setValorTotal(produto.getPrecoTotal().replace("R$ ", "").replace(",", ".").trim());
				produto = produtoDAO.search(produto.getNomeProd()).get(0);
				vendaProduto.setIdProduto(produto.getIdProduto());
				vendaProdutoDAO.create(vendaProduto);
			}

			Alert aviso = new Alert(AlertType.INFORMATION);
			aviso.setTitle("Venda registrada");
			aviso.setContentText("A venda foi registrada com sucesso");
			aviso.show();
			arrayProdutos.clear();
			carregarTableProdutos(arrayProdutos);
			txtProduto.setText("");
			txtPrecoUN.setText("");
			txtDesconto.setText("0,00");
			txtQuantidade.setText("");
			txtUN.setValue("");
			txtCliente.setText("");
			txtCpf.setText("");
			txtTotalCompra.setText("0,00");
		}

	}

	@FXML
	void actionRemover(ActionEvent event) {
		int linha = tableProdutos.getSelectionModel().getSelectedIndex();
		if (linha == -1) {
			Alert erro = new Alert(AlertType.ERROR);
			erro.setTitle("Erro!");
			erro.setContentText("Erro! Selecione um produto para excluir!");
			erro.show();
		} else {
			Alert msg = new Alert(AlertType.CONFIRMATION);
			msg.setTitle("Excluir produto");
			msg.setContentText("Deseja realmente excluir este Produto da venda?");

			Optional<ButtonType> confirmacao = msg.showAndWait();
			if (confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
				Alert msg2 = new Alert(AlertType.CONFIRMATION);
				msg2.setTitle("Produto excluido!");
				msg2.setContentText("O produto foi excluido com sucesso da Veda!");
				msg2.show();
				arrayProdutos.remove(linha);
				carregarTableProdutos(arrayProdutos);
			}
		}
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
