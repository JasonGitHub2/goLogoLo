/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import static djf.AppPropertyType.APP_CLIPBOARD_FOOLPROOF_SETTINGS;
import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import java.util.ArrayList;
import javafx.scene.Node;
import jtps.jTPS_Transaction;


public class CutComponents_Transaction implements jTPS_Transaction{
    GoLogoLo app;
   
    LogoPrototype tableCutData; 
    int indexOfData;
    Node cutNode;
    LogoData data;
    
    
    
    
    
     public CutComponents_Transaction(GoLogoLo appData, LogoPrototype clipboardCutData,Node nodeCut ){
        app=appData;
        data = (LogoData)app.getDataComponent();
        tableCutData=clipboardCutData;   
        cutNode=nodeCut;
        
        indexOfData=data.getItemIndex(tableCutData);
     }
    
    
    @Override
    public void doTransaction() {
      
       
        
        data.getComponents().remove(tableCutData);        
        data.getEditComponents().remove(indexOfData);
        data.reorderTable();
             app.getFoolproofModule().updateAll();
   
        // app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }

    @Override
    public void undoTransaction() {
        LogoData data = (LogoData)app.getDataComponent();
        
        data.getComponents().add(indexOfData, tableCutData);
        data.getEditComponents().add(indexOfData, cutNode);
        data.clearSelected();
        data.selectItem(tableCutData);
       data.selectNodeInPane(cutNode);
       data.reorderTable();
             app.getFoolproofModule().updateAll();
     //   app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    
    }
    
}
