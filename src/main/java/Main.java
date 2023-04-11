import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        calculator.friends.addItems();  // Добавление количества друзей
        calculator.products.addItems(); // Добавление товаров
        if (calculator.products.prodCost==0.0d){
            return;
        }
        calculator.outString();         // Вывод результатов
    }
}
class Calculator{
    Friends friends;
    Products products;
    Formatter formatter;
    Calculator(){
        this.formatter = new Formatter();
        this.friends = new Friends();
        this.products = new Products(this.formatter);
    }
    void outString(){
        System.out.println("Добавленные товары:");
        System.out.println(this.products.prodNames);
        System.out.print("Итоговая сумма равна: ");
        System.out.println(this.formatter.rubFullFormat(this.products.prodCost));
        System.out.print("Сумма, которую должен заплатить каждый, равна: ");
        System.out.println(this.formatter.rubFullFormat((this.products.prodCost / this.friends.friendsCount)));
        // Если сумма не делится без остатка
        double d = checkBalance();
        if(!(d == 0.0d)){
            System.out.print("И " + this.formatter.rubFullFormat(d) + ", кто то должен добавить.");
        }
    }
    double checkBalance(){
        double d = (this.products.prodCost / this.friends.friendsCount) * 100;
        d = Math.round(d);
        d = d/100;
        return (this.products.prodCost - this.friends.friendsCount * d);
    }
}

class Friends{
    int friendsCount;
    void addItems(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("На скольких человек необходимо разделить счёт?");
        while(true) {
            if(addFriend(scanner)){
                break;
            }
        }
    }
    boolean addFriend(Scanner scanner){
        if(!checkFormat(scanner)){
            System.out.println("Нужно указать число.");
            scanner.next();
            return false;
        }
        int friendsCount = scanner.nextInt();
        if(!checkCount(friendsCount)){
            System.out.println("Укажите количество больше одного");
            return false;
        }
        this.friendsCount = friendsCount;
        return true;
    }
    boolean checkFormat(Scanner scanner){
        return scanner.hasNextInt();
    }
    boolean checkCount(int friendsCount){
        return friendsCount > 1;
    }
}

class Products{
    String prodNames = "";
    Double prodCost = 0.0;
    Formatter formatter;
    Products(Formatter formatter){
        this.formatter = formatter;
    }
    void addItems(){
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            count++;
            // Добавляем имя товара
            System.out.println("Для добавления нового товара введите название! Для завершения, введите \"Завершить\".");
            if(!addName(scanner, count)) {
                break;
            }
            // Добавляем цену товара
            System.out.println("Введите цену в формате \"рубли.копейки\".");
            addCost(scanner);

            System.out.println("Товар добавлен.\n");
        }
    }
    boolean addName(Scanner scanner, int count){ // Добавление имени товара
        String prodName = scanner.next();
        if(checkName(prodName)) {
            String format = "%d. %s"; // Символ переноса строки в методе addCost()
            this.prodNames = this.prodNames + String.format(format, count, prodName);
            return true;
        }else{
            return false;
        }
    }
    void addCost(Scanner scanner){ // Добавление стоимости товара
        scanner.useLocale(Locale.ENGLISH);
        double prodCost;
        while(true){
            if(checkFormat(scanner)){
                prodCost = scanner.nextDouble();
                if(checkPositive(prodCost)){
                    break;
                }
            }else{
                scanner.next();
            }
            System.out.println("Ошибка. Введите цену в формате \"рубли.копейки\".");
        }

        this.prodNames = this.prodNames + " - " + this.formatter.rubFullFormat(prodCost) + "\n"; // Вывод суммы товара для наглядности
        this.prodCost = this.prodCost + prodCost;
    }
    boolean checkName(String name){ // Проверка введенного слова
        String constEnd = "завершить";
        return !constEnd.equalsIgnoreCase(name);
    }
    boolean checkFormat(Scanner scanner){
        return scanner.hasNextDouble();
    }
    boolean checkPositive(double prodCost){
        return prodCost > 0;
    }
}

class Formatter{
    String rubFullFormat(double d){ // Вывод отформатирвоанной суммы в рублях
        return rubCostFormat(d) + " " + rubNameFormat(d);
    }
    String rubCostFormat(double d){ // Формат суммы
        String format = "%.2f";
        return String.format(Locale.ENGLISH, format, d);
    }
    String rubNameFormat(double d){ // Склонение валюты
        String rubI = "Рубль";
        String rubM = "Рублей";
        String rubS = "Рубля";
        d = Math.floor(d);
        int mod = (int)(d % 100);
        int div = (mod / 10);
        int res = (mod > 19) ? (mod - div * 10) : mod;
        if(res==1){
            return rubI;
        }else if(res < 5 && res > 1){
            return rubS;
        }else{
            return rubM;
        }
    }
}