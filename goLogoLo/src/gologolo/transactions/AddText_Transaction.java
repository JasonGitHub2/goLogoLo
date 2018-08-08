/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import djf.ui.AppNodesBuilder;
import gologolo.GoLogoLo;
import gologolo.components.TextComponent;

import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import static gologolo.goLogoLoPropertyType.TEXT_FIELD_COMPONENT;
import gologolo.workspace.controller.LogoController;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_TEXT_FIELD;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class AddText_Transaction implements jTPS_Transaction{

    LogoData data;
    LogoPrototype textToAdd;
    GoLogoLo app;
     TextField addText;
   
    public AddText_Transaction(LogoData initData, LogoPrototype initNewComponent,GoLogoLo logoApp) {
        data = initData;
        textToAdd = initNewComponent;
        app=logoApp;
    }
    
    @Override
    public void doTransaction() {
       
        //add text name to table
        data.addTextField(textToAdd);   
         
      
        
       // AppNodesBuilder goLogoLoBuilder = app.getGUIModule().getNodesBuilder();
        
       // Pane editPane=(Pane) app.getGUIModule().getGUINode(LOGO_EDIT_PANE);
       
       // addText=goLogoLoBuilder.buildTextField(TEXT_FIELD_COMPONENT, editPane, null, CLASS_LOGO_DIALOG_TEXT_FIELD , true, true, true);
        //addText.replaceText(0, 0, textToAdd.getText());
        //addText.setLayoutX(200);
        //addText.setLayoutY(200);
        
        //set event controller if double clicked on PANE THEN EDIT
       
        //FIX- OK BUTTON AND CANCEL BUTTON WEIRD FOR EDIT DIALOG=============================================================================
      
       
        
        // TextField textFieldToAdd=userText.getTextField();    
        //  editPane.getChildren().add(textFieldToAdd);
     
        //deselct previous components and select new
        data.clearSelected();
        data.selectItem(textToAdd);
    }

    @Override
    public void undoTransaction() {
       //remove from table and reselect
     
       

        data.removeTextField(textToAdd);
       
        ArrayList<LogoPrototype> componentList= data.getCurrentItemsOrder();
        data.clearSelected();
        if(componentList.size()>0)
        {
            LogoPrototype firstComponent=componentList.get(componentList.size()-1);
            data.selectItem(firstComponent);
            
        }
        
        
       
       //remove form workspace
       Pane editPane=(Pane) app.getGUIModule().getGUINode(LOGO_EDIT_PANE);
       
       editPane.getChildren().remove(addText);
       
    }
    
}
