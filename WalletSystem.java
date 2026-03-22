import java.util.concurrent.*;

public class WalletSystem {

    private final ConcurrentHashMap<String, Account> map = new ConcurrentHashMap<>();

    public void createUser(String id, long bal) {
        map.putIfAbsent(id, new Account(bal));
    }

    public long getBalance(String id) {
        return get(id).balance;
    }

    public void credit(String id, long amt) {
        Account a = get(id);
        synchronized (a) {
            a.balance += amt;
        }
    }

    public boolean debit(String id, long amt) {
        Account a = get(id);
        synchronized (a) {
            if (a.balance < amt) return false;
            a.balance -= amt;
            return true;
        }
    }

    private Account get(String id) {
        Account a = map.get(id);
        if (a == null) throw new RuntimeException();
        return a;
    }

    static class Account {
        long balance;
        Account(long b) { balance = b; }
    }

    public static void main(String[] args) throws Exception {
        WalletSystem ws = new WalletSystem();
        ws.createUser("u1", 100);

        ExecutorService es = Executors.newFixedThreadPool(2);

        es.submit(() -> System.out.println(ws.debit("u1", 50)));
        es.submit(() -> System.out.println(ws.debit("u1", 70)));

        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println(ws.getBalance("u1"));
    }
}