package com.demo.flights.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.flights.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {

	@Override
	public List<String> all() {
		return Arrays.asList("Delhi-Mumbai", "Delhi-Chennai", "Delhi Hyderabad", "Delhi-Bengaluru", "Delhi-Ahmedabad",
				"Delhi-Pune", "Delhi-Indore", "Delhi-Cochin", "Delhi-Leh");
	}
}
