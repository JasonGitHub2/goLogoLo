/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.data.LogoText;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class UnderlineText_Transaction implements jTPS_Transaction{
    
    GoLogoLo app;
    LogoPrototype selectedText;
    LogoData data;
    LogoText textNode;
    
     public UnderlineText_Transaction(LogoPrototype selectedData, GoLogoLo appData){
         app=appData;
         selectedText=selectedData;
         data=(LogoData)app.getDataComponent();   
         textNode=(LogoText) data.getSelectedNode(selectedText);    
     }
    
    @Override
    public void doTransaction() {
       if(!textNode.getIsUnderline()){
           textNode.setUnderline(true);
           textNode.setIsUnderline(true);
       }
       else if(textNode.getIsUnderline()){
           textNode.setUnderline(false);
            textNode.setIsUnderline(false);
       }
    }

    @Override
    public void undoTransaction() {
      if(!textNode.getIsUnderline()){
           textNode.setUnderline(true);
           textNode.setIsUnderline(true);
       }
       else if(textNode.getIsUnderline()){
           textNode.setUnderline(false);
           textNode.setIsUnderline(false);
       }
    }
    
}
