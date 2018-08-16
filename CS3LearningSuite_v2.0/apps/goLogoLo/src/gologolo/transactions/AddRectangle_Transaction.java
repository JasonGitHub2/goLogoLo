/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import djf.ui.AppNodesBuilder;
import gologolo.GoLogoLo;

import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.data.LogoRectangle;
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
    LogoPrototype rectangleData;
    
    GoLogoLo app;
    LogoRectangle rectangleShape;
   
     
    public AddRectangle_Transaction(LogoData initData,GoLogoLo logoApp) {
        data = initData;
        rectangleShape=new LogoRectangle();
       
        app=logoApp;
        rectangleData=new LogoPrototype();
        rectangleData.setType("Rectangle");
    }
    
    @Override
    public void doTransaction() {
        //add to table and pane
        
        data.addRectangleDataAndNode(rectangleData,rectangleShape); 
        
       
   
    }

    @Override
    public void undoTransaction() {
     //  remove from table and pane and reselect previous
       
       data.removeRectangleDataAndNode(rectangleData,rectangleShape);
      
       
       //reslect previous 
        ArrayList<LogoPrototype> componentList= data.getCurrentItemsOrder(); 
        if(componentList.size()>0)
        {           
            data.selectItem(componentList.size()-1);
            data.selectNodeInPane(componentList.size()-1);
            
        }
        
        
      
    }
}
