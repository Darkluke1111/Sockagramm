package client;

import java.io.File;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
  
  private ChoiceBox selectFilter;
  private Button selectInput;
  private Button convert;

  private MenuBar mb;

  public View() {
    createScene();
  }
  
  private void createScene() {

    root = new BorderPane();

    gp = new GridPane();


    selectInput = new Button("Select Image");

    convert = new Button("Convert");

    selectFilter = new ChoiceBox();
    selectFilter.setItems(FXCollections.observableList(Arrays.asList(Filter.values())));
    selectFilter.getSelectionModel().selectFirst();

    gp.add(selectFilter, 1, 1);
    gp.add(selectInput, 2, 1);
    gp.add(convert, 3, 1);

    inputImage = new ImageView();
    outputImage = new ImageView();

    gp.add(inputImage, 1, 2);
    gp.add(outputImage, 2, 2);

    root.setCenter(gp);

    mb = new MenuBar();
    root.setTop(mb);


    scene = new Scene(root);
  }
  
  public void connectHandlers(Controller controller) {
    controller.setView(this);
    selectInput.setOnAction(controller::handleSelectImageFile);
    selectFilter.getSelectionModel()
            .selectedIndexProperty()
            .addListener(controller::handlerFilterChange);
    convert.setOnAction(controller::handleConversion);
  }

}
