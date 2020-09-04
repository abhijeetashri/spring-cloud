package com.data.transformer.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.transformer.service.DataFunctionService;

@Service
public class DataFunctionServiceImpl implements DataFunctionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataFunctionServiceImpl.class);

	@Override
	public Map<String, String> alphatizeMyData(Map<String, String> dataMap) {

		// Order the Keys in Map alphabetically
		return new TreeMap<>(dataMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String flattenMyData(Map<String, Object> dataMap) {

		// Convert JSONArray to a simple String
		JSONObject json = new JSONObject(dataMap);
		json.keys().forEachRemaining(aKey -> {
			Object aValue = null;
			try {
				aValue = json.get((String) aKey);
				if (aValue instanceof JSONArray) {
					int size = ((JSONArray) aValue).length();
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < size; i++) {
						sb.append(((JSONArray) aValue).get(i));
						sb.append(",");
					}
					sb.deleteCharAt(sb.length() - 1);
					json.putOpt((String) aKey, sb.toString());
				}
			} catch (JSONException e) {
				LOGGER.error("Error in reading JSON object: ", e);
			}
		});
		return JSONObject.wrap(json).toString();
	}

	public Map<String, Object> getSystemDetails() throws IOException {
		Map<String, Object> systemInfo = new HashMap<>();
		calculateDiskSpaces(systemInfo);
		systemInfo.put("cores", String.valueOf(Runtime.getRuntime().availableProcessors()));
		return systemInfo;
	}

	private static void calculateDiskSpaces(Map<String, Object> systemInfo) throws IOException {
		class DiskInfo {
			String discname;
			String freeSpace;

			public String getDiscname() {
				return discname;
			}

			public void setDiscname(String discname) {
				this.discname = discname;
			}

			public String getFreeSpace() {
				return this.freeSpace;
			}

			public void setFreeSpace(long freeSpace) {
				this.freeSpace = freeSpace / (1024 * 1024) + " MB";
			}

			@Override
			public String toString() {
				Map<String, String> diskInformation = new HashMap<>();
				diskInformation.put("discname", this.getDiscname());
				diskInformation.put("availability", this.getFreeSpace());
				return diskInformation.toString();
			}
		}

		List<DiskInfo> disks = new ArrayList<>();
		File[] roots = File.listRoots();
		for (File file : roots) {
			DiskInfo diskInfo = new DiskInfo();
			diskInfo.setDiscname(file.getCanonicalPath());
			diskInfo.setFreeSpace(file.getFreeSpace());
			disks.add(diskInfo);
		}

		systemInfo.put("disc-space-availability", disks);
	}
}
