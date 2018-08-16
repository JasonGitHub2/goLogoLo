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
import static gologolo.goLogoLoPropertyType.LOGO_FOCUS_DISTANCE_SLIDER;
import javafx.scene.control.Slider;
import javafx.scene.paint.RadialGradient;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class FocusDistance_Transaction  implements jTPS_Transaction{
    LogoData data;
    GoLogoLo app;
    LogoPrototype selected;
    double distance;
    LogoRectangle selectedRectangle;
    RadialGradient newGradient;
    boolean isCircle=false;
    boolean isRectangle=false;
    boolean isImage=false;
    int index;
     Slider focusDistanceSlider;
    double oldDistance;
    public FocusDistance_Transaction(LogoData thisData,GoLogoLo appLogo,LogoPrototype component,double newDistance,boolean isCircles,boolean isImages,boolean isRectangles){
        data=thisData;
        app=appLogo;
        selected=component;
       distance=newDistance;
        index=data.getItemIndex(selected);
     focusDistanceSlider=(Slider)data.getApp().getGUIModule().getGUINode(LOGO_FOCUS_DISTANCE_SLIDER);
        isCircle=isCircles;
        isRectangle=isRectangles;
        isImage=isImages;
    }
    
    @Override
    public void doTransaction() {
      if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          oldDistance=selectedRectangle.getFocusDistance();
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),distance,selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setFocusDistance(distance);
          selectedRectangle.setFill(newGradient);
          focusDistanceSlider.setValue(distance);
         }
    }

    @Override
    public void undoTransaction() {
        if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
         
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),oldDistance,selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setFocusDistance(oldDistance);
          selectedRectangle.setFill(newGradient);
          focusDistanceSlider.setValue(oldDistance);
         }
    }
    
}

    

