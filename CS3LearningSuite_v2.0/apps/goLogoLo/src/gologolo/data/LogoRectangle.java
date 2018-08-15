/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jasoncao
 */
public class LogoRectangle extends Rectangle implements Cloneable
{
    Rectangle rectangle;
    double width;
    double height;
    String color;
    String borderColor;
    double xCoordinate;
    double yCoordinate;    
    String name;
    String type;
    int order;
   
    public LogoRectangle(){
        rectangle= new Rectangle();
        rectangle.setWidth(300);
        rectangle.setHeight(150);
        rectangle.setFill(WHITE);
        rectangle.setStroke(BLACK);
        rectangle.setY(300);
        rectangle.setX(250);
        borderColor="BLACK";
        color="WHITE";
        name="DEFAULT";
        type="Rectangle";
        order=0;
       
    }
       public LogoRectangle(double width,double height,Paint fill,Paint stroke,double x,double y){
        rectangle= new Rectangle();
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(fill);
        rectangle.setStroke(stroke);
        rectangle.setY(y);
        rectangle.setX(x);
        borderColor=rectangle.getStroke().toString();
        color=rectangle.getFill().toString();
        name="DEFAULT";
        type="Rectangle";
        order=0;
       
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
    
    
    
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setRectangleWidth(double width) {
        rectangle.setWidth(width);
    }

    public void setRectangleHeight(double height) {
        rectangle.setHeight(height);
    }

//    public void setColor(String color) {
//       rectangle.setFill(color);
//    }

//    public void setBorderColor(String borderColor) {
//        this.borderColor = borderColor;
//    }

    public void setXCoordinate(double xCoordinate) {
        rectangle.setX(xCoordinate);
    }

    public void setYCoordinate(double yCoordinate) {
        rectangle.setY(yCoordinate);
    }

  

    public Rectangle getRectangle() {
        return rectangle;
    }
    
    
    public double getRectangleWidth() {
        return rectangle.getWidth();
    }

    public double getRectangleHeight() {
        return rectangle.getHeight();
    }

    public double getxCoordinate() {
        return rectangle.getX();
    }

    public double getyCoordinate() {
        return rectangle.getY();
    }

    public Paint getColor() {
        Paint fill=rectangle.getFill();
                return fill;
    }

    public Paint getBorderColor() {
       Paint border=rectangle.getStroke();
                return border;
    }
    
    
    public Object clone() {
       
             return new LogoRectangle(   rectangle.getWidth()
                                        ,rectangle.getHeight()
                                        ,rectangle.getFill()
                                        ,rectangle.getStroke()
                                         ,rectangle.getX()
                                         ,rectangle.getY()); 
                                      
     }
}
