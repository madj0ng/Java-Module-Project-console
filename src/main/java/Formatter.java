import java.util.Locale;

public class Formatter {
    String rubFullFormat(double d) { // Вывод отформатированной суммы в рублях
        return rubCostFormat(d) + " " + rubNameFormat(d);
    }

    String rubCostFormat(double d) { // Формат суммы
        String format = "%.2f";
        return String.format(Locale.ENGLISH, format, d);
    }

    String rubNameFormat(double d) { // Склонение валюты
        String[] rubForms = {"рубль", "рублей", "рубля"};
        int mod = (int) (d % 100);
        int res = (mod > 19) ? (mod % 10) : mod;
        switch (res) {
            case 1:
                return rubForms[0];
            case 2:
            case 3:
            case 4:
                return rubForms[2];
            default:
                return rubForms[1];
        }
    }
}
