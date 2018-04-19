package com.epam.tat.shape;

import java.util.Comparator;

/**
 * @author Tatiana Ermolitskaya
 */
public class ShapeComparator implements Comparator<Shape> {
    public int compare(Shape o1, Shape o2) {
        if (o1.getArea() > o2.getArea()) {
            return -1;
        } else if (o1.getArea() < o2.getArea()) {
            return 1;
        } else {
            return 0;
        }
    }
}
