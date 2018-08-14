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
import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class ChangeFont_Transaction implements jTPS_Transaction {
    LogoData data;
    String newFont;
    LogoPrototype fontToChange;
    GoLogoLo app;
    double size;
    LogoText textToChange;
    Font oldFont;
    String oldFontName;
    
    public ChangeFont_Transaction(String font,LogoPrototype selectedLogo,GoLogoLo appLogo){
        newFont=font;
        fontToChange=selectedLogo;
        app=appLogo;
        data=(LogoData)app.getDataComponent();   
        textToChange= (LogoText) data.getSelectedNode(selectedLogo);
        oldFont=textToChange.getFont();
        oldFontName=textToChange.getFontName();
  
        
    }
    @Override
    public void doTransaction() {
       Font chosenFont=new Font(newFont,textToChange.getFontSize());
       textToChange.setFont(chosenFont);
       textToChange.setFontName(newFont);
    
    }

    @Override
    public void undoTransaction() {
        
         textToChange.setFont(oldFont);
         textToChange.setFontName(oldFontName);
    }
    
}
