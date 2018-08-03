package com.jyp.rpi.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class SaveMyRpiSettings {

	static private String myDeviceId;
	static private String mqttBrokerServer;
	static private String webServer;

	String[] args;

	@SuppressWarnings("unchecked")
	public static void writeJsonFile() {
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		String curpath = jarDir.getAbsolutePath();
		String jsonfilepath = curpath + "/putting.conf";

		JSONObject puttingInfo = new JSONObject();

		puttingInfo.put(".myDeviceId", myDeviceId);
		puttingInfo.put("mqttBrokerServer", mqttBrokerServer);
		puttingInfo.put("webServer", webServer);

		try {

			FileWriter file = new FileWriter(jsonfilepath);
			file.write(puttingInfo.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(puttingInfo);
		System.out.println(jsonfilepath);
		System.out.println("saved.");

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner user_input = new Scanner(System.in);
		String keyin = "";
		System.out.println("------------------------------------");
		System.out.println(" Settting");
		System.out.println("------------------------------------");
		System.out.print("1. My Device ID [rpihome1] : ");
		keyin = user_input.nextLine();
		if (keyin.equals("")) {
			myDeviceId = "rpihome1";
		} else {
			myDeviceId = keyin.trim();
		}

		System.out.print("2. MQTT Broker server [tcp://broker.mqttdashboard.com:1883] : ");
		keyin = user_input.nextLine();
		if (keyin.equals("")) {
			mqttBrokerServer = "tcp://broker.mqttdashboard.com:1883";
		} else {
			mqttBrokerServer = keyin.trim();
		}

		System.out.print("3. HTTP server [http://192.168.0.50:8080/putting/] : ");
		keyin = user_input.nextLine();
		if (keyin.equals("")) {
			webServer = "http://192.168.0.50:8080/putting/";
		} else {
			webServer = keyin.trim();
		}

		writeJsonFile();
	}

}
