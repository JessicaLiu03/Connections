package connections;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ConnectionsApp extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Connections");
        primaryStage.setScene(new Scene(FXMLLoader.load(ConnectionsApp.class.getResource("App.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        ConnectionsApp.launch(args);
    }
}