/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLo;
import gologolo.data.LogoCircle;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.data.LogoRectangle;
import static gologolo.goLogoLoPropertyType.COLOR_PICKER;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class BorderColor_Transaction implements jTPS_Transaction{
    LogoPrototype selected;
    LogoData data;
    GoLogoLo app;
    Color newColor;
    Color oldColor;
   ColorPicker borderColorPicker;
    public BorderColor_Transaction(LogoData logoData, LogoPrototype selectedItem,GoLogoLo app1,Color thisColor){
        selected=selectedItem;
        data=logoData;
        app=app1;
        newColor=thisColor;
        borderColorPicker=(ColorPicker)data.getApp().getGUIModule().getGUINode(COLOR_PICKER);
        
        
    }
    
    @Override
    public void doTransaction() {
       if(selected.getType().equalsIgnoreCase("Rectangle")){
           int index=data.getItemIndex(selected);
           LogoRectangle shape=(LogoRectangle)data.getEditComponents().get(index);
           
           oldColor=shape.getBorderColor();
           shape.setStroke(newColor);
           shape.setBorderColor(newColor);
           borderColorPicker.setValue(newColor);
           
       }
       
       else if(selected.getType().equalsIgnoreCase("Circle")){
           int index=data.getItemIndex(selected);
           LogoCircle shape=(LogoCircle)data.getEditComponents().get(index);
           
           oldColor=shape.getBorderColor();
           shape.setStroke(newColor);
           shape.setBorderColor(newColor);
           borderColorPicker.setValue(newColor);
       }
       
    }

    @Override
    public void undoTransaction() {
        
        if(selected.getType().equalsIgnoreCase("Rectangle")){
         
           int index=data.getItemIndex(selected);
           LogoRectangle shape=(LogoRectangle)data.getEditComponents().get(index);
           
           shape.setStroke(oldColor);
           shape.setBorderColor(oldColor);  
            borderColorPicker.setValue(oldColor);
       }
         
         else if(selected.getType().equalsIgnoreCase("Circle")){
           int index=data.getItemIndex(selected);
           LogoCircle shape=(LogoCircle)data.getEditComponents().get(index);
           
           shape.setStroke(oldColor);
           shape.setBorderColor(oldColor);  
            borderColorPicker.setValue(oldColor);
           
       }

    }
    
}