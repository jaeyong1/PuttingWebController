package com.jyp.rpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.jyp.rpi.gpio.IExternalCtrl;
import com.jyp.rpi.json.MyRpiSettings;

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
 [웹DB site] http://localhost:8080/putting/
 
 [명령어]
 startfieldchange - Web에서 '적용하기' 버튼 누름
 stopfieldchange - Web에서 적용 취소시킴 
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
	/** 이 기기고유 ID (=DB에 deviceId) */
	private String deviceId = MyRpiSettings.getMyDeviceId();

	/** ================================================================================== */

	/** 브로커서버에서 퍼팅기 프로젝트의 토픽 */
	private String topic = "/jyp/rpicontrol/";

	/** 브로커서버 주소 */
	private final String BROKER_URL = MyRpiSettings.getMqttBrokerServer();

	/** MQTT 클래스 재시작 필요 */
	private static boolean requestRestartMQTT = false;

	/** 내 클라이언트 ID, 중복시 먼저접속 끊어짐 */
	private String MQTTclientID = "RPI_Client_" + deviceId + (new Random().nextInt(100) + 100);

	/** 브로커와 연결을 가지고 있는 객체 */
	private MqttClient myClient;
	private MqttConnectOptions connOpt;

	/** 이 라즈베리파이가 응답할 토픽 */
	String myTopic = topic + deviceId + "/";

	/** 웹DB map정보 쿼리 URL */
	private final String WebSiteURL = MyRpiSettings.getWebServer() + "fielddata?mapid=";

	/** 생성자. 시작시 브로커서버에 접속 */
	public MQTTMonitor() {
		super();
		MQTTConnect();
		MQTTSub();
	}

	/**
	 * True일 경우, MQTT 클래스가 Exception 발생으로 스스로 복구하지 못하는 상태에 빠진 상태. 외부에서 재시작 필요
	 * 
	 * @return 재시작필요여부
	 */
	public static boolean isRequestRestartMQTT() {
		return requestRestartMQTT;
	}

	/**
	 * MQTT를 외부에서 재시작 후에 false로 리셋해주어야 함
	 * 
	 * @param requestRestartMQTT
	 */
	public static void setRequestRestartMQTT(boolean requestRestartMQTT) {
		if (requestRestartMQTT == true) {
			RPi_FieldController.getExtenalCtrlInstance().setStateLED(IExternalCtrl.STATE_INTERNET_CONNECTION_ERROR);
		} else {
			RPi_FieldController.getExtenalCtrlInstance().setStateLED(IExternalCtrl.STATE_NORMAL_OPERATION);
		}
		MQTTMonitor.requestRestartMQTT = requestRestartMQTT;

	}

	/** Get - 기기 고유 ID */
	public String getDeviceId() {
		return deviceId;
	}

	/** Set - 기기 고유 ID */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	// Connect to Broker
	public void MQTTConnect() {
		try {
			System.out.println("MQTTConnect()");
			connOpt = new MqttConnectOptions();
			myClient = new MqttClient(BROKER_URL, MQTTclientID);
			myClient.setCallback(this);
			System.out.println("Connected to " + BROKER_URL);
			myClient.connect(connOpt);
		} catch (MqttException e) {
			e.printStackTrace();
			setRequestRestartMQTT(true);
			System.out.println("[FATAL] MQTTConnect(). set MQTTMoritor restart flag");
		}
	}

	// Broker와 연결해제. Exception 발생 무시가능.
	public void MQTTdisconnect() {
		try {
			myClient.disconnect();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 브로커와 연결여부
	public boolean MQTTisconnected() {
		return myClient.isConnected();
	}

	// Subscribe 하기. 모니터링시작
	public void MQTTSub() {
		try {
			int subQoS = 2;
			System.out.println("Subscribed Topic is " + myTopic);
			myClient.subscribe(myTopic, subQoS);
		} catch (Exception e) {
			e.printStackTrace();
			setRequestRestartMQTT(true);
			System.out.println("[FATAL] MQTTSub(). set MQTTMoritor restart flag");
		}

	}

	@Override
	public void connectionLost(Throwable t) {
		System.out.println("MQTT Connection Lost");
		MQTTConnect();
		MQTTSub();
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("MQTT deliveryComplete");

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("---------------------------------------");
		System.out.println("MQTT messageArrived. topic:" + topic + ", message:" + message);

		// 풀밭변경 시작 명령어 수신
		if (message.toString().startsWith("startfieldchange")) {
			int startlen = "startfieldchange.mapid=".length();
			String strMapId = message.toString().substring(startlen);
			System.out.println("Get Field Data from DB server. Map ID = " + strMapId);

			// Read raw data from DB
			String rawDBData = getFieldData(WebSiteURL + strMapId);
			RPi_FieldController.setStrRawFieldStringData(rawDBData);

			RPi_FieldController.startExternalFieldChanging();

		}

		// 풀밭변경 멈추기 명령어 수신
		if (message.toString().startsWith("stopfieldchange")) {
			RPi_FieldController.stopExternalFieldChanging();
		}

		System.out.println("---------------------------------------//");
		System.out.println("");

	}

	/** 웹 서버에서 기기제어 DB 데이터 가져옴 */
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
			System.out.println("url To Read is invalid");
			e.printStackTrace();

			setRequestRestartMQTT(true);
			System.out.println("[FATAL] MalformedURLException. set MQTTMoritor restart flag");
		} catch (IOException e) {
			System.out.println("[FATAL] IOException. Internet connection failed");
			e.printStackTrace();

			setRequestRestartMQTT(true);
			System.out.println("[FATAL] IOException. set MQTTMoritor restart flag");
		}

		return result.toString();
	}

	/** 풀밭변경 진행사항 MQTT로 뿌림 */
	public void MQTTpublish(String content) {
		int pubqos = 2;
		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(pubqos);
		try {
			myClient.publish(myTopic, message);
		} catch (MqttException e) {
			e.printStackTrace();
			setRequestRestartMQTT(true);
			System.out.println("[FATAL] MQTTpublish(). set MQTTMoritor restart flag");
		}
	}

}