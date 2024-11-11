import java.util.Arrays;
public class Main
{

    public static void main(String[] args)
    {
        Fraction[] fractions = {
                new Fraction(1, 1, 2),  // 1 1/2
                new Fraction(2, 1, 4),  // 2 1/4
                new Fraction(1, 3, 4),  // 1 3/4
                new Fraction(0, 2, 3)   // 0 2/3
        };


        Arrays.sort(fractions); // Сортировка массива дробей


        System.out.println("Дроби в порядке возрастания:");
        for (Fraction fraction : fractions)
        {
            System.out.println(fraction); // Вывод отсортированного массива
        }

        
        Fraction fraction1 = new Fraction(1, 1, 2);
        Fraction fraction2 = new Fraction(2, 1, 4);

        System.out.println("Сложение: " + fraction1.add(fraction2));
        System.out.println("Вычитание: " + fraction1.subtract(fraction2));
        System.out.println("Умножение: " + fraction1.multiply(fraction2));
    }
}
public class Fraction implements Comparable<Fraction>
{
    private int integerPart;
    private int fractionalPart;
    private int denominator;


    public Fraction(int integerPart, int fractionalPart, int denominator)
    {
        this.integerPart = integerPart;
        this.fractionalPart = fractionalPart;
        this.denominator = denominator;
    }


    public int getIntegerPart()
    {
        return integerPart;
    }


    public int getFractionalPart()
    {
        return fractionalPart;
    }


    public int getDenominator()
    {
        return denominator;
    }


    private double toDecimal()
    {
        return integerPart + (double) fractionalPart / denominator; // Преобразование дробного числа в вещественное для удобства операций
    }


    public Fraction add(Fraction other)
    {
        int commonDenominator = this.denominator * other.denominator;
        int newIntegerPart = this.integerPart + other.integerPart;
        int newFractionalPart = (this.fractionalPart * other.denominator) + (other.fractionalPart * this.denominator); // Сложение дробей


        newIntegerPart += newFractionalPart / commonDenominator;
        newFractionalPart = newFractionalPart % commonDenominator;

        return new Fraction(newIntegerPart, newFractionalPart, commonDenominator); // Преобразуем дробь, если дробная часть больше знаменателя
    }


    public Fraction subtract(Fraction other)
    {
        int commonDenominator = this.denominator * other.denominator;
        int newIntegerPart = this.integerPart - other.integerPart;
        int newFractionalPart = (this.fractionalPart * other.denominator) - (other.fractionalPart * this.denominator); // Вычитание дробей


        if (newFractionalPart < 0)
        {
            newIntegerPart -= 1;
            newFractionalPart += commonDenominator; // Преобразуем дробь, если дробная часть становится отрицательной
        }

        return new Fraction(newIntegerPart, newFractionalPart, commonDenominator);
    }


    public Fraction multiply(Fraction other)
    {
        int newIntegerPart = this.integerPart * other.integerPart;
        int newFractionalPart = this.fractionalPart * other.fractionalPart; // Умножение дробей
        int newDenominator = this.denominator * other.denominator;


        newIntegerPart += newFractionalPart / newDenominator;
        newFractionalPart = newFractionalPart % newDenominator; // Преобразуем дробь, если дробная часть больше знаменателя

        return new Fraction(newIntegerPart, newFractionalPart, newDenominator);
    }


    @Override
    public int compareTo(Fraction other)
    {
        double thisDecimal = this.toDecimal();
        double otherDecimal = other.toDecimal();
        return Double.compare(thisDecimal, otherDecimal); // Сравнение дробей
    }

    @Override
    public String toString()
    {
        return integerPart + " " + fractionalPart + "/" + denominator;
    }
}
