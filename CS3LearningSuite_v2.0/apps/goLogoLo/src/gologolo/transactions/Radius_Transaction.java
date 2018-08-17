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
import static gologolo.goLogoLoPropertyType.LOGO_RADIUS_SLIDER;
import javafx.scene.control.Slider;
import javafx.scene.paint.RadialGradient;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class Radius_Transaction  implements jTPS_Transaction{
    LogoData data;
    GoLogoLo app;
    LogoPrototype selected;
    double radius;
    LogoRectangle selectedRectangle;
    RadialGradient newGradient;
    boolean isCircle=false;
    boolean isRectangle=false;
LogoCircle selectedCircle;
     RadialGradient oldGradient;
    int index;
    double oldRadius;
     Slider radiusSlider;
    public Radius_Transaction(LogoData thisData,GoLogoLo appLogo,LogoPrototype component,double newRadius,boolean isCircles,boolean isRectangles){
        data=thisData;
        radiusSlider=(Slider)data.getApp().getGUIModule().getGUINode(LOGO_RADIUS_SLIDER);
        app=appLogo;
        selected=component;
        radius=newRadius;
        index=data.getItemIndex(selected);
     
        isCircle=isCircles;
        isRectangle=isRectangles;

    }
    
    @Override
    public void doTransaction() {
      if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          oldRadius=selectedRectangle.getRadius();
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),radius,selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setRadius(radius);
          selectedRectangle.setFill(newGradient);
          radiusSlider.setValue(radius);
         }
       else if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
            oldRadius=selectedCircle.getGradientRadius();
          newGradient=new RadialGradient(selectedCircle.getFocusAngle(),selectedCircle.getFocusDistance(),selectedCircle.getCenterX(),
          selectedCircle.getCenterY(),radius,selectedCircle.getProportion(),selectedCircle.getCycleMethod(),selectedCircle.getStop0(),selectedCircle.getStop1());
             selectedCircle.setGradientRadius(radius);
          selectedCircle.setFill(newGradient);
            radiusSlider.setValue(radius);
         }
    }

    @Override
    public void undoTransaction() {
     if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
         
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),oldRadius,selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setRadius(oldRadius);
          selectedRectangle.setFill(newGradient);
          radiusSlider.setValue(oldRadius);
         }
      else  if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
          oldGradient=new RadialGradient(selectedCircle.getFocusAngle(),selectedCircle.getFocusDistance(),selectedCircle.getCenterX(),
          selectedCircle.getCenterY(),oldRadius,selectedCircle.getProportion(),selectedCircle.getCycleMethod(),selectedCircle.getStop0(),selectedCircle.getStop1());
           selectedCircle.setRadius(oldRadius);
          selectedCircle.setFill(oldGradient);
       radiusSlider.setValue(oldRadius);    
    }
    }
    
}
