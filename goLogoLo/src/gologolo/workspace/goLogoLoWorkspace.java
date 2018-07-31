/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace;

import djf.AppTemplate;
import djf.components.AppWorkspaceComponent;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import gologolo.GoLogoLo;
import gologolo.data.LogoPrototype;
import static gologolo.goLogoLoPropertyType.ADD_CIRCLE_BUTTON;
import static gologolo.goLogoLoPropertyType.ADD_IMAGE_BUTTON;
import static gologolo.goLogoLoPropertyType.ADD_RECTANGLE_BUTTON;
import static gologolo.goLogoLoPropertyType.ADD_TEXT_BUTTON;
import static gologolo.goLogoLoPropertyType.ADD_TRIANGLE_BUTTON;
import static gologolo.goLogoLoPropertyType.BOLD_BUTTON;
import static gologolo.goLogoLoPropertyType.BORDER_COLOR_OPTIONS;
import static gologolo.goLogoLoPropertyType.COLOR_PICKER;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_COLOR_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_CONTROL_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_RAIDIUS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_THICKNESS_SLIDER;

import static gologolo.goLogoLoPropertyType.LOGO_CHART_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_COMPONENTS_BUTTONS_PANE;
import static gologolo.goLogoLoPropertyType.DECREASE_TEXT_BUTTON;
import static gologolo.goLogoLoPropertyType.DEFAULT_BORDER_COLOR;
import static gologolo.goLogoLoPropertyType.DEFAULT_FONT;
import static gologolo.goLogoLoPropertyType.DEFAULT_FONT_SIZE;
import static gologolo.goLogoLoPropertyType.DELETE_BUTTON;
import static gologolo.goLogoLoPropertyType.FONT_OPTIONS;
import static gologolo.goLogoLoPropertyType.FONT_SIZE_OPTIONS;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_BORDER_COLOR_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_BORDER_RADIUS_LABEL;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_BORDER_THICKNESS_LABEL;
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
import static gologolo.goLogoLoPropertyType.LOGO_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_RIGHT_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_TABLE_BUTTONS_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_TABLE_VIEW;
import static gologolo.goLogoLoPropertyType.GOLOGOLO_TYPE_COLUMN;
import static gologolo.goLogoLoPropertyType.UNDERLINE_BUTTON;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_BOX;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_COLUMN;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_EDIT_BOX;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_ICON;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_ICON_PANE;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_LEFT_BOX;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_REGULAR_LABEL;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_RIGHT_BOX;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_RIGHT_ICON_PANE;

