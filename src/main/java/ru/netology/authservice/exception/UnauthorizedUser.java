package ru.netology.authservice.exception;

public class UnauthorizedUser extends RuntimeException {
	public UnauthorizedUser(String msg) {
		super(msg);
	}
}
