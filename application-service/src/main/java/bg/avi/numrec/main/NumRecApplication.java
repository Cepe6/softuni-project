package bg.avi.numrec.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "bg.avi.numrec.controller", "bg.avi.numrec.db.repository", "bg.avi.numrec.service"})
@EnableJpaRepositories("bg.avi.numrec.db.repository")
@EntityScan("bg.avi.numrec.db.entity") 
public class NumRecApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumRecApplication.class, args);
	}

}
