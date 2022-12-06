package ru.lionsoft.hello.java;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Первый класс на языке Java
 * @author Igor Morenko
 * @version 1.0
 */
public class Lab1 {

    /**
     * Главный метод приложения
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // Печать на экране
        System.out.println("Hello Java!");
        /*
         * Многострочный
         * комментарий
         */
        System.out.println(1 + 3);
        System.out.println("Hello, " + "World!");

        // ============= Value ===========
        final int x = 1 + 1; // Int
        System.out.println(x); // 2
//        x = 3; // error!
        final long x1 = 1 + 1;
        // JDK10+
        final var x2 = 1L + 1; // long

        // ============= Variable =========
        int y1 = x;
        // JDK10+
        var y = 1 + 1;
        y = 3;
        System.out.println(y * y); // 9

        // ======== Block =========
        {
            final var xx = 1 + 3;
            System.out.println(xx + y);
        }
        int res;
        {
            final var xx = 1 + 3;
            res = xx + y;
        }
        System.out.println(res);

        // ======== Function =======
        // JDK8+
        final Function<Integer, Integer> addOne = (a) -> a + 1; // lambda = anonymous class
        System.out.println(addOne.apply(1)); // 2

        Function<Integer, Integer> add = addOne; // var
        System.out.println(add.apply(2)); // 3
        add = (a) -> a + 2;
        System.out.println(add.apply(2)); // 3

        final Function2<Integer, Integer, Integer> add2 = (a, b) -> a + b; // val
        System.out.println(add2.apply(10, 20)); // 30

        final Function3<Integer, Long, Byte, Long> add3 = (a, b, c) -> a + b + c;
        System.out.println(add3.apply(123, 2L, (byte)3)); // 128

        // ======== Method =======
        System.out.println(addInt(11, 22)); // 33
        System.out.println(addThenMultiply(10, 20, 3)); // 90
        System.out.println(суммаРазделитьНаРазницу(10, 20, 3, 4)); // -30
        System.out.println("Hello " + name() + "!");
        System.out.println(getSquareString(2.5)); // 6.25 m2

        // ======== String Format =======
        System.out.println("Hello " + name() + "!");
        System.out.println(String.format("Hello %s!", name()));
        final var age = 49.5;
        System.out.printf("Name: %10s, age: %07.2f\n", name(), age);

        final var s1 = "s1 = Hello \"Igor\"\n  from Scala!\nThis is string!\n";
        System.out.println(s1);

        // JDK15+ (JDK13+ enable-preview)
//        final var s2 = """
//                          s2 = Hello "Igor"
//                            from Scala!
//                          This is string!
//                          """;
//        System.out.println(s2);

        // ========= Class ==========
        final var greeter = new Greeting("Hello, ", "!"); // new instance of class Greeting
        greeter.greet("Java Developer"); // Hello, Java Developer!

        // ============= Case Class =============
        final var point = new Point(1, 2);
        final var anotherPoint = Point.apply(1, 2); // fabric method
        final var yetAnotherPoint = Point.apply(2, 2);

        if (point.equals(anotherPoint)) { // object equals
            System.out.println(point + " and " + anotherPoint + " are the same.");
        } else {
            System.out.println(point.toString() + " and " + anotherPoint + " are different.");
        } // Point(1,2) and Point(1,2) are the same.

        if (point.equals(yetAnotherPoint)) {
            System.out.println(point + " and " + yetAnotherPoint + " are the same.");
        } else {
            System.out.println(point + " and " + yetAnotherPoint + " are different.");
        } // Point(1,2) and Point(2,2) are different.

        if (point == anotherPoint) { // references equals
            System.out.println(point + " and " + anotherPoint + " instance are the same.");
        } else {
            System.out.println(point.toString() + " and " + anotherPoint + " instance are different.");
        } // Point(1,2) and Point(1,2) instance are different.

        if (point == yetAnotherPoint) {
            System.out.println(point + " and " + yetAnotherPoint + " instance are the same.");
        } else {
            System.out.println(point + " and " + yetAnotherPoint + " instance are different.");
        } // Point(1,2) and Point(2,2) instance are different.

        // JDK15+ record
        final var point2 = new PointRec(1, 2);
        final var anotherPoint2 = PointRec.apply(1, 2); // fabric method
        final var yetAnotherPoint2 = PointRec.apply(2, 2);

        if (point2.equals(anotherPoint2)) { // object equals
            System.out.println(point2 + " and " + anotherPoint2 + " are the same.");
        } else {
            System.out.println(point2.toString() + " and " + anotherPoint2 + " are different.");
        } // PointRec[x=1, y=2] and PointRec[x=1, y=2] are the same.

        if (point2.equals(yetAnotherPoint2)) {
            System.out.println(point2 + " and " + yetAnotherPoint2 + " are the same.");
        } else {
            System.out.println(point2 + " and " + yetAnotherPoint2 + " are different.");
        } // PointRec[x=1, y=2] and PointRec[x=2, y=2] are different.

        if (point2 == anotherPoint2) { // references equals
            System.out.println(point2 + " and " + anotherPoint2 + " instance are the same.");
        } else {
            System.out.println(point2.toString() + " and " + anotherPoint2 + " instance are different.");
        } // PointRec[x=1, y=2] and PointRec[x=1, y=2] instance are different.

