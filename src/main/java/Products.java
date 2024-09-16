import java.util.Locale;
import java.util.Scanner;

public class Products {
    String prodNames = "";
    Double prodCost = 0.0;
    Formatter formatter;

    Products(Formatter formatter) {
        this.formatter = formatter;
    }

    void addItems() {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            count++;
            // Добавляем имя товара
            System.out.println("Для добавления нового товара введите название! Для завершения, введите \"Завершить\".");
            if (!addName(scanner, count)) {
                break;
            }
            // Добавляем цену товара
            System.out.println("Введите цену в формате \"рубли.копейки\".");
            addCost(scanner);

            System.out.println("Товар добавлен.\n");
        }
    }

    boolean addName(Scanner scanner, int count) { // Добавление имени товара
        String prodName = scanner.next();
        if (checkName(prodName)) {
            String format = "%d. %s"; // Символ переноса строки в методе addCost()
            this.prodNames = this.prodNames + String.format(format, count, prodName);
            return true;
        } else {
            return false;
        }
    }

    void addCost(Scanner scanner) { // Добавление стоимости товара
        scanner.useLocale(Locale.ENGLISH);
        double prodCost;
        while (true) {
            if (checkFormat(scanner)) {
                prodCost = scanner.nextDouble();
                if (checkPositive(prodCost)) {
                    break;
                }
            } else {
                scanner.next();
            }
            System.out.println("Ошибка. Введите цену в формате \"рубли.копейки\".");
        }

        this.prodNames = this.prodNames + " - " + this.formatter.rubFullFormat(prodCost) + "\n"; // Вывод суммы товара для наглядности
        this.prodCost = this.prodCost + prodCost;
    }

    boolean checkName(String name) { // Проверка введенного слова
        String constEnd = "завершить";
        return !constEnd.equalsIgnoreCase(name);
    }

    boolean checkFormat(Scanner scanner) {
        return scanner.hasNextDouble();
    }

    boolean checkPositive(double prodCost) {
        return prodCost > 0;
    }
}
