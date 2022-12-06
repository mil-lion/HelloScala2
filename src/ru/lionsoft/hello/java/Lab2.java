package ru.lionsoft.hello.java;

import java.math.BigInteger;
import java.util.Random;
import java.util.stream.IntStream;

public class Lab2 {
    public static void main(String[] args) {
        // =========== BigInt ===========
        BigInteger b1 = new BigInteger("123456789");
        BigInteger b2 = new BigInteger("9876543210");
        BigInteger b3 = b1.add(b2);
        System.out.println(b3);

        // ============== ветвления =============
        if (!b1.equals(b2)) System.out.println("b1 != b2"); // b1 == b2 <-> b1.equals(b2)

        if (b1.equals(b2))
            System.out.println("b1 == b2");
        else
            System.out.println("b1 != b2");

        System.out.println(b1.equals(b2) ? "b1 == b2" : "b1 != b2"); // ternary operator

        // ============ switch ================

        Random r = new Random();
        int x = r.nextInt(5); // random integer 0..5
        System.out.println(x);

        if (x == 0) System.out.println("zero");
        else if (x == 1) System.out.println("one");
        else if (x == 2 || x == 3) System.out.println("two or three");
        else if (x == 4) System.out.println("four");
        else System.out.println("more four");

        switch (x) { // fast goto
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
                break;
            case 2:
            case 3:
                System.out.println("two or three");
                break;
            case 4:
                System.out.println("four");
                break;
            default:
                System.out.println("more four");
        }
        // JDK12+(enable-preview), JDK14+
        // rule switch
        switch (x) {
            case 0 -> System.out.println("zero");
            case 1 -> System.out.println("one");
            case 2, 3 -> System.out.println("two or three");
            case 4 -> System.out.println("four");
            default -> System.out.println("more four");
        }
        // expression switch
        System.out.println(switch (x) {
                case 0 -> "zero";
                case 1 -> "one";
                case 2, 3 -> "two or three";
                case 4 -> "four";
                default -> "more four";
            }
        );

        // =============== loop ==============
        System.out.println("##### while #####");
        var i = 0;
        while (i < 5)
            System.out.println(i++); // ++i - pre-increment; i++ - post-increment

        System.out.println("##### do-while #####");
//        i = 0;
        do {
            System.out.println(i);
            i--;
        } while (i > 0);

        System.out.println("##### for #####");
        for (int j = 0; j < 5; j++) {
            System.out.println(j);
        }
        for (int m = 0, n = 5; m < 5; m++, n -= 2) {
            System.out.println(m + ", " + n);
        }
        for (int m = 0; m < 5; m++) {
            for (int n = 5; n > -3; n -= 2) {
                System.out.println(m + ", " + n);
            }
        }
        System.out.println("##### for-each #####");
        int[] array = new int[] { 1, 5, 3, 7, 8 };
//        for (i = 0; i < array.length; i++) {
//            System.out.println(array[i]);
//        }
        // JDK5++
        for (int z : array) {
            System.out.println(z);
        }
        // JDK8++ Stream Library (Scala Style)
        IntStream.of(array).forEach(z -> System.out.println(z)); // lambda
        IntStream.of(array).forEach(System.out::println); // reference on method

        // ############### operation #############
        byte a = 127; // int literal
        byte b = 2;
        byte c = (byte)(a + b);     // byte + byte = int cast to byte
        long l = 1_234_567_890_123_456L; // long literal
        int ii = (int)(c + l);      // byte + long = long cast to int
        float f = 0.5f;             // float literal
        double d = 1.33;            // double literal
        float f2 = l + f;           // long + float = float
        double d2 = l + d;          // long + double = double
        float f3 = (float)(l + d);  // long + double = double cast to float

        System.out.println(10);   // 10 dec
        System.out.println(010);  // 8  oct
        System.out.println(0x10); // 16 hex
        System.out.println(0b10); // 2  bin
        System.out.println(0b0100_1011); // 2  bin

        System.out.println(Integer.parseInt("010")); // 10 dec
        System.out.println(Integer.parseInt("010", 2)); // 2 bin
        System.out.println(Integer.parseInt("010", 16)); // 16 hex
        System.out.println(Integer.parseInt("010", 8)); // 8 oct

        // ################## match ##############
        Object obj = (Math.random() > 0.5) ? 111 : "one"; // auto boxing int to Integer JDK5+
        System.out.println("obj = " + obj + ", obj.class = " + obj.getClass());

        // Java Style
        if (obj instanceof String) {
            final String str = (String) obj; // Object cast to String
            System.out.println("str = " + str + " is String, str.length = " + str.length());
        } else if (obj instanceof Integer) {
            final Integer num = (Integer) obj; // Object cast to Integer
            System.out.println("num = " + num + " is Integer, hex = 0x" + Integer.toHexString(num));
        } else {
            System.out.println("???");
        }
        // Scala Style
        // JDK15+ (enabele-preview)
        if (obj instanceof String str) {
            System.out.println("str = " + str + " is String, str.length = " + str.length());
        } else if (obj instanceof Integer num) {
            System.out.println("num = " + num + " is Integer, hex = 0x" + Integer.toHexString(num));
        } else {
            System.out.println("???");
        }

        // ############# Exception ##############
        try {
            int m = (int)(Math.random() + 0.5);
            int n = 5;
            int k = n / m;
            System.out.println(k);
            Integer nnn = 555; //null
            System.out.println(nnn.hashCode());
//          if (n == 5) throw new IllegalArgumentException("n != 5");
        } catch(ArithmeticException ex) {
            System.err.println("Ошибка: Деление на ноль (" + ex.getMessage() + ")");
//        } catch (NullPointerException ex) {
//            System.err.println("Ошибка: " + ex.getMessage());
//        } catch (IllegalArgumentException ex) {
//            System.err.println("Ошибка: " + ex.getMessage());
        } catch (NullPointerException | IllegalArgumentException ex) { // JDK7+
            System.err.println("Ошибка: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Ошибка: " + ex.getMessage());
        } finally {
            System.out.println("Ok");
        }
        System.out.println("Exit!");
    }
}
