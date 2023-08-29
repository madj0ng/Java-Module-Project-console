import java.util.Locale;

public class Formatter {
    String rubFullFormat(double d) { // Вывод отформатирвоанной суммы в рублях
        return rubCostFormat(d) + " " + rubNameFormat(d);
    }

    String rubCostFormat(double d) { // Формат суммы
        String format = "%.2f";
        return String.format(Locale.ENGLISH, format, d);
    }

    String rubNameFormat(double d) { // Склонение валюты
        String rubI = "Рубль";
        String rubM = "Рублей";
        String rubS = "Рубля";
        d = Math.floor(d);
        int mod = (int) (d % 100);
        int div = (mod / 10);
        int res = (mod > 19) ? (mod - div * 10) : mod;
        if (res == 1) {
            return rubI;
        } else if (res < 5 && res > 1) {
            return rubS;
        } else {
            return rubM;
        }
    }
}
