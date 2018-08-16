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
import static gologolo.goLogoLoPropertyType.LOGO_CYCLE_METHOD_COMBO_BOX;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.CycleMethod;
import static javafx.scene.paint.CycleMethod.NO_CYCLE;
import static javafx.scene.paint.CycleMethod.REFLECT;
import static javafx.scene.paint.CycleMethod.REPEAT;
import javafx.scene.paint.RadialGradient;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class CycleMethod_Transaction implements jTPS_Transaction{
    LogoData data;
    GoLogoLo app;
    LogoPrototype selected;
    String cycleMethod;
    LogoRectangle selectedRectangle;
    RadialGradient newGradient;
    boolean isCircle=false;
    boolean isRectangle=false;
    boolean isImage=false;
    int index;
    CycleMethod newCycleMethod;
    CycleMethod oldCycleMethod;
     ComboBox cycleMethodBox;
    
    public CycleMethod_Transaction(LogoData thisData,GoLogoLo appLogo,LogoPrototype component,String method,boolean isCircles,boolean isImages,boolean isRectangles){
        data=thisData;
        cycleMethodBox=(ComboBox)data.getApp().getGUIModule().getGUINode(LOGO_CYCLE_METHOD_COMBO_BOX);
        app=appLogo;
        selected=component;
        cycleMethod=method;
        if(cycleMethod.equals("NO_CYCLE")){
            newCycleMethod=NO_CYCLE;
        }
        else if(cycleMethod.equals("REFLECT")){
            newCycleMethod=REFLECT;
        }
        else if(cycleMethod.equals("REPEAT")){
            newCycleMethod=REPEAT;
        }
       index=data.getItemIndex(selected);
     
        isCircle=isCircles;
        isRectangle=isRectangles;
        isImage=isImages;
    }
    
    @Override
    public void doTransaction() {
      if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
          oldCycleMethod=selectedRectangle.getCycleMethod();
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
         selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),newCycleMethod,selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setCycleMethod(newCycleMethod);
          selectedRectangle.setFill(newGradient);
          cycleMethodBox.setValue(newCycleMethod);
         }
    }

    @Override
    public void undoTransaction() {
        if(isRectangle){
          selectedRectangle=   (LogoRectangle) data.getEditComponents().get(index);
         
          newGradient=new RadialGradient(selectedRectangle.getFocusAngle(),selectedRectangle.getFocusDistance(),selectedRectangle.getCenterX(),
         selectedRectangle.getCenterY(),selectedRectangle.getRadius(),selectedRectangle.getProportion(),oldCycleMethod,selectedRectangle.getStop1(),selectedRectangle.getStop2());
          selectedRectangle.setCycleMethod(oldCycleMethod);
          selectedRectangle.setFill(newGradient);
          cycleMethodBox.setValue(oldCycleMethod);
         }
    }
    
}
