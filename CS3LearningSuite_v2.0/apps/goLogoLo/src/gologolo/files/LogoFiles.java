/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.files;


import static djf.AppPropertyType.APP_EXPORT_PAGE;
import static djf.AppPropertyType.APP_PATH_EXPORT;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import gologolo.data.LogoCircle;
import gologolo.data.LogoData;
import gologolo.data.LogoPrototype;
import gologolo.data.LogoRectangle;
import gologolo.data.LogoText;
import static gologolo.goLogoLoPropertyType.LOGO_EDIT_PANE;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import static javafx.scene.paint.CycleMethod.NO_CYCLE;
import static javafx.scene.paint.CycleMethod.REFLECT;
import static javafx.scene.paint.CycleMethod.REPEAT;
import javafx.scene.paint.Paint;
import static javafx.scene.paint.Paint.valueOf;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.swing.text.html.HTML;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import properties_manager.PropertiesManager;

/**
 *
 * @author jasoncao
 */
public class LogoFiles implements AppFileComponent{
/**
 *
 * @author McKillaGorilla
 */

    // FOR JSON SAVING AND LOADING
    static final String JSON_NAME = "name";
    static final String JSON_TYPE = "type";

     static final String JSON_TEXT = "text";
      static final String JSON_DATA = "table data";
      

     //rectangle
       static final String JSON_RECTANGLE_X_COORDINATE = "rectangle x coordinate";
      static final String JSON_RECTANGLE_Y_COORDINATE = "rectangle y coordinate";
       static final String JSON_RECTANGLE_BORDER = "rectangle border color";
        static final String JSON_RECTANGLE_HEIGHT = "rectangle rectangle height";
        static final String JSON_RECTANGLE_WIDTH = "rectangle rectangle width";
        static final String JSON_RECTANGLE_STOP0= "rectangle rectangle Stop 0";
        static final String JSON_RECTANGLE_STOP1 = "rectangle rectangle Stop 1";
        static final String JSON_RECTANGLE_FOCUS_ANGLE = "rectangle rectangle focus angle";
         static final String JSON_RECTANGLE_FOCUS_DISTANCE = "rectangle rectangle focus distance";
         static final String JSON_RECTANGLE_CENTER_X = "rectangle rectangle center x";
       static final String JSON_RECTANGLE_CENTER_Y = "rectangle rectangle center y";
       static final String JSON_RECTANGLE_RADIUS = "rectangle rectangle radius";
         static final String JSON_RECTANGLE_CYCLE_METHOD = "rectangle rectangle cycle method";
           static final String JSON_RECTANGLE_STROKE_WIDTH = "rectangle rectangle stroke width";
            static final String JSON_RECTANGLE_ARC_HEIGHT = "rectangle rectangle arc height";
         static final String JSON_RECTANGLE_ARC_WIDTH = "rectangle rectangle arc width";
            
           
         
        
        
        
        
        
   //text

      static final String JSON_TEXT_X_COORDINATE = "text x coordinate";
      static final String JSON_TEXT_Y_COORDINATE = "text y coordinate";
      static final String JSON_TEXT_FONT_NAME = "text font name";
       static final String JSON_TEXT_FONT_SIZE = "text font size";
         static final String JSON_TEXT_FONT_COLOR = "text font color";
      static final String JSON_TEXT_IS_BOLD = "text font is bold";
      static final String JSON_TEXT_IS_ITALICIZED = "text font is italicized";
      static final String JSON_TEXT_TEXT_INPUT = "text text input";

    //circle
       static final String JSON_CIRCLE_X_COORDINATE = "circle x coordinate";
     static final String JSON_CIRCLE_Y_COORDINATE = "circle y coordinate";
      static final String JSON_CIRCLE_RADIUS = "circle radius";
     static final String JSON_CIRCLE_STROKE_COLOR = "circle border color";
     static final String JSON_CIRCLE_STROKE_WIDTH = "circle border width";
     static final String JSON_CIRCLE_FOCUS_ANGLE = "circle focus angle";
     static final String JSON_CIRCLE_FOCUS_DISTANCE = "circle focus distance";
     static final String JSON_CIRCLE_GRADIENT_CENTER_X = "circle gradient x";
      static final String JSON_CIRCLE_GRADIENT_CENTER_Y = "circle gradient y";
       static final String JSON_CIRCLE_GRADIENT_RADIUS= "circle gradient radius";
     static final String JSON_CIRCLE_CYCLE_METHOD= "circle cycle method";
      static final String JSON_CIRCLE_STOP_0 = "circle stop 0";
       static final String JSON_CIRCLE_STOP_1 = "circle stop 1";
     
   
       
     
    /**
     * This method is for saving user work.
     * 
     * @param data The data management component for this application.
     * 
     * @param filePath Path (including file name/extension) to where
     * to save the data to.
     * 
     * @throws IOException Thrown should there be an error writing 
     * out data to the file.
     */
@Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
	LogoData toDoData = (LogoData)data;
	
        
	// NOW BUILD THE JSON ARRAY FOR THE LIST
	JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
         Iterator<Node> nodeIt = toDoData.nodeIterator();
        Iterator<LogoPrototype> dataIt = toDoData.componentsIterator();
	
