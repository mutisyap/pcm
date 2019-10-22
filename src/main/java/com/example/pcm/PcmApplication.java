package com.example.pcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcmApplication.class, args);
        
        Restaurant restaurant = new Restaurant();

        // create new cashier
        Thread cashierThread = new Thread(new Cashier(2000, restaurant), "cashier-1");
        cashierThread.start();
        
        Thread waiterThread = new Thread(new Waiter(restaurant), "waiter-1");
        waiterThread.start();
    }

}
