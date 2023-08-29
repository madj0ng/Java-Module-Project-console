import java.util.Scanner;

public class Friends {
    int friendsCount;

    void addItems() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("На скольких человек необходимо разделить счёт?");
        while (true) {
            if (addFriend(scanner)) {
                break;
            }
        }
    }

    boolean addFriend(Scanner scanner) {
        if (!checkFormat(scanner)) {
            System.out.println("Нужно указать число.");
            scanner.next();
            return false;
        }
        int friendsCount = scanner.nextInt();
        if (!checkCount(friendsCount)) {
            System.out.println("Укажите количество больше одного");
            return false;
        }
        this.friendsCount = friendsCount;
        return true;
    }

    boolean checkFormat(Scanner scanner) {
        return scanner.hasNextInt();
    }

    boolean checkCount(int friendsCount) {
        return friendsCount > 1;
    }
}