        while (dataIt.hasNext()) {	
            LogoPrototype item = dataIt.next();
            Node nodeData = nodeIt.next();
            
            if(item.getType().equalsIgnoreCase("Rectangle")){
	    JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_NAME, item.getName())
		    .add(JSON_TYPE, item.getType())
                    .add(JSON_RECTANGLE_BORDER, ((LogoRectangle)nodeData).getStroke().toString())
                     .add(JSON_RECTANGLE_HEIGHT, ((LogoRectangle)nodeData).getHeight())
                     .add(JSON_RECTANGLE_WIDTH, ((LogoRectangle)nodeData).getWidth())
                     .add(JSON_RECTANGLE_STOP0, ((LogoRectangle)nodeData).getStop1().getColor().toString())
                     .add(JSON_RECTANGLE_STOP1, ((LogoRectangle)nodeData).getStop2().getColor().toString())
                     .add(JSON_RECTANGLE_FOCUS_ANGLE, ((LogoRectangle)nodeData).getFocusAngle())
                     .add(JSON_RECTANGLE_FOCUS_DISTANCE, ((LogoRectangle)nodeData).getFocusDistance())
                     .add(JSON_RECTANGLE_CENTER_X, ((LogoRectangle)nodeData).getCenterX())
                     .add(JSON_RECTANGLE_CENTER_Y, ((LogoRectangle)nodeData).getCenterY())
                     .add(JSON_RECTANGLE_RADIUS, ((LogoRectangle)nodeData).getRadius())
                     .add(JSON_RECTANGLE_CYCLE_METHOD, ((LogoRectangle)nodeData).getCycleMethod().toString())
                     .add(JSON_RECTANGLE_STROKE_WIDTH, ((LogoRectangle)nodeData).getStrokeWidth())
                    .add(JSON_RECTANGLE_ARC_HEIGHT, ((LogoRectangle)nodeData).getArcHeight())
                    .add(JSON_RECTANGLE_ARC_WIDTH, ((LogoRectangle)nodeData).getArcWidth())
                     .add(JSON_RECTANGLE_X_COORDINATE, ((LogoRectangle)nodeData).getX())
                     .add(JSON_RECTANGLE_Y_COORDINATE, ((LogoRectangle)nodeData).getY()).build();
 
	    arrayBuilder.add(itemJson);
        }
            
            else if(item.getType().equalsIgnoreCase("Text")){
	    JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_NAME, item.getName())
		    .add(JSON_TYPE, item.getType())
		    .add(JSON_TEXT, item.getText())
                    
                    
                     .add(JSON_TEXT_TEXT_INPUT, ((LogoText)nodeData).getText())
                     .add(JSON_TEXT_IS_ITALICIZED, ((LogoText)nodeData).getIsItalicized())
                     .add(JSON_TEXT_IS_BOLD ,  ((LogoText)nodeData).getIsBold())
                     .add(JSON_TEXT_FONT_COLOR, ((LogoText)nodeData).getFontColor().toString())
                      .add(JSON_TEXT_FONT_SIZE, ((LogoText)nodeData).getFont().getSize())
                     .add(JSON_TEXT_FONT_NAME, ((LogoText)nodeData).getFont().getName())
                     .add(JSON_TEXT_X_COORDINATE, ((LogoText)nodeData).getX())                
                     .add(JSON_TEXT_Y_COORDINATE, ((LogoText)nodeData).getY()).build();
                        
