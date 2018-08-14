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
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class ChangeFontSize_Transaction implements jTPS_Transaction{
    int newSize;
    int oldSize;
    LogoPrototype selectedText;
    LogoText selectedNode;
    GoLogoLo app;
    LogoData data;
    String font;
  
    public ChangeFontSize_Transaction(int size,LogoPrototype selectedData,GoLogoLo appData){
        newSize=size;
        selectedText=selectedData;
        app=appData;
        data=(LogoData)app.getDataComponent();   
        selectedNode=(LogoText) data.getSelectedNode(selectedText);
        oldSize=selectedNode.getFontSize();
        font=selectedNode.getFontName();
    }
    @Override
    public void doTransaction() {
        Font newFont=new Font(font,newSize);
        selectedNode.setFont(newFont);
        selectedNode.setFontName(font);
        selectedNode.setFontSize(newSize);
    }

    @Override
    public void undoTransaction() {
        Font newFont=new Font(font,oldSize);
        selectedNode.setFont(newFont);
        selectedNode.setFontName(font);
        selectedNode.setFontSize(oldSize);
    }
    
}
