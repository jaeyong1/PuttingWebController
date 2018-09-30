package com.jyp.shopmanager.domain;

public class RoomReservation {
	private long ReservedSchduleId;
	private String ReservedRoomNumber;
	private String ReservedStartTime;
	private String ReservedEndTime;
	private String CustCode;
	private String EmCode;
	private String ReservedState;

	public RoomReservation(long reservedSchduleId, String reservedRoomNumber, String reservedStartTime,
			String reservedEndTime, String custCode, String emCode, String reservedState) {
		super();
		ReservedSchduleId = reservedSchduleId;
		ReservedRoomNumber = reservedRoomNumber;
		ReservedStartTime = reservedStartTime;
		ReservedEndTime = reservedEndTime;
		CustCode = custCode;
		EmCode = emCode;
		ReservedState = reservedState;
	}

	public long getReservedSchduleId() {
		return ReservedSchduleId;
	}

	public void setReservedSchduleId(int reservedSchduleId) {
		ReservedSchduleId = reservedSchduleId;
	}

	public String getReservedRoomNumber() {
		return ReservedRoomNumber;
	}

	public void setReservedRoomNumber(String reservedRoomNumber) {
		ReservedRoomNumber = reservedRoomNumber;
	}

	public String getReservedStartTime() {
		return ReservedStartTime;
	}

	public void setReservedStartTime(String reservedStartTime) {
		ReservedStartTime = reservedStartTime;
	}

	public String getReservedEndTime() {
		return ReservedEndTime;
	}

	public void setReservedEndTime(String reservedEndTime) {
		ReservedEndTime = reservedEndTime;
	}

	public String getCustCode() {
		return CustCode;
	}

	public void setCustCode(String custCode) {
		CustCode = custCode;
	}

	public String getEmCode() {
		return EmCode;
	}

	public void setEmCode(String emCode) {
		EmCode = emCode;
	}

	public String getReservedState() {
		return ReservedState;
	}

	public void setReservedState(String reservedState) {
		ReservedState = reservedState;
	}

}
