import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например, 2 + 3):");
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }

    static String calc(String input) {
        String[] tokens = input.split("\\s+");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Некорректное выражение. Пример: 2 + 3");
        }

        try {
            String num1Str = tokens[0];
            String operator = tokens[1];
            String num2Str = tokens[2];

            int num1 = convertInputToNumber(num1Str);
            int num2 = convertInputToNumber(num2Str);

            if ((num1Str.matches("\\d+") && num2Str.matches("\\d+")) ||
                    (!num1Str.matches("\\d+") && !num2Str.matches("\\d+"))) {
                int result = performOperation(num1, num2, operator);

                if (num1Str.matches("\\d+")) {
                    return String.valueOf(result);
                } else {
                    return convertNumberToRoman(result);
                }
            } else {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка преобразования чисел. Проверьте ввод.");
        }
    }

    static int convertInputToNumber(String input) {
        Map<String, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put("I", 1);
        romanNumerals.put("II", 2);
        romanNumerals.put("III", 3);
        romanNumerals.put("IV", 4);
        romanNumerals.put("V", 5);
        romanNumerals.put("VI", 6);
        romanNumerals.put("VII", 7);
        romanNumerals.put("VIII", 8);
        romanNumerals.put("IX", 9);
        romanNumerals.put("X", 10);

        if (input.matches("\\d+")) {
            int number = Integer.parseInt(input);
            if (number >= 1 && number <= 10) {
                return number;
            } else {
                throw new IllegalArgumentException("Число должно быть от 1 до 10 включительно.");
            }
        } else if (romanNumerals.containsKey(input)) {
            return romanNumerals.get(input);
        } else {
            throw new IllegalArgumentException("Неподдерживаемый формат числа: " + input);
        }
    }

    static String convertNumberToRoman(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Результат работы с римскими числами не может быть отрицательным или нулевым.");
        }

        if (num > 10) {
            throw new IllegalArgumentException("Результат работы с римскими числами не может быть больше 10.");
        }

        Map<Integer, String> romanNumerals = new HashMap<>();
        romanNumerals.put(1, "I");
        romanNumerals.put(2, "II");
        romanNumerals.put(3, "III");
        romanNumerals.put(4, "IV");
        romanNumerals.put(5, "V");
        romanNumerals.put(6, "VI");
        romanNumerals.put(7, "VII");
        romanNumerals.put(8, "VIII");
        romanNumerals.put(9, "IX");
        romanNumerals.put(10, "X");

        return romanNumerals.get(num);
    }

    static int performOperation(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    throw new ArithmeticException("Деление на ноль невозможно.");
                }
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция. Поддерживаются: +, -, *, /");
        }
    }
}
