package com.jyp.putting.utils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import org.eclipse.paho.client.mqttv3.MqttException;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyp.putting.controller.HomeController;

import java.io.*;
import java.net.*;

/**
 * MQTT Subscriber on Web server
 * 
 */

/*-
 * 
 * 접속 설정 요약
 * 
 [Broker서버] tcp://broker.mqttdashboard.com:1883
 [토픽 prefix] /jyp/rpicontrol/
 [단말 postfix] rpihome1/명령어      --- DB에 있음
 
 [명령어]
 startfieldchange - Web에서 '적용하기' 버튼 누름
 prog0 - Web의 progress바를 0%로 변경
 prog10 - Web의 progress바를 10%로 변경
 prog20 - Web의 progress바를 20%로 변경
 prog30 - Web의 progress바를 30%로 변경
 prog40 - Web의 progress바를 40%로 변경
 prog50 - Web의 progress바를 50%로 변경
 prog60 - Web의 progress바를 60%로 변경
 prog70 - Web의 progress바를 70%로 변경
 prog80 - Web의 progress바를 80%로 변경
 prog90 - Web의 progress바를 90%로 변경
 prog100 - Web의 progress바를 0%로 변경
  
 *  
 */
public class MQTTMonitor implements MqttCallback {
	String topic = "/jyp/rpicontrol/";// add rPI ID
	int qos = 2;
	String BROKER_URL = "tcp://broker.mqttdashboard.com:1883";
	String clientID = "JavaSample";
	MemoryPersistence persistence = new MemoryPersistence();
	private MqttClient myClient;
	private MqttConnectOptions connOpt;

	private static final Logger logger = LoggerFactory.getLogger(MQTTMonitor.class);

	public void runClient() {
		// Connect to Broker
		try {
			connOpt = new MqttConnectOptions();
			myClient = new MqttClient(BROKER_URL, clientID);
			myClient.setCallback(this);
			myClient.connect(connOpt);
		} catch (MqttException e) {
			e.printStackTrace();
		}

		// setup topic
		logger.info("Connected to " + BROKER_URL);
		String myTopic = topic + "rpihome1/";

		// subscribe to topic if subscriber
		try {
			int subQoS = 0;
			logger.info("Subscribed Topic is " + myTopic);
			myClient.subscribe(myTopic, subQoS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// constructor
	public MQTTMonitor() {
		super();
		runClient();
	}

	@Override
	public void connectionLost(Throwable t) {
		logger.info("MQTT Connection Lost");

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		logger.info("MQTT deliveryComplete");

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		logger.info("MQTT messageArrived. topic:" + topic + ", message:" + message);
		System.out.println("---------------------------------------");
		System.out.println(getFieldData("http://localhost:8080/putting/fielddata?mapid=1"));		
		System.out.println("---------------------------------------");
	}

	public String getFieldData(String urlToRead) {
		StringBuilder result = new StringBuilder();
		URL url;
		try {
			url = new URL(urlToRead);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (MalformedURLException e) {
			logger.info("url To Read is invalid");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("Internet connection failed");
			e.printStackTrace();
		}

		return result.toString();
	}
}