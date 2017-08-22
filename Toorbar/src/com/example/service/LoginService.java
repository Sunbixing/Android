package com.example.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import com.example.domen.Person;
import com.example.utils.StreamTool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Xml;

public class LoginService {
	private Context context;
	

	public LoginService(Context context) {
		this.context = context;
	}

	public static List<Person> getLast() throws Exception {
		
		String path = "http://192.168.0.122:8080/web/Login";
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

	@SuppressWarnings("unused")
	private static List<Person> parsejson(InputStream stream) throws Exception{
		List<Person> persons=new ArrayList<Person>();
		byte[] data=StreamTool.read(stream);
		String js=new String(data);
		JSONArray  array=new JSONArray(js);
		for (int i = 0; i < array.length(); i++) {
			JSONObject  jsonObject=array.getJSONObject(i);
		Person person=new Person(jsonObject.getInt("id"),jsonObject.getString("name"), jsonObject.getString("pwd"));
		persons.add(person);
		}
		return persons;
	}

	public static List<Person> parsexml(InputStream stream) throws Exception {
		List<Person> persons = null;
		Person person = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(stream, "UTF-8");
		int event = parser.getEventType();
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:
				persons = new ArrayList<Person>();
			case XmlPullParser.START_TAG:
				if ("person".equals(parser.getName())) {
					int id = Integer.valueOf(parser.getAttributeValue(0));
					person = new Person();
					person.setId(id);
				} else if ("name".equals(parser.getName())) {
					person.setName(parser.nextText());
				} else if ("pwd".equals(parser.getName())) {
					person.setPwd(parser.nextText());
				}
				break;
			case XmlPullParser.END_TAG:
				if ("person".equals(parser.getName())) {
					persons.add(person);
					person = null;
				}
				break;
			}
			event = parser.next();
		}
		return persons;
	}

	public void save(String nameString, String namePwd) {
		SharedPreferences preferences=context.getSharedPreferences("use", Context.MODE_PRIVATE);
		Editor editor=preferences.edit();
		editor.putString("name",nameString);
		editor.putString("pwd",namePwd);	
		editor.commit();
	}
	public  Map<String,String> Preferences(){
		Map<String,String> use=new HashMap<String, String>();
		SharedPreferences preferences=context.getSharedPreferences("use", Context.MODE_PRIVATE);
		use.put("name", preferences.getString("name", ""));
		use.put("pwd", preferences.getString("pwd", ""));	
		return use;
	}

	
}
