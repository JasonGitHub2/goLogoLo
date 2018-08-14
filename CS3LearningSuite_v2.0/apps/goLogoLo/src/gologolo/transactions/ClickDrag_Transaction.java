/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import javafx.scene.Node;
import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class ClickDrag_Transaction implements jTPS_Transaction{
    double initialX,initialY,finalX,finalY;
    Node nodeToMove;
    
    public ClickDrag_Transaction(double initX,double initY, double lastX, double lastY,Text node){
        initialX=initX;
        initialY=initY;
        finalX=lastX;
        finalY=lastY;
        nodeToMove=node;
    }
    
    public ClickDrag_Transaction(double initX,double initY, double lastX, double lastY,Node node){
        initialX=initX;
        initialY=initY;
        finalX=lastX;
        finalY=lastY;
        nodeToMove=node;
    }
    
    @Override
    public void doTransaction() {
        
        nodeToMove.setTranslateX(finalX);
        nodeToMove.setTranslateY(finalY);
    }

    @Override
    public void undoTransaction() {
         nodeToMove.setTranslateX(initialX);
         nodeToMove.setTranslateY(initialY);
    }
    
}
