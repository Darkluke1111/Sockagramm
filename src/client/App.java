package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

  public static void main(String args[]) {
    Application.launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    View view = new View();
    Controller controller = new Controller();
    view.connectHandlers(controller);
    primaryStage.setScene(view.scene);
    primaryStage.show();
  }
}
