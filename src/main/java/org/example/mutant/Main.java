package org.example.mutant;

import org.example.mutant.repositories.DnaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    @Autowired
    private DnaRepository dnaRepository;
    public static void main(String[] args) {
       SpringApplication.run(Main.class, args);

        System.out.println("funcionando");


    }
}

