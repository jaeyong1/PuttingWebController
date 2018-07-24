package com.jyp.putting.utils;

import java.util.Random;

import org.eclipse.paho.client.mqttv3.MqttClient;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import org.eclipse.paho.client.mqttv3.MqttException;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MQTT Publish 테스트
 * 
 */

/*-
 * 
 * 접속 설정 요약
 * 
 [Broker서버] tcp://broker.mqttdashboard.com:1883
 [토픽 prefix] /jyp/rpicontrol/
 [단말 postfix] 예) rpihome1/명령어      --- DB에 있음
 
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
public class MQTTPublishTest {
	public String deviceId = "publisher";

	String topic = "/jyp/rpicontrol/";// add rPI ID
	int qos = 2;
	String broker = "tcp://broker.mqttdashboard.com:1883";

	String MQTTclientID = "WEBSERVER_" + deviceId + (new Random().nextInt(100) + 300);
	MemoryPersistence persistence = new MemoryPersistence();

	public MQTTPublishTest(String rpiId, String contentmsg) {

		try {
			String content = contentmsg;
			String topic = this.topic + rpiId;
			MqttClient sampleClient = new MqttClient(broker, MQTTclientID, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");
			System.out.println("Publishing message: " + content);
			MqttMessage message = new MqttMessage(content.getBytes());
			message.setQos(qos);
			sampleClient.publish(topic, message);
			System.out.println("Message published");
			sampleClient.disconnect();
			System.out.println("Disconnected");
			// System.exit(0);
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
}