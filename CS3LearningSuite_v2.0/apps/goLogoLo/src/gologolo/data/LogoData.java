/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;

import gologolo.GoLogoLo;
import static gologolo.goLogoLoPropertyType.COLOR_PICKER;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_RAIDIUS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_BORDER_THICKNESS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_CENTER_X_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_CENTER_Y_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_CYCLE_METHOD_COMBO_BOX;

import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import static gologolo.goLogoLoPropertyType.LOGO_FOCUS_ANGLE_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_FOCUS_DISTANCE_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_FONT_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_FONT_SIZE_COMBO_BOX;
import static gologolo.goLogoLoPropertyType.LOGO_ONE_COLOR;
import static gologolo.goLogoLoPropertyType.LOGO_RADIUS_SLIDER;
import static gologolo.goLogoLoPropertyType.LOGO_TABLE_VIEW;
import static gologolo.goLogoLoPropertyType.LOGO_ZERO_COLOR;
import gologolo.transactions.ClickDrag_Transaction;
import gologolo.transactions.ResizeCircle_Transaction;
import gologolo.workspace.controller.LogoController;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.ObservableList;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Slider;
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
import javafx.scene.shape.Shape;
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
 public GoLogoLo getApp(){
     return initApp;
 }

    public ObservableList<LogoPrototype> getComponents() {
        return components;
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
           
        
        if(item.getType().equalsIgnoreCase("Rectangle")||item.getType().equalsIgnoreCase("Image")||item.getType().equalsIgnoreCase("Circle")){
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
        
        
        else if(item.getType().equalsIgnoreCase("Text")){
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

    public ObservableList<Node> getEditComponents() {
        return editComponents;
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
    
    public void swapComponentAndNodes(int indexToMoveUp,int indexToMoveDown){
        LogoPrototype bottomData=components.get(indexToMoveUp);
        LogoPrototype topData=components.get(indexToMoveDown);
        Node topNode=editComponents.get(indexToMoveDown);
        Node bottomNode=editComponents.get(indexToMoveUp);
        components.remove(topData);
        components.add(indexToMoveUp, topData);
        this.clearSelected();
        this.selectItem(bottomData);
       editComponents.remove(topNode);
       editComponents.add(indexToMoveUp, topNode);
       selectNodeInPane(bottomNode);
        this.reorderTable();
    }
    
      public void swapComponentAndNodeMoveDown(int indexToMoveUp,int indexToMoveDown){
        LogoPrototype bottomData=components.get(indexToMoveUp);
        LogoPrototype topData=components.get(indexToMoveDown);
        Node topNode=editComponents.get(indexToMoveDown);
        Node bottomNode=editComponents.get(indexToMoveUp);
        components.remove(topData);
        components.add(indexToMoveUp, topData);
        this.clearSelected();
        this.selectItem(topData);
       editComponents.remove(topNode);
       editComponents.add(indexToMoveUp, topNode);
       selectNodeInPane(topNode);
        this.reorderTable();
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
    
    public Shape getShapeAt(int index){
        return (Shape)editComponents.get(index);
    }
    public Text getTextAt(int index){
        return (Text)editComponents.get(index);
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
    
    
       //for highlighting nodes in pane, call these 3 methods================================
    public void selectNodeInPane(LogoPrototype connectedItem){
       int index=components.indexOf(connectedItem);
       for(Node node:editComponents){
           node.setEffect(null);
       }
   
      InnerShadow innerShadow = new InnerShadow();
      innerShadow.setColor(BLUE);
       editComponents.get(index).setEffect(innerShadow);
       
        //Get all slider and combobox info related to selected Component
       ComboBox fontNameComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_FONT_COMBO_BOX);
       ComboBox fontSizeComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_FONT_SIZE_COMBO_BOX);
       Slider borderThicknessSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_BORDER_THICKNESS_SLIDER);
       ColorPicker borderColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(COLOR_PICKER);
       Slider borderRadiusSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_BORDER_RAIDIUS_SLIDER);
       Slider focusAngleSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_FOCUS_ANGLE_SLIDER);
       Slider focusDistanceSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_FOCUS_DISTANCE_SLIDER);
       Slider centerXSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_CENTER_X_SLIDER);
       Slider centerYSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_CENTER_Y_SLIDER);
       Slider radiusSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_RADIUS_SLIDER);
       ComboBox cycleMethodComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_CYCLE_METHOD_COMBO_BOX);
       ColorPicker zeroColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(LOGO_ZERO_COLOR);
       ColorPicker oneColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(LOGO_ONE_COLOR);
       
       //set the nodes to appropriate values
       LogoPrototype selectedData=components.get(index);
       if(selectedData.getType().equals("Rectangle")){
           LogoRectangle rectangle=(LogoRectangle) editComponents.get(index);
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(rectangle.getRectangleStrokeWidth());
           borderColorComboBox.setValue(rectangle.getBorderColor());
           borderRadiusSlider.setValue(rectangle.getRectangleArcHeight());
           focusAngleSlider.setValue(rectangle.getFocusAngle());
           focusDistanceSlider.setValue(rectangle.getFocusDistance());
           centerXSlider.setValue(rectangle.getCenterX());
           centerYSlider.setValue(rectangle.getCenterY());
           radiusSlider.setValue(rectangle.getRadius());
           cycleMethodComboBox.setValue(rectangle.getCycleMethod().name());
           zeroColorComboBox.setValue(rectangle.getStop0Color());
           oneColorComboBox.setValue(rectangle.getStop1Color());
   
       }
        else if(selectedData.getType().equals("Circle")){
           
           LogoCircle circle=(LogoCircle)editComponents.get(index);
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(circle.getCircleBorderThickness());
           borderColorComboBox.setValue(circle.getBorderColor());
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(circle.getFocusAngle());
           focusDistanceSlider.setValue(circle.getFocusDistance());
           centerXSlider.setValue(circle.getGradientCenterX());
           centerYSlider.setValue(circle.getGradientCenterY());
           radiusSlider.setValue(circle.getGradientRadius());
           cycleMethodComboBox.setValue(circle.getCycleMethod().name());
           zeroColorComboBox.setValue(circle.getStop0().getColor());
           oneColorComboBox.setValue(circle.getStop1().getColor());      
       }
       
       else if(selectedData.getType().equals("Text")){
           
           LogoText text=(LogoText)editComponents.get(index);
           fontNameComboBox.setValue(text.getFontName());
           fontSizeComboBox.setValue(Integer.toString(text.getFontSize()));
           borderThicknessSlider.setValue(0);
           borderColorComboBox.setValue(WHITE);
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(0);
           focusDistanceSlider.setValue(0);
           centerXSlider.setValue(0);
           centerYSlider.setValue(0);
           radiusSlider.setValue(0);
           cycleMethodComboBox.setValue("NO_CYCLE");
           zeroColorComboBox.setValue(WHITE);
           oneColorComboBox.setValue(WHITE);      
       }
       
       else if(selectedData.getType().equals("Image")){
           
     
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(0);
           borderColorComboBox.setValue(WHITE);
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(0);
           focusDistanceSlider.setValue(0);
           centerXSlider.setValue(0);
           centerYSlider.setValue(0);
           radiusSlider.setValue(0);
           cycleMethodComboBox.setValue("NO_CYCLE");
           zeroColorComboBox.setValue(WHITE);
           oneColorComboBox.setValue(WHITE);      
       }
       
         initApp.getFoolproofModule().updateAll();
    }
    
    public void selectNodeInPane(int index){
        for(Node node:editComponents){
           node.setEffect(null);
       }
      
       InnerShadow innerShadow = new InnerShadow();
      innerShadow.setColor(BLUE);
      
      editComponents.get(index).setEffect(innerShadow);
      
       //Get all slider and combobox info related to selected Component
       ComboBox fontNameComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_FONT_COMBO_BOX);
       ComboBox fontSizeComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_FONT_SIZE_COMBO_BOX);
       Slider borderThicknessSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_BORDER_THICKNESS_SLIDER);
       ColorPicker borderColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(COLOR_PICKER);
       Slider borderRadiusSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_BORDER_RAIDIUS_SLIDER);
       Slider focusAngleSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_FOCUS_ANGLE_SLIDER);
       Slider focusDistanceSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_FOCUS_DISTANCE_SLIDER);
       Slider centerXSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_CENTER_X_SLIDER);
       Slider centerYSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_CENTER_Y_SLIDER);
       Slider radiusSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_RADIUS_SLIDER);
       ComboBox cycleMethodComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_CYCLE_METHOD_COMBO_BOX);
       ColorPicker zeroColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(LOGO_ZERO_COLOR);
       ColorPicker oneColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(LOGO_ONE_COLOR);
       
       //set the nodes to appropriate values
       LogoPrototype selectedData=components.get(index);
       if(selectedData.getType().equals("Rectangle")){
           LogoRectangle rectangle=(LogoRectangle) editComponents.get(index);
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(rectangle.getRectangleStrokeWidth());
           borderColorComboBox.setValue(rectangle.getBorderColor());
           borderRadiusSlider.setValue(rectangle.getRectangleArcHeight());
           focusAngleSlider.setValue(rectangle.getFocusAngle());
           focusDistanceSlider.setValue(rectangle.getFocusDistance());
           centerXSlider.setValue(rectangle.getCenterX());
           centerYSlider.setValue(rectangle.getCenterY());
           radiusSlider.setValue(rectangle.getRadius());
           cycleMethodComboBox.setValue(rectangle.getCycleMethod().name());
           zeroColorComboBox.setValue(rectangle.getStop0Color());
           oneColorComboBox.setValue(rectangle.getStop1Color());
   
       }
        else if(selectedData.getType().equals("Circle")){
           
           LogoCircle circle=(LogoCircle)editComponents.get(index);
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(circle.getCircleBorderThickness());
           borderColorComboBox.setValue(circle.getBorderColor());
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(circle.getFocusAngle());
           focusDistanceSlider.setValue(circle.getFocusDistance());
           centerXSlider.setValue(circle.getGradientCenterX());
           centerYSlider.setValue(circle.getGradientCenterY());
           radiusSlider.setValue(circle.getGradientRadius());
           cycleMethodComboBox.setValue(circle.getCycleMethod().name());
           zeroColorComboBox.setValue(circle.getStop0().getColor());
           oneColorComboBox.setValue(circle.getStop1().getColor());      
       }
       
       else if(selectedData.getType().equals("Text")){
           
           LogoText text=(LogoText)editComponents.get(index);
           fontNameComboBox.setValue(text.getFontName());
           fontSizeComboBox.setValue(Integer.toString(text.getFontSize()));
           borderThicknessSlider.setValue(0);
           borderColorComboBox.setValue(WHITE);
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(0);
           focusDistanceSlider.setValue(0);
           centerXSlider.setValue(0);
           centerYSlider.setValue(0);
           radiusSlider.setValue(0);
           cycleMethodComboBox.setValue("NO_CYCLE");
           zeroColorComboBox.setValue(WHITE);
           oneColorComboBox.setValue(WHITE);      
       }
       
       else if(selectedData.getType().equals("Image")){
           
          
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(0);
           borderColorComboBox.setValue(WHITE);
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(0);
           focusDistanceSlider.setValue(0);
           centerXSlider.setValue(0);
           centerYSlider.setValue(0);
           radiusSlider.setValue(0);
           cycleMethodComboBox.setValue("NO_CYCLE");
           zeroColorComboBox.setValue(WHITE);
           oneColorComboBox.setValue(WHITE);      
       }
         initApp.getFoolproofModule().updateAll();
    }
    
    public void selectNodeInPane(Node node){
        int index=editComponents.indexOf(node);
       
       for(Node listNode:editComponents){
           listNode.setEffect(null);
       }
      InnerShadow innerShadow = new InnerShadow();
      innerShadow.setColor(BLUE);
       editComponents.get(index).setEffect(innerShadow);
       
       
       //Get all slider and combobox info related to selected Component
       ComboBox fontNameComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_FONT_COMBO_BOX);
       ComboBox fontSizeComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_FONT_SIZE_COMBO_BOX);
       Slider borderThicknessSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_BORDER_THICKNESS_SLIDER);
       ColorPicker borderColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(COLOR_PICKER);
       Slider borderRadiusSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_BORDER_RAIDIUS_SLIDER);
       Slider focusAngleSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_FOCUS_ANGLE_SLIDER);
       Slider focusDistanceSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_FOCUS_DISTANCE_SLIDER);
       Slider centerXSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_CENTER_X_SLIDER);
       Slider centerYSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_CENTER_Y_SLIDER);
       Slider radiusSlider=(Slider) initApp.getGUIModule().getGUINode(LOGO_RADIUS_SLIDER);
       ComboBox cycleMethodComboBox=(ComboBox) initApp.getGUIModule().getGUINode(LOGO_CYCLE_METHOD_COMBO_BOX);
       ColorPicker zeroColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(LOGO_ZERO_COLOR);
       ColorPicker oneColorComboBox=(ColorPicker) initApp.getGUIModule().getGUINode(LOGO_ONE_COLOR);
       
       
       //set the nodes to appropriate values
       LogoPrototype selectedData=components.get(index);
       if(selectedData.getType().equals("Rectangle")){
           LogoRectangle rectangle=(LogoRectangle) editComponents.get(index);
           
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(rectangle.getRectangleStrokeWidth());
           borderColorComboBox.setValue(rectangle.getBorderColor());
           borderRadiusSlider.setValue(rectangle.getRectangleArcHeight());
           focusAngleSlider.setValue(rectangle.getFocusAngle());
           focusDistanceSlider.setValue(rectangle.getFocusDistance());
           centerXSlider.setValue(rectangle.getCenterX());
           centerYSlider.setValue(rectangle.getCenterY());
           radiusSlider.setValue(rectangle.getRadius());
           cycleMethodComboBox.setValue(rectangle.getCycleMethod().name());
           zeroColorComboBox.setValue(rectangle.getStop0Color());
           oneColorComboBox.setValue(rectangle.getStop1Color());
       }
       else if(selectedData.getType().equals("Circle")){
           
           LogoCircle circle=(LogoCircle)editComponents.get(index);
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(circle.getCircleBorderThickness());
           borderColorComboBox.setValue(circle.getBorderColor());
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(circle.getFocusAngle());
           focusDistanceSlider.setValue(circle.getFocusDistance());
           centerXSlider.setValue(circle.getGradientCenterX());
           centerYSlider.setValue(circle.getGradientCenterY());
           radiusSlider.setValue(circle.getGradientRadius());
           cycleMethodComboBox.setValue(circle.getCycleMethod().name());
           zeroColorComboBox.setValue(circle.getStop0().getColor());
           oneColorComboBox.setValue(circle.getStop1().getColor());      
       }
       
       else if(selectedData.getType().equals("Text")){
           
           LogoText text=(LogoText)editComponents.get(index);
           fontNameComboBox.setValue(text.getFontName());
           fontSizeComboBox.setValue(Integer.toString(text.getFontSize()));
           borderThicknessSlider.setValue(0);
           borderColorComboBox.setValue(WHITE);
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(0);
           focusDistanceSlider.setValue(0);
           centerXSlider.setValue(0);
           centerYSlider.setValue(0);
           radiusSlider.setValue(0);
           cycleMethodComboBox.setValue("NO_CYCLE");
           zeroColorComboBox.setValue(WHITE);
           oneColorComboBox.setValue(WHITE);      
       }
       
       else if(selectedData.getType().equals("Image")){
           
         
           fontNameComboBox.setValue("");
           fontSizeComboBox.setValue("");
           borderThicknessSlider.setValue(0);
           borderColorComboBox.setValue(WHITE);
           borderRadiusSlider.setValue(0);
           focusAngleSlider.setValue(0);
           focusDistanceSlider.setValue(0);
           centerXSlider.setValue(0);
           centerYSlider.setValue(0);
           radiusSlider.setValue(0);
           cycleMethodComboBox.setValue("NO_CYCLE");
           zeroColorComboBox.setValue(WHITE);
           oneColorComboBox.setValue(WHITE);      
       }
       
           initApp.getFoolproofModule().updateAll();
    }
    
    public Iterator<LogoPrototype> componentsIterator() {
        return this.components.iterator();
    }
    
     public Iterator<Node> nodeIterator() {
        return this.editComponents.iterator();
         
    }
     
   
             
     public void addRectangleDataAndNode(LogoPrototype item,LogoRectangle component) {
        components.add(item);
        editComponents.add(component);      
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
            
//            double offsetX = e.getSceneX() - orgSceneX;
//            double offsetY = e.getSceneY() - orgSceneY;
//            double newTranslateX = orgTranslateX + offsetX;
//            double newTranslateY = orgTranslateY + offsetY;
//            component.setWidth(newTranslateX);
//             component.setHeight(newTranslateY);
//                
                    
                    
                    
                    
              
              
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
        
        public void removeRectangleDataAndNode(LogoPrototype item,LogoRectangle component) {
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
    
             component.setOnScroll(e -> {
            double scaleZoom = 1.05;
            double deltaY = e.getDeltaY();
            if (deltaY < 0){
                scaleZoom = 2.0 - scaleZoom;
            }
            component.setScaleX( component.getScaleX() *scaleZoom);
            component.setScaleY( component.getScaleY() * scaleZoom);
  
            });
             
             component.setOnScrollFinished(p -> {
                 ResizeCircle_Transaction transaction = new ResizeCircle_Transaction(component,initApp,component.getRadius());
                initApp.processTransaction(transaction); 
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
