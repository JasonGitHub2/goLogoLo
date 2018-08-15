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
import java.util.ArrayList;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class AddCircle_Transaction implements jTPS_Transaction {
    LogoData data;
    LogoPrototype circleData;

    GoLogoLo app;
    LogoCircle circleShape;
   
     
    public AddCircle_Transaction(LogoData initData,GoLogoLo logoApp) {
        data = initData;
        circleShape=new LogoCircle();
       
        app=logoApp;
        circleData=new LogoPrototype();
        circleData.setType("Circle");
    }
    
    @Override
    public void doTransaction() {
        //add to table and pane
        
        data.addCircleDataAndNode(circleData,circleShape); 
        
       
   
    }

    @Override
    public void undoTransaction() {
     //  remove from table and pane and reselect previous
       
       data.removeCircleDataAndNode(circleData,circleShape);
      
       
       //reslect previous 
        ArrayList<LogoPrototype> componentList= data.getCurrentItemsOrder(); 
        if(componentList.size()>0)
        {           
            data.selectItem(componentList.size()-1);
            data.selectNodeInPane(componentList.size()-1);
            
        }
 
    }
}

