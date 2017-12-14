package client;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Controller implements Listener{
  private View view;
  
  public void handleSelectImageFile() {
    
    FileChooser fc = new FileChooser();
    ExtensionFilter extFilterJPG = new ExtensionFilter("JPG files (*.jpg)", "*.JPG");
    fc.getExtensionFilters().add(extFilterJPG);
    
    File file = fc.showOpenDialog(null);
  }
  

}
