package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import dao.ClienteDAO;
import dao.FuncionarioDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Cliente;
import model.Funcionario;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	public static void main(String[] args) {
		launch(args);
	}
}

