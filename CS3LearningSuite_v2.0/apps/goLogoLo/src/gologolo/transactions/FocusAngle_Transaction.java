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
import static gologolo.goLogoLoPropertyType.LOGO_FOCUS_ANGLE_SLIDER;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Rectangle;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class FocusAngle_Transaction implements jTPS_Transaction{
    LogoData data;
    GoLogoLo app;
    LogoPrototype selected;
    double angle;
    LogoRectangle selectedRectangle;
    RadialGradient newGradient;
    boolean isCircle=false;
    boolean isRectangle=false;
     LogoCircle selectedCircle;
     RadialGradient oldGradient;
    int index;
    Slider focusAngleSlider;
    double oldFocusAngle;
    public FocusAngle_Transaction(LogoData thisData,GoLogoLo appLogo,LogoPrototype component,double newAngle,boolean isCircles,boolean isRectangles){
        data=thisData;
        focusAngleSlider=(Slider)data.getApp().getGUIModule().getGUINode(LOGO_FOCUS_ANGLE_SLIDER);
        app=appLogo;
        selected=component;
        angle=newAngle;
        index=data.getItemIndex(selected);
     
        isCircle=isCircles;
        isRectangle=isRectangles;
    
    }
    
    @Override
    public void doTransaction() {
      if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          oldFocusAngle=selectedRectangle.getFocusAngle();
          newGradient=new RadialGradient(angle,selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setFocusAngle(angle);
          selectedRectangle.setFill(newGradient);
          focusAngleSlider.setValue(angle);
         }
      else if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
           oldFocusAngle=selectedCircle.getFocusAngle();
          newGradient=new RadialGradient(angle,selectedCircle.getFocusDistance(),selectedCircle.getGradientCenterX(),
          selectedCircle.getGradientCenterY(),selectedCircle.getGradientRadius(),selectedCircle.getProportion(),selectedCircle.getCycleMethod(),selectedCircle.getStop0(),selectedCircle.getStop1());
          selectedCircle.setFocusAngle(angle);
           selectedCircle.setFill(newGradient);
          focusAngleSlider.setValue(angle);
         }
    }

    @Override
    public void undoTransaction() {
        if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
     
          newGradient=new RadialGradient(oldFocusAngle,selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setFocusAngle(oldFocusAngle);
          selectedRectangle.setFill(newGradient);
          focusAngleSlider.setValue(oldFocusAngle);
         }
        
         else  if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
          oldGradient=new RadialGradient(angle,selectedCircle.getFocusDistance(),selectedCircle.getGradientCenterX(),
          selectedCircle.getGradientCenterY(),selectedCircle.getGradientRadius(),selectedCircle.getProportion(),selectedCircle.getCycleMethod(),selectedCircle.getStop0(),selectedCircle.getStop1());
           selectedCircle.setFocusAngle(oldFocusAngle);
          selectedCircle.setFill(oldGradient);
         focusAngleSlider.setValue(oldFocusAngle);
    }
    
}
}
