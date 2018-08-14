/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.clipboard;

import djf.components.AppClipboardComponent;
import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.data.LogoRectangle;
import gologolo.data.LogoText;
import gologolo.transactions.CutComponents_Transaction;
import gologolo.transactions.PasteComponent_Transaction;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author jasoncao
 */



public class LogoClipboard implements AppClipboardComponent{
    GoLogoLo  app;
    
  
    LogoPrototype clipboardCutTableData;
    LogoPrototype clipboardCopyTableData;
    Node clipboardCutNode;
    Node clipboardCopyNode;
    
      
    
    
    
    public LogoClipboard(GoLogoLo initApp) {
         app = initApp;
         clipboardCutTableData=null;
         clipboardCopyTableData=null;
         clipboardCutNode=null;  
         clipboardCopyNode=null;
    }

    
    @Override
    public void cut() {
        LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) {
           clipboardCutTableData=data.getSelectedItem();
           clipboardCutNode=data.getSelectedNode(clipboardCutTableData);
            
           clipboardCopyNode = null;
           clipboardCopyTableData = null;
           
          
            CutComponents_Transaction transaction = new CutComponents_Transaction((GoLogoLo)app, clipboardCutTableData,clipboardCutNode);
            app.processTransaction(transaction);
     
            
        }
    }

    

    @Override
    public void copy() {
        LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) {
           LogoPrototype tempClipboardCopyTableData=data.getSelectedItem();
           Node tempClipboardCopyNode=data.getSelectedNode(tempClipboardCopyTableData);  
           copyToCopyNode(tempClipboardCopyNode,tempClipboardCopyTableData);
           copyToCopyData(tempClipboardCopyTableData);
           
           
       
            
        }
    }
           
    
    public void copyToCopyData(LogoPrototype temp){
        clipboardCopyTableData=(LogoPrototype) temp.clone();
        clipboardCutTableData=null;
        
    }

    public void copyToCopyNode(Node temp,LogoPrototype refData){
        LogoData data = (LogoData)app.getDataComponent();
       
         if(temp instanceof Rectangle){
             LogoRectangle rect=new LogoRectangle(((Rectangle)temp).getWidth(),
                                        ((Rectangle)temp).getHeight(),
                                        ((Rectangle)temp).getFill(),
                                       ((Rectangle)temp).getStroke(),
                                        ((Rectangle)temp).getX(),
                                       ((Rectangle)temp).getY()
                                        );
             Rectangle rectangle=rect.getRectangle();
               clipboardCutNode=null;
             
               clipboardCopyNode=rectangle;
         }
        
         else if(temp instanceof Text){
             LogoText logoText=new LogoText (((Text)temp).getX(),((Text)temp).getY(),((Text)temp).getText());
             
            Text text=logoText.getTextNode();
               clipboardCutNode=null;
             
               clipboardCopyNode=text;
         }
        
    }
    
    public void copyToCutData(LogoPrototype temp){
        clipboardCopyTableData=null;
        clipboardCutTableData=(LogoPrototype) temp.clone();
        
    }

    public void copyToCutNode(Node temp,LogoPrototype refData){
        LogoData data = (LogoData)app.getDataComponent();
         
         if(temp instanceof Rectangle){
             LogoRectangle rect=new LogoRectangle(((Rectangle)temp).getWidth(),
                                        ((Rectangle)temp).getHeight(),
                                        ((Rectangle)temp).getFill(),
                                       ((Rectangle)temp).getStroke(),
                                        ((Rectangle)temp).getX(),
                                       ((Rectangle)temp).getY()
                                        );
             Rectangle rectangle=rect.getRectangle();
               clipboardCutNode=rectangle;
             
               clipboardCopyNode=null;
         }
        
         else if(temp instanceof Text){
             LogoText logoText=new LogoText (((Text)temp).getX(),((Text)temp).getY(),((Text)temp).getText());
             
            Text text=logoText.getTextNode();
               clipboardCutNode=text;
             
               clipboardCopyNode=null;
         }
        
   
       
    }





     
    @Override
    public void paste() {
      LogoData data = (LogoData)app.getDataComponent();
        if (data.isItemSelected()) {
            int selectedIndex = data.getItemIndex(data.getSelectedItem());  
            
         
            if ((clipboardCutTableData != null))
            {
                
                PasteComponent_Transaction transaction = new PasteComponent_Transaction((GoLogoLo)app, clipboardCutTableData,clipboardCutNode, selectedIndex);
                copyToCutNode(clipboardCutNode,clipboardCutTableData);
                copyToCutData(clipboardCutTableData);
                app.processTransaction(transaction);
                
                // NOW WE HAVE TO RE-COPY THE CUT ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                
                
            }
            
            else if ((clipboardCopyTableData != null)) 
            {
                
                PasteComponent_Transaction transaction = new PasteComponent_Transaction((GoLogoLo)app, clipboardCopyTableData,clipboardCopyNode, selectedIndex);
                copyToCopyNode(clipboardCopyNode,clipboardCopyTableData);
                copyToCopyData(clipboardCopyTableData);
                app.processTransaction(transaction);
            
                // NOW WE HAVE TO RE-COPY THE COPIED ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
               
                
            }
        }
    }

    
    
    @Override
    public boolean hasSomethingToCut() {
        return ((LogoData)app.getDataComponent()).isItemSelected()
        || ((LogoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToCopy() {
       return ((LogoData)app.getDataComponent()).isItemSelected()
                || ((LogoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToPaste() {
         if ((clipboardCutTableData != null))
            return true;
        else if ((clipboardCopyTableData != null) )
            return true;
        else
            return false;
    }

    
}

