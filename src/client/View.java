package client;

import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class View {

  public Scene scene;
  public ImageView inputImage;
  public ImageView outputImage;
  public Label notifier;
  
  
  private BorderPane root;

  private HBox controlls;
  
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
    gp.setMaxHeight(600);
    gp.setMaxWidth(800);

    controlls = new HBox();
    controlls.setSpacing(10);

    selectInput = new Button("Select Image");

    convert = new Button("Convert");

    selectFilter = new ChoiceBox();
    selectFilter.setItems(FXCollections.observableList(Arrays.asList(Filter.values())));
    selectFilter.getSelectionModel().selectFirst();


    controlls.getChildren().addAll(selectFilter, selectInput, convert);


    inputImage = new ImageView();
    inputImage.setPreserveRatio(true);
    inputImage.setFitHeight(200);
    outputImage = new ImageView();
    outputImage.setPreserveRatio(true);
    outputImage.setFitHeight(200);

    gp.add(controlls,1,1);
    gp.setColumnSpan(controlls,3);
    gp.add(inputImage, 1, 2);
    gp.add(outputImage, 2, 2);
    gp.setHgap(10);
    gp.setVgap(10);

    root.setCenter(gp);

    mb = new MenuBar();
    root.setTop(mb);

    notifier = new Label();
    root.setBottom(notifier);


    scene = new Scene(root);
  }
  
  public void connectHandlers(Controller controller) {
    controller.setView(this);
    selectInput.setOnAction(controller::handleSelectImageFile);
    selectFilter.getSelectionModel()
            .selectedIndexProperty()
            .addListener(controller::handleFilterChange);
    convert.setOnAction(controller::handleConversion);
    inputImage.setImage(controller.getInputImage());
    outputImage.setImage(controller.getOutputImage());
  }

}
