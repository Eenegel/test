package com.epam.tat.shape;

/**
 * @author Tatiana Ermolitskaya
 */
public class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height){
        this.height = height;
        this.width = width;
    }

    @Override
    public int getPerimeter() {
        return (width+height)*2;
    }

    @Override
    public int getArea() {
        return height*width;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Rectangle - ");
        sb.append(width+","+height)
                .append("\n\t")
                .append("Perimeter:")
                .append(getPerimeter())
                .append("\n\t")
                .append("Area:")
                .append(getArea());
        return sb.toString();
    }
}
