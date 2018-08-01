package com.jyp.rpi.gpio;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class ExternalCtrlRpi implements ExternalCtrl {

	private PyObject buildingClass;
	private PythonInterpreter interpreter;
	private ExternalCtrl building;

	public ExternalCtrlRpi() {
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
	public boolean isNoProblem() {
		System.out.println("[Rpi] isNoProblem. ");
		return true;
	}

}
