/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author jasoncao
 */
public class LogoText extends Text implements Cloneable{

    String textToPut;
    double xCoordinate;
    double yCoordinate; 
    String name;
    String type;
    int order;
    Font font;
    Color fontColor;
    String fontName;
    int fontSize;
    boolean isBold;
    boolean isUnderline;
    boolean isItalicized;
    
    public LogoText(String stringInput){
       
        textToPut=stringInput;
        font=new Font("Times New Roman",14);
        fontName="Times New Roman";
        fontSize=14;
      
        xCoordinate=200;
        yCoordinate=200;
        name="DEFAULT";
        type="Text";
        fontColor=BLACK;
        order=0;
        this.setFont(font);
        this.setText(stringInput);
        this.setX(200);
        this.setY(200);
        isBold=false;
        isItalicized=false;
        isUnderline=false;

    }
    
    
    //add in font, font name and font size parameter
       public LogoText(double x,double y,String input){
     
        textToPut=input;
     
        xCoordinate=x;
        yCoordinate=y;
       name="DEFAULT";
        type="Text";
        order=0;
       
    }

    public LogoText(String text, Font font, String fontName, int fontSize, double x, double y, Color fontColor, boolean isBold, boolean isItalicized) {
        
        textToPut=text;
        this.font=font;
        this.fontName=fontName;
        this.fontSize=fontSize;
        this.setX(x);
        this.setY(y);
       
        name="DEFAULT";
        type="Text";
        this.fontColor=fontColor;
        order=0;
        this.setFont(font);
        this.setText(textToPut);
        this.setX(x);
        this.setY(y);
        this.isBold=isBold;
        this.isItalicized=isItalicized;
        isUnderline=false;

    }
 
    public String getName() {
        return name;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        font=new Font(fontName,fontSize);
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
    
  

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }


    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
        font=new Font(fontName,fontSize);
    }
 



    public boolean getIsBold() {
        return isBold;
    }

    public void setIsBold(boolean isBold) {
        this.isBold = isBold;
    }

    public boolean getIsItalicized() {
        return isItalicized;
    }

    public boolean getIsUnderline() {
        return isUnderline;
    }

    public void setIsUnderline(boolean isUnderline) {
        this.isUnderline = isUnderline;
    }

    public void setIsItalicized(boolean isItalicized) {
        this.isItalicized = isItalicized;
    }
 

     public Object clone() {
         return new LogoText( this.getText(),this.getFont(),getFontName(),getFontSize(),this.getX(),this.getY(),getFontColor(),getIsBold(),getIsItalicized() );
     }
}