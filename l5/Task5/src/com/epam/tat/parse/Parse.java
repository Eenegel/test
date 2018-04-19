package com.epam.tat.parse;

import com.epam.tat.shape.*;

/**
 * @author Tatiana Ermolitskaya
 */
public class Parse {

    enum Shapes {triangle, rectangle, circle, square}
    public static Shape createShape(String info){
        Shape newShape = null;
        String[] parts = info.split(":");
        Shapes shap = Shapes.valueOf(parts[0]);
        switch(shap){
            case triangle:{
                int sideTriangle = Integer.valueOf(parts[1]);
                newShape = new Triangle(sideTriangle);
                break;
            }
            case square:{
                int sideSquare = Integer.valueOf(parts[1]);
                newShape = new Square(sideSquare);
                break;
            }
            case rectangle:{
                int width = Integer.valueOf(parts[1]);
                int heigth = Integer.valueOf(parts[2]);
                newShape = new Rectangle(width, heigth);
                break;
            }
            case circle:{
                int radius = Integer.valueOf((parts[1]));
                newShape = new Circle(radius);
                break;
            }
        }
        return newShape;
    }
}
