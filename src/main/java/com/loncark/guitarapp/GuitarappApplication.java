package com.loncark.guitarapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuitarappApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuitarappApplication.class, args);
    }

}

// docker exec -it mysql-container mysql -uroot -ppassword
