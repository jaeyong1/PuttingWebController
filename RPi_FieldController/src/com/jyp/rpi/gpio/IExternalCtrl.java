package com.jyp.rpi.gpio;

public interface IExternalCtrl {
	public static final int STATE_NORMAL_OPERATION = 1;
	public static final int STATE_CHANGING_FIELD_VALUE = 2;
	public static final int STATE_INTERNET_CONNECTION_ERROR = 3;
	public static final int STATE_RESERVED_1 = 4;
	public static final int STATE_RESERVED_2 = 5;
	public static final int STATE_RESERVED_3 = 6;

	public int InitExternalDevice();

	public int setMotorValue(int channel, int percentage);

	public int setStateLED(int state);

	public boolean isError();

	public void setBootDoneLedOn();

	public void setBootDoneLedOff();

}