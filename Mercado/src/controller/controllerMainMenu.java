package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.scene.text.Text;
import model.Produto;

public class controllerMainMenu implements Initializable {

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
    private TableColumn<Produto, String> columnEstoque2;

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
    void actionHome(ActionEvent event) throws IOException {
    	Main.TelaHome();
    }

    @FXML
    void actionProdutos(ActionEvent event) {

    }

    @FXML
    void actionRegistrarVenda(ActionEvent event) {

    }

    @FXML
    void actionSair(ActionEvent event) throws IOException{
        Alert msg = new Alert(AlertType.CONFIRMATION);
        msg.setTitle("Sair do Sistema");
        msg.setHeaderText("Deseja realmente sair do Sistema?");
        msg.setContentText("Você esta saindo do Sistema. Clique em \"OK\" para confirmar!");

        Optional<ButtonType> resultado = msg.showAndWait();

        if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Main.TelaLogin();
        }
    }

    @FXML
    void actionVendas(ActionEvent event) {

    }

    @FXML
    void actionClientes(ActionEvent event) throws IOException {
    	Main.TelaCliente();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Por quê: garante que o nome apareça assim que o controller inicia
        txtUser.setText(controllerLogin.funcionario.getNomeFuncionario());
        carregarTabelaProdutoEB();
        carregarTabelaProdutoAV();

        FuncionarioDAO funDAO = new FuncionarioDAO();
        String totalVendido = funDAO.readTotalVendido(controllerLogin.funcionario.getCpfFuncionario());

        if(totalVendido == null) {
            txtTotalVendido.setText("R$ 0,00");
        } else {
            double total = Double.parseDouble(totalVendido);
            txtTotalVendido.setText("R$" + String.format(Locale.forLanguageTag("pt-BR"), "%.2f", total));
        }
    }

    ObservableList<Produto> ListaProdutosEB;
    public void carregarTabelaProdutoEB() {

        ProdutoDAO prodDAO = new ProdutoDAO();
        ListaProdutosEB = FXCollections.observableArrayList(prodDAO.readProdutoEB());

        columnIndice.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProd"));
        columnCodBarra.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
        columnTipoUn.setCellValueFactory(new PropertyValueFactory<>("tipoUn"));
        columnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        
        applyStockColoringToColumn(columnIndice);
        applyStockColoringToColumn(columnNomeProduto);
        applyStockColoringToColumn(columnCodBarra);
        applyStockColoringToColumn(columnTipoUn);
        applyStockColoringToColumn(columnEstoque);
        tableProdutosEB.setItems(ListaProdutosEB);
    }

    ObservableList<Produto> ListaProdutosAV;
    public void carregarTabelaProdutoAV() {

        ProdutoDAO prodDAO = new ProdutoDAO();
        ListaProdutosAV = FXCollections.observableArrayList(prodDAO.readProdutoAV());

        columnIndice2.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        columnNomeProduto2.setCellValueFactory(new PropertyValueFactory<>("nomeProd"));
        columnCodBarra2.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
        columnEstoque2.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        columnDataVal.setCellValueFactory(new PropertyValueFactory<>("dataVal"));

        // aplica coloração nas colunas da tabela AV com base no dataVal do produto da linha
        applyExpiryColoring(columnIndice2);
        applyExpiryColoring(columnNomeProduto2);
        applyExpiryColoring(columnCodBarra2);
        applyExpiryColoring(columnEstoque2);
        applyExpiryColoring(columnDataVal);

        tableProdutosAV.setItems(ListaProdutosAV);
    }

    // ===== Helpers de coloração por validade =====

    private <T> void applyExpiryColoring(TableColumn<Produto, T> column) {
        column.setCellFactory(makeColoredCellFactory());
    }

    private <T> Callback<TableColumn<Produto, T>, TableCell<Produto, T>> makeColoredCellFactory() {
        return col -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                    setTextFill(Color.BLACK);
                    setStyle("");
                    return;
                }

                setText(item == null ? "" : String.valueOf(item));

                Produto prod = (Produto) getTableRow().getItem();
                LocalDate validade = parseValidade(prod);

                if (validade == null) {
                    // sem data → fica neutro
                    setTextFill(Color.BLACK);
                    return;
                }

                long dias = ChronoUnit.DAYS.between(LocalDate.now(), validade);

                if (dias <= 10) { // inclui vencidos (dias < 0) e até 10 dias
                    setTextFill(Color.RED);
                } else if (dias <= 90) {
                    setTextFill(Color.GOLDENROD);
                } else {
                    setTextFill(Color.GREEN);
                }
            }
        };
    }

    /**
     * Converte Produto.dataVal (String ou outro tipo) em LocalDate.
     * Por quê: bancos costumam variar formato; aceitamos dd/MM/uuuu e uuuu-MM-dd.
     */
    private LocalDate parseValidade(Produto p) {
        Object raw = null;
        try {
            // ajuste o getter se necessário
            raw = p.getDataVal();
        } catch (Exception ignored) { }

        if (raw == null) return null;

        if (raw instanceof LocalDate) return (LocalDate) raw;

        if (raw instanceof java.time.LocalDateTime) {
            return ((java.time.LocalDateTime) raw).toLocalDate();
        }

        if (raw instanceof java.util.Date) {
            return ((java.util.Date) raw).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
        }

        String s = String.valueOf(raw).trim();
        if (s.isEmpty()) return null;

        // Tenta ISO (yyyy-MM-dd) e pt-BR (dd/MM/yyyy)
        LocalDate d = tryParse(s, isoFormatter());
        if (d != null) return d;

        d = tryParse(s, brFormatter());
        return d;
    }

    private LocalDate tryParse(String s, DateTimeFormatter f) {
        try {
            return LocalDate.parse(s, f);
        } catch (Exception e) {
            return null;
        }
    }

    private DateTimeFormatter isoFormatter() {
        return new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendLiteral('-')
                .appendValue(ChronoField.MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(ChronoField.DAY_OF_MONTH, 2)
                .toFormatter()
                .withResolverStyle(ResolverStyle.STRICT);
    }

    private DateTimeFormatter brFormatter() {
        return new DateTimeFormatterBuilder()
                .appendValue(ChronoField.DAY_OF_MONTH, 1, 2, SignStyle.NOT_NEGATIVE)
                .appendLiteral('/')
                .appendValue(ChronoField.MONTH_OF_YEAR, 1, 2, SignStyle.NOT_NEGATIVE)
                .appendLiteral('/')
                .appendValue(ChronoField.YEAR, 4)
                .toFormatter(new Locale("pt", "BR"))
                .withResolverStyle(ResolverStyle.STRICT);
    }
    
    // ================= Helpers: Coloração por Estoque =================

    private <T> void applyStockColoringToColumn(TableColumn<Produto, T> column) {
        column.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setText(null);
                    setTextFill(Color.BLACK);
                    setStyle("");
                    return;
                }

                setText(item == null ? "" : String.valueOf(item));

                Produto prod = (Produto) getTableRow().getItem();
                int qtd;
                try {
                    qtd = Integer.parseInt(
                        prod.getEstoque() == null ? "0" : prod.getEstoque().trim()
                    );
                } catch (NumberFormatException e) {
                    qtd = 0; // inválido → crítico
                }

                if (qtd <= 10) {
                    setTextFill(Color.RED);
                } else if (qtd <= 20 || qtd > 10) {
                    setTextFill(Color.GOLDENROD);
                } else {
                    setTextFill(Color.GREEN);
                }
            }
        });
    }
}
