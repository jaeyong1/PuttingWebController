import java.net.DatagramSocket;
import java.net.SocketException;

public class RPi_FieldController {

	static MQTTMonitor smc = null;

	public static void main(String[] args) {

		try {
			DatagramSocket isRun = new DatagramSocket(1103);
			System.out.println("First run. Keep this Processor");
		} catch (SocketException e) {
			System.out.println("Duplicated run. Exit Program");
		}
		smc = new MQTTMonitor();
	}

}
