package com.example.pcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PcmApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcmApplication.class, args);
        
        Restaurant restaurant = new Restaurant();

        // create new cashier
        Thread cashierThread = new Thread(new Cashier(2000, restaurant), "cashier");
        cashierThread.start();

		/*Thread cashier2 = new Thread(new Cashier(3000, restaurant), "cashier-2");
		 cashier2.start();*/
        
        Thread waiterThread = new Thread(new Waiter(restaurant), "waiter");
        waiterThread.start();

		Thread waitressThread = new Thread(new Waiter(restaurant), "waitress");
		waitressThread.start();
    }

}
