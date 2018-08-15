/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 *
 * @author jasoncao
 */
public class LogoCircle extends Circle implements Cloneable {

    double radius;
   
    String color;
    String borderColor;
    double xCenterCoordinate;
    double yCenterCoordinate;    
    String name;
    String type;
    int order;
   
    public LogoCircle(){
       
        this.setRadius(20);
        this.setFill(WHITE);
        this.setStroke(BLACK);
        this.setCenterX(250);
        this.setCenterY(350);
        borderColor="BLACK";
        color="WHITE";
        name="DEFAULT";
        type="Circle";
        radius=20;
        order=0;
        xCenterCoordinate=250;
        yCenterCoordinate=350;
       
    }
       public LogoCircle(double radiusCircle,Paint fill,Paint stroke,double x,double y){
        
        this.setRadius(radiusCircle);
        this.setFill(fill);
        this.setStroke(stroke);
        this.setCenterX(x);
        this.setCenterY(y);
        borderColor=this.getStroke().toString();
        color=this.getFill().toString();
        
        radius=radiusCircle;
        xCenterCoordinate=x;
        xCenterCoordinate=y;
       
        type="Circle";
        name="DEFAULT";
        type="Rectangle";
        order=0;
       
    }

    public double getCircleRadius() {
        return this.getRadius();
    }

    public void setCircleRadius(double radius) {
        this.setRadius(radius);
    }

    public String getColor() {
        return this.getFill().toString();
    }

    public void setColor(Paint color) {
        this.setFill(color);
    }

    public String getBorderColor() {
        return this.getStroke().toString();
    }

    public void setBorderColor(Paint borderColor) {
        this.setStroke(borderColor);
    }

    public double getxCenterCoordinate() {
        return this.getCenterX();
    }

    public void setxCenterCoordinate(double xCenterCoordinate) {
        this.setCenterX(xCenterCoordinate);
    }

    public double getyCenterCoordinate() {
        return this.getCenterY();
    }

    public void setyCenterCoordinate(double yCenterCoordinate) {
        this.setCenterY(yCenterCoordinate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
       
       
}
