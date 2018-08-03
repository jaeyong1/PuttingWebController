package com.jyp.rpi.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MyRpiSettings {

	static private String myDeviceId;
	static private String mqttBrokerServer;
	static private String webServer;

	static private boolean readed = false;

	public static String getMyDeviceId() {
		if (!readed) {
			readJsonFile();
			readed = true;
		}
		return myDeviceId;
	}

	public static String getMqttBrokerServer() {
		if (!readed) {
			readJsonFile();
			readed = true;
		}
		return mqttBrokerServer;
	}

	public static String getWebServer() {
		if (!readed) {
			readJsonFile();
			readed = true;
		}
		return webServer;
	}

	private static void readJsonFile() {
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);

		System.out.println("Read JSON File(putting.conf)");
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("putting.conf"));
			JSONObject jsonObject = (JSONObject) obj;
			myDeviceId = (String) jsonObject.get(".myDeviceId");
			mqttBrokerServer = (String) jsonObject.get("mqttBrokerServer");
			webServer = (String) jsonObject.get("webServer");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}