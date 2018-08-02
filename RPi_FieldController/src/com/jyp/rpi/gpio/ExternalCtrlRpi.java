package com.jyp.rpi.gpio;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class ExternalCtrlRpi implements ExternalCtrl {

	private PythonInterpreter interpreter;
	private PyObject buildingClass;
	private ExternalCtrl building;

	static private ExternalCtrlRpi instance = null;

	public static synchronized ExternalCtrlRpi getInstance() {
		if (instance == null) {
			instance = new ExternalCtrlRpi();
		}
		return instance;
	}

	private ExternalCtrlRpi() {
		// find .py file and load class
		interpreter = new PythonInterpreter();
		interpreter.exec("from PuttingI2C import PuttingI2C");
		buildingClass = interpreter.get("PuttingI2C");

	}

	public ExternalCtrl create() {
		// get class instance from .py
		PyObject buildingObject = buildingClass.__call__();
		return (ExternalCtrl) buildingObject.__tojava__(ExternalCtrl.class);
	}

	@Override
	public int InitExternalDevice() {
		System.out.println("[Rpi] Init");

		ExternalCtrlRpi factory = new ExternalCtrlRpi();
		building = (ExternalCtrl) factory.create();
		building.InitExternalDevice();
		return 0;
	}

	@Override
	public int setMotorValue(int channel, int percentage) {
		System.out.println("[Rpi] setMotorValue. Ch:" + channel + ", Value:" + percentage);
		return 0;
	}

	@Override
	public int setStateLED(int state) {
		System.out.println("[Rpi] setStateLED. state:" + state);
		return 0;
	}

	@Override
	public boolean isError() {
		System.out.println("[Rpi] isNoProblem. ");
		return true;
	}

	@Override
	public void setBootDoneLedOn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBootDoneLedOff() {
		// TODO Auto-generated method stub

	}

}
