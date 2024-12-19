package searchman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "searchman.entity")
public class SpringPractice001Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringPractice001Application.class, args);
	}

}
