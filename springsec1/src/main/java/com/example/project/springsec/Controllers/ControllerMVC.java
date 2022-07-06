package com.example.project.springsec.Controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControllerMVC implements WebMvcConfigurer{
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/springsec/").setViewName("home");
		registry.addViewController("/springsec/all").setViewName("all");
		registry.addViewController("/springsec/login").setViewName("login");
		registry.addViewController("/springsec/update").setViewName("update");
		registry.addViewController("/springsec/newUser").setViewName("newUser");
		registry.addViewController("/springsec/add").setViewName("add");
		registry.addViewController("/springsec/upDel").setViewName("upDel");
		registry.addViewController("/springsec/delete").setViewName("login");
		registry.addViewController("/springsec/search").setViewName("search");
	}

}
