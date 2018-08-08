/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import djf.ui.AppNodesBuilder;
import gologolo.GoLogoLo;
import gologolo.components.RectangleComponent;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import static gologolo.goLogoLoPropertyType.RECTANGLE_COMPONENT;
import gologolo.workspace.goLogoLoWorkspace;
import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_SHAPE;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class AddRectangle_Transaction implements jTPS_Transaction {
    LogoData data;
    LogoPrototype rectangleToAdd;
    RectangleComponent rectangle;
    GoLogoLo app;
     Rectangle rectangleShapeToAdd;
     
    public AddRectangle_Transaction(LogoData initData, LogoPrototype initNewComponent, RectangleComponent rect,GoLogoLo logoApp) {
        data = initData;
        rectangleToAdd = initNewComponent;
        rectangle=rect;
        app=logoApp;
    }
    
    @Override
    public void doTransaction() {
        //add to table
        data.addRectangle(rectangleToAdd);   
        //add to from pane
      
        
        //Pane editPane=(Pane) app.getGUIModule().getGUINode(LOGO_EDIT_PANE);
        //  rectangleShapeToAdd=rectangle.getRectangle();   
        
        //  editPane.getChildren().add(rectangleShapeToAdd);
       
        //deselct previous components and select new
        data.clearSelected();
        data.selectItem(rectangleToAdd);
    }

    @Override
    public void undoTransaction() {
       //remove from table and reselect previous
       
       data.removeRectangle(rectangleToAdd);
        
        ArrayList<LogoPrototype> componentList= data.getCurrentItemsOrder();
        data.clearSelected();
        if(componentList.size()>0)
        {
            LogoPrototype firstComponent=componentList.get(componentList.size()-1);
            data.selectItem(firstComponent);
            
        }
        
        
        //remove from pane
        
      //  Pane editPane=(Pane) app.getGUIModule().getGUINode(LOGO_EDIT_PANE);
       //  rectangleShapeToAdd=rectangle.getRectangle();  
         //editPane.getChildren().remove(rectangleShapeToAdd);
    }
}