        if (point2 == yetAnotherPoint2) {
            System.out.println(point2 + " and " + yetAnotherPoint2 + " instance are the same.");
        } else {
            System.out.println(point2 + " and " + yetAnotherPoint2 + " instance are different.");
        } // PointRec[x=1, y=2] and PointRec[x=2, y=2] instance are different.

        // ============= Object ==============
        final var newId = IdFactory.getInstance().create();
        System.out.println(newId); // 1
        final var newerId = IdFactory.getInstance().create();
        System.out.println(newerId); // 2

//        final int id = new IdFactory().create(); // error!

        // ============= Trait =========
        Greeter greeter2 = new DefaultGreeter();
        greeter2.greet("Java Developer"); // Hello, Java Developer!

        greeter2 = new CustomizableGreeter("How are you, ", "?");
        greeter2.greet("Java Developer"); // How are you, Java Developer?

        // ============== Type =============
//        Supplier<String> ff = () -> "анонимная функция возвращающая строку";
        List<Object> list = Arrays.asList(
                "a string", // строка java.lang.String
                732,        // целое число java.lang.Integer
                'c',        // символ java.lang.Character
                true,       // логическое значение java.lang.Boolean
                (Supplier<String>) () -> "анонимная функция возвращающая строку", // Lambda
                1.5         // вещественное число (с плавающей точкой) java.lang.Double
        );
        list.forEach(element -> System.out.println(element)); // анонимная функция
        list.forEach(element -> System.out.println(element.getClass()));

        long xx = 987654321; // int -> long
        float yy = xx;  // 9.8765434E8 (заметьте, что некоторая точность теряется в этом случае.)
        char face = '©';
        int number = face; // 169 char -> int
        Long zz = (long)yy; // float -> long
        System.out.printf("xx = %d, yy = %f, face = %c, number = %d, zz = %d\n", xx, yy, face, number, zz);
//        System.out.println("xx.class = " + xx.getClass()); // error: long is not class!!! is primitive type
        System.out.println("zz.class = " + zz.getClass());

        byte b1 = 1;
        byte b2 = 2;
        byte b3 = (byte) (b1 + b2); // byte + byte = int -> byte
        int i1 = 10;
        long l1 = 123456L;
        int i2 = (int) (i1 + l1); // int + long = long -> int

        String str1 = "12345";
        String str2 = "54321";
        int str1Num = Integer.parseInt(str1);
        int str2Num = Integer.parseInt(str2);
        System.out.println(str1Num + str2Num);

    }

    // ======== Functional Interface =======
    @FunctionalInterface
    interface Function2<T1, T2, R> {
        R apply(T1 a, T2 b);
    }

    @FunctionalInterface
    interface Function3<T1, T2, T3, R> {
        R apply(T1 a, T2 b, T3 c);
    }

    // ======== Method =======
    private static int addInt(int x, int y) { return x + y; }
    private static int addThenMultiply(int x, int y, int multiplier) { return (x + y) * multiplier;}
    private static int суммаРазделитьНаРазницу(int x, int y, int a, int b) { return (x + y) / (a - b); }
    private static String name() { return System.getProperty("user.name"); }

    private static double square(double x) {return x * x; }
    private static String getSquareString(double input){
        // final var square = input * input;
        return Double.toString(square(input)) + " m\u00B2";
    }

    // ============= Class =============

    static class Greeting {
        // Fields
        private final String prefix;
        private final String suffix;
        // Constructor
        public Greeting(String prefix, String suffix) {
            this.prefix = prefix;
            this.suffix = suffix;
            System.out.println("Created Instance of class Greeting!");
        }
        // Method
        public void greet(String name) { System.out.println(prefix + name + suffix); }
    }

    // ============= Case Class =============

    static class Point {
        // Field
        private final int x;
        private final int y;
        // Constructor
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        // Fabric Method
        public static Point apply(int x, int y) {
            return new Point(x, y);
        }
        public static Point apply() {
            return new Point(0, 0);
        }
        public static Point apply(char type) {
            switch (type) {
                case 'Z': return new Point(0, 0);
                case 'M': return new Point(100, 100);
                case 'H': return new Point(50, 50);
            }
            return new Point(0, 0);
        }
        // Cast to String
        @Override
        public String toString() {
            return "Point(" + x + "," + y + ')';
        }
        // Equals & HashCode
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    // JDK15+, JDK14 (enable-preview)
    static record PointRec(int x, int y) {
        public static PointRec apply(int x, int y) { return new PointRec(x, y); }
    }

    // ============= Trait =========
    interface Greeter {
        // JDK8+
        default void greet(String name) {
            System.out.println("Hello, " + name + "!");
        }
    }

    static class DefaultGreeter implements Greeter {
    }

    static class CustomizableGreeter
            implements Greeter
    {
        // Fields
        private String prefix;
        private String suffix;
        // Constructor
        public CustomizableGreeter(String prefix, String suffix) {
            this.prefix = prefix;
            this.suffix = suffix;
        }
        // Methods
        @Override
        public void greet(String name) {
            System.out.println(prefix + name + suffix);
        }
    }

}

// ============= Object ============
class IdFactory {
    // Singleton class
    private IdFactory() {
    }

    private static class IdFactoryHolder {
        public static final IdFactory INSTANCE = new IdFactory();
    }

    public static IdFactory getInstance() {
        return IdFactoryHolder.INSTANCE;
    }

    private int counter = 0;

    public int create() {
        return ++counter;
    }
}

