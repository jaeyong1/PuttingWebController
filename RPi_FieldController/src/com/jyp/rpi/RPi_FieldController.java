package com.jyp.rpi;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;

import com.jyp.rpi.gpio.ExternalCtrlImpl;
import com.jyp.rpi.gpio.IExternalCtrl;
import com.jyp.rpi.util.SystemInfo;

public class RPi_FieldController {

	/** MQTT클래스의 클라이언트 객체 */
	private static MQTTMonitor mqttclient = null;

	/** 풀밭변경 stop을 받았는지 체크 */
	private static boolean fieldchangingkeepgoing = false;

	/** MQTT 클라이언트 객체 리턴 */
	public static MQTTMonitor getMqttclient() {
		return mqttclient;
	}

	/** Get - 풀밭변경 stop을 여부 */
	public static boolean isFieldchangingkeepgoing() {
		return fieldchangingkeepgoing;
	}

	/** Set - 풀밭변경 stop을 여부 */
	public static void setFieldchangingkeepgoing(boolean fieldchangingkeepgoing) {
		RPi_FieldController.fieldchangingkeepgoing = fieldchangingkeepgoing;
	}

	/** 웹DB에서 받은 풀밭 높이데이터 */
	private static String strRawFieldStringData = "";

	/** 앱 중복실행방지용 */
	private static ServerSocket onlyOncInstanceSocket;

	/**
	 * MQTT에 Exception 발생시 재시작
	 */
	public static void restartMQTT() {
		mqttclient.MQTTdisconnect();
		mqttclient = null;
		mqttclient = new MQTTMonitor();
		if (mqttclient.MQTTisconnected() != true) {
			MQTTMonitor.setRequestRestartMQTT(true);
		}
	}

	/** 풀밭변경 시작 */
	public static void startExternalFieldChanging() {
		System.out.println("startExternalFieldChanging() - new Thread");
		Thread thrfieldchange = new Thread(new FieldChangeThread());
		setFieldchangingkeepgoing(true);
		thrfieldchange.start();
	}

	/** 풀밭변경 멈춤 */
	public static void stopExternalFieldChanging() {
		System.out.println("stopExternalFieldChanging()");
		setFieldchangingkeepgoing(false);
	}

	/** Get - 웹DB에서 받은 풀밭 높이데이터 */
	public static String getStrRawFieldStringData() {
		return strRawFieldStringData;
	}

	/** Set - 웹DB에서 받은 풀밭 높이데이터 */
	public static void setStrRawFieldStringData(String strRawFieldStringData) {
		RPi_FieldController.strRawFieldStringData = strRawFieldStringData;
	}

	static private IExternalCtrl exctrl = null;

	public static IExternalCtrl getExtenalCtrlInstance() {
		if (exctrl == null) {
			System.out.println("Check OS....");
			// 시스템별 외부제어 객체 구분
			if (SystemInfo.isWindows()) {
				System.out.println("- Windows System(for dev)");
				ExternalCtrlImpl.setDummyMode(true);
				exctrl = ExternalCtrlImpl.getInstance();

			} else if (SystemInfo.isUnix()) {
				System.out.println("- Linux System(Raspberry Pi)");
				exctrl = ExternalCtrlImpl.getInstance();

			} else {
				System.out.println("Unkwon System OS : " + SystemInfo.getOSString());
			}

		}
		return exctrl;
	}

	public static void main(String[] args) {
		try {
			onlyOncInstanceSocket = new ServerSocket(65535, 1, InetAddress.getLocalHost());
			System.out.println("[main] First run. Keep this Processor");
		} catch (Exception e) {
			System.out.println("[main] Duplicated run. Exit Program");
			return;
		}
		// MQTT Fatal 감시 데몬 시작
		Thread mqttfatalth = new Thread(new MQTTFatalCheckDaemonThread());
		mqttfatalth.start();

		// MQTT 연결
		mqttclient = new MQTTMonitor();

		// 시스템 OS 확인
		System.out.println("[main] isWindows : " + SystemInfo.isWindows());
		System.out.println("[main] isUnix : " + SystemInfo.isUnix());

		// Python code 연결 및 GPIO 구동준비
		getExtenalCtrlInstance().InitExternalDevice();

		// Boot_Done PIN high
		getExtenalCtrlInstance().setBootDoneLedOn();
	}

}

/**
 * 외부장치 제어 스레드
 */