	    arrayBuilder.add(itemJson);
        }
          else if(item.getType().equalsIgnoreCase("Circle")){
	    JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_NAME, item.getName())
		    .add(JSON_TYPE, item.getType())
		  
                    
                     .add(JSON_CIRCLE_X_COORDINATE, ((LogoCircle)nodeData).getCenterX())
                      .add(JSON_CIRCLE_Y_COORDINATE, ((LogoCircle)nodeData).getCenterY())
                      .add(JSON_CIRCLE_RADIUS, ((LogoCircle)nodeData).getRadius())
                      .add(JSON_CIRCLE_STROKE_COLOR, ((LogoCircle)nodeData).getStroke().toString())
                      .add(JSON_CIRCLE_STROKE_WIDTH, ((LogoCircle)nodeData).getStrokeWidth())
                      .add(JSON_CIRCLE_FOCUS_ANGLE, ((LogoCircle)nodeData).getFocusAngle())
                      .add(JSON_CIRCLE_FOCUS_DISTANCE, ((LogoCircle)nodeData).getFocusDistance())
                      .add(JSON_CIRCLE_GRADIENT_CENTER_X, ((LogoCircle)nodeData).getGradientCenterX())
                      .add(JSON_CIRCLE_GRADIENT_CENTER_Y, ((LogoCircle)nodeData).getGradientCenterY())
                      .add(JSON_CIRCLE_GRADIENT_RADIUS, ((LogoCircle)nodeData).getGradientRadius())
                      .add(JSON_CIRCLE_CYCLE_METHOD, ((LogoCircle)nodeData).getCycleMethod().toString())
                      .add(JSON_CIRCLE_STOP_0, ((LogoCircle)nodeData).getStop0().getColor().toString())
                      .add(JSON_CIRCLE_STOP_1, ((LogoCircle)nodeData).getStop1().getColor().toString()).build();

                        arrayBuilder.add(itemJson);
        }
            
