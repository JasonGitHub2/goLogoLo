/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controller;

import djf.AppPropertyType;
import gologolo.GoLogoLo;

import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import gologolo.transactions.AddCircle_Transaction;
import gologolo.transactions.AddImage_Transaction;
import gologolo.transactions.AddRectangle_Transaction;
import gologolo.transactions.AddText_Transaction;
import gologolo.transactions.BoldText_Transaction;
import gologolo.transactions.BorderColor_Transaction;
import gologolo.transactions.BorderRadius_Transaction;
import gologolo.transactions.BorderThickness_Transaction;
import gologolo.transactions.CenterX_Transaction;
import gologolo.transactions.CenterY_Transaction;
import gologolo.transactions.ChangeFontSize_Transaction;
import gologolo.transactions.ChangeFont_Transaction;
import gologolo.transactions.CycleMethod_Transaction;
import gologolo.transactions.DecreaseText_Transaction;
import gologolo.transactions.DeleteComponent_Transaction;
import gologolo.transactions.EditText_Transaction;
import gologolo.transactions.FocusAngle_Transaction;
import gologolo.transactions.FocusDistance_Transaction;
import gologolo.transactions.FontColor_Transaction;
import gologolo.transactions.IncreaseText_Transaction;
import gologolo.transactions.ItalicizeText_Transaction;
import gologolo.transactions.MoveDown_Transaction;
import gologolo.transactions.MoveUp_Transaction;
import gologolo.transactions.OneColor_Transaction;
import gologolo.transactions.Radius_Transaction;
import gologolo.transactions.RenameComponent_Transaction;
import gologolo.transactions.UnderlineText_Transaction;
import gologolo.transactions.ZeroColor_Transaction;
import gologolo.workspace.dialog.AddTextDialog;
import gologolo.workspace.dialog.EditTextDialog;
import gologolo.workspace.dialog.RenameDialog;
import gologolo.workspace.dialog.ResizePaneDialog;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 *
 * @author jasoncao
 */
public class LogoController {
    GoLogoLo app;
    ResizePaneDialog resizeDialog;
    RenameDialog itemDialog;
    EditTextDialog editDialog;
    AddTextDialog addTextDialog;
    public LogoController(GoLogoLo initApp) {
        app = initApp;
        
       itemDialog = new RenameDialog(app);
       resizeDialog = new ResizePaneDialog(app);
       addTextDialog=new AddTextDialog(app);
       editDialog=new EditTextDialog(app);
  
    }
    
     public void processResizePane(){
         resizeDialog.showResizeDialog();
         double height=resizeDialog.getResizeHeight();
         double width=resizeDialog.getResizeWidth();
         Pane editPane=(Pane) app.getGUIModule().getGUINode(LOGO_EDIT_PANE);
         
         editPane.setMaxSize((width),height);
        
     }
    
    
    
     public void processBorderColor(LogoPrototype item, Color color){
         LogoData data = (LogoData)app.getDataComponent();
          LogoPrototype selected=item;
            BorderColor_Transaction transaction = new BorderColor_Transaction(data, selected,app,color);
                         app.processTransaction(transaction);
                          app.getFoolproofModule().updateAll();
               
     }
     
     
      public void processChangeFontColor(Color color){
          Color newColor=color;
          LogoData data = (LogoData)app.getDataComponent();
          LogoPrototype selected=data.getSelectedItem();
          if(selected.getType().equalsIgnoreCase("Text"))
          {
              FontColor_Transaction transaction = new FontColor_Transaction(data, selected,app,newColor);
              app.processTransaction(transaction);
               app.getFoolproofModule().updateAll();
          }
      }
      
      
      
     
    public void processBorderRadius(double val){
        LogoData data = (LogoData)app.getDataComponent();
          LogoPrototype selected=data.getSelectedItem();
          if(selected.getType().equalsIgnoreCase("Rectangle")){
              BorderRadius_Transaction transaction = new BorderRadius_Transaction(data, selected,app,val);
                         app.processTransaction(transaction);
                          app.getFoolproofModule().updateAll();
          }
          
    }
    
    
    
