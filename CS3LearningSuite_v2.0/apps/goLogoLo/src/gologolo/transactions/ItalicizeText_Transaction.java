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
import javafx.scene.text.FontPosture;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class ItalicizeText_Transaction  implements jTPS_Transaction{
    LogoPrototype selectedText;
    LogoData data;
    GoLogoLo app;
    LogoText textNode;
    
    public ItalicizeText_Transaction(LogoPrototype selectedData, GoLogoLo appData){
        selectedText=selectedData;
        app=appData;
        data=(LogoData)app.getDataComponent();   
        textNode=(LogoText) data.getSelectedNode(selectedText);    
        
    }

    @Override
    public void doTransaction() {
       if(!textNode.getIsItalicized()){
            textNode.setFont(Font.font(textNode.getFontName(), FontPosture.ITALIC, textNode.getFontSize()));
            textNode.setIsItalicized(true);
                 textNode.setIsBold(false);
        }
        else if(textNode.getIsItalicized()){
            textNode.setFont(Font.font(textNode.getFontName(), FontPosture.REGULAR, textNode.getFontSize()));
            textNode.setIsItalicized(false);
            
        }
    }

    @Override
    public void undoTransaction() {
     if(!textNode.getIsItalicized()){
            textNode.setFont(Font.font(textNode.getFontName(), FontPosture.ITALIC, textNode.getFontSize()));
            textNode.setIsItalicized(true);
            textNode.setIsBold(false);
        }
        else if(textNode.getIsItalicized()){
            textNode.setFont(Font.font(textNode.getFontName(), FontPosture.REGULAR, textNode.getFontSize()));
            textNode.setIsItalicized(false);
         
        }
    }
    
}
