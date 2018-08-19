/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import javafx.scene.Node;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class DeleteComponent_Transaction implements jTPS_Transaction{
    GoLogoLo app;
    LogoPrototype deleteComponent;
    int deletedIndex;
    LogoData data;
    Node deletedNode;
    
    public DeleteComponent_Transaction(GoLogoLo appLogo,LogoPrototype component, LogoData appData) {
       app=appLogo;
       deleteComponent=component; 
       data=appData;
       
    }

    @Override
    public void doTransaction() {
        
        deletedIndex=data.getItemIndex(deleteComponent);
        deletedNode=data.getEditComponents().get(deletedIndex);
        data.getEditComponents().remove(deletedNode);
        data.getComponents().remove(deletedIndex);
        data.reorderTable();
    

        
    }

    @Override
    public void undoTransaction() {
     
      data.getComponents().add(deletedIndex, deleteComponent);
      data.getEditComponents().add(deletedIndex, deletedNode);
      data.reorderTable();
      data.clearSelected();
      data.selectNodeInPane(deletedNode);
      data.selectItem(deleteComponent);
    }
    
}
