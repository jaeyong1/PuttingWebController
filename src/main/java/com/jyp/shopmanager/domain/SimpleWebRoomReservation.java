package com.jyp.shopmanager.domain;

/**
 * Web page에 표시하기 위한 Domain
 * 
 * Controller -> iPhone/Web
 * 
 * @author JY
 *
 */
public class SimpleWebRoomReservation {
	private int reservedRoomNumber;
	private String reservedEndTime;
	private String reservedState;
	private int waiting;

	public int getReservedRoomNumber() {
		return reservedRoomNumber;
	}

	public void setReservedRoomNumber(int reservedRoomNumber) {
		this.reservedRoomNumber = reservedRoomNumber;
	}

	public String getReservedEndTime() {
		return reservedEndTime;
	}

	public void setReservedEndTime(String reservedEndTime) {
		this.reservedEndTime = reservedEndTime;
	}

	public String getReservedState() {
		return reservedState;
	}

	public void setReservedState(String reservedState) {
		this.reservedState = reservedState;
	}

	public int getWaiting() {
		return waiting;
	}

	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

	public SimpleWebRoomReservation(int reservedRoomNumber, String reservedEndTime, String reservedState, int waiting) {
		super();
		this.reservedRoomNumber = reservedRoomNumber;
		this.reservedEndTime = reservedEndTime;
		this.reservedState = reservedState;
		this.waiting = waiting;
	}

	public SimpleWebRoomReservation() {
		super();
	}

}
