package com.demo.circuit.breaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.circuit.breaker.service.FlightService;

@RestController
@RequestMapping(path = "/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;

	@GetMapping(path = "/routes")
	public List<String> routesAvailable() {
		return this.flightService.routes();
	}
}
