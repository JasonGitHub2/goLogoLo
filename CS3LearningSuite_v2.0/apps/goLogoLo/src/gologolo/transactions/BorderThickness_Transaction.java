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
import gologolo.data.LogoText;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_THICKNESS_SLIDER;
import javafx.scene.control.Slider;
import javafx.scene.shape.Shape;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class BorderThickness_Transaction implements jTPS_Transaction{
    LogoPrototype selected;
    LogoData data;
    GoLogoLo app;
    double thicknessLevel;
    double oldThicknessLevel;
    Slider thicknessSlider;
    public BorderThickness_Transaction(LogoData logoData, GoLogoLo app1,LogoPrototype selectedItem,double value){
        selected=selectedItem;
        data=logoData;
        app=app1;
        thicknessLevel=value;
        thicknessSlider=(Slider)data.getApp().getGUIModule().getGUINode(LOGO_BORDER_THICKNESS_SLIDER);
        
        
    }
    
    @Override
    public void doTransaction() {
       if(selected.getType().equalsIgnoreCase("Rectangle")){
           int index=data.getItemIndex(selected);
           LogoRectangle shape=(LogoRectangle)data.getEditComponents().get(index);
           
           oldThicknessLevel=shape.getRectangleStrokeWidth();
           shape.setStrokeWidth(thicknessLevel);
           shape.setRectangleStrokeWidth(thicknessLevel);  
           thicknessSlider.setValue(thicknessLevel);
           
       }
       else if(selected.getType().equalsIgnoreCase("Circle")){
           int index=data.getItemIndex(selected);
           LogoCircle shape=(LogoCircle)data.getEditComponents().get(index);
           
           oldThicknessLevel=shape.getCircleBorderThickness();
           shape.setStrokeWidth(thicknessLevel);
           shape.setCircleBorderThickness(thicknessLevel);  
           thicknessSlider.setValue(thicknessLevel);
           
       }
       
    }

    @Override
    public void undoTransaction() {
         if(selected.getType().equalsIgnoreCase("Rectangle")){
         
            int index=data.getItemIndex(selected);
           LogoRectangle shape=(LogoRectangle)data.getEditComponents().get(index);
           
           
           shape.setStrokeWidth(oldThicknessLevel);
           shape.setRectangleStrokeWidth(oldThicknessLevel);  
           thicknessSlider.setValue(oldThicknessLevel);  
       }
         
         else if(selected.getType().equalsIgnoreCase("Circle")){
           int index=data.getItemIndex(selected);
           LogoCircle shape=(LogoCircle)data.getEditComponents().get(index);
           
           
           shape.setStrokeWidth(oldThicknessLevel);
           shape.setCircleBorderThickness(oldThicknessLevel);  
           thicknessSlider.setValue(oldThicknessLevel);
           
       }

    }
    
}