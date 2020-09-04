package com.data.transformer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/hello")
@RefreshScope
public class HelloController {
	
	@Value("${welcome.message}")
	private String welcomeMessage;

	@GetMapping
	public String sayHello() {
		return welcomeMessage;
	}
}
