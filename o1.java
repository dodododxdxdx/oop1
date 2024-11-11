import java.util.Arrays;
public class Main{
    // Тестирование программы
    public static void main(String[] args) {
        Fraction[] fractions = {
                new Fraction(1, 1, 2),  // 1 1/2
                new Fraction(2, 1, 4),  // 2 1/4
                new Fraction(1, 3, 4),  // 1 3/4
                new Fraction(0, 2, 3)   // 0 2/3
        };

        // Сортировка массива дробей
        Arrays.sort(fractions);

        // Вывод отсортированного массива
        System.out.println("Дроби в порядке возрастания:");
        for (Fraction fraction : fractions) {
            System.out.println(fraction);
        }

        // Демонстрация операций
        Fraction fraction1 = new Fraction(1, 1, 2);
        Fraction fraction2 = new Fraction(2, 1, 4);

        System.out.println("Сложение: " + fraction1.add(fraction2));
        System.out.println("Вычитание: " + fraction1.subtract(fraction2));
        System.out.println("Умножение: " + fraction1.multiply(fraction2));
    }
}
public class Fraction implements Comparable<Fraction> {
    private int integerPart; // целая часть
    private int fractionalPart; // дробная часть
    private int denominator; // знаменатель дробной части

    // Конструктор
    public Fraction(int integerPart, int fractionalPart, int denominator) {
        this.integerPart = integerPart;
        this.fractionalPart = fractionalPart;
        this.denominator = denominator;
    }

    // Получение целой части
    public int getIntegerPart() {
        return integerPart;
    }

    // Получение дробной части
    public int getFractionalPart() {
        return fractionalPart;
    }

    // Получение знаменателя
    public int getDenominator() {
        return denominator;
    }

    // Преобразование дробного числа в вещественное для удобства операций
    private double toDecimal() {
        return integerPart + (double) fractionalPart / denominator;
    }

    // Сложение дробей
    public Fraction add(Fraction other) {
        int commonDenominator = this.denominator * other.denominator;
        int newIntegerPart = this.integerPart + other.integerPart;
        int newFractionalPart = (this.fractionalPart * other.denominator) + (other.fractionalPart * this.denominator);

        // Преобразуем дробь, если дробная часть больше знаменателя
        newIntegerPart += newFractionalPart / commonDenominator;
        newFractionalPart = newFractionalPart % commonDenominator;

        return new Fraction(newIntegerPart, newFractionalPart, commonDenominator);
    }

    // Вычитание дробей
    public Fraction subtract(Fraction other) {
        int commonDenominator = this.denominator * other.denominator;
        int newIntegerPart = this.integerPart - other.integerPart;
        int newFractionalPart = (this.fractionalPart * other.denominator) - (other.fractionalPart * this.denominator);

        // Преобразуем дробь, если дробная часть становится отрицательной
        if (newFractionalPart < 0) {
            newIntegerPart -= 1;
            newFractionalPart += commonDenominator;
        }

        return new Fraction(newIntegerPart, newFractionalPart, commonDenominator);
    }

    // Умножение дробей
    public Fraction multiply(Fraction other) {
        int newIntegerPart = this.integerPart * other.integerPart;
        int newFractionalPart = this.fractionalPart * other.fractionalPart;
        int newDenominator = this.denominator * other.denominator;

        // Преобразуем дробь, если дробная часть больше знаменателя
        newIntegerPart += newFractionalPart / newDenominator;
        newFractionalPart = newFractionalPart % newDenominator;

        return new Fraction(newIntegerPart, newFractionalPart, newDenominator);
    }

    // Сравнение дробей
    @Override
    public int compareTo(Fraction other) {
        double thisDecimal = this.toDecimal();
        double otherDecimal = other.toDecimal();
        return Double.compare(thisDecimal, otherDecimal);
    }

    @Override
    public String toString() {
        return integerPart + " " + fractionalPart + "/" + denominator;
    }
}
