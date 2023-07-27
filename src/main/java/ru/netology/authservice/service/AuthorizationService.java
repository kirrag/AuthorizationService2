package ru.netology.authservice.service;

import java.util.List;
import ru.netology.authservice.domain.Authorities;
import ru.netology.authservice.repository.UserRepository;
import ru.netology.authservice.domain.User;
import ru.netology.authservice.exception.UserNotFoundException;
import ru.netology.authservice.exception.InvalidCredentials;
import ru.netology.authservice.exception.UnauthorizedUser;
import org.springframework.stereotype.Service;

//import org.mindrot.jbcrypt.BCrypt;

@Service
public class AuthorizationService {
	private final UserRepository repository;
	List<Authorities> authorities;

	public AuthorizationService(UserRepository repository) {
		this.repository = repository;
	}

	public List<Authorities> getAuthorities(User user) {

		if (isEmpty(user.getName()) || isEmpty(user.getPassword())) {
			throw new InvalidCredentials("User name or password is empty");
		}

		List<User> users = findAll();

		for (User u : users) {
			//if (u.getName().equals(user.getName()) && BCrypt.checkpw(u.getPassword(), user.getPassword())) {
			if (u.getName().equals(user.getName()) && !u.getPassword().equals(user.getPassword())) {
				authorities =  u.getAuthorities();
			}
		}

		if (isEmpty(user.getAuthorities())) {
			throw new UnauthorizedUser("Unknown user " + user.getName());
		}
		return authorities;
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
