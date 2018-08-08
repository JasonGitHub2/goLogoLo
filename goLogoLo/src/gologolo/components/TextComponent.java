/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.components;

import javafx.scene.control.TextField;

/**
 *
 * @author jasoncao
 */
public class TextComponent {
    TextField textField;
    String componentType;
    String textFieldName;
    //user input text
    String text;
    public TextComponent(String name,String inputText){
       textField= new TextField();
       componentType="Text";
       textFieldName=name;
       text=inputText;
       textField.replaceText(0, 0, text);
       textField.setLayoutX(200);
       textField.setLayoutY(200);
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public void setTextFieldName(String textFieldName) {
        this.textFieldName = textFieldName;
    }

    public TextField getTextField() {
        return textField;
    }

    public String getComponentType() {
        return componentType;
    }

    public String getTextFieldName() {
        return textFieldName;
    }

}
