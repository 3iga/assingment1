package com.uni.oop;

public class Rectangle {
    private double width;
    private double height;
    private int id;
    private static int idGen;

    public Rectangle() {
        width = 1.0;
        height = 1.0;
        id = idGen++;
    }

    public Rectangle(double width, double height) {
        this();
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        }
        else {
            throw new IllegalArgumentException("Width must be greater than 0");
        }
    }

    public void setHeight(double height) {
        if (height > 0) {
            this.height = height;
        }
        else {
            throw new IllegalArgumentException("Height must be greater than 0");
        }
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double area() {
        return height * width;
    }

    public double perimeter() {
        return 2 * (height + width);
    }

    @Override
    public String toString() {
        return String.format("Rectangle id: %d, Width: %f, Height: %f", id, width, height);
    }
}