class FieldChangeThread implements Runnable {

	@Override
	public void run() {
		int index = 0;

		RPi_FieldController.getExtenalCtrlInstance().setStateLED(IExternalCtrl.STATE_CHANGING_FIELD_VALUE);

		// 웹 DB 문자열 (xx,yy,zz) --> 문자열 배열로 파싱
		String rawdata = RPi_FieldController.getStrRawFieldStringData();
		String[] tokens = rawdata.split(",");

		for (index = 0; index < 11; index++) { // index 0 ~ 10 (11 step)
			if (RPi_FieldController.isFieldchangingkeepgoing() == false) {
				System.out.println("[Thread] ExternalGPIOControl - Stop");
				index = Integer.MAX_VALUE;
				return;
			}

			// 예외처리
			if (rawdata == "" || tokens == null || tokens.length < 16) {
				System.out.println("[Thread] ExternalGPIOControl - raw data is blank");
				index = Integer.MAX_VALUE;
				return;
			}

			try {
				// sleep alittle (200ms)
				Thread.sleep(200);

				// send MQTT publish
				RPi_FieldController.getMqttclient().MQTTpublish("prog" + index + "0");

				// SOME GPIO change
				switch (index) {
				case 0:
					break;
				case 1:
					System.out.println("[Thread] - set external Height device 1 " + tokens[0]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(0, Integer.parseInt(tokens[0]));
					System.out.println("[Thread] - set external Height device 2 " + tokens[1]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(1, Integer.parseInt(tokens[1]));

					break;
				case 2:
					System.out.println("[Thread] - set external Height device 3 " + tokens[2]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(2, Integer.parseInt(tokens[2]));
					System.out.println("[Thread] - set external Height device 4 " + tokens[3]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(3, Integer.parseInt(tokens[3]));
					break;
				case 3:
					System.out.println("[Thread] - set external Height device 5 " + tokens[4]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(4, Integer.parseInt(tokens[4]));
					System.out.println("[Thread] - set external Height device 6 " + tokens[5]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(5, Integer.parseInt(tokens[5]));

					break;
				case 4:
					System.out.println("[Thread] - set external Height device 7 " + tokens[6]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(6, Integer.parseInt(tokens[6]));
					System.out.println("[Thread] - set external Height device 8 " + tokens[7]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(7, Integer.parseInt(tokens[7]));

					break;
				case 5:
					System.out.println("[Thread] - set external Height device 9 " + tokens[8]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(8, Integer.parseInt(tokens[8]));
					System.out.println(" - set external Height device 10 " + tokens[9]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(9, Integer.parseInt(tokens[9]));

					break;

				case 6:
					System.out.println("[Thread] - set external Height device 11 " + tokens[10]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(10, Integer.parseInt(tokens[10]));
					System.out.println("[Thread] - set external Height device 12 " + tokens[11]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(11, Integer.parseInt(tokens[11]));

					break;
				case 7:
					System.out.println("[Thread] - set external Height device 13 " + tokens[12]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(12, Integer.parseInt(tokens[12]));
					System.out.println("[Thread] - set external Height device 14 " + tokens[13]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(13, Integer.parseInt(tokens[13]));

					break;
				case 8:
					System.out.println("[Thread] - set external Height device 15 " + tokens[14]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(14, Integer.parseInt(tokens[14]));
					System.out.println("[Thread] - set external Height device 16 " + tokens[15]);
					RPi_FieldController.getExtenalCtrlInstance().setMotorValue(15, Integer.parseInt(tokens[15]));
					RPi_FieldController.getExtenalCtrlInstance().setStateLED(IExternalCtrl.STATE_NORMAL_OPERATION);
					break;
				case 9:
					break;
				case 10:
					break;

				default:
					break;
				}
				//
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // for
		System.out.println("[Thread] ExternalGPIOControl - finish");

	}

}

/**
 * MQTT클래스에서 Fatal에러가 발생하면 재시작 할수 있도록 스레드 띄움
 */
class MQTTFatalCheckDaemonThread implements Runnable {

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1500); // every 1.5 sec

				if (MQTTMonitor.isRequestRestartMQTT() == true) {
					System.out.println("==================================");
					System.out.println("MQTT Fatal flag is setted. Restart");
					System.out.println("==================================");
					MQTTMonitor.setRequestRestartMQTT(false);
					RPi_FieldController.restartMQTT();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		} // while
	}

}