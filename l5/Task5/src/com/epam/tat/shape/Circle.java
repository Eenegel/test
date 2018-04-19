package com.epam.tat.shape;

/**
 * @author Tatiana Ermolitskaya
 */
public class Circle implements Shape {
    private int radius;

    public Circle(int radius){
        this.radius = radius;
    }

    @Override
    public int getPerimeter() {
        return (int)(2*Math.PI*radius);
    }

    @Override
    public int getArea() {
        return (int)(Math.PI*radius*radius);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Circle - ");
        sb.append(radius)
                .append("\n\t")
                .append("Perimeter:")
                .append(getPerimeter())
                .append("\n\t")
                .append("Area:")
                .append(getArea());
        return sb.toString();
    }
}
