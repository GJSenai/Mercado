package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import model.Produto;

public class controllerMainMenu {

    @FXML
    private Button btClientes;

    @FXML
    private Button btFuncionarios;

    @FXML
    private Button btHome;

    @FXML
    private Button btProdutos;

    @FXML
    private Button btRegistrarVenda;

    @FXML
    private Button btSair;

    @FXML
    private Button btVendas;

    @FXML
    private TableColumn<Produto, String> columnCodBarra;

    @FXML
    private TableColumn<Produto, String> columnCodBarra2;

    @FXML
    private TableColumn<Produto, String> columnDataVal;

    @FXML
    private TableColumn<Produto, String> columnEstoque;

    @FXML
    private TableColumn<Produto, String> columnIndice;

    @FXML
    private TableColumn<Produto, String> columnIndice2;

    @FXML
    private TableColumn<Produto, String> columnNomeProduto;

    @FXML
    private TableColumn<Produto, String> columnNomeProduto2;

    @FXML
    private TableColumn<Produto, String> columnTipoUn;

    @FXML
    private TableView<Produto> tableProdutosAV;

    @FXML
    private TableView<Produto> tableProdutosEB;

    @FXML
    private Text txtTotalVendido;

    @FXML
    private Text txtUser;

    @FXML
    void actionFuncionarios(ActionEvent event) {

    }

    @FXML
    void actionHome(ActionEvent event) {

    }

    @FXML
    void actionProdutos(ActionEvent event) {

    }

    @FXML
    void actionRegistrarVenda(ActionEvent event) {

    }

    @FXML
    void actionSair(ActionEvent event) {

    }

    @FXML
    void actionVendas(ActionEvent event) {

    }

    @FXML
    void btClientes(ActionEvent event) {

    }

}
