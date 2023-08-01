package ru.netology.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.netology.authservice.handler.UserHandlerMethodArgumentResolver;
import java.util.List;

@EnableWebMvc
@Configuration
public class WebConfig  implements WebMvcConfigurer {
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new UserHandlerMethodArgumentResolver());
	}
}

