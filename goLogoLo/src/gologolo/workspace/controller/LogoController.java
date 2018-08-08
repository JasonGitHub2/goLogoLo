/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controller;

import gologolo.GoLogoLo;
import gologolo.components.RectangleComponent;
import gologolo.components.TextComponent;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.transactions.AddRectangle_Transaction;
import gologolo.transactions.AddText_Transaction;
import gologolo.transactions.DeleteComponent_Transaction;
import gologolo.transactions.EditText_Transaction;
import gologolo.workspace.dialog.AddTextDialog;
import gologolo.workspace.dialog.EditTextDialog;
import gologolo.workspace.dialog.LogoDialog;

/**
 *
 * @author jasoncao
 */
public class LogoController {
    GoLogoLo app;
    LogoDialog itemDialog;
    EditTextDialog editDialog;
    AddTextDialog addTextDialog;
    public LogoController(GoLogoLo initApp) {
        app = initApp;
        
       itemDialog = new LogoDialog(app);
       addTextDialog=new AddTextDialog(app);
       editDialog=new EditTextDialog(app);
    }
    
    
    public void processAddRectangle(){
        itemDialog.showAddRectangleDialog();
        LogoPrototype newItem = itemDialog.getNewItem();  
        
        
        if (newItem != null) {
            //IF NEWITEM IS VALID
            String name=newItem.getName();
            RectangleComponent rectangle=new RectangleComponent(name);
          
            LogoData data = (LogoData)app.getDataComponent();
            AddRectangle_Transaction transaction = new AddRectangle_Transaction(data, newItem,rectangle,app);
            app.processTransaction(transaction);
           
        }    
        // OTHERWISE TELL THE USER WHAT THEY
        // HAVE DONE WRONG
        else {
            djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "Invalid Name", "Invalid data for a new component");
        }
    }
    
    public void processAddText(){
        addTextDialog.showAddTextDialog();
        LogoPrototype newItem = addTextDialog.getNewItem();  
        
        //GOES TO ELSE
        if (newItem != null) {
            //IF NEWITEM IS VALID
         //  String name=newItem.getName();
         //  String text=newItem.getText();
           //TextComponent textComponent=new TextComponent(name,text);
          
            LogoData data = (LogoData)app.getDataComponent();
            AddText_Transaction transaction = new AddText_Transaction(data, newItem,app);
            app.processTransaction(transaction);
           
        }    
        // OTHERWISE TELL THE USER WHAT THEY
        // HAVE DONE WRONG
        else {
            djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "Invalid Name", "Invalid data for a new component");
        }
    }
    
    
    public void processEditText(){
         LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) {
            LogoPrototype textToEdit = data.getSelectedItem();          
            editDialog.showEditDialog(textToEdit);
             
            LogoPrototype changedText = editDialog.getChangedText();
           
            if (changedText != null) {
                
                
            EditText_Transaction transaction = new EditText_Transaction(textToEdit,changedText.getText(),app,data );
            app.processTransaction(transaction);
            }     
        }
    }
    
    
    public void processDeleteComponent(){
        LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) { 
            LogoPrototype dataToDelete=data.getSelectedItem();
            DeleteComponent_Transaction transaction = new DeleteComponent_Transaction(app,dataToDelete,data);
            app.processTransaction(transaction);
        }
        
    }

    public void processSelectNode() {
       LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) { 
            LogoPrototype temp=data.getSelectedItem();
            data.selectNodeInPane(temp);
        }
    }
}
