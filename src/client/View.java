package client;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View {

  public Scene scene;
  public ImageView inputImage;
  public ImageView outputImage;
  
  
  private BorderPane root;
  
  private GridPane gp;
  
  private Button selectFilter;
  private Button selectInput;

  private MenuBar mb;

  private File defaultImage = new File("test.jpg");

  public View() {
    createScene();
  }
  
  private void createScene() {

    root = new BorderPane();

    gp = new GridPane();

    selectFilter = new Button("Select Filter");
    selectInput = new Button("Select Image");

    gp.add(selectFilter, 1, 1);
    gp.add(selectInput, 2, 1);

    inputImage = new ImageView(new Image(defaultImage.toURI().toString()));
    outputImage = new ImageView();

    gp.add(inputImage, 1, 2);
    gp.add(outputImage, 2, 2);

    root.setCenter(gp);

    mb = new MenuBar();
    root.setTop(mb);


    scene = new Scene(root);
  }
  
  public void connectHandlers(Controller controller) {
    selectInput.setOnAction(controller::selectImageFile);
  }

}
