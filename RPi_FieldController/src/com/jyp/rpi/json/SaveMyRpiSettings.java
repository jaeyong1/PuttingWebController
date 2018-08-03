package com.jyp.rpi.json;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;

public class SaveMyRpiSettings {

	public static void writeJsonFile() {
		
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + s);
		
		
		JSONObject puttingInfo = new JSONObject();
		puttingInfo.put(".myDeviceId", "rpihome1");
		puttingInfo.put("mqttBrokerServer", "tcp://broker.mqttdashboard.com:1883");
		puttingInfo.put("webServer", "http://192.168.0.50:8080/putting/");

		try {
			FileWriter file = new FileWriter("putting.conf");
			file.write(puttingInfo.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(puttingInfo);

	}

	public static void main(String[] args) {
		writeJsonFile();
	}

}
