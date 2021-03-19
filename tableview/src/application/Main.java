package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			  
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("application/web.fxml"));
            Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("web.fxml").toExternalForm());
			//scene.getStylesheets().add("/application/application.css");
			primaryStage.setScene(scene);
			primaryStage.show();
			scene.getRoot().requestFocus();//key event needs focus
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
