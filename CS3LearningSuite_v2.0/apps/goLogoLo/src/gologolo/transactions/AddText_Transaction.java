/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import djf.ui.AppNodesBuilder;
import gologolo.GoLogoLo;


import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.data.LogoText;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import static gologolo.goLogoLoPropertyType.TEXT_FIELD_COMPONENT;
import gologolo.workspace.controller.LogoController;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_DIALOG_TEXT_FIELD;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class AddText_Transaction implements jTPS_Transaction{

    LogoData data;
    LogoPrototype textToAdd;
    GoLogoLo app;
    LogoText textNode;
  
    String textInput;
   
    public AddText_Transaction(LogoData initData, LogoPrototype initNewComponent,GoLogoLo logoApp) {
         app=logoApp;
        data = initData;
        textToAdd = initNewComponent;
       
        textInput=initNewComponent.getText();
        textNode=new LogoText(textInput);
       
        
    }
    
    @Override
    public void doTransaction() {
       
        //add text name to table
        data.addTextDataAndNode(textToAdd,textNode);
         
      
        
       
     
       
    }

    @Override
    public void undoTransaction() {
       //remove from table and reselect
     
       

        data.removeTextDataAndNode(textToAdd,textNode);

    }
    
}
