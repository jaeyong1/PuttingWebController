package com.jyp.rpi.gpio;

import java.io.*;
import java.net.*;

public class ExternalCtrlImpl implements IExternalCtrl {

	// to state led thread
	Thread stateledthr = null;

	// default value of state led
	static private int stateledcnt = IExternalCtrl.STATE_NORMAL_OPERATION;

	// to singleton
	static private ExternalCtrlImpl instance = null;

	public static synchronized ExternalCtrlImpl getInstance() {
		if (instance == null) {
			instance = new ExternalCtrlImpl();
		}
		return instance;
	}

	public void sendExternalControlMessage(String msg) {
		// send TCP to 9999 port

		try {
			// make socket
			Socket sock = new Socket("0.0.0.0", 9999);
			OutputStream out = sock.getOutputStream();
			InputStream in = sock.getInputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			// send
			pw.print(msg);
			System.out.println("< send msg : " + msg);
			pw.flush();

			// receive
			// String inline = br.readLine();
			// System.out.println(inline);

			// close
			pw.close();
			sock.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// constructor
	private ExternalCtrlImpl() {

		// Daemon thread to control State Led
		stateledthr = new Thread(new stateLedThread());
		stateledthr.setDaemon(true);
		System.out.println("thread start!!");
		stateledthr.start();

	}

	@Override
	public int InitExternalDevice() {
		System.out.println("[ExternalCtrlImpl] Init");
		sendExternalControlMessage("all_zero");
		return 0;
	}

	@Override
	public int setMotorValue(int channel, int percentage) {
		System.out.println("[ExternalCtrlImpl] setMotorValue. Ch:" + channel + ", Value:" + percentage);
		sendExternalControlMessage("motor " + channel + " " + percentage);
		return 0;
	}

	@Override
	public int setStateLED(int state) {
		System.out.println("[ExternalCtrlImpl] setStateLED. state:" + state);
		// set global variable
		stateledcnt = state;
		return 0;
	}

	@Override
	public boolean isError() {
		System.out.println("[ExternalCtrlImpl] isError? ");
		// boolean r = getPyputtingInstance().isError();
		// System.out.println("[ExternalCtrlImpl] isError=" + r);
		return false;
	}

	@Override
	public void setBootDoneLedOn() {
		// BootDone GPIO High
		sendExternalControlMessage("bootdoneled on");
	}

	@Override
	public void setBootDoneLedOff() {
		// BootDone GPIO Low
		sendExternalControlMessage("bootdoneled off");

	}

	// ONLY for stateLedThread
	protected static int getStateledcnt() {
		return stateledcnt;
	}

}

/**
 * Thread for repeating on and off
 * 
 */
class stateLedThread implements Runnable {

	@Override
	public void run() {

		int i = 0;
		while (true) {
			try {
				for (i = 0; i < ExternalCtrlImpl.getStateledcnt(); i++) {
					// State GPIO High
					ExternalCtrlImpl.getInstance().sendExternalControlMessage("stateled on");
					Thread.sleep(180);

					// State GPIO Low
					ExternalCtrlImpl.getInstance().sendExternalControlMessage("stateled off");
					Thread.sleep(180);
				}

				// State GPIO Low
				ExternalCtrlImpl.getInstance().sendExternalControlMessage("stateled off");
				Thread.sleep(1200);

			} catch (InterruptedException e) {
			}
		}
	}
}
