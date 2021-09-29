package br.com.bandtec.ink4yousembanco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

@SpringBootApplication
public class Ink4YouSemBancoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ink4YouSemBancoApplication.class, args);
	}

}
