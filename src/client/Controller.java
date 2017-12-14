package client;

import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.net.UnknownHostException;

public class Controller {
  private View view;

  private byte filterNumber = 0;

  private Image inputImage;
  private File inputFile;
  
  public void handleSelectImageFile(ActionEvent event) {
    
    FileChooser fc = new FileChooser();
    ExtensionFilter extFilterJPG = new ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    fc.getExtensionFilters().add(extFilterJPG);
    
    File file = fc.showOpenDialog(null);

    if(file != null) {
      inputFile = file;
      inputImage = new Image(file.toURI().toString());
      view.inputImage.setImage(inputImage);
    }
  }

  public void handlerFilterChange(Observable observable, Number oldVal, Number newVal) {
    filterNumber = newVal.byteValue();
  }

  public void handleConversion(ActionEvent actionEvent) {
    if(inputImage != null) {
      try {
        WebClient wc = new WebClient(WebClient.SERVER_IP, WebClient.SERVER_PORT);
        File outputFile = new File(inputFile.getParent() + "\\" + inputFile.getName().replace(".","_conv."));
        wc.sendRequest(filterNumber,inputFile, outputFile);
      } catch (UnknownHostException e) {
        e.printStackTrace();
      } catch (BadResponseException e) {
        e.printStackTrace();
      }
    }
  }

  public void setView(View view) {
    this.view = view;
  }

  public View getView() {
    return view;
  }


}
