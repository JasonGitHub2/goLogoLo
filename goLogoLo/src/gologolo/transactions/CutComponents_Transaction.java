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

/**
 *
 * @author jasoncao
 */
//public class CutComponents_Transaction implements jTPS_Transaction{
//    GoLogoLo app;
//    ArrayList<LogoPrototype> clipboardDataToCut;
//    ArrayList<Node> clipboardCutNodes;
//    ArrayList<Integer> cutComponentsLocations;
//    LogoPrototype tableData;
//    int indexOfData;
//    Node cutNode;
//    
//    public CutComponents_Transaction(GoLogoLo appData, ArrayList<LogoPrototype> clipboardCutData ){
//       app=appData;
//        clipboardDataToCut=clipboardCutData;
//        clipboardCutNodes=new ArrayList<Node> ();
//        
//    }
//    
//    
//    
//    
//  //   public CutComponents_Transaction(GoLogoLo appData, LogoPrototype clipboardCutData ){
//   //     app=appData;
//   //     tableData=clipboardCutData;    
//   //  }
//    
//    
//    @Override
//    public void doTransaction() {
//        LogoData data = (LogoData)app.getDataComponent();
//        cutComponentsLocations = data.removeAllInTable(clipboardDataToCut);
//        clipboardCutNodes=data.removeAllPane(cutComponentsLocations);
//        
//      //  indexOfData=data.remove(tableData);
//       // cutNode=data.removeFromPane(indexOfData);
//        
//   
//        // app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
//    }
//
//    @Override
//    public void undoTransaction() {
//        LogoData data = (LogoData)app.getDataComponent();
//        data.addAllToTable(clipboardDataToCut, cutComponentsLocations);
//        data.addAllToPane(clipboardCutNodes, cutComponentsLocations);
//      // data.addItemAt(tableData, indexOfData, cutNode);
//        
//        
//     //   app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
//    
//    }
//    
//}

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
