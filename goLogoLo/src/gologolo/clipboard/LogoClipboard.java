/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.clipboard;

import djf.components.AppClipboardComponent;
import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.transactions.CutComponents_Transaction;
import gologolo.transactions.PasteComponent_Transaction;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jasoncao
 */
//public class LogoClipboard implements AppClipboardComponent{
//    GoLogoLo  app;
//    ArrayList<LogoPrototype> clipboardCutTableData;
//    ArrayList<LogoPrototype> clipboardCopiedTableData;
//    ArrayList<Node> clipboardCopiedNodes;
//    ArrayList<Node> clipboardCutNodes;
//   // ArrayList<Node> clipboardCopiedNodes;
//   // LogoPrototype clipboardTableData;
//    
//      
//    
//    
//    
//    public LogoClipboard(GoLogoLo initApp) {
//         app = initApp;
//         clipboardCutTableData=null;
//         clipboardCopiedTableData=null;
//         clipboardCutNodes=null;
//        clipboardCopiedNodes=null;
//     //    clipboardCopiedNodes=null;  
//    //     clipboardTableData=null;
//    }
//
//    @Override
//    public void cut() {
//        LogoData data = (LogoData)app.getDataComponent();
//        if (data.isItemSelected()) {
//         clipboardCutTableData = new ArrayList(data.getSelectedItems());
//            
//            clipboardCopiedTableData = null;
//            clipboardCopiedNodes = null;
//            
//            CutComponents_Transaction transaction = new CutComponents_Transaction((GoLogoLo)app, clipboardCutTableData );
//            app.processTransaction(transaction);
//            //clipboardTableData=data.getSelectedItem();
//          //  CutComponents_Transaction transaction = new CutComponents_Transaction((GoLogoLo)app, clipboardTableData);
//            app.processTransaction(transaction);
//     
//            
//        }
//    }
//
//    
//    
//    
//    
//    //CHANGE
//    
//    @Override
//    public void copy() {
//        LogoData data = (LogoData)app.getDataComponent();
//        if (data.isItemSelected() || data.areItemsSelected()) {
//            ArrayList<LogoPrototype> tempTableData = new ArrayList(data.getSelectedItems());
//            ArrayList<Integer> tempNodeDataIndex = new ArrayList(data.getSelectedItemsIndex(tempTableData));
//            copyToCopiedDataClipboard(tempTableData);
//            copyToCopiedNodeClipboard(tempNodeDataIndex);
//        }
//    }
//
//    private void copyToCopiedDataClipboard(ArrayList<LogoPrototype> dataToCopy) {
//        clipboardCutTableData = null;
//        clipboardCopiedTableData= copyTableData(dataToCopy);
//     //   app.getFoolproofModule().updateAll();        
//    }
//    
//    
//    private void copyToCopiedNodeClipboard(ArrayList<Integer> dataToCopy) {
//        clipboardCutNodes = null;        
//        clipboardCopiedNodes= copyPaneNode(dataToCopy);
//      //  app.getFoolproofModule().updateAll();        
//    }
//    
//     private void copyDataToCopiedCutClipboard(ArrayList<LogoPrototype> dataToCopy) {
//        clipboardCutTableData= copyTableData(dataToCopy);
//        clipboardCopiedTableData = null;        
//      //  app.getFoolproofModule().updateAll();        
//    }
//    
//      private void copyNodeToCopiedCutClipboard(ArrayList<Node> itemsToCopy) {
//        clipboardCopiedNodes= copyPaneNodeUsingList(itemsToCopy);
//        clipboardCutNodes = null;        
//        app.getFoolproofModule().updateAll();        
//    }
//      
//      
//      private ArrayList<Node> copyPaneNodeUsingList(ArrayList<Node>toCopy) {
//          LogoData data = (LogoData)app.getDataComponent();
//         ArrayList<Node> nodeCopy = new ArrayList<>();   
//         for(int i=0;i<toCopy.size();i++){
//           if(toCopy.get(i) instanceof Rectangle)
//           {
//               Rectangle tempRect=new Rectangle();
//               tempRect.setWidth(300);
//               tempRect.setHeight(150);
//               tempRect.setFill(WHITE);
//               tempRect.setStroke(BLACK);
//               tempRect.setY(300);
//               tempRect.setX(250);
//               nodeCopy.add(tempRect);
//               
//           }
//           
//           else if(toCopy.get(i) instanceof TextField){
//              
//               TextField tempText=new TextField();
//                tempText.replaceText(0, 0,((TextField) toCopy.get(i)).getText());
//                tempText.setLayoutX(200);
//                tempText.setLayoutY(200);
//                tempText.setEditable(false);
//                nodeCopy.add(tempText);
//           }
//             
//             
//         }
//        return nodeCopy;
//          
//      }
//      
//      
//    private ArrayList<LogoPrototype> copyTableData(ArrayList<LogoPrototype> dataToCopy) {
//        ArrayList<LogoPrototype> tempCopy = new ArrayList();         
//        for (LogoPrototype prototypeToCopy : dataToCopy) {
//           LogoPrototype copiedData = (LogoPrototype)prototypeToCopy.clone();
//           tempCopy.add(copiedData);
//        }        
//        return tempCopy;
//    }
//    
//    
//     private ArrayList<Node> copyPaneNode(ArrayList<Integer> dataToCopy) {
//         LogoData data = (LogoData)app.getDataComponent();
//         ArrayList<Node> tempNodeCopy = new ArrayList<>();         
//      
//        for (Integer nodeIndexToCopy : dataToCopy) {
//           Node copiedNode = data.deepCloneNode(nodeIndexToCopy);
//           tempNodeCopy.add(copiedNode);
//       }        
//        return tempNodeCopy;
//    }
//    
//    private void recloneNodeClipboard(ArrayList<Integer> dataToCopy) {
//        clipboardCutNodes = null;        
//        clipboardCopiedNodes= copyPaneNode(dataToCopy);
//      //  app.getFoolproofModule().updateAll();        
//    }
//     
//    
//     
//    @Override
//    public void paste() {
//      LogoData data = (LogoData)app.getDataComponent();
//        if (data.isItemSelected()) {
//            int selectedIndex = data.getItemIndex(data.getSelectedItem());  
//            
//            ArrayList<LogoPrototype> pasteData = clipboardCutTableData;
//            ArrayList<Node> pasteNodes = clipboardCutNodes;
//            
//            if ((clipboardCutTableData != null)
//                    && (!clipboardCutTableData.isEmpty())) {
//                
//                PasteComponent_Transaction transaction = new PasteComponent_Transaction((GoLogoLo)app, pasteData,pasteNodes, selectedIndex);
//                app.processTransaction(transaction);
//                
//                // NOW WE HAVE TO RE-COPY THE CUT ITEMS TO MAKE
//                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
//                copyDataToCopiedCutClipboard(clipboardCopiedTableData);
//                copyNodeToCopiedCutClipboard(clipboardCopiedNodes);
//            }
//            
//            else if ((clipboardCopiedTableData != null)
//                    && (!clipboardCopiedTableData.isEmpty())) {
//                
//                PasteComponent_Transaction transaction = new PasteComponent_Transaction((GoLogoLo)app, clipboardCopiedTableData,clipboardCopiedNodes, selectedIndex);
//                app.processTransaction(transaction);
//            
//                // NOW WE HAVE TO RE-COPY THE COPIED ITEMS TO MAKE
//                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
//                copyToCopiedDataClipboard(clipboardCopiedTableData);
//                copyPaneNodeUsingList(clipboardCopiedNodes);
//                
//            }
//        }
//    }
//
//    @Override
//    public boolean hasSomethingToCut() {
//        return ((LogoData)app.getDataComponent()).isItemSelected()
//        || ((LogoData)app.getDataComponent()).areItemsSelected();
//    }
//
//    @Override
//    public boolean hasSomethingToCopy() {
//       return ((LogoData)app.getDataComponent()).isItemSelected()
//                || ((LogoData)app.getDataComponent()).areItemsSelected();
//    }
//
//    @Override
//    public boolean hasSomethingToPaste() {
//         if ((clipboardCutTableData != null) && (!clipboardCutTableData.isEmpty()))
//            return true;
//        else if ((clipboardCopiedTableData != null) && (!clipboardCopiedTableData.isEmpty()))
//            return true;
//        else
//            return false;
//    }
//    
//}
//


