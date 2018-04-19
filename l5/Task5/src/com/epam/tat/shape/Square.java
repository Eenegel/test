package com.epam.tat.shape;

/**
 * @author Tatiana Ermolitskaya
 */
public class Square implements Shape {
    private int sideSquare;

    public Square(int sideSquare){
        this.sideSquare = sideSquare;
    }

    @Override
    public int getPerimeter() {
        return sideSquare*4;
    }

    @Override
    public int getArea() {
        return sideSquare*sideSquare;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Square - ");
        sb.append(sideSquare)
                .append("\n\t")
                .append("Perimeter:")
                .append(getPerimeter())
                .append("\n\t")
                .append("Area:")
                .append(getArea());
        return sb.toString();
    }
}
