package ru.netology.authservice.handler;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.netology.authservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public final class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	User user;

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().equals(User.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer modelAndViewContainer,
			NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webDataBinderFactory) throws Exception {
		String name = nativeWebRequest.getParameter("user");
		String password = nativeWebRequest.getParameter("password");
		if (isNotSet(name)) {
			name = "defaultName";
		}

		if (isNotSet(password)) {
			password = "defaultPassword";
		}

		return new User(name, password);
	}

	private boolean isNotSet(String value) {
		return value == null;
	}
}
