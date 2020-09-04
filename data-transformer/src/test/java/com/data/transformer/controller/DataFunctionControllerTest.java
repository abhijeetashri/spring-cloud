package com.data.transformer.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.data.transformer.service.DataFunctionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DataFunctionController.class)
public class DataFunctionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DataFunctionService functionService;

	private final String BASE_URI = "/data/functions";

	@Test
	public void alphatizedDataTest() throws Exception {
		Map<String, String> dataMap = alphatizedMap();
		when(functionService.alphatizeMyData(Mockito.anyMap())).thenReturn(dataMap);

		mockMvc.perform(
				put(BASE_URI + "/alpha").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(mapToJson(dataMap)))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void flattenizedDataTest() throws Exception {
		Map<String, String> dataMap = alphatizedMap();
		Map<String, String> flattenedDataMap = new HashMap<>(dataMap);
		flattenedDataMap.put("contact", "1234567890, 0987654321");
		when(functionService.flattenMyData(Mockito.anyMap())).thenReturn(flattenedDataMap.toString());

		mockMvc.perform(post(BASE_URI + "/flatten").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(mapToJson(dataMap))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	private Map<String, String> alphatizedMap() {
		Map<String, String> data = new HashMap<>();
		data.put("name", "Qwert Yuiop");
		data.put("gender", "Male");
		data.put("country", "Hungary");
		data.put("contact", "[1234567890, 0987654321]");
		return data;
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}
}
