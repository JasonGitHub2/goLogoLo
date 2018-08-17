/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.data.LogoRectangle;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_RAIDIUS_SLIDER;
import javafx.scene.control.Slider;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class BorderRadius_Transaction implements jTPS_Transaction{
    LogoPrototype selected;
    LogoData data;
    GoLogoLo app;
    Slider radiusSlider;
    double arcHeight;
    double arcWidth;
    double oldArcHeight;
    double oldArcWidth;
            
    
    
    public BorderRadius_Transaction(LogoData logoData, LogoPrototype selectedItem,GoLogoLo app1,double value){
        selected=selectedItem;
        data=logoData;
        app=app1;
        radiusSlider=(Slider)data.getApp().getGUIModule().getGUINode(LOGO_BORDER_RAIDIUS_SLIDER);
        arcHeight=value;
        arcWidth=value;
        
    }
    
    @Override
    public void doTransaction() {
       if(selected.getType().equalsIgnoreCase("Rectangle")){
           int index=data.getItemIndex(selected);
           LogoRectangle shape=(LogoRectangle)data.getEditComponents().get(index);
           oldArcHeight=shape.getRectangleArcHeight();
           oldArcWidth=shape.getRectangleArcWidth();
           
           shape.setArcHeight(arcHeight);
           shape.setArcWidth(arcWidth);
           shape.setRectangleArcHeight(arcHeight);
           shape.setRectangleArcWidth(arcWidth);
           radiusSlider.setValue(arcHeight);
           
       }
       
      
       
    }

    @Override
    public void undoTransaction() {
        
        if(selected.getType().equalsIgnoreCase("Rectangle")){
         
           int index=data.getItemIndex(selected);
           LogoRectangle shape=(LogoRectangle)data.getEditComponents().get(index);
           
           shape.setArcHeight(oldArcWidth);
           shape.setArcWidth(oldArcWidth);
           shape.setRectangleArcWidth(oldArcWidth);
           shape.setRectangleArcHeight(oldArcHeight);
           radiusSlider.setValue(oldArcWidth);
       }

    }
    
}

