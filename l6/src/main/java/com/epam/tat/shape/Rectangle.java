package com.epam.tat.shape;

/**
 * @author Tatiana Ermolitskaya
 */
public class Rectangle implements Shape {
    private static final int MULTIPLIER = 31;
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int getPerimeter() {
        return (width + height) * 2;
    }

    public int getArea() {
        return height * width;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Rectangle - ");
        sb.append(width + "," + height)
                .append("\n\t")
                .append("Perimeter:")
                .append(getPerimeter())
                .append("\n\t")
                .append("Area:")
                .append(getArea());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) o;

        if (width != rectangle.width) {
            return false;
        }
        return height == rectangle.height;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = MULTIPLIER * result + height;
        return result;
    }
}
