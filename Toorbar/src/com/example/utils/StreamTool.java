package com.example.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {
	public static byte[] read(InputStream stream) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = stream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, len);
		}
		stream.close();
		return outputStream.toByteArray();
	}
}
