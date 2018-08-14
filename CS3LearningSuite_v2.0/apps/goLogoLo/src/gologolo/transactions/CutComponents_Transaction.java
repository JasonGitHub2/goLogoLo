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
    
    
    
    
    
    
     public CutComponents_Transaction(GoLogoLo appData, LogoPrototype clipboardCutData,Node nodeCut ){
        app=appData;
        tableCutData=clipboardCutData;   
        cutNode=nodeCut;
     }
    
    
    @Override
    public void doTransaction() {
        LogoData data = (LogoData)app.getDataComponent();
       
        
        indexOfData=data.remove(tableCutData);
        cutNode=data.removeFromPane(indexOfData);
        
   
        // app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }

    @Override
    public void undoTransaction() {
        LogoData data = (LogoData)app.getDataComponent();
        
       data.addItemAt(tableCutData, indexOfData, cutNode);
        
        
     //   app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    
    }
    
}
