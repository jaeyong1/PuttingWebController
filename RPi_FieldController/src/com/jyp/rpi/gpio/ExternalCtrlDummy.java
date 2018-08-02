package com.jyp.rpi.gpio;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class ExternalCtrlDummy implements ExternalCtrl {

	// to connect with python
	private PythonInterpreter interpreter;
	private PyObject pyputtingClass;
	private ExternalCtrl pyputtingInstance;

	// to state led thread
	Thread stateledthr = null;

	static private int stateledcnt = ExternalCtrl.STATE_NORMAL_OPERATION;

	// to singleton
	static private ExternalCtrlDummy instance = null;

	public static synchronized ExternalCtrlDummy getInstance() {
		if (instance == null) {
			instance = new ExternalCtrlDummy();

		}
		return instance;
	}

	// constructor
	private ExternalCtrlDummy() {
		// find .py file and load class
		interpreter = new PythonInterpreter();
		interpreter.exec("from PuttingDummy import PuttingDummy");
		pyputtingClass = interpreter.get("PuttingDummy");
		pyputtingInstance = (ExternalCtrl) create();

		// Daemon thread to control State Led
		stateledthr = new Thread(new stateLedThread());
		stateledthr.setDaemon(true);
		System.out.println("thread start!!");
		stateledthr.start();

	}

	// internal usage
	private ExternalCtrl create() {
		// get instance from .py
		PyObject buildingObject = pyputtingClass.__call__();
		return (ExternalCtrl) buildingObject.__tojava__(ExternalCtrl.class);
	}

	@Override
	public int InitExternalDevice() {
		System.out.println("* [Dummy] Init");
		pyputtingInstance.InitExternalDevice();
		return 0;
	}

	@Override
	public int setMotorValue(int channel, int percentage) {
		System.out.println("* [Dummy] setMotorValue. Ch:" + channel + ", Value:" + percentage);
		getPyputtingInstance().setMotorValue(channel, percentage);
		return 0;
	}

	@Override
	public int setStateLED(int state) {
		System.out.println("* [Dummy] setStateLED. state:" + state);
		stateledcnt = state;
		return 0;
	}

	@Override
	public boolean isError() {
		System.out.println("* [Dummy] isError. ");
		return true;
	}

	@Override
	public void setBootDoneLedOn() {
		// BootDone GPIO High
		getPyputtingInstance().setBootDoneLedOn();
	}

	@Override
	public void setBootDoneLedOff() {
		// BootDone GPIO Low
		getPyputtingInstance().setBootDoneLedOff();

	}

	// ONLY for stateLedThread
	protected static int getStateledcnt() {
		return stateledcnt;
	}

	protected ExternalCtrl getPyputtingInstance() {
		return pyputtingInstance;
	}

}

class stateLedThread implements Runnable {

	@Override
	public void run() {

		int i = 0;
		while (true) {
			try {
				for (i = 0; i < ExternalCtrlDummy.getStateledcnt(); i++) {
					// State GPIO High
					// System.out.print("@");
					ExternalCtrlDummy.getInstance().getPyputtingInstance().setStateLED(1);

					Thread.sleep(180);

					// State GPIO Low
					// System.out.print(".");
					ExternalCtrlDummy.getInstance().getPyputtingInstance().setStateLED(0);
					Thread.sleep(180);
				}

				// State GPIO Low
				// System.out.print("...");
				ExternalCtrlDummy.getInstance().getPyputtingInstance().setStateLED(0);
				Thread.sleep(1200);

			} catch (InterruptedException e) {
			}
		}
	}
}
