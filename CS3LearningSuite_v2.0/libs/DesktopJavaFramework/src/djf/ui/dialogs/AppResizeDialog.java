/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package djf.ui.dialogs;

import static djf.AppPropertyType.CHANGE_DIMENSION_LABEL;
import static djf.AppPropertyType.GOLOGOLO_CHANGE_LOGO_DIMENSIONS_LABEL;
import static djf.AppPropertyType.GOLOGOLO_HEIGHT_LABEL;
import static djf.AppPropertyType.GOLOGOLO_WIDTH_LABEL;
import static djf.AppPropertyType.TITLE_PANE;
import static djf.AppPropertyType.TITLE_TOP;
import static djf.AppPropertyType.ZOOM_IN_BUTTON;
import static djf.AppPropertyType.ZOOM_OUT_BUTTON;
import djf.AppTemplate;
import djf.modules.AppLanguageModule;
import djf.ui.AppNodesBuilder;
import static djf.ui.style.DJFStyle.CLASS_DJF_TOOLBAR_PANE;
import static djf.ui.style.DJFStyle.CLASS_DJF_WELCOME_HEADER;
import static djf.ui.style.DJFStyle.CLASS_DJF_WELCOME_NEW_PANE;
import static djf.ui.style.DJFStyle.CLASS_SNAP_LABEL;
import static djf.ui.style.DJFStyle.CLASS_TDLM_DIALOG_GRID;
import static djf.ui.style.DJFStyle.CLASS_TDLM_TEXT_FIELD;
import javafx.event.EventHandler;
import static javafx.geometry.Pos.CENTER;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author jasoncao
 */
public class AppResizeDialog extends Stage{

    public AppResizeDialog() {
     BorderPane pane=new BorderPane();
     this.setTitle("Change Logo Dimension");
     Label height=new Label("Height:");
     Label width=new Label("Width: ");
     TextField text1=new TextField();
     TextField text2=new TextField();
     VBox dimInfo=new VBox();
     HBox heightPane=new HBox();
     HBox widthPane=new HBox();
     heightPane.getChildren().add(height);
     heightPane.getChildren().add(text1);
     widthPane.getChildren().add(width);
     widthPane.getChildren().add(text2);
     Label titleLabel=new Label("    Change The Logo Dimensions");
     dimInfo.getChildren().add(heightPane);
     dimInfo.getChildren().add(widthPane);
  
    pane.setTop(titleLabel);
    pane.setCenter(dimInfo);
    
     dimInfo.setAlignment(CENTER);
     
     
     HBox buttons=new HBox();
     Button okButton=new Button();
     Button cancelButton=new Button();
     okButton.setText("Confirm");
     cancelButton.setText("Cancel");
     buttons.getChildren().add(okButton);
     buttons.getChildren().add(cancelButton);
     okButton.setOnAction(e->{
        this.hide();
     });
     cancelButton.setOnAction(e->{
        this.hide();
     });
        pane.setBottom(buttons);
        buttons.setAlignment(CENTER);
     Scene scene=new Scene(pane);
     
     this.setScene(scene);
             
        
        
    }
}