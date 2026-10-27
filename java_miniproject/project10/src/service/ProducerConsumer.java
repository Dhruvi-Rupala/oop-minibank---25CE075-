package service;

public class ProducerConsumer {

    private final int[] buffer = new int[5];
    private int count = 0;
    private int next = 0;
    private int read = 0;

    public synchronized void produce(int value) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }

        buffer[next] = value;
        next = (next + 1) % buffer.length;
        count++;

        System.out.println("Produced transaction: " + value);

        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (count == 0) {
            wait();
        }

        int value = buffer[read];
        read = (read + 1) % buffer.length;
        count--;

        notifyAll();

        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    pc.produce(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Processed transaction: " + pc.consume());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Producer-consumer completed.");
    }
}