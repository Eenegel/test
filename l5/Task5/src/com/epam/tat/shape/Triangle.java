package com.epam.tat.shape;

/**
 * @author Tatiana Ermolitskaya
 */
public class Triangle implements Shape {

    private int side;

    public Triangle(int side){
        this.side = side;
    }

    @Override
    public int getPerimeter() {
        return  side * 3;
    }

    @Override
    public int getArea() {
        return (int)(side*side*Math.sqrt(3))/4;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Triangle - ");
        sb.append(side)
                .append("\n\t")
                .append("Perimeter:")
                .append(getPerimeter())
                .append("\n\t")
                .append("Area:")
                .append(getArea());
        return sb.toString();
    }
}
