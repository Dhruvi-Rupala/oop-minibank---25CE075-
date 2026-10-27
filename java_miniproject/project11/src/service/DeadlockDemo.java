package service;

public class DeadlockDemo {

    private static final Object A = new Object();
    private static final Object B = new Object();

    private static void oppositeOrder() {
        Thread first = new Thread(() -> {
            synchronized (A) {
                System.out.println("Thread 1 locked A");
                pause();
                synchronized (B) {
                    System.out.println("Thread 1 locked B");
                }
            }
        }, "Thread-1");

        Thread second = new Thread(() -> {
            synchronized (B) {
                System.out.println("Thread 2 locked B");
                pause();
                synchronized (A) {
                    System.out.println("Thread 2 locked A");
                }
            }
        }, "Thread-2");

        first.setDaemon(true);
        second.setDaemon(true);

        first.start();
        second.start();

        try {
            first.join(500);
            second.join(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (first.isAlive() && second.isAlive()) {
            System.out.println("Deadlock detected in opposite-order example.");
        }
    }

    private static void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void orderedTransfer(Object first, Object second, String name) {
        Object low = first;
        Object high = second;

        synchronized (low) {
            synchronized (high) {
                System.out.println(name + " completed transfer.");
            }
        }
    }

    private static void fixedOrder() {
        Thread first = new Thread(
                () -> orderedTransfer(A, B, "A to B"));

        Thread second = new Thread(
                () -> orderedTransfer(A, B, "B to A"));

        first.start();
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Fixed-order transfers completed.");
    }

    public static void main(String[] args) {
        System.out.println("===== DEADLOCK DEMONSTRATION =====");
        oppositeOrder();

        System.out.println("\n===== FIXED LOCK ORDER =====");
        fixedOrder();
    }
}