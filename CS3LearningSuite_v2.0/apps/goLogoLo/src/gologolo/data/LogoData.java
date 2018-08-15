/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;

import gologolo.GoLogoLo;

import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_TABLE_VIEW;
import gologolo.transactions.ClickDrag_Transaction;
import gologolo.workspace.controller.LogoController;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.ObservableList;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author jasoncao
 */
//for adding to table
public class LogoData implements AppDataComponent{
    

   
    
    ObservableList<LogoPrototype> components;
    TableViewSelectionModel dataSelectionModel;
    
    ObservableList<Node> editComponents;
    SelectionModel paneSelectionModel;
    double xCoordinate;
    double yCoordinate;
     double mouseX;
        double mouseY;
    //for dragging textfield
    double mouseXCoordinate;
    double mouseYCoordinate;
    double translatedX;
    double translatedY;
    GoLogoLo initApp;
    double initialX;
    double initialY;  
    double lastX;
    double lastY;
    double orgSceneX;
    double orgSceneY;
    double orgTranslateX;
      double orgTranslateY;
    
    
    
    public LogoData(GoLogoLo logoApp) {
        initApp=logoApp;
           // GET ALL THE THINGS WE'LL NEED TO MANIUPLATE THE TABLE
        TableView tableView = (TableView) initApp.getGUIModule().getGUINode(LOGO_TABLE_VIEW);
        components = tableView.getItems();
        dataSelectionModel = tableView.getSelectionModel();
        dataSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        
        
        
        Pane paneNode=(Pane)initApp.getGUIModule().getGUINode(LOGO_EDIT_PANE);
        editComponents=paneNode.getChildren();
                

        
    }

    @Override
    public void reset() {
      AppGUIModule gui = initApp.getGUIModule();
        
     
        // CLEAR OUT THE ITEMS FROM THE TABLE
        TableView tableView = (TableView)gui.getGUINode(LOGO_TABLE_VIEW);
        components = tableView.getItems();
        components.clear();
    }
    public boolean isValidLogoData(String name){
        String componentName=name;
        if (componentName.trim().length() == 0)
        { return false;}
        else return true;
    }
    
    public void addToTable(LogoPrototype itemToAdd) {
        components.add(itemToAdd);
        reorderTable();
    }
    
     
     
     public void removeRectangle(LogoPrototype itemToRemove) {
         int indexToRemove=components.indexOf(itemToRemove);
         components.remove(itemToRemove);
         
         editComponents.remove(indexToRemove);
         
         reorderTable();
    }
     
     
  
    public void removeText(LogoPrototype itemToRemove) {
      
        int indexToRemove=components.indexOf(itemToRemove);
         components.remove(itemToRemove);
         editComponents.remove(indexToRemove);
         reorderTable();
    }

    public void changeText(LogoPrototype textFieldToChange,String newText) {
        LogoPrototype oldTextField=textFieldToChange;
        String textToAdd=newText;
        
        oldTextField.setText(newText);
        int indexOfText=components.indexOf(oldTextField);
        
        Text textInPane=(Text) editComponents.get(indexOfText);
      
        textInPane.setText(textToAdd);
    }
    
    
     public Node deleteComponent(LogoPrototype itemToRemove,LogoData logoData) {
         LogoData data=logoData;
         LogoPrototype componentToRemove=itemToRemove;
       
         int indexToRemoveInPane= components.indexOf(componentToRemove);
         Node node=editComponents.get(indexToRemoveInPane);
         components.remove(componentToRemove);
         editComponents.remove(indexToRemoveInPane);
         reorderTable();
         return node;
     }
    
    public void clearSelected() {
        this.dataSelectionModel.clearSelection();
    }
    
    public LogoPrototype getSelectedItem() {
        if (!isItemSelected()) {
            return null;
        }
        return getSelectedItems().get(0);
    }
    
     public int getItemIndex(LogoPrototype item) {
        return components.indexOf(item);
    }