import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_TABLE;
import static gologolo.workspace.style.LogoStyle.LOGO_COMBO_BOX;
import static gologolo.workspace.style.LogoStyle.LOGO_LONG_COMBO_BOX;
import static gologolo.workspace.style.LogoStyle.LOGO_SLIDER;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
        //the logo under the table
        HBox tableButtonsPane        = goLogoLoBuilder.buildHBox(LOGO_TABLE_BUTTONS_PANE,          goLogoLoLeftPane,         null,   CLASS_LOGO_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button moveUpButton          = goLogoLoBuilder.buildIconButton(MOVE_UP_BUTTON,        tableButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button moveDownButton        = goLogoLoBuilder.buildIconButton(MOVE_DOWN_BUTTON,        tableButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button editButton            = goLogoLoBuilder.buildIconButton(RENAME_BUTTON,        tableButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        
        
        //THE PANE IN THE MIDDLE(WHITE ONE) FOR EDITING LOGOS (PLAIN WHITE PANE)
        VBox goLogoLoEditPane= goLogoLoBuilder.buildVBox(LOGO_EDIT_PANE,     null,   null,   CLASS_LOGO_EDIT_BOX,     HAS_KEY_HANDLER,        FOCUS_TRAVERSABLE,      ENABLED);
        
        
        //THE RIGHT PANE
        VBox goLogoLoRightPane= goLogoLoBuilder.buildVBox(LOGO_RIGHT_PANE,     null,   null,   CLASS_LOGO_RIGHT_BOX,     HAS_KEY_HANDLER,        FOCUS_TRAVERSABLE,      ENABLED);
        goLogoLoRightPane.setSpacing(10);
        
        //TOP ICON BAR ON RIGHT PANE
        HBox componentButtonsPane         = goLogoLoBuilder.buildHBox(LOGO_COMPONENTS_BUTTONS_PANE,           goLogoLoRightPane,            null,   CLASS_LOGO_RIGHT_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        Button addTextButton              = goLogoLoBuilder.buildIconButton(ADD_TEXT_BUTTON,             componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addImageButton             = goLogoLoBuilder.buildIconButton(ADD_IMAGE_BUTTON,            componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addRectangleButton         = goLogoLoBuilder.buildIconButton(ADD_RECTANGLE_BUTTON,        componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addCircleButton            = goLogoLoBuilder.buildIconButton(ADD_CIRCLE_BUTTON,           componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addTriangleButton          = goLogoLoBuilder.buildIconButton(ADD_TRIANGLE_BUTTON,         componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button deleteButton               = goLogoLoBuilder.buildIconButton(DELETE_BUTTON,     componentButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);

        
   
        //FONT BAR PANE
        HBox fontButtonsPane            = goLogoLoBuilder.buildHBox(LOGO_FONT_BUTTONS_PANE,           goLogoLoRightPane,            null,   CLASS_LOGO_RIGHT_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        
        //font and size slider bars
       
        
        ArrayList <String> fontNames=new ArrayList<>();
        String defaultFont="Times New Roman";
        fontNames.add(defaultFont);
        ComboBox fontComboBox           =goLogoLoBuilder.buildComboBox(LOGO_FONT_COMBO_BOX,           FONT_OPTIONS,                    DEFAULT_FONT,            fontButtonsPane,            null,           LOGO_COMBO_BOX,          HAS_KEY_HANDLER,            FOCUS_TRAVERSABLE,          ENABLED);
        
         
         ArrayList<String> fontSizes=new ArrayList<>();
         String defaultSize="72";
         fontNames.add(defaultFont);
         ComboBox fontSizeComboBox           =goLogoLoBuilder.buildComboBox(LOGO_FONT_SIZE_COMBO_BOX,          FONT_SIZE_OPTIONS,                    DEFAULT_FONT_SIZE,            fontButtonsPane,            null,           LOGO_COMBO_BOX,          HAS_KEY_HANDLER,            FOCUS_TRAVERSABLE,          ENABLED);

        //font buttons
        Button boldButton                = goLogoLoBuilder.buildIconButton(BOLD_BUTTON,                  fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button italicizeButton           = goLogoLoBuilder.buildIconButton(ITALICIZE_BUTTON,             fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button increaseTexttButton       = goLogoLoBuilder.buildIconButton(INCREASE_TEXT_BUTTON,         fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button decreaseTextButton        = goLogoLoBuilder.buildIconButton(DECREASE_TEXT_BUTTON,         fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button underlineButton           = goLogoLoBuilder.buildIconButton(UNDERLINE_BUTTON,             fontButtonsPane,         null,    CLASS_LOGO_ICON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        
              
        
        //border pane (boder sliders)
        VBox borderControlPane            = goLogoLoBuilder.buildVBox(LOGO_BORDER_CONTROL_PANE,           goLogoLoRightPane,            null,   CLASS_LOGO_RIGHT_ICON_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        
        //border thickness 
        Label borderThicknessTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_BORDER_THICKNESS_LABEL, borderControlPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Slider borderThickness                  =goLogoLoBuilder.buildSlider(LOGO_BORDER_THICKNESS_SLIDER,    borderControlPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        borderThicknessTextfield.setTextFill(Color.WHITE);

        //border color
        ColorPicker pickColor=goLogoLoBuilder.buildColorPicker(COLOR_PICKER, borderControlPane, null, defaultSize, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label borderColorTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_BORDER_COLOR_LABEL, borderControlPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        borderColorTextfield.setTextFill(Color.WHITE);
        ArrayList<String> borderColor=new ArrayList<>();
        String defaultColor="#99ff22";
        borderColor.add(defaultColor);
        ComboBox borderColorComboBox           =goLogoLoBuilder.buildComboBox(LOGO_BORDER_COLOR_COMBO_BOX,           BORDER_COLOR_OPTIONS,                    DEFAULT_BORDER_COLOR,            borderControlPane,            null,           LOGO_LONG_COMBO_BOX,          HAS_KEY_HANDLER,            FOCUS_TRAVERSABLE,          ENABLED);
      
        
        //border raidus 
        Label borderRadiusTextfield      =goLogoLoBuilder.buildLabel(GOLOGOLO_BORDER_RADIUS_LABEL, borderControlPane,     null,   CLASS_LOGO_REGULAR_LABEL, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        borderRadiusTextfield.setTextFill(Color.WHITE);
        Slider borderRaidus                    =goLogoLoBuilder.buildSlider(LOGO_BORDER_RAIDIUS_SLIDER,        borderControlPane,              null,   LOGO_SLIDER,    0,           10,      HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
       



        

        //gradient pane
        
        
        goLogoLoPane.setLeft(goLogoLoLeftPane);
        goLogoLoPane.setCenter(goLogoLoEditPane);
        goLogoLoPane.setRight(goLogoLoRightPane);
        
        
        
        
        
        
        workspace = new BorderPane();
	((BorderPane)workspace).setCenter(goLogoLoPane);
    }

    
    
    
    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
