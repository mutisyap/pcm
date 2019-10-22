package com.example.pcm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * A cashier creates orders and then sends the orders to waiters.
 */
public class Cashier implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(Cashier.class);
    private final Restaurant restaurant;
    private List<String> availableDishes;
    private Random rand = new Random();
    private long sleepTime;

    public Cashier(long sleepTime, Restaurant restaurant) {
        availableDishes = new ArrayList<>();

        availableDishes.add("Mukimo");
        availableDishes.add("Chapo");
        availableDishes.add("Mursik");
        availableDishes.add("Omena");

        this.sleepTime = sleepTime;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        while (true) {
            try {
                createOrder();

                logger.debug("Thread sleeping for : {}", sleepTime);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ex) {
                    logger.error("Unable to sleep. ", ex);
                    break;
                }
            } catch (Exception ex) {
                logger.error("Encountered exception ", ex);
            }
        }
    }

    private void createOrder() {
        logger.debug("Request to create new order.");

        String orderNumber = UUID.randomUUID().toString();
        String food = availableDishes.get(rand.nextInt(availableDishes.size()));

        Order order = new Order(orderNumber, food, LocalDateTime.now());
        
        // queue the order
        restaurant.addToQueue(order);
        logger.debug("Successfully created order : {}, Queue Size = {}", order, restaurant.getOrders().size());

        synchronized (restaurant.getOrders()) {
            restaurant.getOrders().notifyAll();
        }
    }
}
