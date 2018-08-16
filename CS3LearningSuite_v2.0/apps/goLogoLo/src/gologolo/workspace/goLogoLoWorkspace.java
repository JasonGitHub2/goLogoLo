/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace;

import djf.AppPropertyType;
import djf.AppTemplate;
import djf.components.AppWorkspaceComponent;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import static gologolo.goLogoLoPropertyType.ADD_CIRCLE_BUTTON;
import static gologolo.goLogoLoPropertyType.ADD_IMAGE_BUTTON;
import static gologolo.goLogoLoPropertyType.ADD_RECTANGLE_BUTTON;
import static gologolo.goLogoLoPropertyType.ADD_TEXT_BUTTON;
import static gologolo.goLogoLoPropertyType.ADD_TRIANGLE_BUTTON;
import static gologolo.goLogoLoPropertyType.BOLD_BUTTON;
import static gologolo.goLogoLoPropertyType.BORDER_COLOR_OPTIONS;
import static gologolo.goLogoLoPropertyType.COLOR_PICKER;
import static gologolo.goLogoLoPropertyType.CYCLE_METHOD_OPTIONS;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_COLOR_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_CONTROL_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_RAIDIUS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_THICKNESS_SLIDER;

import static gologolo.goLogoLoPropertyType.LOGO_CHART_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_COMPONENTS_BUTTONS_PANE;
import static gologolo.goLogoLoPropertyType.DECREASE_TEXT_BUTTON;
import static gologolo.goLogoLoPropertyType.DEFAULT_BORDER_COLOR;
import static gologolo.goLogoLoPropertyType.DEFAULT_CYCLE_METHOD;
import static gologolo.goLogoLoPropertyType.DEFAULT_FONT;
import static gologolo.goLogoLoPropertyType.DEFAULT_FONT_SIZE;
import static gologolo.goLogoLoPropertyType.DELETE_BUTTON;
import static gologolo.goLogoLoPropertyType.FONT_OPTIONS;
import static gologolo.goLogoLoPropertyType.FONT_SIZE_OPTIONS;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_BORDER_COLOR_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_BORDER_RADIUS_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_BORDER_THICKNESS_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_CENTER_X_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_CENTER_Y_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_COLOR_GRADIENT_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_CYCLE_METHOD_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_FOCUS_ANGLE_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_FOCUS_DISTANCE_LABEL;
import static gologolo.goLogoLoPropertyType.RENAME_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_FONT_BUTTONS_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_FONT_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_FONT_SIZE_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.INCREASE_TEXT_BUTTON;
import static gologolo.goLogoLoPropertyType.ITALICIZE_BUTTON;
import static gologolo.goLogoLoPropertyType.MOVE_DOWN_BUTTON;
import static gologolo.goLogoLoPropertyType.MOVE_UP_BUTTON;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_NAME_COLUMN;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_ORDER_COLUMN;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_RADIUS_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_STOP_0_COLOR_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_STOP_1_COLOR_LABEL;
import static gologolo.goLogoLoPropertyType.LOGO_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_RIGHT_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_TABLE_BUTTONS_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_TABLE_VIEW;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_TYPE_COLUMN;
import static gologolo.goLogoLoPropertyType.LOGO_CENTER_X_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_CENTER_Y_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_CYCLE_METHOD_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_FOCUS_ANGLE_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_FOCUS_DISTANCE_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_GRADIENT_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_ONE_COLOR;
import static gologolo.goLogoLoPropertyType.LOGO_RADIUS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_ZERO_COLOR;
import static gologolo.goLogoLoPropertyType.UNDERLINE_BUTTON;
import gologolo.workspace.controller.LogoController;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_BOX;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_COLUMN;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_TEXT_FIELD;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_EDIT_BOX;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_ICON;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_ICON_PANE;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_LEFT_BOX;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_REGULAR_LABEL;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_RIGHT_BOX;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_RIGHT_ICON_PANE;

import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_TABLE;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_TITLE_LABEL;
import static gologolo.workspace.style.LogoStyle.LOGO_COMBO_BOX;
import static gologolo.workspace.style.LogoStyle.LOGO_LONG_COMBO_BOX;
import static gologolo.workspace.style.LogoStyle.LOGO_SLIDER;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import properties_manager.PropertiesManager;

/**
 *
 * @author Jason Cao
 */
public class goLogoLoWorkspace extends AppWorkspaceComponent{

