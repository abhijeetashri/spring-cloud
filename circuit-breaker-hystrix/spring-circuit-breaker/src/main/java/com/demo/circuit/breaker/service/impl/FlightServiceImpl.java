package com.demo.circuit.breaker.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.circuit.breaker.service.FlightService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "routesAvailableFromReliableSource")
	@Override
	public List<String> routes() {
		return this.restTemplate.getForObject("http://localhost:8091/routes/all", List.class);
	}

	public List<String> routesAvailableFromReliableSource() {
		return Arrays.asList("Delhi-Mumbai", "Delhi-Chennai", "Delhi Hyderabad", "Delhi-Bengaluru", "Delhi-Ahmedabad",
				"Delhi-Pune", "Delhi-Indore");
	}
}
