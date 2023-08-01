package ru.netology.authservice.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.authservice.domain.User;
import ru.netology.authservice.service.AuthorizationService;
import ru.netology.authservice.domain.Authorities;

@RestController
public class AuthorizationController {

	private final AuthorizationService service;

	AuthorizationController(AuthorizationService service) {
		this.service = service;
	}


	@GetMapping("/authorize")
	public List<Authorities> getAuthorities(User user) {
		return service.getAuthorities(user);
	}

	@GetMapping("/users")
	public List<User> all() {
		return service.findAll();
	}
	// end::get-aggregate-root[]

	@PostMapping("/users")
	public User newUser(@RequestBody User newUser) {
		return service.save(newUser);
	}

	// Single item

	@GetMapping("/users/{id}")
	public User one(@PathVariable Long id) {

		return service.findById(id);
	}

	@PutMapping("/users/{id}")
	public void replaceUser(@RequestBody User newUser, @PathVariable Long id) {
		service.replaceUser(newUser, id);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
	}
}
