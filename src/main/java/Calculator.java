public class Calculator {
    Friends friends;
    Products products;
    Formatter formatter;

    Calculator() {
        this.formatter = new Formatter();
        this.friends = new Friends();
        this.products = new Products(this.formatter);
    }

    void outString() {
        System.out.println("Добавленные товары:");
        System.out.println(this.products.prodNames);
        System.out.print("Итоговая сумма равна: ");
        System.out.println(this.formatter.rubFullFormat(this.products.prodCost));
        System.out.print("Сумма, которую должен заплатить каждый, равна: ");
        System.out.println(this.formatter.rubFullFormat((this.products.prodCost / this.friends.friendsCount)));
        // Если сумма не делится без остатка
        double d = checkBalance();
        if (!(d == 0.0d)) {
            System.out.print("И " + this.formatter.rubFullFormat(d) + ", кто то должен добавить.");
        }
    }

    double checkBalance() {
        double d = (this.products.prodCost / this.friends.friendsCount) * 100;
        d = Math.round(d);
        d = d / 100;
        return (this.products.prodCost - this.friends.friendsCount * d);
    }
}
