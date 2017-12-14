package client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View {
  BorderPane root;
  Stage stage;
  Scene scene;

  public View(Stage stage) {

    root = new BorderPane();

    GridPane gp = new GridPane();

    Button selectFilter = new Button("Select Filter");
    Button selectInput = new Button("Select Image");

    gp.add(selectFilter,1,1);
    gp.add(selectInput,2,1);

    ImageView inputImage = new ImageView();
    ImageView outputImage = new ImageView();

    gp.add(inputImage,2,1);
    gp.add(outputImage,2,2);

    root.setCenter(gp);

    MenuItem item1 = new MenuItem();
    Menu m = new Menu();
    MenuBar mb = new MenuBar();
    root.setTop(mb);


    scene = new Scene(root);
    stage.setScene(scene);
  }
}
