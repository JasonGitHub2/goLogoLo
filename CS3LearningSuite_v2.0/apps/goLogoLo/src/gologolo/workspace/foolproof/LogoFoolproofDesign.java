package gologolo.workspace.foolproof;
import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import static gologolo.goLogoLoPropertyType.BOLD_BUTTON;
import static gologolo.goLogoLoPropertyType.COLOR_PICKER;
import static gologolo.goLogoLoPropertyType.DECREASE_TEXT_BUTTON;
import static gologolo.goLogoLoPropertyType.DELETE_BUTTON;
import static gologolo.goLogoLoPropertyType.FONT_COLOR_PICKER_BUTTON;
import static gologolo.goLogoLoPropertyType.INCREASE_TEXT_BUTTON;
import static gologolo.goLogoLoPropertyType.ITALICIZE_BUTTON;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_RAIDIUS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_THICKNESS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_CENTER_X_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_CENTER_Y_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_CYCLE_METHOD_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_FOCUS_ANGLE_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_FOCUS_DISTANCE_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_FONT_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_FONT_SIZE_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_ONE_COLOR;
import static gologolo.goLogoLoPropertyType.LOGO_RADIUS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_ZERO_COLOR;
import static gologolo.goLogoLoPropertyType.MOVE_DOWN_BUTTON;
import static gologolo.goLogoLoPropertyType.MOVE_UP_BUTTON;
import static gologolo.goLogoLoPropertyType.RENAME_BUTTON;


/**
 *
 * @author McKillaGorilla
 */
public class LogoFoolproofDesign implements FoolproofDesign {
    GoLogoLo app;
    
    public LogoFoolproofDesign(GoLogoLo initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
       
        // CHECK AND SEE IF A TABLE ITEM IS SELECTED
        LogoData data = (LogoData)app.getDataComponent();
        boolean itemIsSelected = data.isItemSelected();
        if(itemIsSelected){
            LogoPrototype selected=data.getSelectedItem();
               gui.getGUINode(MOVE_UP_BUTTON).setDisable(selected.getOrder()==1); 
               gui.getGUINode(MOVE_DOWN_BUTTON).setDisable(selected.getOrder()==data.getComponents().size());
                gui.getGUINode(DELETE_BUTTON).setDisable(false);
               gui.getGUINode(LOGO_FONT_COMBO_BOX).setDisable(!selected.getType().equalsIgnoreCase("Text")); 
                 gui.getGUINode(LOGO_FONT_SIZE_COMBO_BOX).setDisable(!selected.getType().equalsIgnoreCase("Text")); 
                   gui.getGUINode(BOLD_BUTTON).setDisable(!selected.getType().equalsIgnoreCase("Text")); 
                     gui.getGUINode(ITALICIZE_BUTTON).setDisable(!selected.getType().equalsIgnoreCase("Text")); 
                       gui.getGUINode(INCREASE_TEXT_BUTTON).setDisable(!selected.getType().equalsIgnoreCase("Text")); 
                         gui.getGUINode(DECREASE_TEXT_BUTTON).setDisable(!selected.getType().equalsIgnoreCase("Text")); 
                           gui.getGUINode(FONT_COLOR_PICKER_BUTTON).setDisable(!selected.getType().equalsIgnoreCase("Text")); 
             
                         gui.getGUINode(RENAME_BUTTON).setDisable(false);    
             gui.getGUINode(LOGO_BORDER_THICKNESS_SLIDER).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(COLOR_PICKER).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(LOGO_BORDER_RAIDIUS_SLIDER).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
                           
             gui.getGUINode(LOGO_FOCUS_ANGLE_SLIDER).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(LOGO_FOCUS_DISTANCE_SLIDER).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(LOGO_CENTER_X_SLIDER).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(LOGO_CENTER_Y_SLIDER).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(LOGO_RADIUS_SLIDER).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(LOGO_CYCLE_METHOD_COMBO_BOX).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(LOGO_ZERO_COLOR).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             gui.getGUINode(LOGO_ONE_COLOR).setDisable(!(selected.getType().equalsIgnoreCase("Rectangle")||selected.getType().equalsIgnoreCase("Circle")));
             
             
        }
        else
        {
           gui.getGUINode(MOVE_UP_BUTTON).setDisable(true); 
           gui.getGUINode(MOVE_DOWN_BUTTON).setDisable(true);
           gui.getGUINode(RENAME_BUTTON).setDisable(true); 
           gui.getGUINode(DELETE_BUTTON).setDisable(true); 
           
              
             gui.getGUINode(LOGO_FONT_COMBO_BOX).setDisable(true); 
             gui.getGUINode(LOGO_FONT_SIZE_COMBO_BOX).setDisable(true); 
             gui.getGUINode(BOLD_BUTTON).setDisable(true); 
             gui.getGUINode(ITALICIZE_BUTTON).setDisable(true); 
             gui.getGUINode(INCREASE_TEXT_BUTTON).setDisable(true); 
             gui.getGUINode(DECREASE_TEXT_BUTTON).setDisable(true); 
             gui.getGUINode(FONT_COLOR_PICKER_BUTTON).setDisable(true); 
             gui.getGUINode(LOGO_FOCUS_ANGLE_SLIDER).setDisable(true); 
             gui.getGUINode(LOGO_FOCUS_DISTANCE_SLIDER).setDisable(true); 
             gui.getGUINode(LOGO_CENTER_X_SLIDER).setDisable(true); 
             gui.getGUINode(LOGO_CENTER_Y_SLIDER).setDisable(true); 
             gui.getGUINode(LOGO_RADIUS_SLIDER).setDisable(true); 
             gui.getGUINode(LOGO_CYCLE_METHOD_COMBO_BOX).setDisable(true); 
             gui.getGUINode(LOGO_ZERO_COLOR).setDisable(true); 
             gui.getGUINode(LOGO_ONE_COLOR).setDisable(true); 
             
              
            
             
        }
        
    }
}