/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import com.sun.prism.paint.Gradient;
import java.util.List;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.paint.CycleMethod;
import static javafx.scene.paint.CycleMethod.NO_CYCLE;
import static javafx.scene.paint.CycleMethod.REFLECT;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jasoncao
 */
public class LogoRectangle extends Rectangle implements Cloneable
{
  
    double width;
    double height;
    String color;
    String borderColor;
    double xCoordinate;
    double yCoordinate;    
    String name;
    String type;
    int order;
    Stop stop1;
    Stop stop2;
   
   RadialGradient gradient;
   double focusAngle;
   double focusDistance;
   double centerX;
   double centerY;
   double radius;
   boolean proportional;
   CycleMethod cycleMethod;
   
    public LogoRectangle(){
       
        this.setWidth(300);
        this.setHeight(150);

        this.setStroke(BLACK);
        this.setY(300);
        this.setX(250);
        focusAngle=0;
        focusDistance= 0;
        centerX=0;
        centerY=0;
        radius=0;
        proportional=true;
        
        cycleMethod=NO_CYCLE;
 
        borderColor="BLACK";
        color="WHITE";
        name="DEFAULT";
        type="Rectangle";
        order=0;
        stop1=new Stop(0,Color.WHITE);
        stop2=new Stop(1,Color.WHITE);
        gradient=new RadialGradient(focusAngle,focusDistance,centerX,centerY,radius,proportional,cycleMethod,stop1,stop2);
      
    
        this.setFill(gradient);    


    }
    
       public LogoRectangle(double width,double height,Paint fill,Paint stroke,double x,double y){
        
      this.setWidth(width);
      this.setHeight(height);
      this.setFill(fill);
      this.setStroke(stroke);
      this.setY(y);
      this.setX(x);
        
        borderColor=this.getStroke().toString();
        color=this.getFill().toString();
        name="DEFAULT";
        type="Rectangle";
        order=0;
       
    }
      
          
    public Double getFocusAngle() {
        return focusAngle;
    }
     public Double getFocusDistance() {
        return focusDistance;
    } 
      public Color getStop1Color() {
        return stop2.getColor();
      }
      
       public Color getStop0Color() {
        return stop1.getColor();
      }
    
    public Stop getStop2() {
        return stop2;
    }

    public void setStop2(Stop stop2) {
        this.stop2 = stop2;
    }
       
     public Double getCenterX() {
        return centerX;
    }
       public Double getCenterY() {
        return centerY;
    }
       public Double getRadius() {
        return radius;
    }
         public boolean getProportion() {
        return proportional;
    }
           public CycleMethod getCycleMethod() {
        return cycleMethod;
    }
           public Stop getStop1(){
               return stop1;
           }

           
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public void setStop1(Stop stop) {
        stop1 = stop;
    }

    public void setGradient(RadialGradient gradient) {
        this.gradient = gradient;
    }


    public void setFocusAngle(double focusAngle) {
        this.focusAngle = focusAngle;
    }

    public void setFocusDistance(double focusDistance) {
        this.focusDistance = focusDistance;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setProportional(boolean proportional) {
        this.proportional = proportional;
    }

    public void setCycleMethod(CycleMethod cycleMethod) {
        this.cycleMethod = cycleMethod;
    }
           
     
     
     
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getOrder() {
        return order;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
    
 

    public void setRectangleWidth(double width) {
        this.setWidth(width);
    }

    public void setRectangleHeight(double height) {
      this.setHeight(height);
    }

//    public void setColor(String color) {
//       rectangle.setFill(color);
//    }

//    public void setBorderColor(String borderColor) {
//        this.borderColor = borderColor;
//    }

    public void setXCoordinate(double xCoordinate) {
        this.setX(xCoordinate);
    }

    public void setYCoordinate(double yCoordinate) {
        this.setY(yCoordinate);
    }

  

    public Rectangle getRectangle() {
        return this;
    }
    
    
    public double getRectangleWidth() {
        return this.getWidth();
    }

    public double getRectangleHeight() {
        return this.getHeight();
    }

    public double getxCoordinate() {
        return this.getX();
    }

    public double getyCoordinate() {
        return this.getY();
    }

    public Paint getColor() {
        Paint fill=this.getFill();
                return fill;
    }

    public Paint getBorderColor() {
       Paint border=this.getStroke();
                return border;
    }
    
    
    public Object clone() {
       
             return new LogoRectangle(   this.getWidth()
                                        ,this.getHeight()
                                        ,this.getFill()
                                        ,this.getStroke()
                                         ,this.getX()
                                         ,this.getY()); 
                                      
     }
}
