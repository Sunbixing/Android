package com.example.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RegisterService {

	public static boolean save(String nameString, String pwdString) {
		String path = "http://192.168.0.122:8080/web/RegisterServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", nameString);
		params.put("pwd", pwdString);
		try {
			return sendPOST(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean sendPOST(String path, Map<String, String> params,
			String string) throws Exception {
		StringBuilder url = new StringBuilder(path);
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				url.append("&");
				url.append(entry.getKey()).append("=");
				url.append(entry.getValue());

			}
		}
		byte[] data = url.toString().getBytes();
		HttpURLConnection connection = (HttpURLConnection) new URL(path)
				.openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		connection.setRequestProperty("Content-Length",
				String.valueOf(data.length));
		OutputStream stream = connection.getOutputStream();
		stream.write(data);
		if (connection.getResponseCode() == 200) {
			return true;
		}
		return false;
	}

	public static boolean delete(String i) {
		String path = "http://192.168.0.122:8080/web/DeleteServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", i);
		try {
			return sendPOST(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean update(String id, String title, String content,
			String comment) {
		String path = "http://192.168.0.122:8080/web/UpdateServlet";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", id);
		params.put("title", title);
		params.put("content", content);
		params.put("comment", comment);
		try {
			return sendPOST(path, params, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
