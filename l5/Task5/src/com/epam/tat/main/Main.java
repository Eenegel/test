package com.epam.tat.main;

import java.util.ArrayList;
import java.util.List;
import com.epam.tat.shape.Shape;
import com.epam.tat.parse.Parse;

/**
 * @author Tatiana Ermolitskaya
 */
public class Main {


    public static  void main(String[] args){
        String info = "";
        for (String s : args) {
        info = info.concat(s) + " ";
        }
        System.out.println(info + "\n");
        String[] parts = info.split(" ");

        List<Shape> shapes = new ArrayList<Shape>();
        for(String p : parts){
            shapes.add(Parse.createShape(p));
        }

        for(Shape s : shapes){
            System.out.println(s);
        }

    }



}
