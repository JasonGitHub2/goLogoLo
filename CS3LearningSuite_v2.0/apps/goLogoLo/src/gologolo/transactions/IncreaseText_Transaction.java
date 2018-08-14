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
public class IncreaseText_Transaction implements jTPS_Transaction{
    LogoPrototype selectedText;
    LogoData data;
    GoLogoLo app;
    LogoText textNode;
    String fontName;
    int newSize;
    int oldSize;
    Font oldFont;
    Font newFont;
    
    public IncreaseText_Transaction(LogoPrototype selectedData, GoLogoLo appData){
        selectedText=selectedData;
        app=appData;
        data=(LogoData)app.getDataComponent();   
        textNode=(LogoText) data.getSelectedNode(selectedText);    
        fontName=textNode.getFontName();
        oldSize=textNode.getFontSize();
        oldFont=new Font(fontName,oldSize);
        newSize=oldSize+2;
        newFont=new Font(fontName,newSize);
        
    }

    @Override
    public void doTransaction() {
      textNode.setFont(newFont);
      textNode.setFontSize(newSize);
    }

    @Override
    public void undoTransaction() {
         textNode.setFont(oldFont);
      textNode.setFontSize(oldSize);
    }

    
}
