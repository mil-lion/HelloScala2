package ru.lionsoft.hello.java;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

public class Box implements Serializable {

    // Fields
    private int width;
    private int height;
    private int length;

    // Getters & Setters (Properties)

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) throw new IllegalArgumentException("width <= 0");
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0) throw new IllegalArgumentException("height <= 0");
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length <= 0) throw new IllegalArgumentException("length <= 0");
        this.length = length;
    }

    // Constructors

    /**
     * Конструктор по умолчанию
     */
    public Box() {
        this(1);
//        width = height = length = 1;
    }

    /**
     * Конструктор коробки заданного размера
     * @param width ширина коробки
     * @param height высота коробки
     * @param length длина коробки
     */
    public Box(int width, int height, int length) {
        if (width <= 0) throw new IllegalArgumentException("width <= 0");
        if (height <= 0) throw new IllegalArgumentException("height <= 0");
        if (length <= 0) throw new IllegalArgumentException("length <= 0");
        this.width = width;
        this.height = height;
        this.length = length;
    }

    /**
     * Конструктор кубической коробки
     * @param size размер коробки
     */
    public Box(int size) {
        this(size, size, size); // first line!!!
//        width = height = length = size;
    }

    /**
     * Конструктор коробки стандартного типоразмера
     * @param typeSize стандартный типоразмер коробки
     */
    public Box(char typeSize) {
        switch (typeSize) {
            case 'S' -> { width = 1; height = 2; length = 3; }
            case 'M' -> { width = 4; height = 5; length = 6; }
            case 'L' -> { width = 7; height = 8; length = 9; }
            case 'X' -> { width = 10; height = 11; length = 12; }
        }
    }

    // Fabric Method

    /**
     * Альтернативный контсруктор (фабричный метод)
     * @param width ширина коробки
     * @param height длина коробки
     * @param length высота коробки
     * @return созданная коробк
     */
    public static Box createBox(int width, int height, int length) {
        return new Box(width, height, length);
    }

    // Constants
    public static final char TYPE_SIZE_SMALL = 'S';
    public static final char TYPE_SIZE_MEDIUM = 'M';
    public static final char TYPE_SIZE_LARGE = 'L';
    public static final char TYPE_SIZE_EXTRA_LARGE = 'X';

    /**
     * Альтернативный контсруктор коробки стандартного типоразмера (фабричный метод)
     * @param typeSize стандартный типоразмер коробки
     * @return созданная коробк
     */
    public static Box createStandardBox(char typeSize) {
        return switch (typeSize) {
            case 'S' -> new Box(1, 2, 3);
            case 'M' -> new Box(4, 5, 6);
            case 'L' -> new Box(7, 8, 9);
            case 'X' -> new Box(10, 11, 12);
            default -> new Box();
        };
    }

    // Cast to String

    @Override
    public String toString() {
        return "Box{" +
                "width=" + width +
                ", height=" + height +
                ", length=" + length +
                '}';
    }

    // Equals & HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return width == box.width && height == box.height && length == box.length;
    }

    @Override
    public int hashCode() {
        // NetBeans Style
//        int hash= 3;
//        hash = hash * 31 + width;
//        hash = hash * 31 + height;
//        hash = hash * 31 + length;
//        return hash;
        // IDEA Style
//        return Objects.hash(width, height, length);
        // Scala Style
        return Stream.of(width, height, length)
                    .map(x -> x.hashCode())
                    .reduce(3, (h, x) -> h * 31 + x);
    }

    // Pseudo-Properties (Read-Only Properties) - Getters

    /**
     * Получить периметер коробки (длину ребер)
     * @return
     */
    public int getPerimeter() {
        return (width + height + length) * 4;
    }

    public int getSquareSurface() {
        return (width * height + width * length + height * length) * 2;
    }

    public int getVolume() {
        return width * height * length;
    }

    // Methods
    public void open() {
        System.out.println("Открыли коробку!");
    }

    public void close() {
        System.out.println("Закрыли коробку!");
    }

    // Static Function

    /**
     * Вычислить периметр коробки (длину ребер)
     * @param width
     * @param height
     * @param length
     * @return
     */
    public static int perimeter(int width, int height, int length) {
        return (width + height + length) * 4;
     }

    public static int squareSurface(int width, int height, int length) {
        return (width * height + width * length + height * length) * 2;
    }

    public static int volume(int width, int height, int length) {
        return width * height * length;
    }
}
