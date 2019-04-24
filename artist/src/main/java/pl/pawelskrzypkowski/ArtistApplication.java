package pl.pawelskrzypkowski;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import pl.pawelskrzypkowski.storage.StorageService;

@SpringBootApplication
public class ArtistApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ArtistApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}
}
