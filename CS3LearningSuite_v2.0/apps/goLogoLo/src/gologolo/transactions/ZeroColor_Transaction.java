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
    boolean isImage=false;
    int index;
    Stop newStop;
       ColorPicker colorOneBox;
      Stop oldStop;
      Color oldColor;
    
    public ZeroColor_Transaction(LogoData thisData,GoLogoLo appLogo,LogoPrototype component,Color color,boolean isCircles,boolean isImages,boolean isRectangles){
        data=thisData;
        app=appLogo;
         colorOneBox=(ColorPicker)data.getApp().getGUIModule().getGUINode(LOGO_ZERO_COLOR);
        selected=component;
        newColor=color;
        index=data.getItemIndex(selected);
        newStop=new Stop(0,newColor);
        isCircle=isCircles;
        isRectangle=isRectangles;
        isImage=isImages;
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
    }
    
}