public class LogoClipboard implements AppClipboardComponent{
    GoLogoLo  app;
    
  
    LogoPrototype clipboardCutTableData;
    LogoPrototype clipboardCopyTableData;
    Node clipboardCutNode;
    Node clipboardCopyNode;
    
      
    
    
    
    public LogoClipboard(GoLogoLo initApp) {
         app = initApp;
         clipboardCutTableData=null;
         clipboardCopyTableData=null;
         clipboardCutNode=null;  
         clipboardCopyNode=null;
    }

    
    @Override
    public void cut() {
        LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) {
           clipboardCutTableData=data.getSelectedItem();
           clipboardCutNode=data.getSelectedNode(clipboardCutTableData);
            
           clipboardCopyNode = null;
           clipboardCopyTableData = null;
           
          
            CutComponents_Transaction transaction = new CutComponents_Transaction((GoLogoLo)app, clipboardCutTableData,clipboardCutNode);
            app.processTransaction(transaction);
     
            
        }
    }

    

    @Override
    public void copy() {
        LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) {
           LogoPrototype tempClipboardCopyTableData=data.getSelectedItem();
           Node tempClipboardCopyNode=data.getSelectedNode(tempClipboardCopyTableData);  
           copyToCopyNode(tempClipboardCopyNode,tempClipboardCopyTableData);
           copyToCopyData(tempClipboardCopyTableData);
           
           
       
            
        }
    }
           
    
    public void copyToCopyData(LogoPrototype temp){
        clipboardCopyTableData=(LogoPrototype) temp.clone();
        clipboardCutTableData=null;
        
    }

    public void copyToCopyNode(Node temp,LogoPrototype refData){
        LogoData data = (LogoData)app.getDataComponent();
        // int index=data.getItemIndex(refData);
         
        clipboardCopyNode=data.deepCloneNode(temp);
        clipboardCutNode=null;
    }
    
    public void copyToCutData(LogoPrototype temp){
        clipboardCopyTableData=null;
        clipboardCutTableData=(LogoPrototype) temp.clone();
        
    }

    public void copyToCutNode(Node temp,LogoPrototype refData){
        LogoData data = (LogoData)app.getDataComponent();
         
         
        clipboardCutNode=data.deepCloneNode(temp);
        clipboardCopyNode=null;
   
       
    }





     
    @Override
    public void paste() {
      LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) {
            int selectedIndex = data.getItemIndex(data.getSelectedItem());  
            
         
            if ((clipboardCutTableData != null))
            {
                
                PasteComponent_Transaction transaction = new PasteComponent_Transaction((GoLogoLo)app, clipboardCutTableData,clipboardCutNode, selectedIndex);
                copyToCutNode(clipboardCutNode,clipboardCutTableData);
                copyToCutData(clipboardCutTableData);
                app.processTransaction(transaction);
                
                // NOW WE HAVE TO RE-COPY THE CUT ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                
                
            }
            
            else if ((clipboardCopyTableData != null)) 
            {
                
                PasteComponent_Transaction transaction = new PasteComponent_Transaction((GoLogoLo)app, clipboardCopyTableData,clipboardCopyNode, selectedIndex);
                copyToCopyNode(clipboardCopyNode,clipboardCopyTableData);
                copyToCopyData(clipboardCopyTableData);
                app.processTransaction(transaction);
            
                // NOW WE HAVE TO RE-COPY THE COPIED ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
               
                
            }
        }
    }

    
    
    @Override
    public boolean hasSomethingToCut() {
        return ((LogoData)app.getDataComponent()).isItemSelected()
        || ((LogoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToCopy() {
       return ((LogoData)app.getDataComponent()).isItemSelected()
                || ((LogoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToPaste() {
         if ((clipboardCutTableData != null))
            return true;
        else if ((clipboardCopyTableData != null) )
            return true;
        else
            return false;
    }

    
}

