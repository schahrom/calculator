import java.util.Arrays;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) throws Exeption {
        Scanner in = new Scanner(System.in);
        System.out.println("Input: ");              //Пользователь вводит выражение, которое записывается в строку
        String enteredExpression = in.nextLine();
        System.out.println("Output: " + calc(enteredExpression));
    }
    public static String calc(String input) throws Exeption {
        methods methods = new methods(); //Объект класса в котором метод будет производить окончательные вычислнеия

        if (input.length() == 0) {
            throw new Exeption("Введена пустая строка");
        }

        String[] enteredArray = input.split(" ");   //Элементы из введенной строки в массиве
        if (enteredArray.length != 3) {
            throw new Exeption("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }


        String[] allowedValueArabic = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] allowedValueRoman = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII",
                "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI",
                "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV",
                "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV",
                "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII",
                "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};        //Массивы для проверки вводимых значений (и использования для вывода в случае с римскими цифрами)


        boolean arabicOne = Arrays.asList(allowedValueArabic).contains(enteredArray[0]);
        boolean arabicTwo = Arrays.asList(allowedValueArabic).contains(enteredArray[2]);
        boolean romanOne = Arrays.asList(allowedValueRoman).contains(enteredArray[0]);
        boolean romanTwo = Arrays.asList(allowedValueRoman).contains(enteredArray[2]);

        if ((arabicOne && romanTwo) || (romanOne && arabicTwo)) {
            throw new Exeption("Одновременное использование разных сиситем счисления.");
        }

        if (arabicOne && arabicTwo) {
            int firstNumb = Integer.parseInt(enteredArray[0]);
            int secondNumb = Integer.parseInt(enteredArray[2]); //Необходимые перобразования значений


            if (0 == firstNumb || firstNumb > 10 || secondNumb == 0 || secondNumb > 10) {
                throw new Exeption("Недопустимые величины значений (1 - 10).");
            }

            String elem = enteredArray[1];       //Определение оператора ("+", "-" и т. д.)
            int results = methods.calculation(elem, firstNumb, secondNumb);
            String resStr = Integer.toString(results);
            return resStr;
        }


        if (romanOne && romanTwo) {
            int indOne = 0;
            int indTwo = 0;
            for (int i = 0; i < allowedValueRoman.length; ++i) {
                if (allowedValueRoman[i].equals(enteredArray[0])) {
                    indOne += i;
                }
                if (allowedValueRoman[i].equals(enteredArray[2])) {
                    indTwo += i;
                }
            }
            if (0 == indOne || indOne > 10 || indTwo == 0 || indTwo > 10) {
                throw new Exeption("Недопустимые величины значений (I - X).");
            }

            int firstNumber = Integer.parseInt(allowedValueArabic[indOne]);
            int secondNumber = Integer.parseInt(allowedValueArabic[indTwo]);


            String elem = enteredArray[1];       //Присваивание переменной оператора в виде строки ("+", "-" и т. д.)

            int results = methods.calculation(elem, firstNumber, secondNumber);

            if (results < 0) {
                throw new Exeption("в римской системе нет чисел меньше нуля.");
            } else {
                return allowedValueRoman[methods.calculation(elem, firstNumber, secondNumber)];
            }
        }
    return " ";
    }
}


class methods {

    int calculation(String elem, int firstNumb, int secondNumb){
        int res;
        switch (elem) {
            case "+":
                res = firstNumb + secondNumb;
                break;
            case "-":
                res = firstNumb - secondNumb;                       // Вычисления
                break;
            case "*":
                res = firstNumb * secondNumb;
                break;
            case "/":
                res = firstNumb / secondNumb;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + elem);
        }
        return res;
    }
}


