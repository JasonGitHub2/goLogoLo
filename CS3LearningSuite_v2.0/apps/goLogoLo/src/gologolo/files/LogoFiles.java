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
import javafx.scene.paint.Paint;
import static javafx.scene.paint.Paint.valueOf;
import javafx.scene.shape.Rectangle;
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
      
      
      static final String JSON_RECTANGLE_X_COORDINATE = "rectangle x coordinate";
      static final String JSON_RECTANGLE_Y_COORDINATE = "rectangle y coordinate";
       static final String JSON_RECTANGLE_BORDER = "rectangle border color";
        static final String JSON_RECTANGLE_FILL_COLOR = "rectangle fill color";
        static final String JSON_RECTANGLE_HEIGHT = "rectangle rectangle height";
        static final String JSON_RECTANGLE_WIDTH = "rectangle rectangle width";
        static final String JSON_TEXT_X_COORDINATE = "text x coordinate";
      static final String JSON_TEXT_Y_COORDINATE = "text y coordinate";
     
      static final String JSON_TEXT_TEXT_INPUT = "text text input";

    
    
   
    
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
            
            if(nodeData instanceof Rectangle){
	    JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_NAME, item.getName())
		    .add(JSON_TYPE, item.getType())

                    
                     .add(JSON_RECTANGLE_FILL_COLOR, ((Rectangle)nodeData).getFill().toString())
                     .add(JSON_RECTANGLE_BORDER, ((Rectangle)nodeData).getStroke().toString())
                     .add(JSON_RECTANGLE_HEIGHT, ((Rectangle)nodeData).getHeight())
                     .add(JSON_RECTANGLE_WIDTH, ((Rectangle)nodeData).getWidth())
                     .add(JSON_RECTANGLE_X_COORDINATE, ((Rectangle)nodeData).getX())
                     .add(JSON_RECTANGLE_Y_COORDINATE, ((Rectangle)nodeData).getY()).build();
        
	    arrayBuilder.add(itemJson);
        }
            
            else if(nodeData instanceof Text){
	    JsonObject itemJson = Json.createObjectBuilder()
		    .add(JSON_NAME, item.getName())
		    .add(JSON_TYPE, item.getType())
		    .add(JSON_TEXT, item.getText())
                    
                     
                     .add(JSON_TEXT_TEXT_INPUT, ((Text)nodeData).getText())
                     .add(JSON_TEXT_X_COORDINATE, ((Text)nodeData).getX())                
                     .add(JSON_TEXT_Y_COORDINATE, ((Text)nodeData).getY()).build();
        
	    arrayBuilder.add(itemJson);
        }
        
               
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
	    
                 Node node=loadPaneNode(jsonItem,toDoData);
                 toDoData.addTextDataAndNode(item,(LogoText)node);
             }
             
             else  if(jsonItem.getString(JSON_TYPE).equalsIgnoreCase("Rectangle")){
                 LogoPrototype item = loadTableData(jsonItem);
	    
                 Node node=loadPaneNode(jsonItem,toDoData);
                  toDoData.addRectangleDataAndNode(item,(LogoRectangle)node);
             }
            
	}
    }
    
    public double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
    
    
     public Node loadPaneNode(JsonObject jsonItem,LogoData data) {
         LogoData toDoData = (LogoData)data;
          if(jsonItem.getString(JSON_TYPE).equalsIgnoreCase("Text")){
              
             
             int x=jsonItem.getInt(JSON_TEXT_X_COORDINATE);
             
             int y=jsonItem.getInt(JSON_TEXT_Y_COORDINATE);
             String input=jsonItem.getString(JSON_TEXT_TEXT_INPUT);
             
             LogoText textObject=new LogoText(x,y,input);
             Text text=textObject.getTextNode();
           
             return text;
         }
         else{
             int x=jsonItem.getInt(JSON_RECTANGLE_X_COORDINATE);
             
             int y=jsonItem.getInt(JSON_RECTANGLE_Y_COORDINATE);
              String border=jsonItem.getString(JSON_RECTANGLE_BORDER);
               String fill=jsonItem.getString(JSON_RECTANGLE_FILL_COLOR);
               int height=jsonItem.getInt(JSON_RECTANGLE_HEIGHT);
                int width=jsonItem.getInt(JSON_RECTANGLE_WIDTH);
                Paint fillColor=valueOf(fill);
                 Paint borderColor=valueOf(border);
                
                LogoRectangle rectangleObject=new LogoRectangle(width,height,fillColor,borderColor,x,y);
                Rectangle rectangle=rectangleObject.getRectangle();
            
             
             return rectangle;
         }
         
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
