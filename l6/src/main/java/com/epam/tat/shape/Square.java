package com.epam.tat.shape;

/**
 * @author Tatiana Ermolitskaya
 */
public class Square implements Shape {

    private static final int COUNT_SIDE = 4;
    private int sideSquare;

    public Square(int sideSquare) {
        this.sideSquare = sideSquare;
    }

    public int getPerimeter() {
        return sideSquare * COUNT_SIDE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;

        return sideSquare == square.sideSquare;
    }

    @Override
    public int hashCode() {
        return sideSquare;
    }

    public int getArea() {
        return sideSquare * sideSquare;
    }

    @Override
    public String toString() {
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
