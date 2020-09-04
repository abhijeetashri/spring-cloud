package com.demo.flights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.flights.service.RouteService;

@RestController
@RequestMapping(path = "/routes")
public class RoutesController {

	@Autowired
	private RouteService routeService;

	@GetMapping(path = "/all")
	public List<String> allRoutes() {
		return this.routeService.all();
	}
}
