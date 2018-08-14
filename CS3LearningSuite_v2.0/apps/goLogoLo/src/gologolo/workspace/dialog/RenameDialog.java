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
import static gologolo.goLogoLoPropertyType.LOGO_ADD_DIALOG_CANCEL_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_ADD_DIALOG_CANCEL_BUTTON_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_ADD_DIALOG_NAME_LABEL;
import static gologolo.goLogoLoPropertyType.LOGO_ADD_DIALOG_OK_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_ADD_DIALOG_OK_BUTTON_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_CHANGE_NAME_LABEL;
import static gologolo.goLogoLoPropertyType.LOGO_CHANGE_NAME_LABEL_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_ADD_HEADER;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_CANCEL_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_HEADER;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_HEADER_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_OK_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_RENAME_HEADER;
import static gologolo.goLogoLoPropertyType.LOGO_DIALOG_RENAME_HEADER_TEXT;
import static gologolo.goLogoLoPropertyType.LOGO_NAME_LABEL;
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
public class RenameDialog extends Stage {
    GoLogoLo app; 
 
    GridPane gridPane;
    String newName;
    Label headerLabel = new Label();    
    Label changeNameLabel = new Label();
    TextField nameTextField = new TextField();   
    HBox okCancelPane = new HBox();
    Button okButton = new Button();
    Button cancelButton = new Button();


    LogoPrototype itemToEdit;
    LogoPrototype newItem;
    LogoPrototype editItem;
  

    EventHandler cancelHandler;
    EventHandler addItemOkHandler;
    EventHandler editItemOkHandler;  
    boolean editing;

    
    
    public RenameDialog(GoLogoLo initApp) {
        // KEEP THIS FOR WHEN THE WORK IS ENTERED
        newName="";
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
        initGridNode(headerLabel,           LOGO_DIALOG_RENAME_HEADER,                CLASS_LOGO_DIALOG_HEADER,       0, 0, 3, 1, true);
        initGridNode(changeNameLabel,           LOGO_CHANGE_NAME_LABEL,        CLASS_LOGO_DIALOG_PROMPT,       0, 1, 1, 1, true);
        initGridNode(nameTextField,         null,                              CLASS_LOGO_DIALOG_TEXT_FIELD,   1, 1, 1, 1, false);
        initGridNode(okCancelPane,          null,                                   CLASS_LOGO_DIALOG_PANE,    0, 2, 3, 1, false);

        okButton = new Button();
        cancelButton = new Button();
       app.getGUIModule().addGUINode(LOGO_ADD_DIALOG_OK_BUTTON, okButton);
       app.getGUIModule().addGUINode(LOGO_ADD_DIALOG_CANCEL_BUTTON, cancelButton);
       okButton.getStyleClass().add(CLASS_LOGO_DIALOG_BUTTON);
       cancelButton.getStyleClass().add(CLASS_LOGO_DIALOG_BUTTON);
       okCancelPane.getChildren().add(okButton);
       okCancelPane.getChildren().add(cancelButton);
       okCancelPane.setAlignment(Pos.CENTER);

        AppLanguageModule languageSettings = app.getLanguageModule();
        languageSettings.addLabeledControlProperty(LOGO_ADD_DIALOG_OK_BUTTON + "_TEXT",    okButton.textProperty());
        languageSettings.addLabeledControlProperty(LOGO_ADD_DIALOG_CANCEL_BUTTON + "_TEXT",    cancelButton.textProperty());
       
        // AND SETUP THE EVENT HANDLERS
       
        okButton.setOnAction(e->{
            processCompleteWork();
       });
       cancelButton.setOnAction(e->{
           newName="";
       
           this.hide();
        });   
    }
    
    
    public void showRenameDialog(LogoPrototype selectedItem){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String headerText = props.getProperty(LOGO_DIALOG_RENAME_HEADER_TEXT);
        headerLabel.setText(headerText);
        setTitle(headerText);
        String renameText = props.getProperty( LOGO_CHANGE_NAME_LABEL_TEXT);
        changeNameLabel.setText(renameText);

        String okText=props.getProperty( LOGO_ADD_DIALOG_OK_BUTTON_TEXT);
        okButton.setText(okText);
        String cancelText=props.getProperty( LOGO_ADD_DIALOG_CANCEL_BUTTON_TEXT);
        cancelButton.setText(cancelText);

        
        // USE THE TEXT IN THE HEADER FOR ADD
        
        nameTextField.setText(selectedItem.getName());
       
        
       
   
        
        // AND OPEN THE DIALOG
        showAndWait();
    }
    
    
    public void processCompleteWork(){
        String name = nameTextField.getText();
        LogoData data=(LogoData)app.getDataComponent();
         if (data.isValidLogoData(name)) {
         newName=name;
         }
         else{
             //create dialog saying name field is empty
         }
         this.hide();
    }
        
    public String getNewName() {
        return newName;
    }
    
}   
   
