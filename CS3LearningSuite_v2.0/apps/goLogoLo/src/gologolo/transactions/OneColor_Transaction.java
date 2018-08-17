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
import static gologolo.goLogoLoPropertyType.LOGO_ONE_COLOR;
import static gologolo.goLogoLoPropertyType.LOGO_ZERO_COLOR;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class OneColor_Transaction  implements jTPS_Transaction{
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
  
    
    public OneColor_Transaction(LogoData thisData,GoLogoLo appLogo,LogoPrototype component,Color color,boolean isCircles,boolean isRectangles){
        data=thisData;
        colorOneBox=(ColorPicker)data.getApp().getGUIModule().getGUINode(LOGO_ONE_COLOR);
        app=appLogo;
        selected=component;
        newColor=color;
        index=data.getItemIndex(selected);
        newStop=new Stop(1,newColor);
        isCircle=isCircles;
        isRectangle=isRectangles;

    }
    
    @Override
    public void doTransaction() {
      if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          oldStop=selectedRectangle.getStop2();
          oldColor=selectedRectangle.getStop1Color();
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),newStop);
          selectedRectangle.setStop2(newStop);
          selectedRectangle.setFill(newGradient);
          colorOneBox.setValue(newColor);
         }
      else if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
           oldStop=selectedCircle.getStop1();
          newGradient=new RadialGradient(selectedCircle.getFocusAngle(),selectedCircle.getFocusDistance(),selectedCircle.getCenterX(),
          selectedCircle.getCenterY(),selectedCircle.getRadius(),selectedCircle.getProportion(),selectedCircle.getCycleMethod(),selectedCircle.getStop0(),newStop);
             selectedCircle.setStop1(newStop);
          selectedCircle.setFill(newGradient);
          colorOneBox.setValue(newColor);
         }
    }

    @Override
    public void undoTransaction() {
    if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
          selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),selectedRectangle.getCycleMethod(),selectedRectangle.getStop1(),oldStop);
          selectedRectangle.setStop2(oldStop);
          selectedRectangle.setFill(newGradient);
          colorOneBox.setValue(oldColor);
         }
     else  if(isCircle){
         
          selectedCircle=   (LogoCircle) data.getEditComponents().get(index);
          oldGradient=new RadialGradient(selectedCircle.getFocusAngle(),selectedCircle.getFocusDistance(),selectedCircle.getCenterX(),
          selectedCircle.getCenterY(),selectedCircle.getRadius(),selectedCircle.getProportion(),selectedCircle.getCycleMethod(),selectedCircle.getStop0(),oldStop);
            selectedCircle.setStop1(oldStop);
          selectedCircle.setFill(oldGradient);
            colorOneBox.setValue(oldColor);
    }
    }
    
}
