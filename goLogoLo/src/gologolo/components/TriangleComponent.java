/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.components;

import com.sun.javafx.sg.prism.NGShape;
import javafx.scene.shape.Polygon;

/**
 *
 * @author jasoncao
 */
public class TriangleComponent extends Polygon {
    
    Polygon triangle;
    String componentType;
    
    public TriangleComponent(){
        triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
        0.0, 0.0,
        20.0, 20.0,
        10.0, 10.0}
         );    
        componentType="Triangle";
    }

    public Polygon getTriangle() {
        return triangle;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setTriangle(Polygon triangle) {
        this.triangle = triangle;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

 
}
