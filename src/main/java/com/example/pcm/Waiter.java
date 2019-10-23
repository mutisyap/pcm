package com.example.pcm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * From the cashier, orders flow to the waiters
 */
public class Waiter implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(Waiter.class);
    private final Restaurant restaurant;
    private Random rand = new Random();

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Order order;
                synchronized (this.restaurant.getOrders()) {

                    if (this.restaurant.getOrders().isEmpty()) {
                        // let the threads wait
                        try {
                            logger.debug("No element in queue thread is waiting");

                            this.restaurant.getOrders().wait();
                        } catch (InterruptedException e) {
                            logger.warn("Queue is empty but thread cannot wait", e);
                            break; // thread ends
                        }
                    }

                    order = restaurant.removeFromQueue();
                    logger.debug("Polled from queue. orders = {}, order = {}", restaurant.getOrders(), order);
                }

                if (order != null) {
                    serve(order);
                }
            } catch (Exception ex) {
                logger.error("Encountered exception ", ex);
            }
        }
    }
    

    private void serve(Order order) throws InterruptedException {
        logger.debug("Starting to serve order : {}", order);

        // assume we wait for 1 - 5seconds, and then come with a response
        int timeWait = (rand.nextInt(5 - 1) + 1) * 1000;
        Thread.sleep(timeWait);

        logger.debug("Served Order : {}", order);
    }
}