//               else if(item.getType().equalsIgnoreCase("Image")){
//                    JsonObject itemJson = Json.createObjectBuilder()
//		    .add(JSON_NAME, item.getName())
//		    .add(JSON_TYPE, item.getType())
//		  
//                 
//                      .add(JSON_CIRCLE_STOP_0, ((LogoCircle)nodeData).getStop0().toString())
//                      .add(JSON_CIRCLE_STOP_1, ((LogoCircle)nodeData).getStop1().toString()).build();
//
//                        arrayBuilder.add(itemJson);
//        }
	}
    
        
	JsonArray itemsArray = arrayBuilder.build();
	JsonObject toDoDataJSO = Json.createObjectBuilder()

		.add(JSON_DATA, itemsArray)
		.build();
        
        

        
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(toDoDataJSO);

	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(toDoDataJSO);

	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
        
    }
    
    
    /**
     * This method loads data from a JSON formatted file into the data 
     * management component and then forces the updating of the workspace
     * such that the user may edit the data.
     * 
     * @param data Data management component where we'll load the file into.
     * 
     * @param filePath Path (including file name/extension) to where
     * to load the data from.
     * 
     * @throws IOException Thrown should there be an error
     * reading
     * in data from the file.
     */
    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
	LogoData toDoData = (LogoData)data;
	toDoData.reset();
	
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
	
	
	// AND NOW LOAD ALL THE ITEMS
	JsonArray jsonItemArray = json.getJsonArray(JSON_DATA);
	for (int i = 0; i < jsonItemArray.size(); i++) {
	    JsonObject jsonItem = jsonItemArray.getJsonObject(i);
	     if(jsonItem.getString(JSON_TYPE).equalsIgnoreCase("Text")){ 
           
                 LogoPrototype item = loadTableData(jsonItem);
	    
                 LogoText node=loadPaneTextNode(jsonItem,toDoData);
                 toDoData.addTextDataAndNode(item,(LogoText)node);
             }
             
             
             else  if(jsonItem.getString(JSON_TYPE).equalsIgnoreCase("Rectangle")){
                 LogoPrototype item = loadTableData(jsonItem);
	    
                 LogoRectangle node=loadPaneRectangleNode(jsonItem,toDoData);
                  toDoData.addRectangleDataAndNode(item,(LogoRectangle)node);
             }
              
             else  if(jsonItem.getString(JSON_TYPE).equalsIgnoreCase("Circle")){
                 LogoPrototype item = loadTableData(jsonItem);
	    
                 LogoCircle node=loadPaneCircleNode(jsonItem,toDoData);
                  toDoData.addCircleDataAndNode(item,(LogoCircle)node);
             }
            
	}
    }
    
    public double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
    
    
    public LogoText loadPaneTextNode(JsonObject jsonItem,LogoData data) {
         LogoData toDoData = (LogoData)data;
              
             
             int x=jsonItem.getInt(JSON_TEXT_X_COORDINATE);        
             int y=jsonItem.getInt(JSON_TEXT_Y_COORDINATE);
             String input=jsonItem.getString(JSON_TEXT_TEXT_INPUT);
              Boolean isItalicized=jsonItem.getBoolean(JSON_TEXT_IS_ITALICIZED);
              Boolean isBold=jsonItem.getBoolean(JSON_TEXT_IS_BOLD);  
              String fontColor=jsonItem.getString(JSON_TEXT_FONT_COLOR);
              int fontSize=jsonItem.getInt(JSON_TEXT_FONT_SIZE);
              String fontName=jsonItem.getString(JSON_TEXT_FONT_NAME);
                Font font=new Font(fontName,fontSize);
              if(isBold){
                     
                    font=Font.font(fontName,FontWeight.BOLD,fontSize);
              }
              else  if(isItalicized){
                       
                    font=Font.font(fontName,FontPosture.ITALIC,fontSize);
              }
              else{
                   font=new Font(fontName,fontSize);
              }
              Color colorText=(Color) valueOf(fontColor);
              
             LogoText textObject=new LogoText(input,font,fontName,fontSize,x,y,colorText,isBold,isItalicized);
        
           
             return textObject;
    }
    
    public LogoRectangle loadPaneRectangleNode(JsonObject jsonItem,LogoData data) {
         LogoData toDoData = (LogoData)data;          
             
             double x=jsonItem.getInt(JSON_RECTANGLE_X_COORDINATE);    
             double y=jsonItem.getInt(JSON_RECTANGLE_Y_COORDINATE);
             double width=jsonItem.getInt(JSON_RECTANGLE_WIDTH);
             double height=jsonItem.getInt(JSON_RECTANGLE_HEIGHT);
             String borderColor=jsonItem.getString(JSON_RECTANGLE_BORDER);
              String stop0=jsonItem.getString(JSON_RECTANGLE_STOP0);
              String stop1=jsonItem.getString(JSON_RECTANGLE_STOP1);
              double focusAngle=jsonItem.getInt(JSON_RECTANGLE_FOCUS_ANGLE); 
               double focusDistance=jsonItem.getInt(JSON_RECTANGLE_FOCUS_DISTANCE); 
              double centerX=jsonItem.getInt(JSON_RECTANGLE_CENTER_X); 
              double centerY=jsonItem.getInt(JSON_RECTANGLE_CENTER_Y); 
               double radius=jsonItem.getInt(JSON_RECTANGLE_RADIUS); 
                String cycleMethod=jsonItem.getString(JSON_RECTANGLE_CYCLE_METHOD);
               double strokeWidth=jsonItem.getInt(JSON_RECTANGLE_STROKE_WIDTH); 
              double arcHeight=jsonItem.getInt(JSON_RECTANGLE_ARC_HEIGHT); 
               double arcWidth=jsonItem.getInt(JSON_RECTANGLE_ARC_WIDTH); 
               Color border=(Color) valueOf(borderColor);
               Color stop0Color=(Color) valueOf(stop0);
                 Color stop1Color=(Color) valueOf(stop1);
                 Stop stopZero=new Stop(0,stop0Color);
                 Stop stopOne=new Stop(1,stop1Color);
        CycleMethod method=NO_CYCLE;
        if(cycleMethod.equals("NO_CYCLE")){
            method=NO_CYCLE;
        }
        else if(cycleMethod.equals("REFLECT")){
            method=REFLECT;
        }
        else if(cycleMethod.equals("REPEAT")){
            method=REPEAT;
        }
       Color strokeColor=(Color) valueOf(borderColor);  
                 
      LogoRectangle rectangle=new LogoRectangle(width,height,border,x,y,stopZero,stopOne,focusAngle,focusDistance,centerX,centerY,radius,true,method,strokeWidth,arcHeight,arcWidth,strokeColor);
            
             return rectangle;
    }
      

        public LogoCircle loadPaneCircleNode(JsonObject jsonItem,LogoData data) {
            
            LogoData toDoData = (LogoData)data;
         
             
             double x=jsonItem.getInt(JSON_CIRCLE_X_COORDINATE);
             double y=jsonItem.getInt(JSON_CIRCLE_Y_COORDINATE);
             double radius=jsonItem.getInt(JSON_CIRCLE_RADIUS);
             String strokeColor=jsonItem.getString(JSON_CIRCLE_STROKE_COLOR);
             double strokeWidth=jsonItem.getInt(JSON_CIRCLE_STROKE_WIDTH);
             double focusAngle=jsonItem.getInt(JSON_CIRCLE_FOCUS_ANGLE);
             double focusDistance=jsonItem.getInt(JSON_CIRCLE_FOCUS_DISTANCE);
             double gradientX=jsonItem.getInt(JSON_CIRCLE_GRADIENT_CENTER_X);
             double gradientY=jsonItem.getInt(JSON_CIRCLE_GRADIENT_CENTER_Y);
             double gradientRadius=jsonItem.getInt(JSON_CIRCLE_GRADIENT_RADIUS);
             String cycleMethod=jsonItem.getString(JSON_CIRCLE_CYCLE_METHOD);
             String stop0=jsonItem.getString(JSON_CIRCLE_STOP_0);
             String stop1=jsonItem.getString(JSON_CIRCLE_STOP_1);
                  
             Color border=(Color) valueOf(strokeColor);
               Color stop0Color=(Color) valueOf(stop0);
                 Color stop1Color=(Color) valueOf(stop1);
                 Stop stopZero=new Stop(0,stop0Color);
                 Stop stopOne=new Stop(1,stop1Color);
        CycleMethod method=NO_CYCLE;
        if(cycleMethod.equals("NO_CYCLE")){
            method=NO_CYCLE;
        }
        else if(cycleMethod.equals("REFLECT")){
            method=REFLECT;
        }
        else if(cycleMethod.equals("REPEAT")){
            method=REPEAT;
        }
       
        LogoCircle circle=new LogoCircle(radius,border,x,y,strokeWidth,focusAngle,focusDistance,gradientX,gradientY,gradientRadius,method,stopZero,stopOne);
       
         return circle;
         }
        
        
    public LogoPrototype loadTableData(JsonObject jsonItem) {
	// GET THE DATA
        if(jsonItem.getString(JSON_TYPE).equalsIgnoreCase("Text")){
            
	String name = jsonItem.getString(JSON_NAME);
	String type = jsonItem.getString(JSON_TYPE);

        String text=jsonItem.getString(JSON_TEXT);
        
	// THEN USE THE DATA TO BUILD AN ITEM
        LogoPrototype item = new LogoPrototype(name, type,text);
        // ALL DONE, RETURN IT
	return item;
        }
        
        else {
//            if(jsonItem.getString(JSON_TYPE).equals("Rectangle")){
            
	String name = jsonItem.getString(JSON_NAME);
	String type = jsonItem.getString(JSON_TYPE);

       
        
	// THEN USE THE DATA TO BUILD AN ITEM
        LogoPrototype item = new LogoPrototype(name, type);
        // ALL DONE, RETURN ITT
	return item;
        }
      
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    
    
  
        
    /**
     * This method would be used to export data to another format,
     * which we're not doing in this assignment.
     */
    @Override
    public void exportData(AppDataComponent data, String savedFileName) throws IOException {
         String fileName = savedFileName.substring(0, savedFileName.indexOf("."));
         String fileToExport = fileName + ".png";
        try {
             LogoData logoData = (LogoData)data;
            PropertiesManager props = PropertiesManager.getPropertiesManager();          
            String exportDirPath = props.getProperty(APP_PATH_EXPORT) + fileName +".png";      
              File exportedFile=new File(exportDirPath);
              Pane editPane=(Pane)logoData.getApp().getGUIModule().getGUINode(LOGO_EDIT_PANE);
              WritableImage image = editPane.snapshot(new SnapshotParameters(), null);


              try {
              ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", exportedFile);
              } catch (IOException e) {
       
            }
            
           
           
           
        }
        catch(Exception e) {
            throw new IOException("Error loading " + savedFileName);
        }
    }
            

           
       
            
  
    

    private void saveDocument(Document doc, String outputFilePath)
            throws TransformerException, TransformerConfigurationException {
//        TransformerFactory factory = TransformerFactory.newInstance();
//        Transformer transformer = factory.newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
//        Result result = new StreamResult(new File(outputFilePath));
//        Source source = new DOMSource(doc);
//        transformer.transform(source, result);
    }

    /**
     * This method is provided to satisfy the compiler, but it
     * is not used by this application.
     */
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        
    }
}
