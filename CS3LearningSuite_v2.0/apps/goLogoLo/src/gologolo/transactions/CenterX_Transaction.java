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
import static gologolo.goLogoLoPropertyType.LOGO_CENTER_X_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.RadialGradient;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class CenterX_Transaction  implements jTPS_Transaction{
    LogoData data;
    GoLogoLo app;
    LogoPrototype selected;
    double centerX;
    double oldCenterX;
    LogoRectangle selectedRectangle;
     LogoCircle selectedCircle;
     RadialGradient oldGradient;
    RadialGradient newGradient;
    
    boolean isCircle=false;
    boolean isRectangle=false;

    int index;
    Slider centerXSlider;
    
    public CenterX_Transaction(LogoData thisData,GoLogoLo appLogo,LogoPrototype component,double newX,boolean isCircles,boolean isRectangles){
        data=thisData;
        centerXSlider=(Slider)data.getApp().getGUIModule().getGUINode(LOGO_CENTER_X_SLIDER);
        app=appLogo;
        selected=component;
        centerX=newX;
        index=data.getItemIndex(selected);
         
        isCircle=isCircles;
        isRectangle=isRectangles;

    }
    
    @Override
    public void doTransaction() {
      if(isRectangle){
         
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          oldCenterX=selectedRectangle.getCenterX();
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),centerX,
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setCenterX(centerX);
          selectedRectangle.setFill(newGradient);
          
          centerXSlider.setValue(centerX);
         }
      else if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
          oldCenterX=selectedCircle.getGradientCenterX();
          newGradient=new RadialGradient(selectedCircle.getFocusAngle(),selectedCircle.getFocusDistance(),centerX,
          selectedCircle.getGradientCenterY(),selectedCircle.getGradientRadius(),selectedCircle.getProportion(),selectedCircle.getCycleMethod(),selectedCircle.getStop0(),selectedCircle.getStop1());
          selectedCircle.setGradientCenterX(centerX);
          selectedCircle.setFill(newGradient);
          
          centerXSlider.setValue(centerX);
         }
    }

    @Override
    public void undoTransaction() {
         if(isRectangle){
         
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);     
          oldGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),oldCenterX,
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setCenterX(oldCenterX);
          selectedRectangle.setFill(oldGradient);
          centerXSlider.setValue(oldCenterX);
         }
         
         else  if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
          oldGradient=new RadialGradient(selectedCircle.getFocusAngle(),selectedCircle.getFocusDistance(),oldCenterX,
          selectedCircle.getGradientCenterY(),selectedCircle.getGradientRadius(),selectedCircle.getProportion(),selectedCircle.getCycleMethod(),selectedCircle.getStop0(),selectedCircle.getStop1());
          selectedCircle.setGradientCenterX(oldCenterX);
          selectedCircle.setFill(oldGradient);
          centerXSlider.setValue(oldCenterX);
         }
    }
    
}

    

