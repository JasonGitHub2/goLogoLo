/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLo;
import gologolo.data.LogoCircle;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class ResizeCircle_Transaction implements  jTPS_Transaction{
GoLogoLo logoApp;
LogoCircle circle;
double oldRadius;
double newRadius;
    public ResizeCircle_Transaction(LogoCircle circle,GoLogoLo app,double newRadius){
        logoApp=app;
        this.circle=circle;
        oldRadius=circle.getCircleRadius();
        this.newRadius=newRadius;
    }
    @Override
    public void doTransaction() {
     circle.setRadius(newRadius);
     circle.setCircleRadius(newRadius);
    }

    @Override
    public void undoTransaction() {
      circle.setRadius(oldRadius);
     circle.setCircleRadius(oldRadius);
    }
    
}
