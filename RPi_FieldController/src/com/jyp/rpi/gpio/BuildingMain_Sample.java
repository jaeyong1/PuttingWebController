package com.jyp.rpi.gpio;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class BuildingMain_Sample {
	private PyObject buildingClass;
	private PythonInterpreter interpreter;

	public BuildingMain_Sample() {
		interpreter = new PythonInterpreter();
		interpreter.exec("from Building import Building");
		buildingClass = interpreter.get("Building");
	}

	public BuildingType create(String name, String location, String id) {

		PyObject buildingObject = buildingClass.__call__(new PyString(name), new PyString(location), new PyString(id));
		return (BuildingType) buildingObject.__tojava__(BuildingType.class);
	}

	public static void main(String[] args) {
		BuildingMain_Sample factory = new BuildingMain_Sample();

		BuildingType building = (BuildingType) factory.create("BUILDING-A", "100 WEST MAIN", "1");
		System.out.println("[java] " + building.getBuildingAddress());

	}
}
