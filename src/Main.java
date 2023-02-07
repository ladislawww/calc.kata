import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите число: ");
        String exp = scan.nextLine();
        System.out.println(Calc(exp));
    }

    public static String Calc(String input) {

        ConvertTo convertTo = new ConvertTo();
        String[] actions  = {"+", "*", "-", "/"};
        String[] eActions = {"\\+", "\\*", "-", "/"};


        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
         if (actionIndex == -1){
             throw new RuntimeException("Вы ввели неверное арифметическое действие");
         }

        String[] string = input.split(eActions[actionIndex]);
        if (convertTo.romNum(string[0]) == convertTo.romNum(string[1])) {
            int a, b;
            boolean romNum = convertTo.romNum(string[0]);

            if (romNum) {
                a = convertTo.romToInt(string[0]);
                b = convertTo.romToInt(string[1]);

            } else {
                a = Integer.parseInt(string[0]);
                b = Integer.parseInt(string[1]);
            }

            if (a > 10 || b > 10 || a <= 0 || b <= 0) {
                throw new RuntimeException("Не удоволетворяет заданию.");
            }

            int result = 0;
            switch (actions[actionIndex]) {
                case "+" -> result = a + b;
                case "-" -> {
                    result = a - b;
                    if (romNum && result < 0) {
                        throw new RuntimeException("В римской системе нет отрицательных чисел");
                    }
                }
                case "/" -> result = a / b;
                case "*" -> result = a * b;
            }

            if (romNum) {
                if (result == 0) {
                    throw new RuntimeException("Резудьтат не может быть равен 0.");
                }
                return (convertTo.intToRom(result));
            } else {
                return (Integer.toString(result));
            }
        } else {
            throw new RuntimeException("Используются одновременно разные системы счисления");
        }
    }
}