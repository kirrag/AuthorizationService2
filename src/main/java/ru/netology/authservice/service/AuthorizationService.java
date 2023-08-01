package ru.netology.authservice.service;

import java.util.Collections;
import java.util.List;
import ru.netology.authservice.domain.Authorities;
import ru.netology.authservice.repository.UserRepository;
import ru.netology.authservice.domain.User;
import ru.netology.authservice.exception.UserNotFoundException;
import ru.netology.authservice.exception.InvalidCredentials;
import ru.netology.authservice.exception.UnauthorizedUser;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt; 

@Service
public class AuthorizationService {
	private final UserRepository repository;
	List<Authorities> authorities;

	public AuthorizationService(UserRepository repository) {
		this.repository = repository;
	}

	public List<Authorities> getAuthorities(User user) {
		List<Authorities> userAuthorities = Collections.emptyList();
		String hashed = "";

		if (isEmpty(user.getName())) {
			throw new InvalidCredentials("User name is empty");
		}

		if (isEmpty(user.getPassword())) {
			throw new InvalidCredentials("Password is empty");
		}

		List<User> users = findAll();
		for (User u : users) {
			if (u.getName().equals(user.getName()) ) {
				userAuthorities = u.getAuthorities();
				hashed = u.getPassword();
			}
		}

		if (isEmpty(userAuthorities) || ! BCrypt.checkpw(user.getPassword(), hashed)) {
			throw new UnauthorizedUser("Unauthorized user " + user.getName());
		}

		return userAuthorities;
	}

	private boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	private boolean isEmpty(List<?> str) {
		return str == null || str.isEmpty();
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	public List<User> findAll() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	public User save(User newUser) {
		return repository.save(newUser);
	}

	// Single item

	public User findById(Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}

	public User replaceUser(User newUser, Long id) {

		return repository.findById(id)
				.map(user -> {
					user.setName(newUser.getName());
					user.setPassword(newUser.getPassword());
					user.setAuthorities(newUser.getAuthorities());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return repository.save(newUser);
				});
	}

	public void deleteUser(Long id) {
		repository.deleteById(id);
	}
}
