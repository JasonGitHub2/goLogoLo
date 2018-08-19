/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jasoncao
 */
public class LogoImage extends ImageView implements Cloneable{
   ImageView imageView;
    double width;
    double height;
   
    double xCoordinate;
    double yCoordinate;    
    String name;
    String type;
    int order;
    
    public LogoImage(ImageView image){
        imageView=image;
        imageView.setX(300);
        imageView.setY(300);
        xCoordinate=300;
        yCoordinate=300;
        name="DEFAULT";
        type="Image";
        order=0;
    }
    public LogoImage(ImageView image,double x,double y,String imageName){
        imageView=image;
        imageView.setX(x);
        imageView.setY(y);
        xCoordinate=x;
        yCoordinate=y;
        name=imageName;
        type="Image";
 
    }

    private LogoImage(ImageView imageView, double xCoordinate, double yCoordinate) {
         this.imageView=imageView;
        imageView.setX(xCoordinate);
        imageView.setY(yCoordinate);
        
        name="DEFAULT";
        type="Image";
        order=0;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public double getxCoordinate() {
        return imageView.getX();
    }

    public void setxCoordinate(double xCoordinate) {
        imageView.setX(xCoordinate);
    }

    public double getyCoordinate() {
        return imageView.getY();
    }

    public void setyCoordinate(double yCoordinate) {
           imageView.setY(yCoordinate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  
    public Object clone() {
         return new LogoImage(getImageView(),imageView.getX(),imageView.getY());
     }
    
}
