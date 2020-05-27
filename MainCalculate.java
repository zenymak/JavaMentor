package Calculate;

import java.util.Scanner;

public class MainCalculate {
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException {

//      Ввод выражения
        Scanner scan = new Scanner(System.in);

        String expression = scan.nextLine();

        scan.close();

//      Объвление массива строк и разделение строки expression на элементы массива. Разделитель пробел
        String[] k = expression.split(" ");

        if (ArabicOrRoman(k)) {
//          Выполняется если работа ведется с арабскими числами
            int firstVar = Integer.parseInt(k[0]);
            int secondVar = Integer.parseInt(k[2]);
            String sign = k[1];

            ErrorDetected(k, firstVar, secondVar);

            AnswerArabic(sign, firstVar, secondVar);
        } else {
//          Выполняется если работа ведется с римскими числами
            int firstVar = toArabicNumber(k[0]);
            int secondVar = toArabicNumber(k[2]);

            String sign = k[1];

            ErrorDetected(k, firstVar, secondVar);

            AnswerRoman(sign, firstVar, secondVar);
        }
    }
//  Метод выводящий ответ, если работа ведется с римскиими числами
//  Также обрабатывает исключение при делении на ноль
    private static void AnswerRoman(String znak, int a, int b) throws ArithmeticException {
        double tmpAnswer = 0;
        String answer = "";
        try {
            switch (znak) {
                case "+":
                    tmpAnswer = a + b;
                    break;
                case "/":
                    if (b == 0)
                        throw new ArithmeticException();
                    tmpAnswer = (double) a / b;
                    break;
                case "-":
                    tmpAnswer = a - b;
                    break;
                case "*":
                    tmpAnswer = a * b;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (ArithmeticException aex) {
            System.err.println("Делить на ноль нельзя!");
            System.exit(0);
        } catch (IllegalArgumentException iaex) {
            System.err.println("Такой знак не поддерживается!");
            System.exit(0);
        }
        while (tmpAnswer >= 100) {
            answer += "C";
            tmpAnswer -= 100;
        }
        while (tmpAnswer >= 90) {
            answer += "XC";
            tmpAnswer -= 90;
        }
        while (tmpAnswer >= 50) {
            answer += "L";
            tmpAnswer -= 50;
        }
        while (tmpAnswer >= 40) {
            answer += "XL";
            tmpAnswer -= 40;
        }
        while (tmpAnswer >= 10) {
            answer += "X";
            tmpAnswer -= 10;
        }
        while (tmpAnswer >= 9) {
            answer += "IX";
            tmpAnswer -= 9;
        }
        while (tmpAnswer >= 5) {
            answer += "V";
            tmpAnswer -= 5;
        }
        while (tmpAnswer >= 4) {
            answer += "IV";
            tmpAnswer -= 4;
        }
        while (tmpAnswer >= 1) {
            answer += "I";
            tmpAnswer -= 1;
        }
        System.out.println(answer);
    }

//  Метод обрабатывающий большинство исключений
    private static void ErrorDetected(String[] k, int a, int b) {
        try {
            if ((a > 10 || a < 0) || (b > 10 || b < 0) || !(k.length == 3)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException iaex) {
            System.err.println("Неверный формат ввода данных!");
            System.exit(0);
        }
    }

//  Метод с помощью которого определяется какие заданны числа: арабские или римские
    private static boolean ArabicOrRoman(String[] k) throws NumberFormatException {
        try {
            Integer.parseInt(k[0]);
            Integer.parseInt(k[2]);
            return true;
        } catch (NumberFormatException nfex) {
            return false;
        }
    }
//  Метод преобразовывает римские числа в арабские
    private static int toArabicNumber(String roman) {
        switch (roman) {
            case "0":
                return 0;
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return -1;
        }
    }

//  Метод выводящий ответ, если работа ведется с арабскими числами
//  Также обрабатывает исключение при делении на ноль
    private static void AnswerArabic(String sign, int a, int b) throws ArithmeticException {
        try {
            switch (sign) {
                case "+":
                    System.out.println(a + b);
                    break;
                case "/":
                    System.out.println(a / b);
                    break;
                case "-":
                    System.out.println(a - b);
                    break;
                case "*":
                    System.out.println(a * b);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } catch (ArithmeticException aex) {
            System.err.println("Делить на ноль нельзя!");
            System.exit(0);
        } catch (IllegalArgumentException iaex) {
            System.err.println("Такой знак не поддерживается!");
            System.exit(0);
        }
    }
}