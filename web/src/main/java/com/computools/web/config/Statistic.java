package com.computools.web.config;

import com.computools.web.controller.UserController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class Statistic implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        while (true){
            System.out.println("Operation per second -> " + UserController.count.get());
            UserController.count.set(0);
            Thread.sleep(999);
        }

    }
}