    public void addItemAt(LogoPrototype item, int itemIndex,Node component) {
        components.add(itemIndex, item);
        editComponents.add(itemIndex, component);
              this.clearSelected();
              this.selectItem(item);
              selectNodeInPane(component);
           
        
        if(component instanceof Rectangle){
              component.setOnMouseClicked(e -> {
            //app.getFoolproofModule().updateAll();
            {
              this.clearSelected();
              this.selectItem(item);
              selectNodeInPane(component);
            }
        });
       
            
            
          component.setOnMousePressed(e->{
            this.clearSelected();
           this.selectItem(item);
            selectNodeInPane(component);

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = component.getTranslateX();
            orgTranslateY = component.getTranslateY();
         });
        
         
            component.setOnMouseDragged(e->{
            this.clearSelected();
              this.selectItem(item);
              selectNodeInPane(component);
              
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            component.setTranslateX(newTranslateX);
            component.setTranslateY(newTranslateY);
            component.setOnMouseReleased(x->{
              
                ClickDrag_Transaction transaction = new ClickDrag_Transaction( orgTranslateX, orgTranslateY, newTranslateX, newTranslateY,component);
                initApp.processTransaction(transaction);
            });
        });
        
           }
        
        
        if(component instanceof Text){
             //SELECTION 
        //double click edit in pane
             LogoController eventController=new LogoController((GoLogoLo)initApp);
             
             component.setOnMouseClicked(e -> {
                   
               //selection of node in table 
                this.clearSelected();
                 this.selectItem(item);
                selectNodeInPane(component);     
            if (e.getClickCount() == 2)
                {
                //selection of node in table 
                    this.clearSelected();
                    this.selectItem(item);
                    selectNodeInPane(component);
                    eventController.processEditText();
                }
           
             
                          
            
        });
        
          
          //dragging rectangle
          
            component.setOnMousePressed(e->{
            this.clearSelected();
           this.selectItem(item);
            selectNodeInPane(component);

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = component.getTranslateX();
            orgTranslateY = component.getTranslateY();
         });
        
         
            component.setOnMouseDragged(e->{
            this.clearSelected();
              this.selectItem(item);
              selectNodeInPane(component);
              
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            component.setTranslateX(newTranslateX);
            component.setTranslateY(newTranslateY);
            component.setOnMouseReleased(x->{
              
                ClickDrag_Transaction transaction = new ClickDrag_Transaction( orgTranslateX, orgTranslateY, newTranslateX, newTranslateY,component);
                initApp.processTransaction(transaction);
            });
        });
        
           }
         reorderTable();
    }

    
    public boolean isItemSelected() {
        ObservableList<LogoPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() == 1);
    }
    
        public void selectItem(LogoPrototype itemToSelect) {
        this.dataSelectionModel.select(itemToSelect);
    }
         public void selectItem(int index) {
        this.dataSelectionModel.select(index);
    }
        
        public ArrayList<LogoPrototype> getCurrentItemsOrder() {
        ArrayList<LogoPrototype> orderedItems = new ArrayList();
        for (LogoPrototype item : components) {
            orderedItems.add(item);
        }
        return orderedItems;
    }
        

     public ObservableList<LogoPrototype> getSelectedItems() {
        return (ObservableList<LogoPrototype>)this.dataSelectionModel.getSelectedItems();
    }
     
     
     public ArrayList<Integer> getSelectedItemsIndex(ArrayList<LogoPrototype> dataTable) {
        ArrayList<Integer> indexes=new ArrayList<>();
        
        for(int i=0;i<dataTable.size();i++){
            indexes.add(components.indexOf(dataTable.get(i)));
        }
        
         return indexes;
    }
     
     
      public boolean areItemsSelected() {
        ObservableList<LogoPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() > 1);        
    }
      
      
      
       public ArrayList<Integer> removeAllInTable(ArrayList<LogoPrototype> itemsToRemove) {
        ArrayList<Integer> itemIndices = new ArrayList();
        for (LogoPrototype item: itemsToRemove) {
            itemIndices.add(components.indexOf(item));
        }
        for (LogoPrototype item: itemsToRemove) {
            components.remove(item);
        }
        reorderTable();
        return itemIndices;
    }
      
       
       public ArrayList<Node> removeAllPane(ArrayList<Integer> indexesToRemove) {
           
        ArrayList<Integer> itemIndices = indexesToRemove;
        ArrayList<Node>paneNodes = new ArrayList<Node>();
      
        for (Integer index: itemIndices) {
            paneNodes.add(editComponents.get(index));
        }
        
        for (Node node: paneNodes) {
            editComponents.remove(node);
        }
        reorderTable();
        return paneNodes;
    }
       
       
       public void addAllToTable(ArrayList<LogoPrototype> itemsToAdd, ArrayList<Integer> addItemLocations) {
        for (int i = 0; i < itemsToAdd.size(); i++) {
            LogoPrototype dataToAdd = itemsToAdd.get(i);
            Integer location = addItemLocations.get(i);
            components.add(location, dataToAdd);
        }
        reorderTable();
    }
       
       public void addAllToPane(ArrayList<Node> itemsToAdd, ArrayList<Integer> addItemLocations) {
        for (int i = 0; i < itemsToAdd.size(); i++) {
            Node dataToAdd = itemsToAdd.get(i);
            Integer location = addItemLocations.get(i);
            editComponents.add(location, dataToAdd);
        }
        
       }
       
        
        public int remove(LogoPrototype tableData){
            int index=components.indexOf(tableData);
            components.remove(tableData);
            reorderTable();
            return index;
            
        }
        
         public Node removeFromPane(int index){
            Node node=(Node)editComponents.get(index);
            editComponents.remove(index);
            return node;
            
        }

    public void getInfo() {
       int a=components.size();
       int b=editComponents.size();
    }
       
    public void reorderTable(){
        int i=1;
        for(LogoPrototype data:components){
            data.setOrder(i++);
        }
    }
    
    public void selectCorrespondingData(Node node){
        int index=editComponents.indexOf(node);
        LogoPrototype temp=components.get(index);
        this.selectItem(temp);
    }
    
    
    
    public Rectangle createRectangle(){
        Rectangle  rectangle=new Rectangle();
        rectangle.setWidth(300);
         rectangle.setHeight(150);
         rectangle.setFill(WHITE);
        rectangle.setStroke(BLACK);
        rectangle.setY(300);
        rectangle.setX(250);
        
         rectangle.setOnMouseClicked(e -> {
            //app.getFoolproofModule().updateAll();
            {
              this.clearSelected();
              this.selectCorrespondingData(rectangle);
              selectNodeInPane(rectangle);
            }
        });
         
         rectangle.setOnMousePressed(e->{
            this.clearSelected();
              this.selectCorrespondingData(rectangle);
            selectNodeInPane(rectangle);

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = rectangle.getTranslateX();
            orgTranslateY = rectangle.getTranslateY();
         });
        
         
            rectangle.setOnMouseDragged(e->{
            this.clearSelected();
              this.selectCorrespondingData(rectangle);
              selectNodeInPane(rectangle);
              
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            rectangle.setTranslateX(newTranslateX);
            rectangle.setTranslateY(newTranslateY);
            rectangle.setOnMouseReleased(x->{
              
                ClickDrag_Transaction transaction = new ClickDrag_Transaction( orgTranslateX, orgTranslateY, newTranslateX, newTranslateY,rectangle);
                initApp.processTransaction(transaction);
            });
        });
        
        
          
        //set anything else
        return rectangle;
        
    }
    
    
    
    public Node deepCloneNode(int nodeIndexToCopy){
        
        if(editComponents.get(nodeIndexToCopy) instanceof Rectangle){
        Rectangle tempRectangle= createRectangle();
       
        return tempRectangle;
             
     }
        
        
        else if (editComponents.get(nodeIndexToCopy) instanceof TextField){
        TextField addText=new TextField();
        addText.replaceText(0, 0, components.get(nodeIndexToCopy).getText());
        addText.setLayoutX(200);
        addText.setLayoutY(200);
        addText.setEditable(false);
        //set anything else
        
        
        //SELECTION 
        //double click edit in pane
          LogoController eventController=new LogoController((GoLogoLo)initApp);
          addText.setOnMouseClicked(e -> {
           
                //selection of node in table 
                this.clearSelected();
                this.selectCorrespondingData(addText);      
                selectNodeInPane(addText);
                   
            if (e.getClickCount() == 2) {
                //selection of node in table 
                this.clearSelected();
                this.selectCorrespondingData(addText);
                selectNodeInPane(addText);
                 
                eventController.processEditText();
            }
            
        });
        
     
          
        addText.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                
                    this.clearSelected();
                this.selectCorrespondingData(addText);     
                mouseXCoordinate = e.getSceneX();
                mouseYCoordinate = e.getSceneY();
                initialX=e.getSceneX();
                initialY=e.getSceneY();
                
                translatedX = ((TextField) e.getSource()).getTranslateX();
                translatedY = ((TextField) e.getSource()).getTranslateY();
              
    });

          
        //CHANGE!!! TO PRESSED

        addText.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
             this.clearSelected();
                this.selectCorrespondingData(addText);
           Pane editPane= (Pane) initApp.getGUIModule().getGUINode(LOGO_EDIT_PANE);
           Bounds editPaneBounds=editPane.getLayoutBounds();
           Bounds rectangleBounds=addText.getLayoutBounds();
           double mouseXcoordinate=e.getX();
           double mouseYcoordinate=e.getY();
            if(mouseXcoordinate>editPane.getScaleX()){
                 if(mouseYcoordinate>editPane.getScaleY())
                 {
                     if(mouseXcoordinate<737)
                     {
                        if(mouseYcoordinate<734)                        
                        {
                             double newTranslateX = translatedX + (e.getSceneX() - mouseXCoordinate);
                             double newTranslateY =translatedY + (e.getSceneY() -mouseYCoordinate);
                             ((TextField) (e.getSource())).setTranslateX(newTranslateX);
                              ((TextField) (e.getSource())).setTranslateY(newTranslateY);
                              lastX=newTranslateX;
                              lastY=newTranslateY;
                        }
                     }
                 }
            }
          //  ClickDrag_Transaction drag=new ClickDrag_Transaction(initialX,initialY,lastX,lastY);
         });    
        
        
        return addText;
     }
               
        else{
            return null;
        }
        
 
    }

    
    public Node deepCloneNode(Node nodeToCopy){
        
        if((nodeToCopy) instanceof Rectangle){
        Rectangle tempRectangle= createRectangle();
       
        return tempRectangle;
             
     }
        
        
        else if ((nodeToCopy) instanceof TextField){
        TextField addText=new TextField();
        addText.replaceText(0, 0, ((TextField)nodeToCopy).getText());
        addText.setLayoutX(200);
        addText.setLayoutY(200);
        addText.setEditable(false);
        //set anything else
        
        
        //SELECTION 
        //double click edit in pane
          LogoController eventController=new LogoController((GoLogoLo)initApp);
          addText.setOnMouseClicked(e -> {
          
                //selection of node in table 
                this.clearSelected();
                this.selectCorrespondingData(addText);      
                selectNodeInPane(addText);
            
            
            if (e.getClickCount() == 2) {
                //selection of node in table 
                this.clearSelected();
                this.selectCorrespondingData(addText);
                selectNodeInPane(addText);
                 
                eventController.processEditText();
            }
           
        });
        
          
          //dragging rectangle
          
       addText.setOnMousePressed(e->{
            this.clearSelected();
            this.selectCorrespondingData(addText);    
            selectNodeInPane(addText);

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = addText.getTranslateX();
            orgTranslateY = addText.getTranslateY();
         });
        
         
            addText.setOnMouseDragged(e->{
            this.clearSelected();
               this.selectCorrespondingData(addText);    
              selectNodeInPane(addText);
              
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            addText.setTranslateX(newTranslateX);
            addText.setTranslateY(newTranslateY);
            addText.setOnMouseReleased(x->{
              
                ClickDrag_Transaction transaction = new ClickDrag_Transaction( orgTranslateX, orgTranslateY, newTranslateX, newTranslateY,addText);
                initApp.processTransaction(transaction);
            });
        });
        
        return addText;
     }
        
        
        else{
            return null;
        }
        
 
    }
    

    public Node getSelectedNode(LogoPrototype connectedItem){
        int index;
        index=components.indexOf(connectedItem);
        Node paneNode=editComponents.get(index);
        return paneNode;
    }
    
    
       //for highlighting nodes in pane, call these 3 methods
    public void selectNodeInPane(LogoPrototype connectedItem){
       int index=components.indexOf(connectedItem);
       for(Node node:editComponents){
           node.setEffect(null);
       }
       Glow glow=new Glow();
        editComponents.get(index).setEffect(glow);
//      InnerShadow innerShadow = new InnerShadow();
//      innerShadow.setColor(BLUE);
//       editComponents.get(index).setEffect(innerShadow);
       
    }
    
    public void selectNodeInPane(int index){
        for(Node node:editComponents){
           node.setEffect(null);
       }
         Glow glow=new Glow();
        editComponents.get(index).setEffect(glow);
//       InnerShadow innerShadow = new InnerShadow();
//      innerShadow.setColor(BLUE);
//       editComponents.get(index).setEffect(innerShadow);
    }
    
    public void selectNodeInPane(Node node){
        int index=editComponents.indexOf(node);
       
       for(Node listNode:editComponents){
           listNode.setEffect(null);
       }
      InnerShadow innerShadow = new InnerShadow();
      innerShadow.setColor(BLUE);
       editComponents.get(index).setEffect(innerShadow);
    }
    
    public Iterator<LogoPrototype> componentsIterator() {
        return this.components.iterator();
    }
    
     public Iterator<Node> nodeIterator() {
        return this.editComponents.iterator();
         
    }
     
   
             
     public void addRectangleDataAndNode(LogoPrototype item,Rectangle component) {
        components.add(item);
        editComponents.add((Node)component);      
        this.clearSelected();
         this.selectItem(item);
         selectNodeInPane(component);
         reorderTable(); 
        
        
         //Drag click and move for this rectagle
              component.setOnMouseClicked(e -> {
            
            {
              this.clearSelected();
              this.selectItem(item);
              selectNodeInPane(component);
              
            }
        });
       
            
            
          component.setOnMousePressed(e->{
           this.clearSelected();
           this.selectItem(item);
           selectNodeInPane(component);

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = component.getTranslateX();
            orgTranslateY = component.getTranslateY();
         });
        
         
            component.setOnMouseDragged(e->{
            this.clearSelected();
            this.selectItem(item);
            selectNodeInPane(component);
              
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            component.setTranslateX(newTranslateX);
            component.setTranslateY(newTranslateY);
            component.setOnMouseReleased(x->{
              
                ClickDrag_Transaction transaction = new ClickDrag_Transaction( orgTranslateX, orgTranslateY, newTranslateX, newTranslateY,component);
                initApp.processTransaction(transaction);
            });
         });
        
           }
        
        public void removeRectangleDataAndNode(LogoPrototype item,Rectangle component) {
           components.remove(item);
            editComponents.remove(component);
            this.clearSelected();           
            reorderTable(); 
        }
        
         public void removeTextDataAndNode(LogoPrototype item,Text component) {
           components.remove(item);
            editComponents.remove(component);
            this.clearSelected();           
            reorderTable(); 
        }
        
      
        
        
        public void addTextDataAndNode(LogoPrototype item,LogoText component) {
        components.add(item);
        editComponents.add(component);      
        this.clearSelected();
        this.selectItem(item);
        selectNodeInPane(component);
        LogoController eventController=new LogoController((GoLogoLo)initApp);
        
       
           component.setOnMouseClicked(e -> {
             //selection of node in table 
                this.clearSelected();
                this.selectItem(item);
                selectNodeInPane(component);                 
            
              if (e.getClickCount() == 2) {
                  System.out.println("DOUBLE CLICK!!!");
                //selection of node in table        
                 
                 this.clearSelected();
                 this.selectItem(item);
                  selectNodeInPane(component);
                 eventController.processEditText();
            }
     });
           
            
           
          component.setOnMousePressed(e->{
           this.clearSelected();
           this.selectItem(item);
           selectNodeInPane(component);

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = component.getTranslateX();
            orgTranslateY = component.getTranslateY();
         });
        
         
            component.setOnMouseDragged(e->{
            
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            component.setTranslateX(newTranslateX);      
            component.setTranslateY(newTranslateY);
            
            component.setOnMouseReleased(x->{
              
                ClickDrag_Transaction transaction = new ClickDrag_Transaction( orgTranslateX, orgTranslateY, newTranslateX, newTranslateY,component);
                initApp.processTransaction(transaction);
            });
         });
            
     
        reorderTable(); 
       
        }
            
    public void addImageDataAndNode(LogoPrototype item,ImageView component) {  
        components.add(item);
        editComponents.add(component);
        this.clearSelected();
        this.selectItem(item);
       
        selectNodeInPane(component);
        LogoController eventController=new LogoController((GoLogoLo)initApp);
        
       
           component.setOnMouseClicked(e -> {
            
            
  
                //selection of node in table 
                this.clearSelected();
                 this.selectItem(item);
                selectNodeInPane(component);                          
         });
           
            
           
          component.setOnMousePressed(e->{
           this.clearSelected();
           this.selectItem(item);
           selectNodeInPane(component);

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = component.getTranslateX();
            orgTranslateY = component.getTranslateY();
         });
        
         
            component.setOnMouseDragged(e->{
            
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            component.setTranslateX(newTranslateX);      
            component.setTranslateY(newTranslateY);
            
            component.setOnMouseReleased(x->{
              
                ClickDrag_Transaction transaction = new ClickDrag_Transaction( orgTranslateX, orgTranslateY, newTranslateX, newTranslateY,component);
                initApp.processTransaction(transaction);
            });
         });
            
     
        reorderTable(); 
       
        }
    

    public void removeImageDataAndNode(LogoPrototype item,ImageView component) {  
            components.remove(item);
            editComponents.remove(component);
            this.clearSelected();           
            reorderTable(); 
 
        }

     public void addCircleDataAndNode(LogoPrototype item,LogoCircle component) {
        components.add(item);
        editComponents.add((Node)component);      
        this.clearSelected();
         this.selectItem(item);
         selectNodeInPane(component);
         reorderTable(); 
        
        
         //Drag click and move for this rectagle
              component.setOnMouseClicked(e -> {
            
            {
              this.clearSelected();
              this.selectItem(item);
              selectNodeInPane(component);
              
            }
        });
       
            
            
          component.setOnMousePressed(e->{
           this.clearSelected();
           this.selectItem(item);
           selectNodeInPane(component);

            orgSceneX = e.getSceneX();
            orgSceneY = e.getSceneY();
            orgTranslateX = component.getTranslateX();
            orgTranslateY = component.getTranslateY();
         });
        
         
            component.setOnMouseDragged(e->{
            this.clearSelected();
            this.selectItem(item);
            selectNodeInPane(component);
              
            double offsetX = e.getSceneX() - orgSceneX;
            double offsetY = e.getSceneY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
            double newTranslateY = orgTranslateY + offsetY;

            component.setTranslateX(newTranslateX);
            component.setTranslateY(newTranslateY);
            component.setOnMouseReleased(x->{
              
                ClickDrag_Transaction transaction = new ClickDrag_Transaction( orgTranslateX, orgTranslateY, newTranslateX, newTranslateY,component);
                initApp.processTransaction(transaction);
            });
         });
        
           }
        
        public void removeCircleDataAndNode(LogoPrototype item,LogoCircle component) {
           components.remove(item);
            editComponents.remove(component);
            this.clearSelected();           
            reorderTable(); 
        }
}
