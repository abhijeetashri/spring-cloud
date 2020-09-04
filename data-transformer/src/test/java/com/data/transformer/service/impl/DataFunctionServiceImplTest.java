package com.data.transformer.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DataFunctionServiceImplTest {

	@InjectMocks
	private DataFunctionServiceImpl dataFunctionService;

	@Test
	public void alphatizeMyDataTest() {
		// Arrange
		Map<String, String> data = alphatizedStringMap();

		// Act
		Map<String, String> returnedDataMap = dataFunctionService.alphatizeMyData(alphatizedStringMap());

		// Assert
		assertEquals("The data size did not match", data.size(), returnedDataMap.size());
	}

	@Test
	public void flattenMyDataTest() throws JSONException {
		// Arrange
		// Act
		String returnedData = dataFunctionService.flattenMyData(alphatizedObjectMap());

		// Assert
		assertNotNull("Empty String returned!", returnedData);
	}

	private Map<String, String> alphatizedStringMap() {
		Map<String, String> data = new HashMap<>();
		data.put("name", "Qwert Yuiop");
		data.put("gender", "Male");
		data.put("country", "Hungary");
		data.put("contact", "[\"1234567890\", \"0987654321\"]");
		return data;
	}

	private Map<String, Object> alphatizedObjectMap() {
		Map<String, Object> data = new HashMap<>();
		List<String> contacts = new ArrayList<>();
		contacts.add("1234567890");
		contacts.add("0987654321");
		data.put("name", "Qwert Yuiop");
		data.put("gender", "Male");
		data.put("country", "Hungary");
		data.put("contact", contacts);
		return data;
	}
}
