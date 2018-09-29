package com.jyp.shopmanager.domain;

public class RoomReservation {
	private int ReservedSchduleId;
	private String ReservedDate;
	private String ShopCode;
	private String ReservedRoomNumber;
	private String ReservedStartTime;
	private String ReservedEndTime;
	private String CustCode;
	private String EmCode;
	private String ReservedState;

	public RoomReservation(int reservedSchduleId, String reservedDate, String shopCode, String reservedRoomNumber,
			String reservedStartTime, String reservedEndTime, String custCode, String emCode, String reservedState) {
		super();
		ReservedSchduleId = reservedSchduleId;
		ReservedDate = reservedDate;
		ShopCode = shopCode;
		ReservedRoomNumber = reservedRoomNumber;
		ReservedStartTime = reservedStartTime;
		ReservedEndTime = reservedEndTime;
		CustCode = custCode;
		EmCode = emCode;
		ReservedState = reservedState;
	}

	public int getReservedSchduleId() {
		return ReservedSchduleId;
	}

	public void setReservedSchduleId(int reservedSchduleId) {
		ReservedSchduleId = reservedSchduleId;
	}

	public String getReservedDate() {
		return ReservedDate;
	}

	public void setReservedDate(String reservedDate) {
		ReservedDate = reservedDate;
	}

	public String getShopCode() {
		return ShopCode;
	}

	public void setShopCode(String shopCode) {
		ShopCode = shopCode;
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
