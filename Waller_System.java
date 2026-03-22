import java.util.concurrent.ConcurrentHashMap;

class User {
    double balance;

    User(double balance) {
        this.balance = balance;
    }

    synchronized void credit(double amt) {
        balance += amt;
    }

    synchronized boolean debit(double amt) {
        if (balance < amt) return false;
        balance -= amt;
        return true;
    }

    synchronized double getBalance() {
        return balance;
    }
}

class Wallet {
    private final ConcurrentHashMap<String, User> map = new ConcurrentHashMap<>();

    void createUser(String id, double bal) {
        map.putIfAbsent(id, new User(bal));
    }

    void credit(String id, double amt) {
        map.get(id).credit(amt);
    }

    boolean debit(String id, double amt) {
        return map.get(id).debit(amt);
    }

    double getBalance(String id) {
        return map.get(id).getBalance();
    }
}

public class Waller_System {
    public static void main(String[] args) throws Exception {

        Wallet w = new Wallet();
        w.createUser("u1", 100);

        Thread t1 = new Thread(() ->
                System.out.println("T1: " + w.debit("u1", 50)));

        Thread t2 = new Thread(() ->
                System.out.println("T2: " + w.debit("u1", 70)));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final: " + w.getBalance("u1"));
    }
}