    public goLogoLoWorkspace(GoLogoLo app) {
        super(app);
    

        // LAYOUT THE APP
        initLayout();
        
        // 
        //initFoolproofDesign();
    }
        
    
    
    
    // THIS HELPER METHOD INITIALIZES ALL THE CONTROLS IN THE WORKSPACE
    private void initLayout() {
        // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder goLogoLoBuilder = app.getGUIModule().getNodesBuilder();
        
        
        //THE PANE IN THE FIRST BACKGROUND (PURPLE)
        BorderPane goLogoLoPane= goLogoLoBuilder.buildBorderPane(LOGO_PANE,     null,   null,   CLASS_LOGO_BOX,     HAS_KEY_HANDLER,        FOCUS_TRAVERSABLE,      ENABLED);
        
   
        //THE LEFT PANE WITH TABLE AND MOVEMENT ICONS  
        VBox goLogoLoLeftPane= goLogoLoBuilder.buildVBox(LOGO_CHART_PANE,                 null,       null,   CLASS_LOGO_LEFT_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
       //table
        TableView<LogoPrototype> logoTable  = goLogoLoBuilder.buildTableView(LOGO_TABLE_VIEW,       goLogoLoLeftPane,          null,   CLASS_LOGO_LEFT_BOX, HAS_KEY_HANDLER,    FOCUS_TRAVERSABLE,  true);
        TableColumn orderColumn      = goLogoLoBuilder.buildTableColumn(  GOLOGOLO_ORDER_COLUMN,        logoTable,                   CLASS_LOGO_COLUMN);
        TableColumn nameColumn      = goLogoLoBuilder.buildTableColumn(  GOLOGOLO_NAME_COLUMN,          logoTable,                   CLASS_LOGO_COLUMN);
        TableColumn typeColumn      = goLogoLoBuilder.buildTableColumn(  GOLOGOLO_TYPE_COLUMN,          logoTable,                   CLASS_LOGO_COLUMN);
        orderColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("order"));
        nameColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("name"));
        typeColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("type"));
        


        //the logo under the table
        HBox tableButtonsPane        = goLogoLoBuilder.buildHBox(LOGO_TABLE_BUTTONS_PANE,          goLogoLoLeftPane,         null,   CLASS_LOGO_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button moveUpButton          = goLogoLoBuilder.buildIconButton(MOVE_UP_BUTTON,        tableButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button moveDownButton        = goLogoLoBuilder.buildIconButton(MOVE_DOWN_BUTTON,        tableButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button editButton            = goLogoLoBuilder.buildIconButton(RENAME_BUTTON,        tableButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        
        
        //THE PANE IN THE MIDDLE(WHITE ONE) FOR EDITING LOGOS (PLAIN WHITE PANE)
        
        Pane goLogoLoEditPane= goLogoLoBuilder.buildPane(LOGO_EDIT_PANE,     null,   null,   CLASS_LOGO_EDIT_BOX,     HAS_KEY_HANDLER,        FOCUS_TRAVERSABLE,      ENABLED);

        //THE RIGHT PANE
        VBox goLogoLoRightPane= goLogoLoBuilder.buildVBox(LOGO_RIGHT_PANE,     null,   null,   CLASS_LOGO_RIGHT_BOX,     HAS_KEY_HANDLER,        FOCUS_TRAVERSABLE,      ENABLED);
        goLogoLoRightPane.setSpacing(5);
        
        //TOP ICON BAR ON RIGHT PANE
        HBox componentButtonsPane         = goLogoLoBuilder.buildHBox(LOGO_COMPONENTS_BUTTONS_PANE,           goLogoLoRightPane,            null,   CLASS_LOGO_RIGHT_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button addTextButton              = goLogoLoBuilder.buildIconButton(ADD_TEXT_BUTTON,             componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addImageButton             = goLogoLoBuilder.buildIconButton(ADD_IMAGE_BUTTON,            componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addRectangleButton         = goLogoLoBuilder.buildIconButton(ADD_RECTANGLE_BUTTON,        componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addCircleButton            = goLogoLoBuilder.buildIconButton(ADD_CIRCLE_BUTTON,           componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addTriangleButton          = goLogoLoBuilder.buildIconButton(ADD_TRIANGLE_BUTTON,         componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button deleteButton               = goLogoLoBuilder.buildIconButton(DELETE_BUTTON,     componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);

        
   
        //FONT BAR PANE
        FlowPane fontButtonsPane               =goLogoLoBuilder.buildFlowPane(LOGO_FONT_BUTTONS_PANE,           goLogoLoRightPane,            null,   CLASS_LOGO_RIGHT_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED); 
        fontButtonsPane.setPrefWrapLength(20);   
        //font and size slider bars  
        ArrayList <String> fontNames=new ArrayList<>();
        String defaultFont="Times New Roman";
        fontNames.add(defaultFont);
        ComboBox fontComboBox           =goLogoLoBuilder.buildComboBox(LOGO_FONT_COMBO_BOX,           FONT_OPTIONS,                    DEFAULT_FONT,            fontButtonsPane,            null,           LOGO_COMBO_BOX,          HAS_KEY_HANDLER,            FOCUS_TRAVERSABLE,          ENABLED);
        
         
         ArrayList<String> fontSizes=new ArrayList<>();
         String defaultSize="72";
         fontNames.add(defaultFont);
         ComboBox fontSizeComboBox           =goLogoLoBuilder.buildComboBox(LOGO_FONT_SIZE_COMBO_BOX,          FONT_SIZE_OPTIONS,                    DEFAULT_FONT_SIZE,            fontButtonsPane,            null,           LOGO_COMBO_BOX,          HAS_KEY_HANDLER,            FOCUS_TRAVERSABLE,          ENABLED);
         fontButtonsPane.rowValignmentProperty();
        //font buttons
        Button boldButton                = goLogoLoBuilder.buildIconButton(BOLD_BUTTON,                  fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button italicizeButton           = goLogoLoBuilder.buildIconButton(ITALICIZE_BUTTON,             fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button increaseTextButton       = goLogoLoBuilder.buildIconButton(INCREASE_TEXT_BUTTON,         fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button decreaseTextButton        = goLogoLoBuilder.buildIconButton(DECREASE_TEXT_BUTTON,         fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button underlineButton           = goLogoLoBuilder.buildIconButton(UNDERLINE_BUTTON,             fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        
        
              
        
        //border pane (boder sliders)
        VBox borderControlPane            = goLogoLoBuilder.buildVBox(LOGO_BORDER_CONTROL_PANE,           goLogoLoRightPane,            null,   CLASS_LOGO_RIGHT_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        
        //border thickness 
        Label borderThicknessTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_BORDER_THICKNESS_LABEL, borderControlPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider borderThickness                  =goLogoLoBuilder.buildSlider(LOGO_BORDER_THICKNESS_SLIDER,    borderControlPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        borderThicknessTextfield.setTextFill(Color.WHITE);
        //border color
        Label borderColorTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_BORDER_COLOR_LABEL, borderControlPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        borderColorTextfield.setTextFill(Color.WHITE);
        ColorPicker pickColor=goLogoLoBuilder.buildColorPicker(COLOR_PICKER, borderControlPane, null, LOGO_LONG_COMBO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);    
        //border raidus 
        Label borderRadiusTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_BORDER_RADIUS_LABEL, borderControlPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        borderRadiusTextfield.setTextFill(Color.WHITE);
        Slider borderRaidus                    =goLogoLoBuilder.buildSlider(LOGO_BORDER_RAIDIUS_SLIDER,        borderControlPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        
        //gradient pane
         VBox gradientPane            = goLogoLoBuilder.buildVBox(LOGO_GRADIENT_PANE,           goLogoLoRightPane,            null,   CLASS_LOGO_RIGHT_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
         Label gradientTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_COLOR_GRADIENT_LABEL, gradientPane,     null,   CLASS_LOGO_TITLE_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
         gradientTextfield.setTextFill(Color.WHITE);
        
        Label focusAngleTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_FOCUS_ANGLE_LABEL, gradientPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        focusAngleTextfield.setTextFill(Color.WHITE);
        Slider focusAngle                    =goLogoLoBuilder.buildSlider(LOGO_FOCUS_ANGLE_SLIDER,        gradientPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        Label focusDistanceTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_FOCUS_DISTANCE_LABEL, gradientPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        focusDistanceTextfield.setTextFill(Color.WHITE);
        Slider focusDistance                    =goLogoLoBuilder.buildSlider(LOGO_FOCUS_DISTANCE_SLIDER,        gradientPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        Label centerXTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_CENTER_X_LABEL, gradientPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        centerXTextfield.setTextFill(Color.WHITE);
        Slider centerX                    =goLogoLoBuilder.buildSlider(LOGO_CENTER_X_SLIDER,        gradientPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        Label centerYTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_CENTER_Y_LABEL, gradientPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        centerYTextfield.setTextFill(Color.WHITE);
        Slider centerY                    =goLogoLoBuilder.buildSlider(LOGO_CENTER_Y_SLIDER,        gradientPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        Label radiusTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_RADIUS_LABEL, gradientPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        radiusTextfield.setTextFill(Color.WHITE);
        Slider radius                   =goLogoLoBuilder.buildSlider(LOGO_RADIUS_SLIDER,        gradientPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        //combo box for cycle method
        Label cycleMethodTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_CYCLE_METHOD_LABEL, gradientPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        cycleMethodTextfield.setTextFill(Color.WHITE);
              
        ComboBox cycleMethodComboBox           =goLogoLoBuilder.buildComboBox(LOGO_CYCLE_METHOD_COMBO_BOX,           CYCLE_METHOD_OPTIONS,                    DEFAULT_CYCLE_METHOD,            gradientPane,            null,           LOGO_LONG_COMBO_BOX,          HAS_KEY_HANDLER,            FOCUS_TRAVERSABLE,          ENABLED);
        
        Label stopZeroColorTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_STOP_0_COLOR_LABEL, gradientPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        stopZeroColorTextfield.setTextFill(Color.WHITE);
        ColorPicker zeroColor=goLogoLoBuilder.buildColorPicker(LOGO_ZERO_COLOR, gradientPane, null, LOGO_LONG_COMBO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);    
        
        Label stopOneColorTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_STOP_1_COLOR_LABEL, gradientPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        stopOneColorTextfield.setTextFill(Color.WHITE);
        ColorPicker oneColor=goLogoLoBuilder.buildColorPicker(LOGO_ONE_COLOR, gradientPane, null, LOGO_LONG_COMBO_BOX, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);    
     
    
         
        goLogoLoPane.setLeft(goLogoLoLeftPane);
        goLogoLoPane.setCenter(goLogoLoEditPane);
        goLogoLoPane.setRight(goLogoLoRightPane);
        workspace = new BorderPane();
	((BorderPane)workspace).setCenter(goLogoLoPane);
      
        
        //event handlers
        
        LogoController eventController=new LogoController((GoLogoLo)app);
        addRectangleButton.setOnAction(e->{
            eventController.processAddRectangle();
        });
        
        addCircleButton.setOnAction(e->{
            eventController.processAddCircle();
        });
        
        addTextButton.setOnAction(e->{
            eventController.processAddText();
        });
        
         deleteButton.setOnAction(e->{
            eventController.processDeleteComponent();
        });
        
         editButton.setOnAction(e->{
            eventController.processRenameComponent();
        });
         
            boldButton.setOnAction(e->{
            eventController.processBoldText();
           
        });
         
            
               italicizeButton.setOnAction(e->{
            eventController.processItalicizeText();
        });
         
          fontSizeComboBox.setOnAction(e->{
            eventController.processChangeFontSize((String)fontSizeComboBox.getValue());  
        });
         
         
         
          fontComboBox.setOnAction(e->{
            eventController.processChangeFont((String)fontComboBox.getValue());  
        });
          
//          goLogoLoEditPane.setOnMouseClicked(e -> {
//                      LogoData data = (LogoData)app.getDataComponent();
//                      data.c
//          });
//          
          addImageButton.setOnAction(e->{
             File file= djf.ui.dialogs.AppDialogsFacade.showOpenDialog(null,null);
             String filePath=file.getAbsolutePath();
            try {
                eventController.processAddImage(file,filePath);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(goLogoLoWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
         
         underlineButton.setOnAction(e->{
            eventController.processUnderlineText();
        });
         
        increaseTextButton.setOnAction(e->{
            eventController.processIncreaseText();
        });
         
        decreaseTextButton.setOnAction(e->{
            eventController.processDecreaseText();
        });
        
        moveUpButton.setOnAction(e->{
            eventController.processMoveUp();
        });
        
        focusAngle.valueProperty().addListener(e->{
            eventController.processFocusAngle((focusAngle.valueProperty().doubleValue()));
        });
         
        focusDistance.valueProperty().addListener(e->{
            eventController.processFocusDistance((focusAngle.valueProperty().doubleValue()));
        });
          
        centerX.valueProperty().addListener(e->{
            eventController.processCenterX((focusAngle.valueProperty().doubleValue()));
        });
        centerY.valueProperty().addListener(e->{
            eventController.processCenterY((focusAngle.valueProperty().doubleValue()));
        });
         radius.valueProperty().addListener(e->{
            eventController.processRadius((focusAngle.valueProperty().doubleValue()));
        });
         
            cycleMethodComboBox.setOnAction(e->{
            eventController.processCycleMethod((String)cycleMethodComboBox.getValue());  
        });
              zeroColor.setOnAction(e->{
            eventController.processZeroColor(zeroColor.getValue());  
        });
              
               oneColor.setOnAction(e->{
            eventController.processOneColor(oneColor.getValue());  
        });
         
         
         
         //edit double click on table
         logoTable.setOnMouseClicked(e -> {
             LogoData data = (LogoData)app.getDataComponent();
            eventController.processSelectNode();
            if (e.getClickCount() == 2&& data.getSelectedItem().getType().equalsIgnoreCase("Text")) {
                
                eventController.processEditText();
            }
           
               
            
        });
         

    }
  
    
    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showNewDialog() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
