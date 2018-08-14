/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GoLogoLo;
import gologolo.data.LogoData;
import gologolo.data.LogoImage;
import gologolo.data.LogoPrototype;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import jtps.jTPS_Transaction;

/**
 *
 * @author jasoncao
 */
public class AddImage_Transaction implements jTPS_Transaction
{   
    LogoPrototype imageProtoType;
    File fileImage;
    String imagePath;
    GoLogoLo app;
    ImageView imageView;
    Image image;
    LogoData data;
    FileInputStream imageStream;
    LogoImage logoImageView;
    ImageView actualImageView;
    
    public AddImage_Transaction(File imageFile,GoLogoLo appLogo,String path) throws FileNotFoundException{
        fileImage=imageFile;
        imageStream=new FileInputStream(imageFile);
        
        image=new Image(imageStream);
        imageView= new ImageView(image);
        logoImageView=new LogoImage(imageView);
        actualImageView=logoImageView.getImageView();
        imagePath=path;
        app=appLogo;
        data=(LogoData)app.getDataComponent();
        imageProtoType=new LogoPrototype();
        imageProtoType.setName("DEFAULT");
        imageProtoType.setType("Image");
        
    }
    
    
    @Override
    public void doTransaction() {
        data.addImageDataAndNode(imageProtoType,actualImageView);
    }

    @Override
    public void undoTransaction() {
       
        
        data.removeImageDataAndNode(imageProtoType,actualImageView);
      
       
       //reslect previous 
        ArrayList<LogoPrototype> componentList= data.getCurrentItemsOrder(); 
        if(componentList.size()>0)
        {           
            data.selectItem(componentList.size()-1);
            data.selectNodeInPane(componentList.size()-1);
            
        }
        
        
      
    }
    
 
    
}