    public void processFocusAngle(Double value){
          LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                  if(selectedItem.getType().equalsIgnoreCase("Rectangle")){
                
                
                 FocusAngle_Transaction transaction = new FocusAngle_Transaction(data, app,selectedItem,value,false,true);
                 app.processTransaction(transaction);
                  }
                    else  if(selectedItem.getType().equalsIgnoreCase("Circle")){
                         FocusAngle_Transaction transaction = new FocusAngle_Transaction(data, app,selectedItem,value,true,false);
                         app.processTransaction(transaction);
                          app.getFoolproofModule().updateAll();
                    }
                
     }
    }
    
    public void processBorderThickness(double val){
           LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
           {
                LogoPrototype selectedItem=data.getSelectedItem();
                 BorderThickness_Transaction transaction = new BorderThickness_Transaction(data, app,selectedItem,val);
                 app.processTransaction(transaction);
                  app.getFoolproofModule().updateAll();
                 
           }
    }
    
    
      public void processFocusDistance(Double value){
          LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                  if(selectedItem.getType().equalsIgnoreCase("Rectangle")){
  
                 FocusDistance_Transaction transaction = new FocusDistance_Transaction(data, app,selectedItem,value,false,true);
                 app.processTransaction(transaction);
                 
                  }
                  else  if(selectedItem.getType().equalsIgnoreCase("Circle")){
                         FocusDistance_Transaction transaction = new FocusDistance_Transaction(data, app,selectedItem,value,true,false);
                 app.processTransaction(transaction);
                  app.getFoolproofModule().updateAll();
                    }
                
                  
     }
    }
       public void processCenterX(Double value){
          LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                  if(selectedItem.getType().equalsIgnoreCase("Rectangle")){
  
                 CenterX_Transaction transaction = new CenterX_Transaction(data, app,selectedItem,value,false,true);
                 app.processTransaction(transaction);
                  }
                  else  if(selectedItem.getType().equalsIgnoreCase("Circle")){
                         CenterX_Transaction transaction = new CenterX_Transaction(data, app,selectedItem,value,true,false);
                 app.processTransaction(transaction);
                  app.getFoolproofModule().updateAll();
                    }
                
     }
    }
        public void processCenterY(Double value){
          LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                  if(selectedItem.getType().equalsIgnoreCase("Rectangle")){
  
                CenterY_Transaction transaction = new CenterY_Transaction(data, app,selectedItem,value,false,true);
                 app.processTransaction(transaction);
                  }
                  else  if(selectedItem.getType().equalsIgnoreCase("Circle")){
                         CenterY_Transaction transaction = new CenterY_Transaction(data, app,selectedItem,value,true,false);
                 app.processTransaction(transaction);
                  app.getFoolproofModule().updateAll();
                    }
                
     }
    }
         public void processRadius(Double value){
          LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                  if(selectedItem.getType().equalsIgnoreCase("Rectangle")){
  
                 Radius_Transaction transaction = new Radius_Transaction(data, app,selectedItem,value,false,true);
                 app.processTransaction(transaction);
                  }
                  else  if(selectedItem.getType().equalsIgnoreCase("Circle")){
                         Radius_Transaction transaction = new Radius_Transaction(data, app,selectedItem,value,true,false);
                 app.processTransaction(transaction);
                  app.getFoolproofModule().updateAll();
                    }
                
     }
    }
         
        public void processCycleMethod(String value){
          LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                  if(selectedItem.getType().equalsIgnoreCase("Rectangle")){
  
                CycleMethod_Transaction transaction = new CycleMethod_Transaction(data, app,selectedItem,value,false,true);
                 app.processTransaction(transaction);
                  }
                  else  if(selectedItem.getType().equalsIgnoreCase("Circle")){
                         CycleMethod_Transaction transaction = new CycleMethod_Transaction(data, app,selectedItem,value,true,false);
                 app.processTransaction(transaction);
                  app.getFoolproofModule().updateAll();
                    }
                
        }
      }
        
        
         public void processZeroColor(Color value){
          LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                  if(selectedItem.getType().equalsIgnoreCase("Rectangle")){
  
                ZeroColor_Transaction transaction = new ZeroColor_Transaction(data, app,selectedItem,value,false,true);
                 app.processTransaction(transaction);
                  }
                  else  if(selectedItem.getType().equalsIgnoreCase("Circle")){
                         ZeroColor_Transaction transaction = new ZeroColor_Transaction(data, app,selectedItem,value,true,false);
                 app.processTransaction(transaction);
                  app.getFoolproofModule().updateAll();
                    }
                
        }
      }
         
        
        
         
         public void processOneColor(Color value){
          LogoData data = (LogoData)app.getDataComponent();
           if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                  if(selectedItem.getType().equalsIgnoreCase("Rectangle")){
  
                OneColor_Transaction transaction = new OneColor_Transaction(data, app,selectedItem,value,false,true);
                 app.processTransaction(transaction);
                  }
                  else  if(selectedItem.getType().equalsIgnoreCase("Circle")){
                         OneColor_Transaction transaction = new OneColor_Transaction(data, app,selectedItem,value,true,false);
                 app.processTransaction(transaction);
                  app.getFoolproofModule().updateAll();
                    }
                
        }
      }
         
        
        
        
      
    public void processAddRectangle(){
    
            LogoData data = (LogoData)app.getDataComponent();
            AddRectangle_Transaction transaction = new AddRectangle_Transaction(data, app);
            app.processTransaction(transaction);
             

       
    }
    public void processAddCircle(){
    
            LogoData data = (LogoData)app.getDataComponent();
            AddCircle_Transaction transaction = new AddCircle_Transaction(data, app);
            app.processTransaction(transaction);
             app.getFoolproofModule().updateAll();

       
    }
     public void processMoveUp(){
    
            LogoData data = (LogoData)app.getDataComponent();
            if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                {
                    if(selectedItem.getOrder()!=1){
                        MoveUp_Transaction transaction = new MoveUp_Transaction(selectedItem, app);
                         app.processTransaction(transaction);
                          app.getFoolproofModule().updateAll();
                    }
                }
                
            }
       
    }
     
      public void processMoveDown(){
    
            LogoData data = (LogoData)app.getDataComponent();
            if (data.isItemSelected())  
            {
                LogoPrototype selectedItem=data.getSelectedItem();
                {
                    if(selectedItem.getOrder()!=data.getComponents().size()){
                        MoveDown_Transaction transaction = new MoveDown_Transaction(selectedItem, app);
                         app.processTransaction(transaction);
                          app.getFoolproofModule().updateAll();
                    }
                }
                
            }
       
    }
    
     public void processChangeFont(String newFont){
          LogoData data = (LogoData)app.getDataComponent();
         String font;
          if (data.isItemSelected())  {
               LogoPrototype selectedText=data.getSelectedItem();
               if(selectedText.getType().equalsIgnoreCase("Text")){
                  font=newFont;
                   ChangeFont_Transaction transaction = new ChangeFont_Transaction(font,selectedText,app);
                    app.processTransaction(transaction);
                     app.getFoolproofModule().updateAll();
               }
          
          }     
     }  
     
         public void processBoldText()
         {
             LogoData data = (LogoData)app.getDataComponent();  
             if (data.isItemSelected())  {
               LogoPrototype selectedText=data.getSelectedItem();
               if(selectedText.getType().equalsIgnoreCase("Text")){
                   BoldText_Transaction transaction = new BoldText_Transaction(selectedText,app);
                    app.processTransaction(transaction);
                     app.getFoolproofModule().updateAll();
                }
           }
         }
         
          public void processItalicizeText()
         {
             LogoData data = (LogoData)app.getDataComponent();  
             if (data.isItemSelected())  {
               LogoPrototype selectedText=data.getSelectedItem();
               if(selectedText.getType().equalsIgnoreCase("Text")){
                   ItalicizeText_Transaction transaction = new ItalicizeText_Transaction(selectedText,app);
                    app.processTransaction(transaction);
                     app.getFoolproofModule().updateAll();
                }
           }
         }
         
         
        public void processChangeFontSize(String newSize){
             LogoData data = (LogoData)app.getDataComponent();
             int newFontSize;
              if (data.isItemSelected())  {
               LogoPrototype selectedText=data.getSelectedItem();
               if(selectedText.getType().equalsIgnoreCase("Text")){
                   int newIntSize=Integer.parseInt(newSize);
                    newFontSize=newIntSize;
                    ChangeFontSize_Transaction transaction = new ChangeFontSize_Transaction(newFontSize,selectedText,app);
                    app.processTransaction(transaction);
                     app.getFoolproofModule().updateAll();
               }
        }
    }
    public void processAddText(){
        addTextDialog.showAddTextDialog();
        LogoPrototype newItem = addTextDialog.getNewItem();  
        
        //GOES TO ELSE
        if (newItem != null) {
        
            LogoData data = (LogoData)app.getDataComponent();
            AddText_Transaction transaction = new AddText_Transaction(data, newItem,app);
            app.processTransaction(transaction);
             app.getFoolproofModule().updateAll();
           
        }    
        // OTHERWISE TELL THE USER WHAT THEY
        // HAVE DONE WRONG
        else {
            djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "Invalid Name", "Invalid data for a new component");
        }
    }
     public void processUnderlineText(){
             LogoData data = (LogoData)app.getDataComponent();  
             if (data.isItemSelected())  {
               LogoPrototype selectedText=data.getSelectedItem();
               if(selectedText.getType().equalsIgnoreCase("Text")){
                   UnderlineText_Transaction transaction = new UnderlineText_Transaction(selectedText,app);
                    app.processTransaction(transaction);
                }
           }
     }
    
      public void processDecreaseText(){
          LogoData data = (LogoData)app.getDataComponent();  
             if (data.isItemSelected())  {
               LogoPrototype selectedText=data.getSelectedItem();
               if(selectedText.getType().equalsIgnoreCase("Text")){
                   DecreaseText_Transaction transaction = new DecreaseText_Transaction(selectedText,app);
                    app.processTransaction(transaction);
                     app.getFoolproofModule().updateAll();
                }
           }
      }
     public void processIncreaseText(){
             LogoData data = (LogoData)app.getDataComponent();  
             if (data.isItemSelected())  {
               LogoPrototype selectedText=data.getSelectedItem();
               if(selectedText.getType().equalsIgnoreCase("Text")){
                   IncreaseText_Transaction transaction = new IncreaseText_Transaction(selectedText,app);
                    app.processTransaction(transaction);
                     app.getFoolproofModule().updateAll();
                     
                }
           }
     }
    
     
    public void processRenameComponent(){
        LogoData data = (LogoData)app.getDataComponent();
         if (data.isItemSelected() ) {
             
             LogoPrototype componentToRename = data.getSelectedItem();  
             itemDialog.showRenameDialog(componentToRename);
             String name=itemDialog.getNewName();
              if (name.equals("")||name.equalsIgnoreCase(componentToRename.getName())) {
                    djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "Please change to valid name", "Change to valid name");
              }
              else{
               RenameComponent_Transaction transaction = new RenameComponent_Transaction(name,componentToRename,app);
              app.processTransaction(transaction);
               app.getFoolproofModule().updateAll();
             }
        }
    }
    public void processAddImage(File file, String path) throws FileNotFoundException{
        File imageFile=file;
        String imagePath=path;
        if(file!=null){
           
            
            
            
            AddImage_Transaction transaction = new AddImage_Transaction(imageFile,app,imagePath);
            app.processTransaction(transaction);
             app.getFoolproofModule().updateAll();
        }        
    }
    
    public void processEditText(){
         LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected() ) {
            LogoPrototype textToEdit = data.getSelectedItem();          
            editDialog.showEditDialog(textToEdit);
             
            LogoPrototype changedText = editDialog.getChangedText();
           
            if (changedText != null) {
                
                
            EditText_Transaction transaction = new EditText_Transaction(textToEdit,changedText.getText(),app,data );
            app.processTransaction(transaction);
             app.getFoolproofModule().updateAll();
            }     
        }
    }
    
    
    public void processDeleteComponent(){
        LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) { 
            LogoPrototype dataToDelete=data.getSelectedItem();
            DeleteComponent_Transaction transaction = new DeleteComponent_Transaction(app,dataToDelete,data);
            app.processTransaction(transaction);
             app.getFoolproofModule().updateAll();
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
