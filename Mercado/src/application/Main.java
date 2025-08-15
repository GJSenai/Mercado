package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Cliente;
import model.Funcionario;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene main;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			stage = primaryStage;
			
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/viewLogin.fxml"));
			main = new Scene(fxmlLogin);
			
			stage.setTitle("Tela de Login");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/carrinho_compras.png")));
			
			primaryStage.setScene(main);
			primaryStage.setTitle("Java App");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void TelaHome() throws IOException{
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/view/viewMainMenu.fxml"));
		Parent TelaHome = fxmlHome.load();
		main = new Scene(TelaHome);
		stage.setTitle("Pegue e Pague - Menu Principal");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public static void TelaLogin() throws IOException{
		FXMLLoader fxmlHome = new FXMLLoader();
		fxmlHome.setLocation(Main.class.getResource("/view/viewLogin.fxml"));
		Parent TelaLogin = fxmlHome.load();
		main = new Scene(TelaLogin);
		stage.setTitle("Pegue e Pague - Login");		
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	public static void TelaCliente() throws IOException{
		FXMLLoader fxmlCliente = new FXMLLoader();
		fxmlCliente.setLocation(Main.class.getResource("/view/viewRelatorioClientes.fxml"));
		Parent TelaCliente = fxmlCliente.load();
		main = new Scene(TelaCliente);
		stage.setTitle("Pegue-Pague - Clientes");		
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	private static Stage cadCliente;
	public static void TelaCadastroCliente() throws IOException {
		FXMLLoader clienteCadastro = new FXMLLoader();
		clienteCadastro.setLocation(Main.class.getResource("/view/viewCadastroCliente.fxml"));
		Parent cadastroCliente = clienteCadastro.load();
		Scene scene2 = new Scene(cadastroCliente);
		cadCliente = new Stage ();
		cadCliente.setTitle("Cadastro/Edição do Cliente");
		cadCliente.initModality(Modality.APPLICATION_MODAL);
		cadCliente.setScene(scene2);
		cadCliente.centerOnScreen();
		cadCliente.showAndWait();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}



