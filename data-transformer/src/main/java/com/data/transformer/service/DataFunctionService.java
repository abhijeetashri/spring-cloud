package com.data.transformer.service;

import java.io.IOException;
import java.util.Map;

public interface DataFunctionService {

	Map<String, String> alphatizeMyData(Map<String, String> dataMap);

	String flattenMyData(Map<String, Object> dataMap);
	
	Map<String, Object> getSystemDetails()  throws IOException;
}
