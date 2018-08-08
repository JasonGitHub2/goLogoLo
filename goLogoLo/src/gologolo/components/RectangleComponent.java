/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.components;


import static gologolo.workspace.style.LogoStyle.CLASS_LOGO_SHAPE;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jasoncao
 */
public class RectangleComponent {
    Rectangle rectangle;
    String componentType;
    String rectangleName;
    public RectangleComponent(String name){
        rectangle= new Rectangle();
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        rectangle.setY(300);
         rectangle.setX(250);
      //   rectangle.getStyleClass().add(CLASS_LOGO_SHAPE);
        
        componentType="Rectangle";
        rectangleName=name;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public String getComponentType() {
        return componentType;
    }
    public String getRectangleName() {
        return rectangleName;
    }
   
    public void setRectangleName(String name) {
        rectangleName=name;
    }
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }
    
}
