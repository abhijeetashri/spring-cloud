package com.data.transformer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.transformer.service.DataFunctionService;

@RestController
@RequestMapping(path = "/data/functions")
public class DataFunctionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataFunctionController.class);

	@Autowired
	private DataFunctionService functionService;

	@PutMapping(path = "/alpha", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, String> alphatize(@RequestBody Map<String, String> requestBody) {
		return this.functionService.alphatizeMyData(requestBody);
	}

	@PostMapping(path = "/flatten", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String flatten(@RequestBody Map<String, Object> requestBody) {
		return this.functionService.flattenMyData(requestBody);
	}

	@GetMapping(path = "/status")
	public Map<String, Object> systemStatus() {
		try {
			return this.functionService.getSystemDetails();
		} catch (IOException e) {
			LOGGER.error("Error getting system status", e);
			return new HashMap<>();
		}
	}
}
