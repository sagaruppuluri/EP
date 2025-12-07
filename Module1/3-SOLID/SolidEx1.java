/**
 * SOLID Principles -
 * SOLID is an acronym for the first five object-oriented design (OOD)
 * principles by Robert C. Martin
 *
 * S - Single Responsibility Principle - A class should have one and only one reason to change.
 * O -
 * L -
 * I -
 * D -
 */

class AccountService {

    AccountRepository repository = new AccountRepository();
    NotificationService notificationService = new NotificationService();

    public void openAccount() {
        // 1
        System.out.println("fill account internal details");

        repository.create();

        notificationService.sendNotification();
    }
}

class AccountRepository {
    public void create() {
        // 2
        System.out.println("store account object in database");
    }
}

class NotificationService {
    public void sendNotification() {
        // 3
        System.out.println("send out welcome message");
    }
}

public class SolidEx1 {
    public static void main(String[] args) {
        AccountService service = new AccountService();
        service.openAccount();
    }
}
