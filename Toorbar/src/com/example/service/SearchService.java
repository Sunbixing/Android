package com.example.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.example.domen.Cele;
import com.example.domen.Good;
import com.example.domen.Result;

public class SearchService {
	public static List<Cele> getCele() throws Exception {

		String path = "http://192.168.0.122:8080/web/CeleServlet";
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		if (connection.getResponseCode() == 200) {
			InputStream stream = connection.getInputStream();
			return parsexmlcele(stream);
		}
		return null;

	}

	public static List<Good> getGood() throws Exception {

		String path = "http://192.168.0.122:8080/web/GoodsServlet";
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		if (connection.getResponseCode() == 200) {
			InputStream stream = connection.getInputStream();
			return parsexmlgood(stream);
		}
		return null;

	}

	public static List<Result> getLast() throws Exception {

		String path = "http://192.168.0.122:8080/web/Search";
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		if (connection.getResponseCode() == 200) {
			InputStream stream = connection.getInputStream();
			return parsexml(stream);
		}
		return null;

	}

	public static List<Cele> parsexmlcele(InputStream stream) throws Exception {
		List<Cele> results = null;
		Cele result = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(stream, "UTF-8");
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				results = new ArrayList<Cele>();
			case XmlPullParser.START_TAG:
				if ("cele".equals(parser.getName())) {
					int id = Integer.valueOf(parser.getAttributeValue(0));
					result = new Cele();
					result.setId(id);
				} else if ("title".equals(parser.getName())) {
					result.setTitle(parser.nextText());
				} else if ("content".equals(parser.getName())) {
					result.setContent(parser.nextText());
				} else if ("comments".equals(parser.getName())) {
					result.setComments(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if ("cele".equals(parser.getName())) {
					results.add(result);
					result = null;
				}
				break;
			}
			event = parser.next();
		}
		return results;
	}

	public static List<Result> parsexml(InputStream stream) throws Exception {
		List<Result> results = null;
		Result result = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(stream, "UTF-8");
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				results = new ArrayList<Result>();
			case XmlPullParser.START_TAG:
				if ("result".equals(parser.getName())) {
					int id = Integer.valueOf(parser.getAttributeValue(0));
					result = new Result();
					result.setId(id);
				} else if ("title".equals(parser.getName())) {
					result.setTitle(parser.nextText());
				} else if ("content".equals(parser.getName())) {
					result.setContent(parser.nextText());
				} else if ("comments".equals(parser.getName())) {
					result.setComments(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if ("result".equals(parser.getName())) {
					results.add(result);
					result = null;
				}
				break;
			}
			event = parser.next();
		}
		return results;
	}

	public static List<Good> parsexmlgood(InputStream stream) throws Exception {
		List<Good> goods = null;
		Good good = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(stream, "UTF-8");
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				goods = new ArrayList<Good>();
			case XmlPullParser.START_TAG:
				if ("good".equals(parser.getName())) {
					int id = Integer.valueOf(parser.getAttributeValue(0));
					good = new Good();
					good.setId(id);
				} else if ("name".equals(parser.getName())) {
					good.setName(parser.nextText());
				} else if ("univalent".equals(parser.getName())) {
					good.setUnivalent(Integer.valueOf(parser.nextText()));
				} else if ("quantity".equals(parser.getName())) {
					good.setQuantity(Integer.valueOf(parser.nextText()));
				}
				break;
			case XmlPullParser.END_TAG:
				if ("good".equals(parser.getName())) {
					goods.add(good);
					good = null;
				}
				break;
			}
			event = parser.next();
		}
		return goods;
	}
}
