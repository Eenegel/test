package com.epam.tat.shape;

/**
 * @author Tatiana Ermolitskaya
 */
public class Triangle implements Shape {

    private static final double DEEGREES = 60.0;
    private static final int COUNT_SIDE = 3;
    private int side;

    public Triangle(int side) {
        this.side = side;
    }

    public int getPerimeter() {
        return  side * COUNT_SIDE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Triangle triangle = (Triangle) o;

        return side == triangle.side;
    }

    @Override
    public int hashCode() {
        return side;
    }

    public int getArea() {
        double halfSide = side / 2;
        return (int) (side * halfSide * Math.sin(Math.toRadians(DEEGREES)));
    }

    @Override
    public String toString() {
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
