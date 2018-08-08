/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.dialog;

import djf.modules.AppLanguageModule;
import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import static gologolo.goLogoLoPropertyType.LOGO_ADD_TEXT_DIALOG_CANCEL_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_ADD_TEXT_DIALOG_OK_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_ADD_HEADER;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_CANCEL_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_HEADER;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_HEADER_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_OK_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_DIALOG_CANCEL_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_DIALOG_OK_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_TEXT_LABEL;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_TEXT_LABEL_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_NAME_LABEL;
import static gologolo.goLogoLoPropertyType.LOGO_TEXT_LABEL;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_BUTTON;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_GRID;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_HEADER;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_PANE;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_PROMPT;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_TEXT_FIELD;
import java.time.LocalDate;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
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
public class EditTextDialog extends Stage {
    GoLogoLo app; 
 
    GridPane gridPane;
    
    Label headerLabel = new Label();   
    Label editTextLabel = new Label();   
    TextField editTextComponentTextField = new TextField();   
    HBox okCancelPane = new HBox();
    Button okButton = new Button();
    Button cancelButton = new Button();


    LogoPrototype changedText;
    LogoPrototype oldText;
  
  

    EventHandler cancelHandler;
    EventHandler addItemOkHandler;
    EventHandler editItemOkHandler;  


    
    
    public EditTextDialog(GoLogoLo initApp) {
        // KEEP THIS FOR WHEN THE WORK IS ENTERED
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
        initGridNode(headerLabel,           LOGO_DIALOG_HEADER,                CLASS_LOGO_DIALOG_HEADER,       0, 0, 3, 1, true);
      
        initGridNode(editTextLabel,             LOGO_EDIT_TEXT_LABEL,                CLASS_LOGO_DIALOG_PROMPT,       0, 1, 1, 1, true);
        initGridNode(editTextComponentTextField,         null,                  CLASS_LOGO_DIALOG_TEXT_FIELD,   1, 1, 1, 1, false);
        initGridNode(okCancelPane,          null,                              CLASS_LOGO_DIALOG_PANE,         0, 2, 3, 1, false);

        okButton = new Button("OK");
        cancelButton = new Button("Cancel");
       app.getGUIModule().addGUINode(LOGO_EDIT_DIALOG_OK_BUTTON, okButton);
       app.getGUIModule().addGUINode(LOGO_EDIT_DIALOG_CANCEL_BUTTON, cancelButton);
       okButton.getStyleClass().add(CLASS_LOGO_DIALOG_BUTTON);
       cancelButton.getStyleClass().add(CLASS_LOGO_DIALOG_BUTTON);
       okCancelPane.getChildren().add(okButton);
       okCancelPane.getChildren().add(cancelButton);
       okCancelPane.setAlignment(Pos.CENTER);

//        AppLanguageModule languageSettings = app.getLanguageModule();
//        languageSettings.addLabeledControlProperty(LOGO_EDIT_DIALOG_OK_BUTTON+ "_TEXT",    okButton.textProperty());
//        languageSettings.addLabeledControlProperty(LOGO_EDIT_DIALOG_CANCEL_BUTTON + "_TEXT",    cancelButton.textProperty());
//       
        // AND SETUP THE EVENT HANDLERS
      
          editTextComponentTextField.setOnAction(e->{
            processCompleteWork();
        });
        
        okButton.setOnAction(e->{
            processCompleteWork();
       });
       cancelButton.setOnAction(e->{
           changedText = null;
         
           this.hide();
        });   
    }
    

    public void showEditDialog(LogoPrototype previousData){
        oldText=previousData;
        changedText=new LogoPrototype(previousData.getName(),previousData.getText());
        
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String headerText = props.getProperty(LOGO_DIALOG_HEADER_TEXT);
        headerLabel.setText(headerText);
        setTitle(headerText);
         String editText = props.getProperty(LOGO_EDIT_TEXT_LABEL_TEXT);
         editTextLabel.setText(editText);
         

        // USE THE TEXT IN THE HEADER FOR ADD
        
        editTextComponentTextField.setText(oldText.getText());
   
        // AND OPEN THE DIALOG
        showAndWait();
    }
    

    public void processCompleteWork(){
        
        String thisText = editTextComponentTextField.getText();
        LogoData data=(LogoData)app.getDataComponent();
        
        if (data.isValidLogoData(thisText)) {
         changedText.setText(thisText);
         }
         else{
             //create dialog saying name field is empty
         }
         this.hide();
    }
        
    public LogoPrototype getChangedText() {
        return changedText;
    }
     public LogoPrototype getOldText() {
        return oldText;
    }
    
}   
   
