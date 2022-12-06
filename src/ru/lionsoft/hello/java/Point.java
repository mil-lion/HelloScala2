package ru.lionsoft.hello.java;

public class Point {
    // Constant
    private static final int BOUND = 100;

    // Fields
    private int x;
    private int y;

    // Constructors
    public Point() {
    }

    public Point(int x) {
        this.x = x;
    }

//    public Point(int y) {
//        this.y = y;
//    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters & Setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < -BOUND || x > BOUND) printWarning();
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < -BOUND || y > BOUND) printWarning();
        this.y = y;
    }

    // Methods
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    private void printWarning() {
        System.out.println("WARNING: Out of bounds");
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}
