package ru.lionsoft.hello.java;

public class Lab3 {

    public static void main(String[] args) {

        var p1 = new Point(2, 3);
        var p2 = new Point(-5, 10);
        System.out.println(p1.getX()); // 2
        System.out.println(p1);   // Point(2, 3)
        System.out.println(p2.toString()); // Point(-5, 10)

        p1.move(1, 2);
        System.out.println(p1); // Point(3, 5)
        System.out.println(p2); // Point(-5, 10)

        p2.setX(6);
        System.out.println(p2); // Point(6, 10)

        var p3 = new Point();
        var p4 = new Point(1);
        System.out.println(p3); // Point(0, 0)
        System.out.println(p4); // Point(1, 0)

        // В Java реализовать не возможно
//        val p5 = new Point(_y = 2)
//        println(p5) // Point(0, 2)

        p4.setX(200);  // WARNING: Out of bounds
        p4.setY(-128); // WARNING: Out of bounds
    }
}
