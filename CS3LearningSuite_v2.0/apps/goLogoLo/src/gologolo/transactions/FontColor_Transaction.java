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
import static gologolo.goLogoLoPropertyType.FONT_COLOR_PICKER;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class FontColor_Transaction  implements jTPS_Transaction{

    LogoData data;
    LogoPrototype selected;
    GoLogoLo app;
    Color newColor;
    Color oldColor;
    ColorPicker fontColor;
    LogoText text;
    
    
    public FontColor_Transaction(LogoData thisData,LogoPrototype selectedItem,GoLogoLo initApp,Color color){
        data=thisData;
        selected=selectedItem;
        app=initApp;
        newColor=color;
        fontColor=(ColorPicker)data.getApp().getGUIModule().getGUINode(FONT_COLOR_PICKER);
        int index=data.getItemIndex(selected);
        text=(LogoText) data.getEditComponents().get(index);
    }
    @Override
    public void doTransaction() {
       oldColor=text.getFontColor();
       text.setFill(newColor);
       fontColor.setValue(newColor);
    }

    @Override
    public void undoTransaction() {
      text.setFill(oldColor);
        fontColor.setValue(oldColor);
    }
    
}
