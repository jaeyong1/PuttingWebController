package com.jyp.putting.domiain;

public class Player {
	int LocationId; // 지점ID
	String LocationName; // 지점이름
	int deviceId; // 장치ID
	int selectedMapId; // 선택한 지도 ID
	String heightData; // 높이데이터
	int runState; // 동작상태

	public int getLocationId() {
		return LocationId;
	}

	public void setLocationId(int locationId) {
		LocationId = locationId;
	}

	public String getLocationName() {
		return LocationName;
	}

	public void setLocationName(String locationName) {
		LocationName = locationName;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getSelectedMapId() {
		return selectedMapId;
	}

	public void setSelectedMapId(int selectedMapId) {
		this.selectedMapId = selectedMapId;
	}

	public String getHeightData() {
		return heightData;
	}

	public void setHeightData(String heightData) {
		this.heightData = heightData;
	}

	public int getRunState() {
		return runState;
	}

	public void setRunState(int runState) {
		this.runState = runState;
	}

	public Player() {
		super();
	}

	public Player(int locationId, String locationName, int deviceId, int selectedMapId, String heightData,
			int runState) {
		super();
		LocationId = locationId;
		LocationName = locationName;
		this.deviceId = deviceId;
		this.selectedMapId = selectedMapId;
		this.heightData = heightData;
		this.runState = runState;
	}

}
