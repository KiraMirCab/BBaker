package es.iessoterohernandez.BBaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan(basePackages = "es.iessoterohernandez.BBaker.model")
public class BBakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BBakerApplication.class, args);
	}

}
