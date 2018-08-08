/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

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
//public class PasteComponent_Transaction implements jTPS_Transaction{
//
//    GoLogoLo app;
//    ArrayList<LogoPrototype>dataToPaste;
//    ArrayList<Node> nodeToPaste;
//    int indexOfSelected;
//    
//    
//    public PasteComponent_Transaction(GoLogoLo appLogo,ArrayList<LogoPrototype> data,ArrayList<Node>node,int index){
//        app=appLogo;
//        dataToPaste=data;
//        nodeToPaste=node;
//        indexOfSelected=index;
//    }
//    
//    
//    
//    @Override
//    public void doTransaction() {
//        LogoData data = (LogoData)app.getDataComponent();
//
//        int index = indexOfSelected+1;
//        for (int i=0;i<dataToPaste.size();i++)
//        {
//            data.addItemAt(dataToPaste.get(i), index,nodeToPaste.get(i));
//            index++;
//          
//        }
//    }
//
//    @Override
//    public void undoTransaction() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//}
public class PasteComponent_Transaction implements jTPS_Transaction{

    GoLogoLo app;
    LogoPrototype dataToPaste;
    Node nodeToPaste;
    int indexOfSelected;
    
    
    public PasteComponent_Transaction(GoLogoLo appLogo,LogoPrototype data,Node node,int index){
        app=appLogo;
        dataToPaste=data;
        nodeToPaste=node;
        indexOfSelected=index;
    }
   
    
    
    @Override
    public void doTransaction() {
        LogoData data = (LogoData)app.getDataComponent();

        int index = indexOfSelected+1;
       
            data.addItemAt(dataToPaste, index,nodeToPaste);
       
    }

    @Override
    public void undoTransaction() {
         LogoData data = (LogoData)app.getDataComponent();
         data.remove(dataToPaste);
         data.removeFromPane(indexOfSelected);
    }
    
}
