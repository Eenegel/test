package com.epam.tat.parse;

import com.epam.tat.shape.*;

/**
 * @author Tatiana Ermolitskaya
 */
public class Parse {

    enum Shapes { triangle, rectangle, circle, square }

    public static Shape createShape(String info) {
        Shape newShape = null;
        String[] parts = info.split(":");
        Shapes shap = Shapes.valueOf(parts[0]);
        switch (shap) {
            case triangle:
                newShape = new Triangle(Integer.valueOf(parts[1]));
                break;
            case square:
                newShape = new Square(Integer.valueOf(parts[1]));
                break;
            case rectangle:
                newShape = new Rectangle(Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
                break;
            case circle:
                newShape = new Circle(Integer.valueOf(parts[1]));
                break;
            default: break;
        }
        return newShape;
    }
}
