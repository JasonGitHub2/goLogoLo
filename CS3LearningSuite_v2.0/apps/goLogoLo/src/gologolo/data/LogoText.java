/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author jasoncao
 */
public class LogoText extends Text implements Cloneable{
    Text text;
    String textToPut;
    double xCoordinate;
    double yCoordinate; 
    String name;
    String type;
    int order;
    Font font;
    String fontName;
    int fontSize;
    boolean isBold;
    boolean isUnderline;
    boolean isItalicized;
    
    public LogoText(String stringInput){
        text=new Text();
        textToPut=stringInput;
        font=new Font("Times New Roman",14);
        fontName="Times New Roman";
        fontSize=14;
        text.setText(stringInput);
        text.setX(200);
        text.setFont(font);
        text.setY(200);
        xCoordinate=200;
        yCoordinate=200;
        name="DEFAULT";
        type="Text";
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
        text=new Text();
        textToPut=input;
        text.setText(input);
        text.setX(x);
        text.setY(y);
        xCoordinate=x;
        yCoordinate=y;
       
        type="Text";
        order=0;
       
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
    
    
    public Text getTextNode() {
        return text;
    }

    public void setTextNode(Text text) {
        
        this.text = text;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
        font=new Font(fontName,fontSize);
    }
 
   
    public String getTextInput() {
        return text.getText();
    }

    public void setTextInput(String textToPut) {
        this.textToPut = textToPut;
        text.setText(textToPut);
        this.setText(textToPut);
    }


    public Font getTextFont() {
        return text.getFont();
    }

    public void setTextFont(Font font) {
        text.setFont(font);
        this.setFont(font);
    }

    public double getxCoordinate() {
        return text.getX();
    }

    public void setxCoordinate(double xCoordinateInput) {
        this.xCoordinate = xCoordinateInput;
        text.setX(xCoordinateInput);
        this.setxCoordinate(xCoordinateInput);
    }

    public double getyCoordinate() {
        return text.getY();
    }

    public void setyCoordinate(double yCoordinateInput) {
        this.yCoordinate = yCoordinateInput;
        text.setY(yCoordinateInput);
        this.setY(yCoordinateInput);
    }
     public Object clone() {
       
             return new LogoText(   text.getX(),text.getY(),this.getTextInput());
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
    
}
