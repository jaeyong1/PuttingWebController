package com.jyp.rpi.gpio;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class ExternalCtrlImpl implements IExternalCtrl {

	// to connect with python
	private PythonInterpreter interpreter;
	private PyObject pyputtingClass;
	private IExternalCtrl pyputtingInstance;

	// to state led thread
	Thread stateledthr = null;

	// default value of state led
	static private int stateledcnt = IExternalCtrl.STATE_NORMAL_OPERATION;

	// to singleton
	static private ExternalCtrlImpl instance = null;

	// dummy .py loading
	static private boolean isDummyMode = false;

	public static synchronized ExternalCtrlImpl getInstance() {
		if (instance == null) {
			instance = new ExternalCtrlImpl();
		}
		return instance;
	}

	public static void setDummyMode(boolean isDummyMode) {
		ExternalCtrlImpl.isDummyMode = isDummyMode;
		instance = null; // setting change -> reload object
	}

	// constructor
	private ExternalCtrlImpl() {
		// find .py file and load class
		interpreter = new PythonInterpreter();
		if (isDummyMode) {
			System.out.println("load PuttingDummy.py ...");
			interpreter.exec("from PuttingDummy import PuttingDummy");
			pyputtingClass = interpreter.get("PuttingDummy");
		} else {
			System.out.println("load PuttingRpi.py ...");
			interpreter.exec("from PuttingRpi import PuttingRpi");
			pyputtingClass = interpreter.get("PuttingRpi");
		}
		pyputtingInstance = (IExternalCtrl) create();

		// Daemon thread to control State Led
		stateledthr = new Thread(new stateLedThread());
		stateledthr.setDaemon(true);
		System.out.println("thread start!!");
		stateledthr.start();

	}

	// internal usage
	private IExternalCtrl create() {
		// get instance from .py
		PyObject buildingObject = pyputtingClass.__call__();
		return (IExternalCtrl) buildingObject.__tojava__(IExternalCtrl.class);
	}

	@Override
	public int InitExternalDevice() {
		System.out.println("* [Dummy] Init");
		getPyputtingInstance().InitExternalDevice();
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
		// set global variable
		stateledcnt = state;
		return 0;
	}

	@Override
	public boolean isError() {
		System.out.println("* [Dummy] isError? ");
		boolean r = getPyputtingInstance().isError();
		System.out.println("* [Dummy] isError=" + r);
		return r;
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

	protected IExternalCtrl getPyputtingInstance() {
		return pyputtingInstance;
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
					ExternalCtrlImpl.getInstance().getPyputtingInstance().setStateLED(1);

					Thread.sleep(180);

					// State GPIO Low
					ExternalCtrlImpl.getInstance().getPyputtingInstance().setStateLED(0);
					Thread.sleep(180);
				}

				// State GPIO Low
				ExternalCtrlImpl.getInstance().getPyputtingInstance().setStateLED(0);
				Thread.sleep(1200);

			} catch (InterruptedException e) {
			}
		}
	}
}
