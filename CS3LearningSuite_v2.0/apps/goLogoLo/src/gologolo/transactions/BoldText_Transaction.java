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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class BoldText_Transaction implements jTPS_Transaction{
    LogoPrototype selectedText;
    LogoData data;
    GoLogoLo app;
    LogoText textNode;
    
    public BoldText_Transaction(LogoPrototype selectedData, GoLogoLo appData){
        selectedText=selectedData;
        app=appData;
        data=(LogoData)app.getDataComponent();   
        textNode=(LogoText) data.getSelectedNode(selectedText);    
        
    }

    @Override
    public void doTransaction() {
        if(!textNode.getIsBold()){
            textNode.setFont(Font.font(textNode.getFontName(), FontWeight.BOLD, textNode.getFontSize()));
            textNode.setIsBold(true);
             textNode.setIsItalicized(false);
        }
        else if(textNode.getIsBold()){
            textNode.setFont(Font.font(textNode.getFontName(), FontWeight.NORMAL, textNode.getFontSize()));
            textNode.setIsBold(false);
                
        }
    }

    @Override
    public void undoTransaction() {
       if(!textNode.getIsBold()){
            textNode.setFont(Font.font(textNode.getFontName(), FontWeight.BOLD, textNode.getFontSize()));
            textNode.setIsBold(true);
            textNode.setIsItalicized(false);
        }
        else if(textNode.getIsBold()){
            textNode.setFont(Font.font(textNode.getFontName(), FontWeight.NORMAL, textNode.getFontSize()));
            textNode.setIsBold(false);
            
        }
    }
    
}
