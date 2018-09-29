package com.jyp.shopmanager.domain;

import java.util.ArrayList;

public class RoomReservationList {
	private boolean success;
	private ArrayList<RoomReservation> list;
	private int total_count;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<RoomReservation> getList() {
		return list;
	}

	public void setList(ArrayList<RoomReservation> list) {
		this.list = list;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

}
