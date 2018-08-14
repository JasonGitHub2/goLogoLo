/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jasoncao
 */
public class LogoPrototype implements Cloneable{
       IntegerProperty order;  
      final StringProperty name;
      final StringProperty type;
      String text;
     
      public static final String DEFAULT_NAME = "DEFAULT";
      public static final String DEFAULT_TYPE = "DEFAULT";
      public static final int DEFAULT_ORDER = -1;
  
      
      
    //have to add in order still
    public LogoPrototype() {
        name = new SimpleStringProperty(DEFAULT_NAME);
        type = new SimpleStringProperty(DEFAULT_TYPE);
        order = new SimpleIntegerProperty(DEFAULT_ORDER);
        text="";
       
  
    }
  public LogoPrototype(String initName,String nodeType){
        this();
        name.set(initName);
        type.set(nodeType);
        text="";
        order.set(0);
      
        
    }
     public LogoPrototype(String initName,String nodeType,String initText){
        this();
        name.set(initName);
        type.set(nodeType);
        text=initText;
        order.set(0);
      
    }

    public IntegerProperty orderProperty() {
        return order;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty typeProperty() {
        return type;
    }
    
  
    public String getName(){
        return name.get();
    }
     public String getType(){
        return type.get();
    }
      public int getOrder(){
        return order.get();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    
    public void setOrder(int orderNum) {
       order.set(orderNum);
    }
    
     public void setName(String logoName) {
       name.set(logoName);
    }
     
      public void setType(String logoType) {
       type.set(logoType);
    }
    
     public Object clone() {
       if(text==null){
           return new LogoPrototype(name.getValue(),type.getValue());
       }
       else{
             return new LogoPrototype(   name.getValue(), 
                                        type.getValue(), 
                                        this.getText()
             );
       }
     }
}
