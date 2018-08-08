/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.components;

import javafx.scene.shape.Circle;

/**
 *
 * @author jasoncao
 */
public class CircleComponent {
    Circle circle;
    String componentType;
    public CircleComponent(){
        circle= new Circle();
        componentType="Circle";
    }

    public Circle getCircle() {
        return circle;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }
    
}
