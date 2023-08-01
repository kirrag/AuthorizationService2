package ru.netology.authservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.Arrays;

import ru.netology.authservice.repository.UserRepository;
import ru.netology.authservice.domain.User;
import ru.netology.authservice.domain.Authorities;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	private static final List<Authorities> aclr = Arrays.asList(Authorities.valueOf("READ"));
	private static final List<Authorities> aclrw = Arrays.asList(Authorities.valueOf("READ"),
			Authorities.valueOf("WRITE"));
	private static final List<Authorities> aclrwd = Arrays.asList(Authorities.valueOf("READ"),
			Authorities.valueOf("WRITE"), Authorities.valueOf("DELETE"));

	@Bean
	CommandLineRunner initDatabase(UserRepository repository) {

		return args -> {
			log.info("Preloading "
					+ repository.save(new User("ivan", BCrypt.hashpw("qwerty123", BCrypt.gensalt()), aclr)));
			log.info("Preloading "
					+ repository.save(new User("oleg", BCrypt.hashpw("welcome1", BCrypt.gensalt()), aclrw)));
			log.info("Preloading "
					+ repository.save(new User("masha", BCrypt.hashpw("Welcome1!", BCrypt.gensalt()), aclrwd)));
		};
	}
}
