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
import static gologolo.goLogoLoPropertyType.LOGO_ZERO_COLOR;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class ZeroColor_Transaction  implements jTPS_Transaction{
    LogoData data;
    GoLogoLo app;
    LogoPrototype selected;
   Color newColor;
    LogoRectangle selectedRectangle;
    RadialGradient newGradient;
    boolean isCircle=false;
    boolean isRectangle=false;
    LogoCircle selectedCircle;
     RadialGradient oldGradient;
    int index;
    Stop newStop;
       ColorPicker colorOneBox;
      Stop oldStop;
      Color oldColor;
    
    public ZeroColor_Transaction(LogoData thisData,GoLogoLo appLogo,LogoPrototype component,Color color,boolean isCircles,boolean isRectangles){
        data=thisData;
        app=appLogo;
         colorOneBox=(ColorPicker)data.getApp().getGUIModule().getGUINode(LOGO_ZERO_COLOR);
        selected=component;
        newColor=color;
        index=data.getItemIndex(selected);
        newStop=new Stop(0,newColor);
        isCircle=isCircles;
        isRectangle=isRectangles;

    }
    
    @Override
    public void doTransaction() {
      if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          oldStop=selectedRectangle.getStop1();
          oldColor=selectedRectangle.getStop0Color();
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),newStop,selectedRectangle.getStop2());
          selectedRectangle.setStop1(newStop);
          selectedRectangle.setFill(newGradient);
          colorOneBox.setValue(newColor);
         }
      else if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
           oldStop=selectedCircle.getStop0();
          newGradient=new RadialGradient(selectedCircle.getFocusAngle(),selectedCircle.getFocusDistance(),selectedCircle.getGradientCenterX(),
          selectedCircle.getGradientCenterY(),selectedCircle.getGradientRadius(),selectedCircle.getProportion(),selectedCircle.getCycleMethod(),newStop,selectedCircle.getStop1());
             selectedCircle.setStop0(newStop);
          selectedCircle.setFill(newGradient);
          colorOneBox.setValue(newColor);
         }
    }

    @Override
    public void undoTransaction() {
       if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),oldStop,selectedRectangle.getStop2());
          selectedRectangle.setStop1(oldStop);
          selectedRectangle.setFill(newGradient);
                  colorOneBox.setValue(oldColor);
         }
       else  if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
          oldGradient=new RadialGradient(selectedCircle.getFocusAngle(),selectedCircle.getFocusDistance(),selectedCircle.getGradientCenterX(),
          selectedCircle.getGradientCenterY(),selectedCircle.getGradientRadius(),selectedCircle.getProportion(),selectedCircle.getCycleMethod(),oldStop,selectedCircle.getStop1());
            selectedCircle.setStop0(oldStop);
          selectedCircle.setFill(oldGradient);
            colorOneBox.setValue(oldColor);
    }
    }
    
}
