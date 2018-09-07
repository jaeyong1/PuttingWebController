package com.jyp.putting.domain;

import java.util.ArrayList;

public class PlayerList {
	private boolean success;
	private ArrayList<Player> list;
	private int total_count;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<Player> getList() {
		return list;
	}

	public void setList(ArrayList<Player> list) {
		this.list = list;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

}
