package ru.netology.authservice.domain;

import java.util.Objects;
import java.util.List;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

@Entity
public class User {

	private @Id @GeneratedValue Long id;
	//@NotBlank
	private String name;
	//@Size(min = 4, max = 15)
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Authorities> authorities;
	
	// Entity теребует чтоб обязательно был дефолтный конструктор
	public User() {

	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
		this.authorities = Collections.emptyList();
	}

	public User(String name, String password, List<Authorities> authorities) {
		this.name = name;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return this.password;
	}

	public List<Authorities> getAuthorities() {
		return this.authorities;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		//String salt = BCrypt.gensalt();
		//this.password = BCrypt.hashpw(password, salt);
		this.password = password;
	}

	public void setAuthorities(List<Authorities> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof User))
			return false;
		User user = (User) o;
		return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name)
				&& Objects.equals(this.password, user.password)
				//&& BCrypt.checkpw(this.password, user.password)
				&& Objects.equals(this.authorities, user.authorities);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.password, this.authorities);
	}

	@Override
	public String toString() {
		// return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ",
		// authorities='" + this.authorities + '\'' + '}';
		return "User{" + "id=" + this.id + ", name='" + this.name + "\'" + "}";
	}
}
