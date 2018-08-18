/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.dialog;

import djf.modules.AppLanguageModule;
import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import static gologolo.goLogoLoPropertyType.LOGO_HEIGHT_LABEL;
import static gologolo.goLogoLoPropertyType.LOGO_RESIZE_CANCEL;
import static gologolo.goLogoLoPropertyType.LOGO_RESIZE_CANCEL_LABEL_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_RESIZE_OK;
import static gologolo.goLogoLoPropertyType.LOGO_RESIZE_OK_LABEL_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_WIDTH_LABEL;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_BUTTON;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_GRID;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_PANE;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_PROMPT;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_TEXT_FIELD;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author jasoncao
 */
public class ResizePaneDialog extends Stage {
    GoLogoLo app; 
   
    GridPane gridPane;
    String height;
    String width;
    Label heightLabel = new Label();    
    Label widthLabel = new Label();
    TextField heightTextField = new TextField();   
    TextField widthTextField = new TextField();   
    HBox okCancelPane = new HBox();
    Button okButton = new Button();
    Button cancelButton = new Button();


    EventHandler cancelHandler;
    EventHandler addItemOkHandler;
    EventHandler editItemOkHandler;  
  

     public ResizePaneDialog(GoLogoLo initApp) {
        // KEEP THIS FOR WHEN THE WORK IS ENTERED
      
        height="";
        width="";
        app = initApp;
        // EVERYTHING GOES IN HERE
        gridPane = new GridPane();
        gridPane.getStyleClass().add(CLASS_LOGO_DIALOG_GRID);
        initDialog();

        // NOW PUT THE GRID IN THE SCENE AND THE SCENE IN THE DIALOG
        Scene scene = new Scene(gridPane);
        this.setScene(scene);
        
        // SETUP THE STYLESHEET
        app.getGUIModule().initStylesheet(this);
        scene.getStylesheets().add(CLASS_LOGO_DIALOG_GRID);                             
        
        // MAKE IT MODAL
        this.initOwner(app.getGUIModule().getWindow());
        this.initModality(Modality.APPLICATION_MODAL);
      }
    
    
        protected void initGridNode(Node node, Object nodeId, String styleClass, int col, int row, int colSpan, int rowSpan, boolean isLanguageDependent) {
        // GET THE LANGUAGE SETTINGS
        AppLanguageModule languageSettings = app.getLanguageModule();
        
        // TAKE CARE OF THE TEXT
        if (isLanguageDependent) {
            languageSettings.addLabeledControlProperty(nodeId + "_TEXT", ((Labeled)node).textProperty());
            ((Labeled)node).setTooltip(new Tooltip(""));
            languageSettings.addLabeledControlProperty(nodeId + "_TOOLTIP", ((Labeled)node).tooltipProperty().get().textProperty());
        }
        
        // PUT IT IN THE UI
        if (col >= 0)
            gridPane.add(node, col, row, colSpan, rowSpan);

        // SETUP IT'S STYLE SHEET
        node.getStyleClass().add(styleClass);
    }
    
        private void initDialog() {
        // THE NODES ABOVE GO DIRECTLY INSIDE THE GRID
       
        initGridNode(heightLabel,           LOGO_HEIGHT_LABEL,        CLASS_LOGO_DIALOG_PROMPT,       0, 1, 1, 1, true);
        initGridNode(heightTextField,         null,                   CLASS_LOGO_DIALOG_TEXT_FIELD,   1, 1, 1, 1, false);
        initGridNode(widthLabel,           LOGO_WIDTH_LABEL,        CLASS_LOGO_DIALOG_PROMPT,       0, 2, 1, 1, true);
        initGridNode(widthTextField,         null,                   CLASS_LOGO_DIALOG_TEXT_FIELD,   1, 2, 1, 1, false);
        initGridNode(okCancelPane,          null,                                   CLASS_LOGO_DIALOG_PANE,    0, 3, 3, 1, false);

        okButton = new Button();
        cancelButton = new Button();
       app.getGUIModule().addGUINode(LOGO_RESIZE_OK, okButton);
       app.getGUIModule().addGUINode(LOGO_RESIZE_CANCEL, cancelButton);
       okButton.getStyleClass().add(CLASS_LOGO_DIALOG_BUTTON);
       cancelButton.getStyleClass().add(CLASS_LOGO_DIALOG_BUTTON);
       okCancelPane.getChildren().add(okButton);
       okCancelPane.getChildren().add(cancelButton);
       okCancelPane.setAlignment(Pos.CENTER);

        AppLanguageModule languageSettings = app.getLanguageModule();
        languageSettings.addLabeledControlProperty(LOGO_RESIZE_OK+ "_TEXT",    okButton.textProperty());
        languageSettings.addLabeledControlProperty(LOGO_RESIZE_CANCEL + "_TEXT",    cancelButton.textProperty());
       
        // AND SETUP THE EVENT HANDLERS
       
        okButton.setOnAction(e->{
            processCompleteWork();
       });
       cancelButton.setOnAction(e->{
         height="";
         width="";
       
           this.hide();
        });   
    }
        
    public void showResizeDialog(){
         PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        heightTextField.setText("");
        widthTextField.setText("");
        
        String okText=props.getProperty(LOGO_RESIZE_OK_LABEL_TEXT);
        okButton.setText(okText);
        String cancelText=props.getProperty( LOGO_RESIZE_CANCEL_LABEL_TEXT);
        cancelButton.setText(cancelText);    
        // AND OPEN THE DIALOG
        showAndWait();
    }
    
    public void processCompleteWork(){
        String heightInput = heightTextField.getText();
        String widthInput = widthTextField.getText();
        
         if (!heightInput.equals("0")||!widthInput.equals("0")) {
             double heightUserInput=Double.parseDouble((heightInput));
             double widthUserInput=Double.parseDouble((widthInput));
             height=heightInput;
              width=widthInput;
         }
         else{
             //create dialog saying name field is empty
         }
         this.hide();
    }

    public Double getResizeHeight() {
        return Double.parseDouble(height);
    }

    public Double getResizeWidth() {
          return Double.parseDouble(width);
    }
    
    
}
