package com.jyp.rpi.gpio;

public class ExternalCtrlDummy implements ExternalCtrl {

	@Override
	public int InitExternalDevice() {
		System.out.println("***********************");
		System.out.println("* [Dummy] Init");
		System.out.println("***********************");
		return 0;
	}

	@Override
	public int setMotorValue(int channel, int percentage) {
		System.out.println("***********************");
		System.out.println("* [Dummy] setMotorValue. Ch:" + channel + ", Value:" + percentage);
		System.out.println("***********************");
		return 0;
	}

	@Override
	public int setStateLED(int state) {
		System.out.println("***********************");
		System.out.println("* [Dummy] setStateLED. state:" + state);
		System.out.println("***********************");
		return 0;
	}

	@Override
	public boolean isNoProblem() {
		System.out.println("***********************");
		System.out.println("* [Dummy] isNoProblem. ");
		System.out.println("***********************");
		return true;
	}

}
