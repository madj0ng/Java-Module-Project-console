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