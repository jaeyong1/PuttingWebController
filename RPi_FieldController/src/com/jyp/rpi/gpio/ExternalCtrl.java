package com.jyp.rpi.gpio;

public interface ExternalCtrl {

	/**
	 * Standby the Servo motor driver
	 * 
	 * @return
	 */
	public int InitExternalDevice();

	/**
	 * 
	 * @param channel
	 * @param percentage
	 * @return
	 */
	public int setMotorValue(int channel, int percentage);

	public int setStateLED(int state);

	public boolean isNoProblem();
}