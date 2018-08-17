/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.paint.CycleMethod;
import static javafx.scene.paint.CycleMethod.NO_CYCLE;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

/**
 *
 * @author jasoncao
 */
public class LogoCircle extends Circle implements Cloneable {

    double radius;
   
    String color;
    double xCenterCoordinate;
    Color borderColor;
    double yCenterCoordinate;    
    String name;
    String type;
    int order;
   
     Stop stop0;
    Stop stop1;
   
   RadialGradient gradient;
   double focusAngle;
   double focusDistance;
   double gradientCenterX;
   double gradientCenterY;
   double gradientRadius;
   boolean proportional;
   CycleMethod cycleMethod;
   double borderThickness;
   
    public LogoCircle(){
       
        this.setRadius(80);
        this.setFill(WHITE);
        this.setStroke(BLACK);
        this.setCenterX(250);
        this.setCenterY(350);
        this.setStrokeWidth(1);
        borderThickness=1;
        borderColor=BLACK;
        color="WHITE";
        name="DEFAULT";
        type="Circle";
        radius=20;
        order=0;
        xCenterCoordinate=250;
        yCenterCoordinate=350;
        
        
        focusAngle=0;
        focusDistance= 0;
        gradientCenterX=0;
        gradientCenterY=0;
        gradientRadius=0;
        proportional=true;
        cycleMethod=NO_CYCLE;
        stop0=new Stop(0,Color.WHITE);
        stop1=new Stop(1,Color.WHITE);
        gradient=new RadialGradient(focusAngle,focusDistance,gradientCenterX,gradientCenterY,gradientRadius,proportional,cycleMethod,stop0,stop1);
        this.setFill(gradient);   
        
       
    }
       public LogoCircle(double radiusCircle,Paint fill,Paint stroke,double x,double y){
        
        this.setRadius(radiusCircle);
        this.setFill(fill);
        this.setStroke(stroke);
        this.setCenterX(x);
        this.setCenterY(y);
        
        color=this.getFill().toString();
        
        radius=radiusCircle;
        xCenterCoordinate=x;
        xCenterCoordinate=y;
       
        type="Circle";
        name="DEFAULT";
        type="Rectangle";
        order=0;
       
    }

    public double getCircleBorderThickness() {
        return borderThickness;
    }

    public void setCircleBorderThickness(double borderThickness) {
        this.borderThickness = borderThickness;
    }

    public Stop getStop1() {
        return stop1;
    }

    public void setStop0(Stop stop1) {
        this.stop1 = stop1;
    }

    public Stop getStop0() {
        return stop0;
    }

    public void setStop1(Stop stop2) {
        this.stop0 = stop2;
    }

    public RadialGradient getGradient() {
        return gradient;
    }

    public void setGradient(RadialGradient gradient) {
        this.gradient = gradient;
    }

    public double getFocusAngle() {
        return focusAngle;
    }

    public void setFocusAngle(double focusAngle) {
        this.focusAngle = focusAngle;
    }

    public double getFocusDistance() {
        return focusDistance;
    }

    public void setFocusDistance(double focusDistance) {
        this.focusDistance = focusDistance;
    }

    public double getGradientCenterX() {
        return gradientCenterX;
    }

    public void setGradientCenterX(double gradientCenterX) {
        this.gradientCenterX = gradientCenterX;
    }

    public double getGradientCenterY() {
        return gradientCenterY;
    }

    public void setGradientCenterY(double gradientCenterY) {
        this.gradientCenterY = gradientCenterY;
    }

    public double getGradientRadius() {
        return gradientRadius;
    }

    public void setGradientRadius(double gradientRadius) {
        this.gradientRadius = gradientRadius;
    }

    public boolean getProportion() {
        return proportional;
    }

    public void setProportion(boolean proportional) {
        this.proportional = proportional;
    }

    public CycleMethod getCycleMethod() {
        return cycleMethod;
    }

    public void setCycleMethod(CycleMethod cycleMethod) {
        this.cycleMethod = cycleMethod;
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

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor=borderColor;
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
